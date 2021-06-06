package org.example.utils;

import java.util.Set;
import java.util.stream.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    MANAGER(Stream.of(Permission.MANAGER_PERMISSION, Permission.USER_PERMISSION).collect(Collectors.toSet())),
    USER(Stream.of(Permission.USER_PERMISSION).collect(Collectors.toSet()));
    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
    }
}
