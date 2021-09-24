package com.codingseahorse.tastylab.model.member;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.codingseahorse.tastylab.model.member.MemberPermission.*;

@RequiredArgsConstructor
public enum MembershipRole {
    TASTER(Sets.newHashSet(TASTER_READ_AND_WRITE)),
    BLOGGER(Sets.newHashSet(BLOGGER_READ_AND_WRITE)),
    ADMIN(Sets.newHashSet(ADMIN_READ_AND_WRITE));

    @Getter
    private final Set<MemberPermission> permissions;

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
