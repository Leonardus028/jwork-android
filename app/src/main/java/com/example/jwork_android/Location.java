package com.example.jwork_android;
/**
 * Class Location berfungsi untuk menangani objek yang berkaitan dengan
 * informasi lokasi, seperti nama provinsi, kota dan deskripsi
 * @author Leonardus Kevin
 * @version 27.06.2021
 */
public class Location {
    private String province;
    private String description;
    private String city;

    /**
     * Constructor untuk objects dari class Location
     */
    public Location(String city, String province, String description){
        this.city = city;
        this.province = province;
        this.description = description;
    }
    /**
     * Method ini digunakan untuk mengembalikan nilai provinsi.
     * @return province
     */
    public String getProvince(){
        return province;
    }

    /**
     * Method ini digunakan untuk mengembalikan nilai kota.
     * @return city
     */
    public String getCity(){
        return city;
    }

    /**
     * Method ini digunakan untuk mengembalikan nilai deskripsi.
     * @return deskripsi
     */
    public String getDescription(){
        return description;
    }

    /**
     * Method ini digunakan untuk memberikan nilai provinsi.
     * @param province
     */
    public void setProvince(String province){
        this.province = province;
    }
    /**
     * Method ini digunakan untuk memberikan nilai kota.
     * @param city
     */

    public void setCity(String city){
        this.city = city;
    }

    /**
     * Method ini digunakan untuk memberikan nilai deskripsi.
     * @param description
     */
    public void setDescription(String description){
        this.description = description;
    }
}

