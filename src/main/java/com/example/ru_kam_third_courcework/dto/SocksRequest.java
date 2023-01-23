package com.example.ru_kam_third_courcework.dto;

import com.example.ru_kam_third_courcework.model.Color;
import com.example.ru_kam_third_courcework.model.Size;


public class SocksRequest {

    private Color color;

    private Size size;

    private int cottonRatio;

    private int quantity;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getCottonRatio() {
        return cottonRatio;
    }

    public void setCottonRatio(int cottonRatio) {
        this.cottonRatio = cottonRatio;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
