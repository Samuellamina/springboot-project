package io.sam.project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Manufacturer {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    private Long id;

    private String name;

    @OneToMany(mappedBy = "manufacturerItems")
    private Set<Item> manufacturerItemsItems;
}
