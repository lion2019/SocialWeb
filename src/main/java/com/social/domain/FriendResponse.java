package com.social.domain;

import java.sql.Timestamp;

public class FriendResponse {
    private String nickname_from;
    private String nickname_to;
    private String create_date;
    private String status;

    public String getNickname_from() {
        return nickname_from;
    }

    public void setNickname_from(String nickname_from) {
        this.nickname_from = nickname_from;
    }

    public String getNickname_to() {
        return nickname_to;
    }

    public void setNickname_to(String nickname_to) {
        this.nickname_to = nickname_to;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FriendResponse convert(Friend friend){
        FriendResponse bean = new FriendResponse();

        bean.setNickname_to(friend.getNickname_to());
        bean.setNickname_from(friend.getNickname_from());
        bean.setCreate_date(friend.getCreateDateStr());

        return bean;
    }

    @Override
    public String toString() {
        return "FriendResponse{" +
                "nickname_from='" + nickname_from + '\'' +
                ", nickname_to='" + nickname_to + '\'' +
                ", create_date='" + create_date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
