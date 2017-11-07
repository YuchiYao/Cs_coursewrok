package com.company;

public class Cat extends Animal  {
    private String noise, name;
    private int age;
    public Cat (String name, int age){
        this.name = name ;
        this.age  = age;
        this.noise = "Meow";
    }
    @Override
    public String makeNoise(){
     if (age<5){
         return noise.toUpperCase();
     }else return noise;

    }

    @Override
    public void greet() {
        System.out.println("Cat " + name + " says" + makeNoise());
    }
}


