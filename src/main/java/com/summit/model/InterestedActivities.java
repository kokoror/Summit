package com.summit.model;

import java.util.Objects;

public class InterestedActivities {
  protected Integer favoriteActivityId;
  protected Users userName;
  protected Activities activity;
  
	public InterestedActivities(Integer favoriteActivityId, Users userName, Activities activity) {
		this.favoriteActivityId = favoriteActivityId;
		this.userName = userName;
		this.activity = activity;
	}
	
	public InterestedActivities(Users userName, Activities activity) {
		this.userName = userName;
		this.activity = activity;
	}
	
	public Integer getFavoriteActivityId() {
		return favoriteActivityId;
	}
	
	public void setFavoriteActivityId(Integer favoriteActivityId) {
		this.favoriteActivityId = favoriteActivityId;
	}
	
	public Users getUserName() {
		return userName;
	}
	
	public void setUserName(Users userName) {
		this.userName = userName;
	}
	
	public Activities getActivity() {
		return activity;
	}
	
	public void setActivity(Activities activity) {
		this.activity = activity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(activity, favoriteActivityId, userName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterestedActivities other = (InterestedActivities) obj;
		return Objects.equals(activity, other.activity) && Objects.equals(favoriteActivityId, other.favoriteActivityId)
				&& Objects.equals(userName, other.userName);
	}
	
	@Override
	public String toString() {
		return "InterestedActivities [favoriteActivityId=" + favoriteActivityId + ", userName=" + userName + ", activity="
				+ activity + "]";
	}
	  
 
}
