package com.olq.multimedias.bean;

/**
 * Created by Administrator on 2016/10/20 0020.
 */

public class BelleBase<T> {

    private String status;
    private String total;
    private T tngou;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


    public T getTngou() {
        return tngou;
    }

    public void setTngou(T tngou) {
        this.tngou = tngou;
    }

    @Override
    public String toString() {
        return "BelleBase{" +
                "status='" + status + '\'' +
                ", total='" + total + '\'' +
                ", tngou=" + tngou +
                '}';
    }
}
