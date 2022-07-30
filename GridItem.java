package com.example.demo.controller;

public class GridItem {

    private int node;
    private String color;
    private int row;
    private int column;

    public GridItem(int node, String color, int row, int column) {
        this.node = node;
        this.color = color;
        this.row = row;
        this.column = column;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
