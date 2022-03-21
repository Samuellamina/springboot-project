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
            name = "m_sequence",
            sequenceName = "m_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "m_sequence"
    )
    private Long id;

    private String name;

    @OneToMany(mappedBy = "manufacturerItems")
    private Set<Item> manufacturerItemsItems;
}
