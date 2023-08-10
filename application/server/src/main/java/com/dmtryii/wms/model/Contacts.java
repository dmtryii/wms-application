package com.dmtryii.wms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @Email
    @Column(unique = true)
    @NotEmpty(message = "username should not be empty")
    private String email;

    @Column(unique = true)
    private String phone;

    @Size(min = 2, max = 30, message = "firstName should be between 2 and 30 characters")
    private String firstName;

    @Size(min = 2, max = 30, message = "firstName should be between 2 and 30 characters")
    private String lastName;

    @Column(columnDefinition = "text")
    private String bio;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "social_networks",
            joinColumns = @JoinColumn(name = "contacts_id"))
    private List<String> socialNetworks;

    @JsonBackReference
    @OneToOne(mappedBy = "contacts")
    private User user;

    public Contacts(String email) {
        this.email = email;
    }
}
