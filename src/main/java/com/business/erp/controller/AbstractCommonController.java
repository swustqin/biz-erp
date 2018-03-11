package com.business.erp.controller;

import com.business.erp.RuntimeEnums;

/**
 * @author jadenQin
 */
public abstract class AbstractCommonController {
    public final String SUCCESS = RuntimeEnums.SUCCESS.key();
    public final Integer SUCCESS_CODE = RuntimeEnums.SUCCESS.intValue();
    public final String FAIL = RuntimeEnums.FAIL.key();
    public final Integer FAIL_CODE = RuntimeEnums.FAIL.intValue();
    public final String ACCOUNT_MSG_ERROR_CHECKFAIL = RuntimeEnums.ACCOUNT_MSG_ERROR_CHECKFAIL.value();
    public final String ACCOUNT_MSG_ERROR_NOTFOUND = RuntimeEnums.ACCOUNT_MSG_ERROR_NOTFOUND.value();
    public final String ACCOUNT_MSG_ERROR_LOCK = RuntimeEnums.ACCOUNT_MSG_ERROR_LOCK.value();
    public final String ACCOUNT_MSG_ERROR_AUTHENTICATION = RuntimeEnums.ACCOUNT_MSG_ERROR_AUTHENTICATION.value();
    public final String ACCOUNT_MSG_SUCCESS_CHECKPASS = RuntimeEnums.ACCOUNT_MSG_SUCCESS_CHECKPASS.value();
}
