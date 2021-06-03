package com.social.model.domain;

import com.social.annotation.AutoIncrement;

import java.sql.Timestamp;

/**
 * 留言板 entity
 */
public class Board {
    /** 流水號 */
    @AutoIncrement
    private Integer id;
    /** 暱名 */
    private String nickname;
    /** 訊息 */
    private String message;
    /** 創建日期 */
    private Timestamp create_date;
    /** 房號 */
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

    public String getCreateDateStr(){
        return create_date.toString();
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
