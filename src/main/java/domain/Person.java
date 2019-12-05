package domain;

import java.time.LocalDate;

public class Person {
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthday;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, Gender gender, LocalDate birthday) {
        this(firstName, lastName);
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return birthday;
    }
}
