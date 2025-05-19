package model;

public class Person {
    private final String name;
    private int age;

    public Person(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    public void updateAge(final int newAge) {
        this.age = newAge;
    }

    @Override
    public String toString() {
        return this.name;
    }
}