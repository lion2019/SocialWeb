package com.social.model.domain;

import java.time.LocalDateTime;

/**
 * 儲存線上使用者資訊
 */
public class OnlineUser {
    /** 暱名 */
    private String nickname;
    /** 登入時間 */
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
