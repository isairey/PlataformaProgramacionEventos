package com.ess.essserver.module.booking;

import com.ess.essserver.app.PaymentStatus;
import com.ess.essserver.module.asset.AssetEntity;
import com.ess.essserver.module.asset.AssetRepository;
import com.ess.essserver.module.event.EventEntity;
import com.ess.essserver.module.event.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final AssetRepository assetRepository;
    private final BookingMapper bookingMapper;

    public List<BookingResponseDTO> getAllBookings(Long eventId, PaymentStatus paymentStatus) {

        var event = eventRepository.findById(eventId).get();

        return bookingRepository.findAllByEvent(event).stream()
                .map(bookingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public BookingResponseDTO getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
    }

    public BookingResponseDTO createBooking(BookingRequestDTO dto) {

        validateBookingDate(dto.getStartDate(), dto.getEndDate());

        EventEntity event = eventRepository.findById(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("event not found with ID: " + dto.getEventId()));

        AssetEntity asset = assetRepository.findById(dto.getAssetId())
                .orElseThrow(() -> new RuntimeException("Asset not found with ID: " + dto.getAssetId()));

        validateBookingAsset(asset, dto.getStartDate(), dto.getEndDate());

        BookingEntity booking = bookingMapper.toEntity(dto, event, asset);
        booking = bookingRepository.save(booking);

        return bookingMapper.toResponseDTO(booking);
    }

    public BookingResponseDTO updateBooking(Long id, BookingRequestDTO dto) {

        validateBookingDate(dto.getStartDate(), dto.getEndDate());

        BookingEntity booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));

        EventEntity event = eventRepository.findById(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("event not found with ID: " + dto.getEventId()));

        AssetEntity asset = assetRepository.findById(dto.getAssetId())
                .orElseThrow(() -> new RuntimeException("Asset not found with ID: " + dto.getAssetId()));

        booking.setEvent(event);
        booking.setAsset(asset);
        booking.setStartTime(dto.getStartDate());
        booking.setEndTime(dto.getEndDate());

        validateBookingAsset(booking.getId(), asset, dto.getStartDate(), dto.getEndDate());

        booking = bookingRepository.save(booking);

        return bookingMapper.toResponseDTO(booking);
    }

    public void deleteBooking(Long id) {
        BookingEntity booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
        bookingRepository.delete(booking);
    }

    private void validateBookingAsset(AssetEntity asset, LocalDate startDate, LocalDate endDate) {
        List<BookingEntity> bookingEntities = bookingRepository.findAllByAssetAndStartTimeGreaterThanEqualAndEndTimeLessThanEqual(asset, startDate, endDate);

        if (!bookingEntities.isEmpty()) throw new RuntimeException("Asset already booked for the given time");

    }

    private void validateBookingAsset(Long id, AssetEntity asset, LocalDate startDate, LocalDate endDate) {
        List<BookingEntity> bookingEntities = bookingRepository.findAllByIdNotAndAssetAndStartTimeGreaterThanEqualAndEndTimeLessThanEqual(id, asset, startDate, endDate);

        if (!bookingEntities.isEmpty()) throw new RuntimeException("Asset already booked for the given time");

    }

    private void validateBookingDate(LocalDate startDate, LocalDate endDate) {
        // Get the current local time
        LocalDate currentDate = LocalDate.now();

        // Check if the booking start date is after the current date
        if (startDate.isBefore(currentDate)) {
            throw new RuntimeException("Booking date must be the current date or a future date");
        }

        // Check if the booking start date is after the booking end date
        if (startDate.isAfter(endDate)) {
            throw new RuntimeException("Booking start date cant be after end date");
        }

    }
}