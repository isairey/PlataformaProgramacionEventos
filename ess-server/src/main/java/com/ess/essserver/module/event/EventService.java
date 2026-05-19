package com.ess.essserver.module.event;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public List<EventResponseDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(eventMapper::toEventResponseDTO)
                .collect(Collectors.toList());
    }

    public EventResponseDTO getEventById(Long id) {
        return eventRepository.findById(id)
                .map(eventMapper::toEventResponseDTO)
                .orElseThrow(() -> new RuntimeException("event not found with ID: " + id));
    }

    public EventResponseDTO createEvent(EventRequestDTO eventRequestDTO) {
        EventEntity event = EventEntity.builder()
                .name(eventRequestDTO.getName())
                .location(eventRequestDTO.getLocation())
                .status(eventRequestDTO.getStatus())
                .startDate(eventRequestDTO.getStartDate())
                .endDate(eventRequestDTO.getEndDate())
                .build();

        event = eventRepository.save(event);
        return eventMapper.toEventResponseDTO(event);
    }

    public EventResponseDTO updateEvent(Long id, EventRequestDTO eventRequestDTO) {
        EventEntity event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("event not found with ID: " + id));

        event.setName(eventRequestDTO.getName());
        event.setLocation(eventRequestDTO.getLocation());
        event.setStatus(eventRequestDTO.getStatus());
        event.setStartDate(eventRequestDTO.getStartDate());
        event.setEndDate(eventRequestDTO.getEndDate());
        event.setUpdatedAt(LocalDateTime.now());

        event = eventRepository.save(event);
        return eventMapper.toEventResponseDTO(event);
    }

    public void deleteEvent(Long id) {
        EventEntity event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("event not found with ID: " + id));

        eventRepository.delete(event);
    }

}