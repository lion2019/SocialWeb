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

//    public String getCreate_date() {
//        return create_date;
//    }

//    public void setCreate_date(String create_date) {
//        this.create_date = create_date;
//    }

    public Board convert2Entity() {
        Board board = new Board();
        board.setMessage(message);
        board.setNickname(nickname);
        board.setCreate_date(Timestamp.valueOf(LocalDateTime.now()));
        return board;
    }

    public void valid() throws ValidException {

    }

    @Override
    public String toString() {
        return "Board{" +
//                "id=" + id +
                "nickname='" + nickname + '\'' +
                ", message='" + message + '\'' +
//                ", create_date='" + create_date + '\'' +
                '}';
    }
}
