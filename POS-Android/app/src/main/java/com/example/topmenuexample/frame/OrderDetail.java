package com.example.topmenuexample.frame;

import java.io.Serializable;

public class OrderDetail implements Serializable {

    int imgSrc;
    String menuID;
    String menuName;
    int menuCost;
    int menuCount;

    public OrderDetail() {

    }

    public OrderDetail(int imgSrc, String menuID, String menuName, int menuCost, int menuCount) {
        this.imgSrc = imgSrc;
        this.menuID = menuID;
        this.menuName = menuName;
        this.menuCost = menuCost;
        this.menuCount = menuCount;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuCost() {
        return menuCost;
    }

    public void setMenuCost(int menuCost) {
        this.menuCost = menuCost;
    }

    public int getMenuCount() {
        return menuCount;
    }

    public void setMenuCount(int menuCount) {
        this.menuCount = menuCount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "imgSrc=" + imgSrc +
                ", menuID='" + menuID + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuCost=" + menuCost +
                ", menuCount=" + menuCount +
                '}';
    }
}



