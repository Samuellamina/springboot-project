package io.sam.project.models;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table( name = "roles")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Role {
    @Id
    @GeneratedValue( strategy = IDENTITY)
    private Long id;

    @Enumerated(STRING)
    @Column( length = 20)
    private ERole name;
}
