package com.summit.model;


public class PreferredFeatures {
  protected Integer preferredFeatureId;
  protected Users userName;
  protected Features feature;
  
	public PreferredFeatures(Integer preferredFeatureId, Users userName, Features feature) {
		this.preferredFeatureId = preferredFeatureId;
		this.userName = userName;
		this.feature = feature;
	}
	  
	public PreferredFeatures(Users userName, Features feature) {
		this.userName = userName;
		this.feature = feature;
	}
	
	public Integer getPreferredFeatureId() {
		return preferredFeatureId;
	}
	
	public void setPreferredFeatureId(Integer preferredFeatureId) {
		this.preferredFeatureId = preferredFeatureId;
	}
	
	public Users getUserName() {
		return userName;
	}
	
	public void setUserName(Users userName) {
		this.userName = userName;
	}
	
	public Features getFeature() {
		return feature;
	}
	
	public void setFeature(Features feature) {
		this.feature = feature;
	}



}
