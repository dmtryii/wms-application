package com.dmtryii.wms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class City {
    @Id
    @GeneratedValue(generator = "prod-generator")
    @GenericGenerator(name = "prod-generator",
            parameters = @Parameter(name = "prefix", value = "city"),
            strategy = "com.dmtryii.wms.model.generator.IdGenerator"
    )
    @Column(name = "city_id")
    private String id;
    @Column(nullable = false)
    private String name;
    private String country;
    private Double longitude;
    private Double latitude;
}
