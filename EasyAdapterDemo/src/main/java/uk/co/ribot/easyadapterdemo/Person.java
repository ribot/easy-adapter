package uk.co.ribot.easyadapterdemo;

/**
 * Created by ivan on 14/02/2014.
 */
public class Person {

    private String name;
    private String phoneNumber;
    private int resDrawableId;

    public Person(){

    }

    public Person(String name, String phoneNumber, int resDrawableId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.resDrawableId = resDrawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getResDrawableId() {
        return resDrawableId;
    }

    public void setResDrawableId(int resDrawableId) {
        this.resDrawableId = resDrawableId;
    }
}
