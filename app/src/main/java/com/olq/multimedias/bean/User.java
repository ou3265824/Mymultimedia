package com.olq.multimedias.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2016/10/8 0008.
 */

@Entity
public class User extends BaseBean{

    @Id(autoincrement = true)
    private Long id;
    private String objectId;
    private String username;
    private String password;
    @Generated(hash = 840342775)
    public User(Long id, String objectId, String username, String password) {
        this.id = id;
        this.objectId = objectId;
        this.username = username;
        this.password = password;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getObjectId() {
        return this.objectId;
    }
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }




}
