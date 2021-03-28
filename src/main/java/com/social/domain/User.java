package com.social.domain;

import java.util.Date;

public class User {
    private String id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String adderss;
    private char gender;
    private Date birthDate;
    private BloodType bloodType;

    public User(String id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(String id, String name, String password, String email, String phone, String adderss) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.adderss = adderss;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws UserException {
        if(id != null && (id=id.trim()).length() == 10){
            this.id = id;
        }else{
            throw new UserException("id欄位必須輸入且格式必須正確！");
        }
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws UserException {
        if(password != null && (password = password.trim()).length() > 0){
            this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws UserException {
        if(phone != null && (phone = phone.trim()).length() > 0){
            this.phone = phone;
        }else{
            throw new UserException("手機欄位必須輸入");
        }

    }

    public String getAdderss() {
        return adderss;
    }

    public void setAdderss(String adderss) throws UserException {
        if(adderss != null && (adderss = adderss.trim()).length() > 0){
            this.adderss = adderss;
        }else{
            throw new UserException("地址欄位必須輸入");
        }

    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }
}
