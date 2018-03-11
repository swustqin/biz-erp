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
    USER_NOT_FOUND("User not found by requestId!"),
    ACCOUNT_MSG_ERROR_NOTFOUND(" HR系统中无此账号,请联系系统管理员！"),
    ACCOUNT_MSG_ERROR_CHECKFAIL(" 用户名或密码错误！"),
    ACCOUNT_MSG_ERROR_LOCK(" 输入错误次数超限，账号已被锁定！"),
    ACCOUNT_MSG_ERROR_AUTHENTICATION(" 账号权限不够！"),
    ACCOUNT_MSG_SUCCESS_CHECKPASS(" 登陆成功！");


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
