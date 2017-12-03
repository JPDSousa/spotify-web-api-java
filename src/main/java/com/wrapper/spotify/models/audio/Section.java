package com.wrapper.spotify.models.audio;

@SuppressWarnings("javadoc")
public class Section extends Interval {
	
	private double loudness;
	
	private double tempo;
	private double tempoConfidence;
	
	private int key;
	private double keyConfidence;
	
	private int mode;
	private double modeConfidence;
	
	private int timeSignature;
	private double timeSignatureConfidence;
	
	public double getLoudness() {
		return loudness;
	}
	
	public void setLoudness(double loudness) {
		this.loudness = loudness;
	}
	
	public double getTempo() {
		return tempo;
	}
	
	public void setTempo(double tempo) {
		this.tempo = tempo;
	}
	
	public double getTempoConfidence() {
		return tempoConfidence;
	}
	
	public void setTempoConfidence(double tempoConfidence) {
		this.tempoConfidence = tempoConfidence;
	}
	
	public int getKey() {
		return key;
	}
	
	public void setKey(int key) {
		this.key = key;
	}
	
	public double getKeyConfidence() {
		return keyConfidence;
	}
	
	public void setKeyConfidence(double keyConfidence) {
		this.keyConfidence = keyConfidence;
	}
	
	public int getMode() {
		return mode;
	}
	
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public double getModeConfidence() {
		return modeConfidence;
	}
	
	public void setModeConfidence(double modeConfidence) {
		this.modeConfidence = modeConfidence;
	}
	
	public int getTimeSignature() {
		return timeSignature;
	}
	
	public void setTimeSignature(int timeSignature) {
		this.timeSignature = timeSignature;
	}
	
	public double getTimeSignatureConfidence() {
		return timeSignatureConfidence;
	}
	
	public void setTimeSignatureConfidence(double timeSignatureConfidence) {
		this.timeSignatureConfidence = timeSignatureConfidence;
	}
	
}
