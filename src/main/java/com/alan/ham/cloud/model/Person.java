package com.alan.ham.cloud.model;

public class Person {
  private String lname;
  private String fname;
  private String address;
  private String mobile;
  private String city;

  public Person() {
  }

  public Person(Builder builder){
    this.lname = builder.getLname();
    this.fname = builder.getFname();
    this.mobile = builder.getMobile();
    this.address = builder.getAddress();
    this.city = builder.getCity();
  }

  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public static class Builder {
    private String lname;
    private String fname;
    private String address;
    private String mobile;
    private String city;

    public Builder withLname(String lname){
      this.lname = lname;
      return this;
    }

    public Builder withFname(String fname) {
      this.fname = fname;
      return this;
    }

    public Builder withAddress(String address){
      this.address = address;
      return this;
    }

    public Builder withMobile(String mobile){
      this.mobile = mobile;
      return this;
    }

    public Builder withCity(String city){
      this.city = city;
      return this;
    }

    public Person build(){
      return new Person(this);
    }

    private String getLname() {
      return lname;
    }

    private String getFname() {
      return fname;
    }

    private String getAddress() {
      return address;
    }

    private String getMobile() {
      return mobile;
    }

    private String getCity() {
      return city;
    }
  }

  @Override
  public String toString() {
    return "Person{" +
        "lname='" + lname + '\'' +
        ", fname='" + fname + '\'' +
        ", address='" + address + '\'' +
        ", mobile='" + mobile + '\'' +
        ", city='" + city + '\'' +
        '}';
  }
}
