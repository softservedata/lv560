package com.example.util;

/**
 * The enum, used for getting permissions
 */
public enum Permission {

    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
