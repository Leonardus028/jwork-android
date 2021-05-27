package com.example.jwork_android;

public class Location {
    private String province;
    private String description;
    private String city;


    public Location(String city, String province, String description){
        this.city = city;
        this.province = province;
        this.description = description;
    }

 String getProvince(){
        return province;
    }


    public String getCity(){
        return city;
    }


    public String getDescription(){
        return description;
    }


    public void setProvince(String province){
        this.province = province;
    }


    public void setCity(String city){
        this.city = city;
    }


    public void setDescription(String description){
        this.description = description;
    }
}

