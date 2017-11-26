package com.wrapper.spotify.models.audio;

@SuppressWarnings("javadoc")
public class Interval {
	
	private double start;
	private double duration;
	private double confidence;
	
	public double getStart() {
		return start;
	}
	
	public void setStart(double start) {
		this.start = start;
	}
	
	public double getDuration() {
		return duration;
	}
	
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	public double getConfidence() {
		return confidence;
	}
	
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

}
