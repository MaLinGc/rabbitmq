package com.ml.domain;

import java.util.Date;

public class User {
    private String name;
    private Integer age;
    private Boolean normal;
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getNormal() {
        return normal;
    }

    public void setNormal(Boolean normal) {
        this.normal = normal;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public User() {
    }

    public User(String name, Integer age, Boolean normal, Date birthday) {
        this.name = name;
        this.age = age;
        this.normal = normal;
        this.birthday = birthday;
    }
}
