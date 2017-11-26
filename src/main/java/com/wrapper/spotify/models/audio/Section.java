package com.wrapper.spotify.models.audio;

@SuppressWarnings("javadoc")
public class Section extends Interval {
	
	private float loudness;
	
	private float tempo;
	private float tempoConfidence;
	
	private short key;
	private float keyConfidence;
	
	private short mode;
	private float modeConfidence;
	
	private short timeSignature;
	private float timeSignatureConfidence;
	
	public float getLoudness() {
		return loudness;
	}
	
	public void setLoudness(float loudness) {
		this.loudness = loudness;
	}
	
	public float getTempo() {
		return tempo;
	}
	
	public void setTempo(float tempo) {
		this.tempo = tempo;
	}
	
	public float getTempoConfidence() {
		return tempoConfidence;
	}
	
	public void setTempoConfidence(float tempoConfidence) {
		this.tempoConfidence = tempoConfidence;
	}
	
	public short getKey() {
		return key;
	}
	
	public void setKey(short key) {
		this.key = key;
	}
	
	public float getKeyConfidence() {
		return keyConfidence;
	}
	
	public void setKeyConfidence(float keyConfidence) {
		this.keyConfidence = keyConfidence;
	}
	
	public short getMode() {
		return mode;
	}
	
	public void setMode(short mode) {
		this.mode = mode;
	}
	
	public float getModeConfidence() {
		return modeConfidence;
	}
	
	public void setModeConfidence(float modeConfidence) {
		this.modeConfidence = modeConfidence;
	}
	
	public short getTimeSignature() {
		return timeSignature;
	}
	
	public void setTimeSignature(short timeSignature) {
		this.timeSignature = timeSignature;
	}
	
	public float getTimeSignatureConfidence() {
		return timeSignatureConfidence;
	}
	
	public void setTimeSignatureConfidence(float timeSignatureConfidence) {
		this.timeSignatureConfidence = timeSignatureConfidence;
	}
	
}
