package com.dmtryii.wms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_numbers")
    private String streetNumber;

    @Column(columnDefinition = "text")
    private String details;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private City city;

    @JsonBackReference
    @OneToOne(mappedBy = "address")
    private User user;

    @JsonBackReference
    @OneToOne(mappedBy = "address")
    private Company company;
}
