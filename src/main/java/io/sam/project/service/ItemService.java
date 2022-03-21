package io.sam.project.service;

import io.sam.project.models.Item;
import io.sam.project.models.Manufacturer;
import io.sam.project.models.dtos.ItemDTO;
import io.sam.project.repository.ItemRepository;
import io.sam.project.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ManufacturerRepository manufacturerRepository;

    public List<ItemDTO> findAll() {
        return itemRepository.findAll()
                .stream()
                .map(item -> mapToDTO(item, new ItemDTO()))
                .collect(Collectors.toList());
    }

    public ItemDTO get(Long id) {
        return itemRepository.findById(id)
                .map(item -> mapToDTO(item, new ItemDTO()))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    public Long create(ItemDTO itemDTO) {
        final Item item = new Item();
        mapToEntity(itemDTO, item);
        return itemRepository.save(item).getId();
    }

    public void update(Long id, ItemDTO itemDTO) {
        final Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(itemDTO, item);
        itemRepository.save(item);
    }

    public void delete(final Long id) {
        itemRepository.deleteById(id);
    }

    private ItemDTO mapToDTO(Item item, ItemDTO itemDTO) {
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setQuantity(item.getQuantity());
        itemDTO.setManufacturerItems(item.getManufacturerItems() == null ? null : item.getManufacturerItems().getId());
        return itemDTO;
    }

    private Item mapToEntity(ItemDTO itemDTO, Item item) {
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setQuantity(itemDTO.getQuantity());
        if (itemDTO.getManufacturerItems() != null && (item.getManufacturerItems() == null || !item.getManufacturerItems().getId().equals(itemDTO.getManufacturerItems()))) {
            final Manufacturer manufacturerItems = manufacturerRepository.findById(itemDTO.getManufacturerItems())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "manufacturerItems not found"));
            item.setManufacturerItems(manufacturerItems);
        }
        return item;
    }
}
