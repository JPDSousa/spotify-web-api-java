package com.wrapper.spotify.models.audio;

@SuppressWarnings("javadoc")
public class TrackAnalysis extends Section {
	
	private int numSamples;
	private String sampleMd5;
	
	private int offsetSeconds;
	private int windowSeconds;
	
	private int analysisSampleRate;
	private int analysisChannels;
	
	private double endOfFadeIn;
	private double startOfFadeOut;
	
	private String codeString;
	private double codeVersion;
	
	private String echoPrintString;
	private double echoPrintVersion;
	
	private String synchString;
	private double synchVersion;
	
	private String rythmString;
	private double rythmVersion;
	
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
	
	public int getAnalysisChannels() {
		return analysisChannels;
	}
	
	public void setAnalysisChannels(int analysisChannels) {
		this.analysisChannels = analysisChannels;
	}
	
	public double getEndOfFadeIn() {
		return endOfFadeIn;
	}
	
	public void setEndOfFadeIn(double endOfFadeIn) {
		this.endOfFadeIn = endOfFadeIn;
	}
	
	public double getStartOfFadeOut() {
		return startOfFadeOut;
	}
	
	public void setStartOfFadeOut(double startOfFadeOut) {
		this.startOfFadeOut = startOfFadeOut;
	}
	
	public String getCodeString() {
		return codeString;
	}
	
	public void setCodeString(String codeString) {
		this.codeString = codeString;
	}
	
	public double getCodeVersion() {
		return codeVersion;
	}
	
	public void setCodeVersion(double codeVersion) {
		this.codeVersion = codeVersion;
	}
	
	public String getEchoPrintString() {
		return echoPrintString;
	}
	
	public void setEchoPrintString(String echoPrintString) {
		this.echoPrintString = echoPrintString;
	}
	
	public double getEchoPrintVersion() {
		return echoPrintVersion;
	}
	
	public void setEchoPrintVersion(double echoPrintVersion) {
		this.echoPrintVersion = echoPrintVersion;
	}
	
	public String getSynchString() {
		return synchString;
	}
	
	public void setSynchString(String synchString) {
		this.synchString = synchString;
	}
	
	public double getSynchVersion() {
		return synchVersion;
	}
	
	public void setSynchVersion(double synchVersion) {
		this.synchVersion = synchVersion;
	}
	
	public String getRythmString() {
		return rythmString;
	}
	
	public void setRythmString(String rythmString) {
		this.rythmString = rythmString;
	}
	
	public double getRythmVersion() {
		return rythmVersion;
	}
	
	public void setRythmVersion(double rythmVersion) {
		this.rythmVersion = rythmVersion;
	}

}
