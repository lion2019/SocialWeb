package com.social.domain;

import java.sql.Timestamp;

/**
 * 留言板
 */
public class Board {
    @Id
    private Integer id;
    private String nickname;
    private String message;
    private Timestamp create_date;
    private Integer room_number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public Integer getRoom_number() {
        return room_number;
    }

    public void setRoom_number(Integer room_number) {
        this.room_number = room_number;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", message='" + message + '\'' +
                ", create_date=" + create_date +
                ", room_number=" + room_number +
                '}';
    }
}
