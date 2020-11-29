package com.example.fcmnotification;

public class NotificationModel {

    boolean error;
    String id;
    String name;
    String email;
    String mobile;
    String reg_type;
    String token;

    public NotificationModel(boolean error, String id, String name, String email, String mobile, String reg_type, String token) {
        this.error = error;
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.reg_type = reg_type;
        this.token = token;
    }

    public boolean getError() {
        return error;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getReg_type() {
        return reg_type;
    }

    public String getToken() {
        return token;
    }
}
