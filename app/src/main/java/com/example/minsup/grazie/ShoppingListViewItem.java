package com.example.minsup.grazie;

import android.graphics.drawable.Drawable;

/**
 * Created by Minsub on 2018-05-30.
 */

public class ShoppingListViewItem {

    private Drawable menuImage;
    private String menuName;
    private String menuPrice;
    private String menuTaste;
    private String menuEngName;
    private String arrivalTime;
    private String menuTasteChoice;
    private String quantity;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

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

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getMenuTasteChoice() {
        return menuTasteChoice;
    }

    public void setMenuTasteChoice(String menuTasteChoice) {
        this.menuTasteChoice = menuTasteChoice;
    }

}
