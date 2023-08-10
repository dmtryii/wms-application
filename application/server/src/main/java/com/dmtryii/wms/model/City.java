package com.dmtryii.wms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    @NotEmpty(message = "city name should not be empty")
    private String name;

    @Column(name = "country_name")
    @NotEmpty(message = "country name should not be empty")
    private String countryName;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Address> addresses;
}
