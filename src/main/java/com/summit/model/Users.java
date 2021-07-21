//Submitted by : Shweta
package com.summit.model;

import java.util.Objects;

public class Users {
  protected String username;
  protected String password;
  protected String city;
  protected String state;
  protected String country;

  public Users(String username, String password, String city, String state, String country) {
    this.username = username;
    this.password = password;
    this.city = city;
    this.state = state;
    this.country = country;
  }
  
  public Users(String username) {
	  this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Users users = (Users) o;
    return Objects.equals(username, users.username) && Objects
        .equals(password, users.password) && Objects.equals(city, users.city)
        && Objects.equals(state, users.state) && Objects
        .equals(country, users.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, city, state, country);
  }

  @Override
  public String toString() {
    return "Users{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", country='" + country + '\'' +
        '}';
  }
}
