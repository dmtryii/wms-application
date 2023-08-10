package com.dmtryii.wms.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Company {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 30, message = "name must contain more than 2 char and less than 30")
    @NotEmpty(message = "name should not be empty")
    private String name;

    @Range(min = -10, max = 10, message = "rating must be greater than -10 and less than 10")
    private double rating;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @OneToMany(mappedBy = "company")
    private List<Supplier> suppliers;

    @OneToMany(mappedBy = "company")
    private Set<SupplyOrder> supplyOrders;
}
