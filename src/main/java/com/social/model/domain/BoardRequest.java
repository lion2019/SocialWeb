package com.social.model.domain;

import com.social.exception.ValidException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 留言板 前端傳入的值放入此物件
 */
public class BoardRequest {
    private String nickname;
    private String message;
    private String room_number;

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

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    /**
     * request bean convert to entity
     * @return
     */
    public Board convert2Entity() {
        Board bean = new Board();
        bean.setMessage(message);
        bean.setNickname(nickname);
        bean.setRoom_number(room_number==null?null:Integer.parseInt(room_number));
        bean.setCreate_date(Timestamp.valueOf(LocalDateTime.now()));
        return bean;
    }

    public void valid() throws ValidException {

    }

    @Override
    public String toString() {
        return "BoardRequest{" +
                "nickname='" + nickname + '\'' +
                ", message='" + message + '\'' +
                ", room_number=" + room_number +
                '}';
    }
}
