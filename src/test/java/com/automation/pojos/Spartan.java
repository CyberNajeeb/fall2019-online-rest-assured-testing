package com.automation.pojos;

import java.util.Objects;

public class Spartan {

    /*
{
    “id”: 393,
    “name”: “Michael Scott”,
    “gender”: “Male”,
    “phone”: 6969696969
}
 */

    private int id;
    private String name;
    private String gender;
    private Long phone;

    public Spartan(String name, String gender, Long phone) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public int getId() {
        return this.id;
    }

    public Spartan() {
    }

    public Spartan(int id, String name, String gender, Long phone) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Long getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spartan spartan = (Spartan) o;
        return id == spartan.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, phone);
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }


    public static void main(String[] args) {
        Spartan spartan = new Spartan("Mohammed", "Male", 1234567890L);

        System.out.println("spartan = " + spartan);
    }
}
