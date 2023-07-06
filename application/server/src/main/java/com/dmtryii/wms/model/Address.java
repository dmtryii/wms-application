package com.dmtryii.wms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;
    @Column(name = "current_name")
    private String currentName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private City city;
}
