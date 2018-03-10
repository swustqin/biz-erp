package com.business.erp;

/**
 * @author jadenQin
 */
public enum RuntimeEnums {

    SUCCESS(200), FAIL(500), WARN(201), PAGE(0), SIZE(1), CURRENT_VERSION("v1"),
    LOG_SPECIAL_MARK("* LOG *\t"),
    FAILED_TO_CONNECT_DB("Failed to connect to database. Please try again later"),
    VERSION_IS_REQUIRED("Version number is required!"),
    VERSION_CHECK_FAIL("The input version is not supported"),
    SERVER_RUNTIME_EXCEPTION("Server runtime exception, Please try again later"),
    RECORD_NOT_FOUND("No Record is Found"),
    USER_NOT_FOUND("User not found by requestId!");

    private String runtimePropertiesValue;
    private Integer runtimePropertiesIntValue;

    RuntimeEnums() {
    }

    RuntimeEnums(String runtimePropertiesValue) {
        this.runtimePropertiesValue = runtimePropertiesValue;
    }

    RuntimeEnums(Integer runtimePropertiesIntValue) {
        this.runtimePropertiesIntValue = runtimePropertiesIntValue;
    }

    public String key() {
        return this.name();
    }

    public String value() {
        return this.getRuntimePropertiesValue();
    }

    public Integer intValue() {
        return this.getRuntimePropertiesIntValue();
    }

    private String getRuntimePropertiesValue() {
        return runtimePropertiesValue;
    }

    private Integer getRuntimePropertiesIntValue() {
        return runtimePropertiesIntValue;
    }
}
