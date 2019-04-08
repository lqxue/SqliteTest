package com.sqlitetest.db;

/**
 * Created by 12533 on 2019-4-8.
 */

public class Student {
    private String name;
    private String passwords;

    public Student(String s, String s1) {
        name = s;
        passwords = s1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }
}
