package com.example.subhash.sqlitewithsearch.Model;

public class DetailGetSet {

    private String  name, age, conatct, email;

    public DetailGetSet(){

    }


    public DetailGetSet( String name, String age, String contact, String email){
        this.name = name;
        this.age = age;
        this.conatct = contact;
        this.email = email;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getConatct() {
        return conatct;
    }

    public void setConatct(String conatct) {
        this.conatct = conatct;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
