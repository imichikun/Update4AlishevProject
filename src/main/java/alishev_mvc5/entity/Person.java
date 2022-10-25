package alishev_mvc5.entity;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class Person {
    private int id;

    @NotBlank(message = "this field should not be empty")
    @Pattern(regexp = "[a-zA-Z]+", message = "only alphabet symbols are allowed")
    @Size(min=3, message = "name can't be less than 3 symbols")
    private String name;

    @Range(min=18, message = "person's age can not be less than 18 years")
    private int age;

    @Email(message = "not a valid email")
    @NotBlank(message = "this field should not be empty")
    private String email;

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}