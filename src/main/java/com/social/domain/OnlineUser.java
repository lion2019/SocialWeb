package com.social.domain;

import java.time.LocalDateTime;

public class OnlineUser {
    private String nickname;
    private LocalDateTime loginDateTime;
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoginTimeStr(){
        return loginDateTime.toString();
    }

    @Override
    public String toString() {
        return "OnlineUser{" +
                "nickName='" + nickname + '\'' +
                ", loginDateTime=" + loginDateTime +
                '}';
    }
}
