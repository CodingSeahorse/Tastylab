package com.codingseahorse.tastylab.model.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(
        name = "member_card",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "member_card_id",
                        columnNames = "member_card_id")
        })
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class MemberCard implements UserDetails {
    @Id
    @GeneratedValue(
            strategy = AUTO)
    @Column(
            name = "member_card_id",
            nullable = false,
            updatable = false)
    private Long memberCardId;

    @NonNull
    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

    @NonNull
    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT")
    private String username;

    @NonNull
    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT")
    private String password;

    @Column(
            name = "membership",
            nullable = false,
            columnDefinition = "TEXT")
    private MembershipRole membershipRole;

    // ===== Variables for OVERRIDES =====
    // <editor-fold defaultstate="collapsed" desc="Variables for OVERRIDES">
    @NonNull
    @ElementCollection(
            targetClass = GrantedAuthority.class,
            fetch = EAGER)
    @CollectionTable(
            name = "membercard_grantedAuthorities"
    )
    @Column(
            name = "grantedAuthorities"
    )
    private Set<? extends GrantedAuthority> grantedAuthorities;

    @NonNull
    @Column(
            name = "is_account_non_expired")
    private Boolean isAccountNonExpired;

    @NonNull
    @Column(
            name = "is_account_non_locked")
    private Boolean isAccountNonLocked;

    @NonNull
    @Column(
            name = "is_credentials_non_expired")
    private Boolean isCredentialsNonExpired;

    @NonNull
    @Column(
            name = "is_enabled")
    private Boolean isEnabled;
    //</editor-fold>
    // ===== OVERRIDES =====
    // <editor-fold defaultstate="collapsed" desc="OVERRIDES">
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
    //</editor-fold>
}