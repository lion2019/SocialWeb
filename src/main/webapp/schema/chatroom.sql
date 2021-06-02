create table user
(
    name varchar(20),
    password varchar(20),
    email varchar(40),
    gender char(1),
    nickname varchar(20),
    constraint USER_PK
        primary key (email)
);
comment on table user is '會員';
comment on column user.name is '姓名';
comment on column user.password is '密碼';
comment on column user.email is 'email';
comment on column user.gender is '性別';
comment on column user.nickname is '暱名';
create unique index USER_NICKNAME_UINDEX
	on USER (nickname);

alter table USER
    add nickname varchar(20);
create unique index USER_NICKNAME_UINDEX
	on USER (nickname);

-- auto-generated definition

create table BOARD
(
    ID          INT auto_increment,
    NICKNAME    VARCHAR(20),
    MESSAGE     VARCHAR(100),
    ROOM_NUMBER     int(5),
    CREATE_DATE TIMESTAMP,
    constraint BOARD_PK
        primary key (ID),
    constraint BOARD_USER_NICKNAME_FK
        foreign key (NICKNAME) references USER (NICKNAME)
);
comment on table BOARD is '留言板';
comment on column BOARD.ID is '留言ID';
comment on column BOARD.NICKNAME is '暱名';
comment on column BOARD.MESSAGE is '訊息';
comment on column BOARD.ROOM_NUMBER is '聊天室房號';
comment on column BOARD.CREATE_DATE is '訊息建立時間';
create unique index BOARD_ROOM_NUMBER_UINDEX
	on BOARD (ROOM_NUMBER);

create table FRIEND
(
    NICKNAME_FROM VARCHAR(20) not null,
    NICKNAME_TO   VARCHAR(20) not null,
    CREATE_DATE   TIMESTAMP default CURRENT_TIMESTAMP,
    constraint FRIEND_PK
        primary key (NICKNAME_FROM, NICKNAME_TO),
    constraint FRIEND_USER_NICKNAME_FK
        foreign key (NICKNAME_FROM) references USER (NICKNAME),
    constraint FRIEND_USER_NICKNAME_FK_2
        foreign key (NICKNAME_TO) references USER (NICKNAME)
);
comment on table FRIEND is '好友';
comment on column FRIEND.NICKNAME_FROM is '發起者';
comment on column FRIEND.CREATE_DATE is '建立時間';
