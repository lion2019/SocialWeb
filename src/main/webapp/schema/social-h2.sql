create table user
(
    name varchar(20),
    password varchar(20),
    email varchar(40),
    gender char(1),
    nick_name varchar(20),
    constraint USER_PK
        primary key (email)
);
comment on table user is '會員';
comment on column user.name is '姓名';
comment on column user.password is '密碼';
comment on column user.email is 'email';
comment on column user.gender is '性別';
comment on column user.nick_name is '暱名';
create unique index USER_NICK_NAME_UINDEX
	on USER (nick_name);

alter table USER
    add nick_name varchar(20);
comment on column USER.nick_name is '暱名';
create unique index USER_NICK_NAME_UINDEX
	on USER (nick_name);