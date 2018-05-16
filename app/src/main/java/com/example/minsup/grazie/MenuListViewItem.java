package com.example.minsup.grazie;

import android.graphics.drawable.Drawable;

/**
 * Created by Minsub on 2018-05-10.
 */

public class MenuListViewItem {

    private Drawable menuImage;
    private String menuName;
    private String menuPrice;
    private String menuTaste;
    private String menuEngName;

    public String getMenuEngName() {
        return menuEngName;
    }

    public void setMenuEngName(String menuEngName) {
        this.menuEngName = menuEngName;
    }

    public String getMenuTaste() {
        return menuTaste;
    }

    public void setMenuTaste(String menuTaste) {
        this.menuTaste = menuTaste;
    }

    public Drawable getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(Drawable menuImage) {
        this.menuImage = menuImage;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

}
