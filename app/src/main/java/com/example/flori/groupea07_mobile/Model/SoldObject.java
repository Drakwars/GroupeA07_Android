package com.example.flori.groupea07_mobile.Model;

public class SoldObject {

    private int idObject;
    private int finalPrice;
    private String nameObject;
    private String catObject;

    public SoldObject(int idO, int price, String name, String cat){
        this.idObject = idO;
        this.finalPrice = price;
        this.nameObject = name;
        this.catObject = cat;
    }

    public int getIdObject(){ return idObject;}

    public void setIdObject(int idO){ idObject = idO;}

    public int getFinalPrice(){ return finalPrice;}

    public void setFinalPrice(int price){ finalPrice = price;}

    public String getNameObject(){ return nameObject;}

    public void setNameObject(String name){ nameObject = name;}

    public String getCatObject(){return getCatObject();}

    public void setCatObject(String cat){ catObject = cat;}
}
