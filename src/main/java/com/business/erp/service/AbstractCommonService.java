package com.business.erp.service;

import com.business.erp.RuntimeEnums;

/**
 * Default interface for reserve common service
 */
public interface AbstractCommonService {
    String SUCCESS = RuntimeEnums.SUCCESS.key();
    Integer SUCCESS_CODE = RuntimeEnums.SUCCESS.intValue();
    String FAIL = RuntimeEnums.SUCCESS.key();
    Integer FAIL_CODE = RuntimeEnums.SUCCESS.intValue();
}
