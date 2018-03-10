package com.business.erp.model.po.system;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * The system management role structure
 *
 * @author jadenQin
 */
@Entity
@DynamicUpdate
@Data
public class SysRole implements Serializable {

    @Id
    private String id;
    private String roleName;
    private String remark;
    private String entryId;
    /**
     * Comments:
     * 0: normal    1: error
     */
    private Integer roleStatus;
    /**
     * Comments:
     * 0: default role  1:  others
     */
    private Integer defaultRole;
    private Date entryDatetime;
    private Date updateDatetime;
}
