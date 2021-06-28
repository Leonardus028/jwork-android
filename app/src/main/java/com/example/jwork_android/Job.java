package com.example.jwork_android;
/**Class Job untuk membuat objek Job dan memodifikasi nilai dalam objek Job
 * @author Leonardus Kevin
 * @version 27.06.2021
 */
public class Job {

    private int id;
    private String name;
    private Recruiter recruiter;
    private int fee;
    private String category;
    /**
     * Constructor untuk objek dari class Job
     */
    public Job(int id, String name, Recruiter recruiter, int fee, String category){
        this.id = id;
        this.name = name;
        this.recruiter = recruiter;
        this.fee = fee;
        this.category = category;
    }
    /**
     * Method ini digunakan untuk mengembalikan nilai id.
     * @return id
     */
    public int getId(){
        return id;
    }
    /**
     * Method ini digunakan untuk mengembalikan nilai perekru.
     * @return recruiter
     */
    public Recruiter getRecruiter(){
        return recruiter;
    }
    /**
     * Method ini digunakan untuk mengembalikan nilai name.
     * @return name
     */
    public String getName(){
        return name;
    }
    /**
     * Method ini digunakan untuk mengembalikan nilai fee.
     * @return fee
     */
    public int getFee(){
        return fee;
    }
    /**
     * Method ini digunakan untuk mengembalikan nilai kategori.
     * @return category
     */
    public String getCategory(){
        return category;
    }
    /**
     * Method ini digunakan untuk memberikan nilai id.
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }
    /**
     * Method ini digunakan untuk memberikan nilai name.
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Method ini digunakan untuk memberikan nilai recruiter.
     * @param recruiter
     */
    public void setRecruiter(){
        this.recruiter = recruiter;
    }
    /**
     * Method ini digunakan untuk memberikan nilai fee/bayaran.
     * @param fee
     */
    public void setFee(int fee){
        this.fee = fee;
    }
    /**
     * Method ini digunakan untuk memberikan nilai category.
     * @param category
     */
    public void setCategory(String category){
        this.category = category;
    }
    /**
     * Method ini digunakan untuk menampilkan data
     */
    public String toString(){
        return "\nId = " + id +
                "\nName = " + name +
                "\nSeller = " + getRecruiter().getName() +
                "\nCity =" + getRecruiter().getLocation().getCity() +
                "\nFee = " + fee +
                "\nCategory = " + getCategory();
    }
}
