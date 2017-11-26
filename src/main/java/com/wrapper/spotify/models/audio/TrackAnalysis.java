package com.wrapper.spotify.models.audio;

@SuppressWarnings("javadoc")
public class TrackAnalysis extends Section {
	
	private int numSamples;
	private String sampleMd5;
	
	private int offsetSeconds;
	private int windowSeconds;
	
	private int analysisSampleRate;
	private short analysisChannel;
	
	private float endOfFadeIn;
	private float startOfFadeOut;
	
	private String codeString;
	private float codeVersion;
	
	private String echoPrintString;
	private float echoPrintVersion;
	
	private String synchString;
	private float synchVersion;
	
	private String rythmString;
	private float rythmVersion;
	
	public int getNumSamples() {
		return numSamples;
	}
	
	public void setNumSamples(int numSamples) {
		this.numSamples = numSamples;
	}
	
	public String getSampleMd5() {
		return sampleMd5;
	}
	
	public void setSampleMd5(String sampleMd5) {
		this.sampleMd5 = sampleMd5;
	}
	
	public int getOffsetSeconds() {
		return offsetSeconds;
	}
	
	public void setOffsetSeconds(int offsetSeconds) {
		this.offsetSeconds = offsetSeconds;
	}
	
	public int getWindowSeconds() {
		return windowSeconds;
	}
	
	public void setWindowSeconds(int windowSeconds) {
		this.windowSeconds = windowSeconds;
	}
	
	public int getAnalysisSampleRate() {
		return analysisSampleRate;
	}
	
	public void setAnalysisSampleRate(int analysisSampleRate) {
		this.analysisSampleRate = analysisSampleRate;
	}
	
	public short getAnalysisChannel() {
		return analysisChannel;
	}
	
	public void setAnalysisChannel(short analysisChannel) {
		this.analysisChannel = analysisChannel;
	}
	
	public float getEndOfFadeIn() {
		return endOfFadeIn;
	}
	
	public void setEndOfFadeIn(float endOfFadeIn) {
		this.endOfFadeIn = endOfFadeIn;
	}
	
	public float getStartOfFadeOut() {
		return startOfFadeOut;
	}
	
	public void setStartOfFadeOut(float startOfFadeOut) {
		this.startOfFadeOut = startOfFadeOut;
	}
	
	public String getCodeString() {
		return codeString;
	}
	
	public void setCodeString(String codeString) {
		this.codeString = codeString;
	}
	
	public float getCodeVersion() {
		return codeVersion;
	}
	
	public void setCodeVersion(float codeVersion) {
		this.codeVersion = codeVersion;
	}
	
	public String getEchoPrintString() {
		return echoPrintString;
	}
	
	public void setEchoPrintString(String echoPrintString) {
		this.echoPrintString = echoPrintString;
	}
	
	public float getEchoPrintVersion() {
		return echoPrintVersion;
	}
	
	public void setEchoPrintVersion(float echoPrintVersion) {
		this.echoPrintVersion = echoPrintVersion;
	}
	
	public String getSynchString() {
		return synchString;
	}
	
	public void setSynchString(String synchString) {
		this.synchString = synchString;
	}
	
	public float getSynchVersion() {
		return synchVersion;
	}
	
	public void setSynchVersion(float synchVersion) {
		this.synchVersion = synchVersion;
	}
	
	public String getRythmString() {
		return rythmString;
	}
	
	public void setRythmString(String rythmString) {
		this.rythmString = rythmString;
	}
	
	public float getRythmVersion() {
		return rythmVersion;
	}
	
	public void setRythmVersion(float rythmVersion) {
		this.rythmVersion = rythmVersion;
	}

}
