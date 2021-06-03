package com.social.model.domain;

import com.social.exception.ValidException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 未用到 (保留)
 */
public class FriendRequest {
    private String nickname_to;

    public FriendRequest() {
    }

    public FriendRequest(String nickname_to) {
        this.nickname_to = nickname_to;
    }

    public String getNickname_to() {
        return nickname_to;
    }

    public void setNickname_to(String nickname_to) {
        this.nickname_to = nickname_to;
    }

    /**
     * request bean convert to entity
     * @return
     */
    public Friend convert2Entity() {
        Friend bean = new Friend();
        bean.setNickname_to(nickname_to);
        bean.setCreate_date(Timestamp.valueOf(LocalDateTime.now()));
        return bean;
    }

    public void valid() throws ValidException {

    }

    @Override
    public String toString() {
        return "User [nickname_to=" + nickname_to
                + "]";
    }
}
