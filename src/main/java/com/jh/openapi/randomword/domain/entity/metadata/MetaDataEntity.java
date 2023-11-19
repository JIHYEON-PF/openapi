package com.jh.openapi.randomword.domain.entity.metadata;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
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

    @Column(name = "reg_user_nickname", nullable = false, updatable = false)
    @CreatedBy
    protected String regUserNickname;

    @Column(name = "mod_datetime")
    @LastModifiedDate
    protected LocalDateTime modDatetime;

    @Column(name = "mod_user_nickname")
    @LastModifiedBy
    protected String modUserNickname;

    public String getLastUpdateUserNickname() {
        if (StringUtils.isNotBlank(this.modUserNickname)) {
            return this.modUserNickname;
        }

        return this.regUserNickname;
    }

    public LocalDateTime getLastUpdateDatetime() {
        if (this.modDatetime != null) {
            return this.modDatetime;
        }

        return this.regDatetime;
    }
}
