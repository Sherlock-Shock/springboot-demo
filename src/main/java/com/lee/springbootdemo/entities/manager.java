package com.lee.springbootdemo.entities;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class manager implements Serializable {

    private int mid;
    private String psw;
    private String name;


    public manager() {
    }

    public manager(int mid, String psw, String name) {
        this.mid = mid;
        this.psw = psw;
        this.name = name;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
