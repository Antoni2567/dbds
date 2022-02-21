package com.revature;

public class nCustomer {
    private String verify;
    public String getVerify;
    private int count = 2;
    private int id;
    private String first;
    private String last;
    private String number;
    private String email;
    private String city;
    private String state;
    public nCustomer()
    {
        this.count++;

    }
    public nCustomer(int id,String first,String last,String number, String email,String city,String state,String verify)
    {

       this.id = id;
        this.first = first;
        this.last = last;
        this.number = number;
        this.email = email;
        this.city = city;
        this.state = state;
        this.verify = verify;
    }

   public int getId() {
        return id;
    }


   public void setId(int id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
    public String getVerify() {
        return verify;
    }
    public void setVerify()
    {
        this.verify = verify;
    }
}
