package com.ess.essserver.module.invoice;

import com.ess.essserver.app.PaymentStatus;
import com.ess.essserver.module.booking.BookingEntity;
import com.ess.essserver.module.booking.BookingRepository;
import com.ess.essserver.module.event.EventEntity;
import com.ess.essserver.module.event.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceMapper invoiceMapper;
    private final EventRepository eventRepository;
    private final InvoiceRepository invoiceRepository;
    private final BookingRepository bookingRepository;

    public List<InvoiceResponseDTO> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(invoiceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public InvoiceResponseDTO getInvoiceById(Long id) {
        InvoiceEntity invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with ID: " + id));
        return invoiceMapper.toResponseDTO(invoice);
    }

    public InvoiceResponseDTO createInvoice(InvoiceRequestDTO dto) {
        EventEntity event = eventRepository.findById(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("event not found with ID: " + dto.getEventId()));

        List<BookingEntity> bookings = bookingRepository.findAllByIdInAndEventAndPaymentStatus(dto.getBookingIds(), event, PaymentStatus.PENDING);

        if (bookings.isEmpty()) throw new RuntimeException("event not found with ID: " + dto.getEventId());

        var total = bookings.stream().mapToDouble(booking -> booking.getAsset().getPrice()).sum();

        InvoiceEntity invoice = invoiceMapper.toEntity(event, bookings, BigDecimal.valueOf(total), "PAID", LocalDateTime.now());
        invoice = invoiceRepository.save(invoice);

        bookings.forEach(booking -> {
            booking.setPaymentStatus(PaymentStatus.COMPLETED);
            bookingRepository.save(booking);
        });

        return invoiceMapper.toResponseDTO(invoice);
    }

}