package com.ess.essserver.module.booking;

import com.ess.essserver.module.asset.AssetEntity;
import com.ess.essserver.module.asset.AssetMapper;
import com.ess.essserver.module.event.EventEntity;
import com.ess.essserver.module.event.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    private final AssetMapper assetMapper;
    private final EventMapper eventMapper;

    public BookingEntity toEntity(BookingRequestDTO dto, EventEntity event, AssetEntity asset) {
        return BookingEntity.builder()
                .event(event)
                .asset(asset)
                .startTime(dto.getStartDate())
                .endTime(dto.getEndDate())
                .build();
    }

    public BookingResponseDTO toResponseDTO(BookingEntity entity) {
        return BookingResponseDTO.builder()
                .id(entity.getId())
                .asset(assetMapper.toResponseDTO(entity.getAsset()))
                .event(eventMapper.toEventResponseDTO(entity.getEvent()))
                .startDate(entity.getStartTime())
                .startDate(entity.getStartTime())
                .endDate(entity.getEndTime())
                .paymentStatus(entity.getPaymentStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}