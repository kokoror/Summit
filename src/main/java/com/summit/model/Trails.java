package com.summit.model;

import java.util.Objects;

public class Trails {
  protected Integer trailId;
  protected String trailName;
  protected String area;
  protected String city;
  protected String state;
  protected String country;
  protected Double latitude;
  protected Double longitude;
  protected Double elevationGain;
  protected DifficultyLevel level;
  protected RouteType type;

  public enum DifficultyLevel {
    EASY("EASY"), MEDIUM("MEDIUM"), DIFFICULT("DIFFICULT"), EXTRA_DIFFICULT("EXTRA DIFFICULT");
    public final String level;

    DifficultyLevel(String level) {
      this.level = level;  
    }
    ///added 
    public static DifficultyLevel fromString(String level) {
        for (DifficultyLevel d : DifficultyLevel.values()) {
            if (d.level.equalsIgnoreCase(level)) {
                return d;
            }
        }
        return null;
    }
    
  }
  
  
    public enum RouteType{ OUTBACK("OUT AND BACK"), P2P("POINT TO POINT"), LOOP("LOOP");
      public final String type;
      RouteType(String type) {
        this.type = type;
      }
      public static RouteType fromString(String type) {
          for (RouteType r : RouteType.values()) {
              if (r.type.equalsIgnoreCase(type)) {
                  return r;
              }
          }
          return null;
      }
    }

  public Trails(Integer trailId, String trailName, String area, String city, String state,
      String country, Double latitude, Double longitude, Double elevationGain,
      DifficultyLevel level, RouteType type) {
    this.trailId = trailId;
    this.trailName = trailName;
    this.area = area;
    this.city = city;
    this.state = state;
    this.country = country;
    this.latitude = latitude;
    this.longitude = longitude;
    this.elevationGain = elevationGain;
    this.level = level;
    this.type = type;
  }

  public Trails( String trailName, String area, String city, String state,
      String country, Double latitude, Double longitude, Double elevationGain,
      DifficultyLevel level, RouteType type) {
    this.trailName = trailName;
    this.area = area;
    this.city = city;
    this.state = state;
    this.country = country;
    this.latitude = latitude;
    this.longitude = longitude;
    this.elevationGain = elevationGain;
    this.level = level;
    this.type = type;
  }

  public Integer getTrailId() {
    return trailId;
  }

  public void setTrailId(Integer trailId) {
    this.trailId = trailId;
  }

  public String getTrailName() {
    return trailName;
  }

  public void setTrailName(String trailName) {
    this.trailName = trailName;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
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

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getElevationGain() {
    return elevationGain;
  }

  public void setElevationGain(Double elevationGain) {
    this.elevationGain = elevationGain;
  }

  public DifficultyLevel getLevel() {
    return level;
  }

  public void setLevel(DifficultyLevel level) {
    this.level = level;
  }

  public RouteType getType() {
    return type;
  }

  public void setType(RouteType type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Trails trails = (Trails) o;
    return Objects.equals(trailId, trails.trailId) && Objects
        .equals(trailName, trails.trailName) && Objects.equals(area, trails.area)
        && Objects.equals(city, trails.city) && Objects
        .equals(state, trails.state) && Objects.equals(country, trails.country)
        && Objects.equals(latitude, trails.latitude) && Objects
        .equals(longitude, trails.longitude) && Objects
        .equals(elevationGain, trails.elevationGain) && level == trails.level
        && type == trails.type;
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(trailId, trailName, area, city, state, country, latitude, longitude, elevationGain,
            level, type);
  }

  @Override
  public String toString() {
    return "Trails{" +
        "trailId=" + trailId +
        ", trailName='" + trailName + '\'' +
        ", area='" + area + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", country='" + country + '\'' +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", elevationGain=" + elevationGain +
        ", level=" + level +
        ", type=" + type +
        '}';
  }
}
