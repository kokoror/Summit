package com.summit.model;

import java.util.Objects;

public class Activities {
  protected Integer activityId;
  protected String activityTag;

  public Activities(Integer activityId, String activityTag) {
    this.activityId = activityId;
    this.activityTag = activityTag;
  }
  
  //added
  public Activities(String activityTag) {
	    this.activityTag = activityTag;
	  }
  

  public Integer getActivityId() {
    return activityId;
  }

  public void setActivityId(Integer activityId) {
    this.activityId = activityId;
  }

  public String getActivityTag() {
    return activityTag;
  }

  public void setActivityTag(String activityTag) {
    this.activityTag = activityTag;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Activities that = (Activities) o;
    return Objects.equals(activityId, that.activityId) && Objects
        .equals(activityTag, that.activityTag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(activityId, activityTag);
  }

  @Override
  public String toString() {
    return "Activities{" +
        "activityId=" + activityId +
        ", activityTag='" + activityTag + '\'' +
        '}';
  }
}
