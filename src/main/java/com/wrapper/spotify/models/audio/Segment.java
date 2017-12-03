package com.wrapper.spotify.models.audio;

import java.util.List;

@SuppressWarnings("javadoc")
public class Segment extends Interval {
	
	private double loudnessStart;
	private double loudnessMaxTime;
	private double loudnessMax;
	private double loudnessEnd;
	private List<Double> pitches;
	private List<Double> timbre;
	
	public double getLoudnessStart() {
		return loudnessStart;
	}
	
	public void setLoudnessStart(double loudnessStart) {
		this.loudnessStart = loudnessStart;
	}
	
	public double getLoudnessMaxTime() {
		return loudnessMaxTime;
	}
	
	public void setLoudnessMaxTime(double loudnessMaxTime) {
		this.loudnessMaxTime = loudnessMaxTime;
	}
	
	public double getLoudnessMax() {
		return loudnessMax;
	}
	
	public void setLoudnessMax(double loudnessMax) {
		this.loudnessMax = loudnessMax;
	}
	
	public double getLoudnessEnd() {
		return loudnessEnd;
	}
	
	public void setLoudnessEnd(double loudnessEnd) {
		this.loudnessEnd = loudnessEnd;
	}
	
	public List<Double> getPitches() {
		return pitches;
	}
	
	public void setPitches(List<Double> pitches) {
		this.pitches = pitches;
	}
	
	public List<Double> getTimbre() {
		return timbre;
	}
	
	public void setTimbre(List<Double> timbre) {
		this.timbre = timbre;
	}

}
