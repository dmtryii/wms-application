package com.dmtryii.wms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Address address;
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Location> location;
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Stock> stock;
}
