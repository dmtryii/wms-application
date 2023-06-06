package com.dmtryii.wms.model;

import com.dmtryii.wms.model.enums.ERole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    @Column(unique = true , nullable = false)
    @Enumerated(EnumType.STRING)
    private ERole name;
    @Column(columnDefinition = "text")
    private String description;

    public Role(Long id, ERole name) {
        this.id = id;
        this.name = name;
    }
}
