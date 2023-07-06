package com.dmtryii.wms.model;

import com.dmtryii.wms.model.enums.ERole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true, updatable = false)
    private String username;
    @Column(length = 3000)
    private String password;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createData;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "contacts_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Contacts contacts;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Address address;
    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> roles;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @PrePersist
    protected void onCreate() {
        this.createData = LocalDateTime.now();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
