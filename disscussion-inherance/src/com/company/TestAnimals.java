package com.company;

public class TestAnimals {
    public static void main(String[] args) {
        Animal a = new Animal("Pluto", 10);
        Cat c = new Cat("Garfield", 6);
        Dog d = new Dog("Fido", 4);

        /*Predict before run*/

        a.greet(); // (A) Pluto says Huh
        c.greet(); // (B) Garfield says Meow
        d.greet(); // (C) Fido says WOOF

        a = c;
        ((Cat) a).greet(); // (D) Garfield says Meow
        a.greet(); //Garfield says Huh

        a = new Dog("Spot", 10);
        d = (Dog) a;  // Add Dog casting
        d.greet();

    }
}
