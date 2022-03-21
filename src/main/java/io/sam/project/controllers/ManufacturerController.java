package io.sam.project.controllers;

import io.sam.project.models.dtos.ManufacturerDTO;
import io.sam.project.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/manufacturers")
@RequiredArgsConstructor
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<ManufacturerDTO>> getAllManufacturers() {
        return ResponseEntity.ok(manufacturerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerDTO> getManufacturer(@PathVariable Long id) {
        return ResponseEntity.ok(manufacturerService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createManufacturer(
            @RequestBody @Valid ManufacturerDTO manufacturerDTO) {
        return new ResponseEntity<>(manufacturerService.create(manufacturerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateManufacturer(@PathVariable Long id,
                                                   @RequestBody @Valid ManufacturerDTO manufacturerDTO) {
        manufacturerService.update(id, manufacturerDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable Long id) {
        manufacturerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
