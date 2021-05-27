package com.social.domain;

import com.social.exception.ResponseEnum;
import com.social.exception.UserException;
import com.social.exception.ValidException;

/**
 * 註冊 前端傳入的值放入此物件
 */
public class RegisterRequest {

    /** 姓名 */
    private String name;
    /** 暱名 */
    private String nickname;
    /** 密碼 */
    private String password1;
    /** 再次確認的密碼 */
    private String password2;
    private String email;
    /**
     * 性別<br/>
     * 注意：基本型別 無法帶入 null 和 資料表結構不符, 盡量別用基本型別 */
    private Character gender;

    public String getName() {
        return name;
    }

    public void setName(String name) throws UserException {
        if(name != null && (name=name.trim()).length() > 0){
            this.name = name;
        }else{
            throw new UserException("姓名欄位必須輸入");
        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) throws UserException {
        if(nickname != null && (nickname=nickname.trim()).length() > 0){
            this.nickname = nickname;
        }else{
            throw new UserException("暱名欄位必須輸入");
        }
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) throws UserException {
        if(password1 != null && (password1 = password1.trim()).length() > 0){
            this.password1 = password1;
        }else{
            throw new UserException("密碼欄位必須輸入");
        }
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) throws UserException {
        if(password2 != null && (password2 = password2.trim()).length() > 0){
            this.password2 = password2;
        }else{
            throw new UserException("密碼欄位必須輸入");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws UserException {
        if(email != null && (email = email.trim()).length() > 0){
            this.email = email;
        }else{
            throw new UserException("email欄位必須輸入且格式需符合email格式");
        }
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    /**
     * request bean convert to entity
     * @return
     */
    public User convert2Entity(){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password1);
        user.setName(name);
        user.setGender(gender);
        user.setNickname(nickname);
        return user;
    }

    /**
     * 驗証用
     */
    public void valid() throws ValidException {
        // 驗証 密碼和再次確認的密碼一致
        if(!password1.equals(password2))
            throw new ValidException(ResponseEnum.password_not_match);
    }
}
