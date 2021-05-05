package org.example.model;

public enum Permission {
    MANAGER_PERMISSION("all_permissions"),
    USER_PERMISSION("one_permission");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
