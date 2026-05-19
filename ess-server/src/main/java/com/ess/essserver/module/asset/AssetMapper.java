package com.ess.essserver.module.asset;

import org.springframework.stereotype.Component;

@Component
public class AssetMapper {

    public AssetEntity toEntity(AssetRequestDTO dto) {
        return AssetEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .condition(dto.getCondition())
                .quantity(dto.getQuantity())
                .status(dto.getStatus())
                .build();
    }

    public AssetResponseDTO toResponseDTO(AssetEntity entity) {
        return AssetResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .category(entity.getCategory())
                .condition(entity.getCondition())
                .quantity(entity.getQuantity())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}