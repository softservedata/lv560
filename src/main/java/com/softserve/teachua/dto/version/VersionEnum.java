package com.softserve.teachua.dto.version;

public enum VersionEnum {
    COMMIT_NUMBER("commitNumber"),
    COMMIT_DATE("commitDate"),
    BUILD_DATE("buildDate");

    private String fieldName;

    private VersionEnum(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String toString() {
        return "VersionEnum{" +
                "fieldName='" + fieldName + '\'' +
                '}';
    }
}
