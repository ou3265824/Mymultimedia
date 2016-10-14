package com.olq.multimedias.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/8 0008.
 */
public class BaseBean implements Serializable{


    /**
     * createdAt : 2016-10-09 14:42:18
     * objectId : 0c1f581711
     * sessionToken : 1000bd2440a8150780496cd548699683
     */

    private String createdAt;
    private String objectId;
    private String sessionToken;
    private String updatedAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
