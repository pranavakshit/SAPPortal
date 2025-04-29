// File: SAPPortal/src/main/java/com/pranav/sportal/model/Student.java
package com.pranav.sportal.model;

public class Student {
    private String name;
    private String sap;
    private String contact;

    public Student(String name, String sap, String contact) {
        this.name = name;
        this.sap = sap;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getSap() {
        return sap;
    }

    public String getContact() {
        return contact;
    }
}
