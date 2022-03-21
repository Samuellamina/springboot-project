package io.sam.project.service;

import io.sam.project.models.Manufacturer;
import io.sam.project.models.dtos.ManufacturerDTO;
import io.sam.project.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public List<ManufacturerDTO> findAll() {
        return manufacturerRepository.findAll()
                .stream()
                .map(manufacturer -> mapToDTO(manufacturer, new ManufacturerDTO()))
                .collect(Collectors.toList());
    }

    public ManufacturerDTO get(Long id) {
        return manufacturerRepository.findById(id)
                .map(manufacturer -> mapToDTO(manufacturer, new ManufacturerDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(ManufacturerDTO manufacturerDTO) {
        final Manufacturer manufacturer = new Manufacturer();
        mapToEntity(manufacturerDTO, manufacturer);
        return manufacturerRepository.save(manufacturer).getId();
    }

    public void update(Long id, ManufacturerDTO manufacturerDTO) {
        final Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(manufacturerDTO, manufacturer);
        manufacturerRepository.save(manufacturer);
    }

    public void delete(final Long id) {
        manufacturerRepository.deleteById(id);
    }

    private ManufacturerDTO mapToDTO(Manufacturer manufacturer,
                                     ManufacturerDTO manufacturerDTO) {
        manufacturerDTO.setId(manufacturer.getId());
        manufacturerDTO.setName(manufacturer.getName());
        return manufacturerDTO;
    }

    private Manufacturer mapToEntity(ManufacturerDTO manufacturerDTO,
                                     Manufacturer manufacturer) {
        manufacturer.setName(manufacturerDTO.getName());
        return manufacturer;
    }


}
