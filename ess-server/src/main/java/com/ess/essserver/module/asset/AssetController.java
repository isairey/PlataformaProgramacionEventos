package com.ess.essserver.module.asset;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Asset Management", description = "CRUD APIs for managing assets")
@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @Operation(summary = "Get all assets")
    @GetMapping
    public ResponseEntity<List<AssetResponseDTO>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @Operation(summary = "Get asset by ID")
    @GetMapping("/{id}")
    public ResponseEntity<AssetResponseDTO> getAssetById(@PathVariable Long id) {
        return ResponseEntity.ok(assetService.getAssetById(id));
    }

    @Operation(summary = "Create a new asset")
    @PostMapping
    public ResponseEntity<AssetResponseDTO> createAsset(
            @Valid @RequestBody AssetRequestDTO dto) {
        return ResponseEntity.ok(assetService.createAsset(dto));
    }

    @Operation(summary = "Update an existing asset")
    @PutMapping("/{id}")
    public ResponseEntity<AssetResponseDTO> updateAsset(
            @PathVariable Long id,
            @Valid @RequestBody AssetRequestDTO dto) {
        return ResponseEntity.ok(assetService.updateAsset(id, dto));
    }

    @Operation(summary = "Delete an asset by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }
}
