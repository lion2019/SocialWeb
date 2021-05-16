package com.social.domain;

import com.social.exception.ValidException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 留言板
 */
public class BoardRequest {
    //    private Integer id;
    private String nickname;
    private String message;
    private String room_number;

//    private String create_date;

//    public Integer getId() {
//        return id;
//    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

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
//    public String getCreate_date() {
//        return create_date;
//    }

//    public void setCreate_date(String create_date) {
//        this.create_date = create_date;
//    }

    /**
     * request bean convert to entity
     * @return
     */
    public Board convert2Entity() {
        Board board = new Board();
        board.setMessage(message);
        board.setNickname(nickname);
        board.setRoom_number(room_number==null?null:Integer.parseInt(room_number));
        board.setCreate_date(Timestamp.valueOf(LocalDateTime.now()));
        return board;
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
