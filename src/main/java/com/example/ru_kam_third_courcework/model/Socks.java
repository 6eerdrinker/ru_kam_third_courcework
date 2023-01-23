package com.example.ru_kam_third_courcework.model;

import java.util.Objects;

public class Socks {

    private final Color color;

    private final Size size;

    private final int cottonRatio;

    public Socks(Color color, Size size, int cottonRatio) {
        this.color = color;
        this.size = size;
        this.cottonRatio = cottonRatio;
    }

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }

    public int getCottonRatio() {
        return cottonRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return cottonRatio == socks.cottonRatio && color == socks.color && size == socks.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size, cottonRatio);
    }
}
