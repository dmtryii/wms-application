package com.dmtryii.wms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contacts_id")
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true)
    private String phone;
    private String firstName;
    private String lastName;
    @Column(columnDefinition = "text")
    private String bio;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "social_networks",
            joinColumns = @JoinColumn(name = "contacts_id"))
    private List<String> socialNetworks;

    public Contacts(String email) {
        this.email = email;
    }
}
