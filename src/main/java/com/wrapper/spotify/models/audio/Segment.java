package com.wrapper.spotify.models.audio;

import java.util.List;

@SuppressWarnings("javadoc")
public class Segment extends Interval {
	
	private float loudnessStart;
	private float loudnessMaxTime;
	private float loudnessMax;
	private float loudnessEnd;
	private List<Float> pitches;
	private List<Float> timbre;
	
	public float getLoudnessStart() {
		return loudnessStart;
	}
	
	public void setLoudnessStart(float loudnessStart) {
		this.loudnessStart = loudnessStart;
	}
	
	public float getLoudnessMaxTime() {
		return loudnessMaxTime;
	}
	
	public void setLoudnessMaxTime(float loudnessMaxTime) {
		this.loudnessMaxTime = loudnessMaxTime;
	}
	
	public float getLoudnessMax() {
		return loudnessMax;
	}
	
	public void setLoudnessMax(float loudnessMax) {
		this.loudnessMax = loudnessMax;
	}
	
	public float getLoudnessEnd() {
		return loudnessEnd;
	}
	
	public void setLoudnessEnd(float loudnessEnd) {
		this.loudnessEnd = loudnessEnd;
	}
	
	public List<Float> getPitches() {
		return pitches;
	}
	
	public void setPitches(List<Float> pitches) {
		this.pitches = pitches;
	}
	
	public List<Float> getTimbre() {
		return timbre;
	}
	
	public void setTimbre(List<Float> timbre) {
		this.timbre = timbre;
	}

}
