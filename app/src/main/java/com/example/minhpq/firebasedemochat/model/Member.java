package com.example.minhpq.firebasedemochat.model;

import android.net.Uri;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by minhpq on 4/23/2018.
 */

public class Member extends RealmObject{
    @PrimaryKey
    private String userId;
    private String avatar;
    private String displayName;
    private String email;
    private String password;

    public Member(String userId, String avatar, String displayName, String email, String password) {
        this.userId = userId;
        this.avatar = avatar;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
