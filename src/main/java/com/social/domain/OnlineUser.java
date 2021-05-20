package com.social.domain;

import java.time.LocalDateTime;

public class OnlineUser {
    private String nickname;
    private LocalDateTime loginDateTime;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getLoginDateTime() {
        return loginDateTime;
    }

    public void setLoginDateTime(LocalDateTime loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

    @Override
    public String toString() {
        return "OnlineUser{" +
                "nickName='" + nickname + '\'' +
                ", loginDateTime=" + loginDateTime +
                '}';
    }
}
