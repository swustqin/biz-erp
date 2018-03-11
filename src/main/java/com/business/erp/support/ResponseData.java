package com.business.erp.support;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Service API provide common request or response structure
 *
 * @author Jaden qin
 */
@Data
public class ResponseData extends AbstractBaseSupport implements Serializable {

    private static final long serialVersionUID = 678673541670991316L;

    /**
     * response business state. 200:success，500:failure
     */
    private Integer code;

    /**
     * response result description
     */
    private String status;

    /**
     * response data
     */
    private Object data;

    /**
     * more information for service description
     */
    private String message;
    private String fromApp;

    private ResponseData() {

    }

    private ResponseData(Object data) {
        this.code = 200;
        this.status = "success";
        this.data = data;
    }

    private ResponseData(Integer code, String status, Object data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    private ResponseData(Integer code, String status, Object data, String message, String fromApp) {
        this.code = code;
        this.status = status;
        this.data = data;
        this.message = message;
        this.fromApp = fromApp;
    }

    /**
     * The full common construction
     *
     * @param code    code
     * @param status  status
     * @param data    data
     * @param message message
     * @param fromApp fromApp
     */
    public static ResponseData build(Integer code, String status, Object data, String message, String fromApp) {
        return new ResponseData(code, status, data, message, fromApp);
    }

    /**
     * The simple common response structure
     *
     * @param code   code
     * @param status status
     * @param data   data
     */
    public static ResponseData build(Integer code, String status, Object data) {
        return new ResponseData(code, status, data);
    }

    /**
     * Default success response result with code 200
     *
     * @param data data
     * @return success response data
     */
    public static ResponseData ok(Object data) {
        return new ResponseData(data);
    }

    /**
     * Default success response result with code 200 and empty data
     *
     * @return success response data
     */
    public static ResponseData ok() {
        return new ResponseData(null);
    }

    /**
     * comparable required
     *
     * @param o object
     * @return compare result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ResponseData that = (ResponseData) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(code, that.code)
                .append(status, that.status)
                .append(data, that.data)
                .append(message, that.message)
                .append(fromApp, that.fromApp)
                .isEquals();
    }

    /**
     * Create hashcode with all the params
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(code)
                .append(status)
                .append(data)
                .append(message)
                .append(fromApp)
                .toHashCode();
    }
}
