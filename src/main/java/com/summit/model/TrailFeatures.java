package com.summit.model;

public class TrailFeatures {
  protected Integer trailFeatureId;
  protected Trails trail;
  protected Features feature;
  
	public TrailFeatures(Integer trailFeatureId, Trails trail, Features feature) {
		this.trailFeatureId = trailFeatureId;
		this.trail = trail;
		this.feature = feature;
	}
	
	public TrailFeatures(Trails trail, Features feature) {
		this.trail = trail;
		this.feature = feature;
	}
	
	public Integer getTrailFeatureId() {
		return trailFeatureId;
	}
	
	public void setTrailFeatureId(Integer trailFeatureId) {
		this.trailFeatureId = trailFeatureId;
	}
	
	public Trails getTrail() {
		return trail;
	}
	
	public void setTrail(Trails trail) {
		this.trail = trail;
	}
	
	public Features getFeature() {
		return feature;
	}
	
	public void setFeature(Features feature) {
		this.feature = feature;
	}


  
}
