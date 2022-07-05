package com.example.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Enum, specifying the roles with included sets of permissions
 */

public enum Role {
    USER(
            Set.of(Permission.USER_READ)
    ),
    MANAGER(
            Set.of(Permission.USER_READ, Permission.USER_WRITE)
    );

    private final Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    /**
     * This method gets the authorities based on permissions of a concrete role
     *
     * @return Set of authorities
     */
    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
