package com.jh.openapi.randomword.domain.entity.metadata;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class MetaDataEntity {
    @Column(name = "reg_datetime", nullable = false, updatable = false)
    @CreatedDate
    protected LocalDateTime regDatetime;

    @Column(name = "reg_user_idx", nullable = false, updatable = false)
    @CreatedBy
    protected Long regUserIdx;

    @Column(name = "mod_datetime")
    @LastModifiedDate
    protected LocalDateTime modDatetime;

    @Column(name = "mod_user_idx")
    @LastModifiedBy
    protected Long modUserIdx;

    public Long getLastUpdateUserIdx() {
        if (this.modUserIdx != null && this.modUserIdx > 0L) {
            return this.modUserIdx;
        }

        return this.regUserIdx;
    }

    public LocalDateTime getLastUpdateDatetime() {
        if (this.modDatetime != null) {
            return this.modDatetime;
        }

        return this.regDatetime;
    }
}
