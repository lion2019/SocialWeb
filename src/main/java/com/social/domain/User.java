package com.social.domain;

/**
 * 會員<br/>
 * 若屬性名稱和資料表欄位名稱不同反射機制出錯
 */
public class User {

    /** 姓名 */
    private String name;
    /** 密碼 */
    private String password;
    private String email;
    /**
     * 性別
     * 注意：基本型別 無法帶入 null 和 資料表結構不符, 盡量別用基本型別
     */
    private Character gender;
    /** 暱名 */
    private String nickname;

    public User() {

    }

    public User(String name, String password, String email,Character gender,String nickname) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", password=" + password + ", email=" + email
                + ", gender=" + gender + ", nickname=" + nickname + "]";
    }
}
