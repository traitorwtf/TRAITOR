package Serialization;

import java.io.Serializable;

/**
 * Created by traitorwtf on 19.03.2017.
 */
public class Person implements Serializable{
    private String name;
    private String surname;

    public String toString() {
        return getName()+" "+getSurname();
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
