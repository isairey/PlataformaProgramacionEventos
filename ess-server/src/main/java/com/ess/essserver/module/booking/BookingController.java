package com.ess.essserver.module.booking;

import com.ess.essserver.app.PaymentStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Booking Management", description = "CRUD APIs for managing bookings")
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    private final Logger logger = LogManager.getLogger(BookingController.class.getName());

    @Operation(summary = "Get all bookings")
    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings(
            @RequestParam(required = false) Long eventId,
            @RequestParam(required = false) PaymentStatus paymentStatus
    ) {
        return ResponseEntity.ok(bookingService.getAllBookings(eventId, paymentStatus));
    }

    @Operation(summary = "Get booking by ID")
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @Operation(summary = "Create a new booking")
    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingRequestDTO dto) {
        logger.info("{}, {}", dto.getStartDate(), dto.getEndDate());
        return ResponseEntity.ok(bookingService.createBooking(dto));
    }

    @Operation(summary = "Update an existing booking")
    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> updateBooking(
            @PathVariable Long id,
            @Valid @RequestBody BookingRequestDTO dto) {
        return ResponseEntity.ok(bookingService.updateBooking(id, dto));
    }

    @Operation(summary = "Delete a booking by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}