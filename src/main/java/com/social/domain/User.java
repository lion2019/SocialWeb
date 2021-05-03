package com.social.domain;

import com.social.exception.UserException;

public class User {
    //    private String id;
    private String name;
    private String password;
    private String email;
//    private String phone;
    /** 屬性名稱和資料表欄位名稱不同反射機制出錯 */
//    private String address;
    /**
     * 基本型別 無法帶入 null 和 資料表結構不符, 盡量別用基本型別
     */
    private Character gender;
//    private Date birthDate;
//    private BloodType bloodType;

    public User() {

    }

    public User(String name, String password, String email) {
//        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(String name, String password, String email, String phone, String address) {
//        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
//        this.phone = phone;
//        this.address = address;
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

//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) throws UserException {
//        if(phone != null && (phone = phone.trim()).length() > 0){
//            this.phone = phone;
//        }else{
//            throw new UserException("手機欄位必須輸入");
//        }
//
//    }

//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) throws UserException {
//        if(address != null && (address = address.trim()).length() > 0){
//            this.address = address;
//        }else{
//            throw new UserException("地址欄位必須輸入");
//        }
//
//    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

//    public Date getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(Date birthDate) {
//        this.birthDate = birthDate;
//    }

//    public BloodType getBloodType() {
//        return bloodType;
//    }
//
//    public void setBloodType(BloodType bloodType) {
//        this.bloodType = bloodType;
//    }

    @Override
    public String toString() {
        return "User [name=" + name + ", password=" + password + ", email=" + email
                + ", gender=" + gender + "]";
    }
}
