package io.sam.project.models.dtos;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ManufacturerDTO {

    private Long id;

    @Size(max = 255)
    private String name;

}
