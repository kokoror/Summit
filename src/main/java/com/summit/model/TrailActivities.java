package com.summit.model;

public class TrailActivities {
	protected Integer trailActivityId;
	protected Trails trail;
	protected Activities activity;
	  
	public TrailActivities(Integer trailActivityId, Trails trail, Activities activity) {
		this.trailActivityId = trailActivityId;
		this.trail = trail;
		this.activity = activity;
	}
	  
	public TrailActivities(Trails trail, Activities activity) {
		this.trail = trail;
		this.activity = activity;
	}
	
	public Integer getTrailActivityId() {
		return trailActivityId;
	}
	
	public void setTrailActivityId(Integer trailActivityId) {
		this.trailActivityId = trailActivityId;
	}
	
	public Trails getTrail() {
		return trail;
	}
	
	public void setTrail(Trails trail) {
		this.trail = trail;
	}
	
	public Activities getActivity() {
		return activity;
	}
	
	public void setActivity(Activities activity) {
		this.activity = activity;
	}
	  

}
