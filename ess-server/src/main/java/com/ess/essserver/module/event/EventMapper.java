package com.ess.essserver.module.event;

import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventResponseDTO toEventResponseDTO(EventEntity event) {
        return EventResponseDTO.builder()
                .id(event.getId())
                .name(event.getName())
                .location(event.getLocation())
                .status(event.getStatus())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .build();
    }
}
