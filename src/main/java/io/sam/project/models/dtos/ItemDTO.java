package io.sam.project.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
    private Long id;

    private String name;

    private String description;

    private int quantity;
}
