-- auto-generated definition
create table english_word
(
    vocabulary   varchar(100)                                         not null comment '단어'
        primary key,
    meaning      varchar(100)                                         not null comment '뜻',
    level        enum ('ELEMENT', 'MIDDLE', 'HIGH') default 'ELEMENT' not null comment '난이도',
    reg_datetime datetime                                             null comment '등록일',
    reg_user_idx int unsigned                       default '1'       not null comment '등록자 idx',
    mod_datetime datetime                                             null comment '수정일자',
    mod_user_idx int unsigned                                         null comment '수정자 idx'
);

-- auto-generated definition
create table user
(
    idx          int unsigned auto_increment comment '식별번호'
        primary key,
    id           varchar(50)                        not null comment '아이디',
    password     varchar(50)                        not null comment '비밀번호',
    nick_name    varchar(50)                        not null comment '닉네임',
    email        varchar(100)                       not null comment '이메일',
    reg_datetime datetime default CURRENT_TIMESTAMP not null comment '등록일시',
    reg_user_idx int unsigned                       not null comment '등록자 idx',
    mod_datetime datetime                           null comment '수정일시',
    mod_user_idx int unsigned                       null comment '수정자 idx',
    constraint user_uk_email
        unique (email),
    constraint user_uk_id
        unique (id),
    constraint user_uk_nick_name
        unique (nick_name)
)
comment '회원 테이블';

