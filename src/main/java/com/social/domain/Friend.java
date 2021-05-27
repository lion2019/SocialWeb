package com.social.domain;

import java.sql.Timestamp;

/**
 * 好友 entity
 */
public class Friend {
    /** 發起者 */
    private String nickname_from;
    /** 好友 */
    private String nickname_to;
    /** 創連日期 */
    private Timestamp create_date;

    public Friend() {
    }

    public Friend(String nickname_from, String nickname_to, Timestamp create_date) {
        this.nickname_from = nickname_from;
        this.nickname_to = nickname_to;
        this.create_date = create_date;
    }

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

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public String getCreateDateStr(){
        return create_date.toString();
    }

    @Override
    public String toString() {
        return "User [nickname_from=" + nickname_from
                + ", nickname_to=" + nickname_to
                + ", create_date=" + create_date + "]";
    }
}
