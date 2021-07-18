package com.summit.model;

import java.util.Objects;

public class Features {
  protected Integer featureId;
  protected String featureTag;

  public Features(Integer featureId, String featureTag) {
    this.featureId = featureId;
    this.featureTag = featureTag;
  }
  
  //added
  public Features(String featureTag) {
	    this.featureTag = featureTag;
	  }

  
  public Integer getFeatureId() {
    return featureId;
  }

  public void setFeatureId(Integer featureId) {
    this.featureId = featureId;
  }

  public String getFeatureTag() {
    return featureTag;
  }

  public void setFeatureTag(String featureTag) {
    this.featureTag = featureTag;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Features features = (Features) o;
    return Objects.equals(featureId, features.featureId) && Objects
        .equals(featureTag, features.featureTag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(featureId, featureTag);
  }

  @Override
  public String toString() {
    return "Features{" +
        "featureId=" + featureId +
        ", featureTag='" + featureTag + '\'' +
        '}';
  }
}
