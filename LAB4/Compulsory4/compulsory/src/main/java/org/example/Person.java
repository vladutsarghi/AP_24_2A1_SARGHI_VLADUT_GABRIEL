package org.example;

public class Person{
    private final String name;
    private int age;

    private boolean hasACar;

    private boolean iDrive;

    String leaving;
    String destination;

    Person(String name, int age, boolean hasACar, boolean iDrive){
        this.name = name;
        this.age = age;
        this.hasACar = hasACar;
        this.iDrive = iDrive;

        condition(age, hasACar, iDrive);
    }

    private void condition(int age,boolean hasACar,boolean iDrive){
        if(age<18){
            this.hasACar = false;
            this.iDrive = false;
        } else if(!hasACar){
            this.iDrive = false;
        }
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean getHasACar() {
        return hasACar;
    }

    public boolean getiDrive() {
        return iDrive;
    }
}
