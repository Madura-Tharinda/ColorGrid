package com.example.demo.controller;

import org.springframework.boot.autoconfigure.web.ServerProperties;

import java.util.*;
import java.util.stream.Collectors;

public class ColorGrid {

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    List<String> colors = new ArrayList<>();

    List<GridItem> gridItems = new ArrayList<>();

    public String getRandomColor(){
        Random random = new Random();
        int index = random.nextInt(colors.size());
        return colors.get(index);
    }


    public void createGrid(int col, int row){
        int node = 1;
        for(int i = 1; i <= row; i++){
            for(int j = 1; j <= col; j++){
                gridItems.add(new GridItem(node, getRandomColor(), i, j));
            }
        }
    }

    public Map<String, List<GridItem>> getLargestConnectingBlock(int col, int row){
        List<GridItem> gridBlock = new ArrayList<>();
        Map<String, List<GridItem>> mapColor = new TreeMap<>();

        for(int index = 0; index < colors.size(); index ++){
            mapColor.put(colors.get(index), new ArrayList<>());
        }

        for(GridItem gridItem : gridItems){

            Optional<GridItem> gridCol = checkForSameColorInColumn(gridItem, col, row);
            if(gridCol.isPresent()){
                GridItem gridItemNext = gridCol.get();
                if(gridBlock.size() == 0){
                    gridBlock.add(gridItem);
                    gridBlock.add(gridItemNext);
                } else {
                    Optional<GridItem> gdCurrent = gridBlock.stream().filter( gd -> gd.getColumn() == gridItem.getColumn() && gd.getRow() == gridItem.getRow()).findAny();
                    if(!gdCurrent.isPresent()){
                        gridBlock.add(gridItem);
                    }
                    Optional<GridItem> gdNext = gridBlock.stream().filter( gd -> gd.getColumn() == gridItemNext.getColumn() && gd.getRow() == gridItemNext.getRow()).findAny();
                    if(!gdNext.isPresent()){
                        gridBlock.add(gridItem);
                    }
                }

            }

            Optional<GridItem> gridRow = checkForSameColorInRow(gridItem, col, row);
            if(gridRow.isPresent()){
                GridItem gridItemNext = gridRow.get();
                if(gridBlock.size() == 0){
                    gridBlock.add(gridItem);
                    gridBlock.add(gridItemNext);
                } else {
                    Optional<GridItem> gdCurrent = gridBlock.stream().filter( gd -> gd.getColumn() == gridItem.getColumn() && gd.getRow() == gridItem.getRow()).findAny();
                    if(!gdCurrent.isPresent()){
                        gridBlock.add(gridItem);
                    }
                    Optional<GridItem> gdNext = gridBlock.stream().filter( gd -> gd.getColumn() == gridItemNext.getColumn() && gd.getRow() == gridItemNext.getRow()).findAny();
                    if(!gdNext.isPresent()){
                        gridBlock.add(gridItem);
                    }
                }

            }

            if(mapColor.get(gridItem.getColor()).size() < gridBlock.size()){
                mapColor.replace(gridItem.getColor(), gridBlock);
            } else {
                mapColor.put(gridItem.getColor(), gridBlock);
            }


        }
        return mapColor;
    }

    public Optional<GridItem> checkForSameColorInColumn( GridItem gridItem, int col, int row){
        Optional<GridItem> gridCol = Optional.empty();
        if( gridItem.getColumn() < col){
            gridCol = gridItems.stream().filter(gd -> gd.getColumn() == gridItem.getColumn() + 1 && gd.getRow() == gridItem.getRow() && gridItem.getColor().equals(gd.getColor())).findAny();

        }
        return gridCol;
    }

    public Optional<GridItem> checkForSameColorInRow( GridItem gridItem, int col, int row){
        Optional<GridItem> gridCol = Optional.empty();
        if( gridItem.getColumn() < row){
            gridCol = gridItems.stream().filter(gd -> gd.getRow() == gridItem.getRow() + 1 && gd.getColumn() == gridItem.getColumn() && gridItem.getColor().equals(gd.getColor())).findAny();

        }
        return gridCol;
    }

    public static void main(String[] args) {
        ColorGrid colorGrid = new ColorGrid();

        List<String> colorList = new ArrayList<>();
        colorList.add("Red");
        colorList.add("Blue");
        colorList.add("Green");
        colorGrid.setColors(colorList);

        colorGrid.createGrid(10,10);

        Map<String, List<GridItem>> colorGridBlock = colorGrid.getLargestConnectingBlock(10,10);
        String color = colorGridBlock.entrySet()
                .stream()
                .max(Comparator.comparingInt(entry -> entry.getValue().size()))
                .map(Map.Entry::getKey)
                .orElse(null);
        System.out.println("Color : " + color);
    }
}
