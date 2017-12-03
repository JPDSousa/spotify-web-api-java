package com.wrapper.spotify.models.audio;

import java.util.List;

@SuppressWarnings("javadoc")
public class AudioAnalysis {

	private List<Interval> bars;
	private List<Interval> beats;
	private Metadata meta;
	private List<Section> sections;
	private List<Segment> segments;
	private List<Interval> tatums;
	private TrackAnalysis track;
	
	public List<Interval> getBars() {
		return bars;
	}
	
	public void setBars(List<Interval> bars) {
		this.bars = bars;
	}
	
	public List<Interval> getBeats() {
		return beats;
	}
	
	public void setBeats(List<Interval> beats) {
		this.beats = beats;
	}
	
	public Metadata getMeta() {
		return meta;
	}
	
	public void setMeta(Metadata meta) {
		this.meta = meta;
	}
	
	public List<Section> getSections() {
		return sections;
	}
	
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	
	public List<Segment> getSegments() {
		return segments;
	}
	
	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}
	
	public List<Interval> getTatums() {
		return tatums;
	}
	
	public void setTatums(List<Interval> tatums) {
		this.tatums = tatums;
	}
	
	public TrackAnalysis getTrack() {
		return track;
	}
	
	public void setTrack(TrackAnalysis track) {
		this.track = track;
	}
	
}
