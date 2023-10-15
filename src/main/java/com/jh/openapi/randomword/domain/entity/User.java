package com.jh.openapi.randomword.domain.entity;

import com.jh.openapi.randomword.domain.entity.metadata.MetaDataEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "user")
public class User extends MetaDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, updatable = false)
    private Long idx;

    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 50)
    private String id;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "nick_name", unique = true, nullable = false, length = 50)
    private String nickName;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;
}
