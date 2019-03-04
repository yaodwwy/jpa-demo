package jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yaodw
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    /**
     * 实体简名称
     */
    @JsonIgnore
    String entityName = this.getClass().getSimpleName();
    /**
     * 创建人
     */
    @CreatedBy
    String createdBy;
    /**
     * 修改人
     */
    @LastModifiedBy
    String modifiedBy;
    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date dateCreated = new Date();
    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date dateModified = new Date();
}
