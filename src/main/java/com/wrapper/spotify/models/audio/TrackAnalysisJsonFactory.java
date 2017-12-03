package com.wrapper.spotify.models.audio;

import com.wrapper.spotify.json.AbstractJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class TrackAnalysisJsonFactory extends AbstractJsonFactory<TrackAnalysis> {

	private SectionJsonFactory sectionFactory;
	
	public TrackAnalysisJsonFactory() {
		sectionFactory = new SectionJsonFactory();
	}
	
	@Override
	public TrackAnalysis fromJson(JSONObject jsonObject) {
		final TrackAnalysis trackAnalysis = new TrackAnalysis();
		sectionFactory.fill(trackAnalysis, jsonObject);
		trackAnalysis.setAnalysisChannels(jsonObject.getInt("analysis_channels"));
		trackAnalysis.setAnalysisSampleRate(jsonObject.getInt("analysis_sample_rate"));
		trackAnalysis.setCodeString(jsonObject.getString("codestring"));
		trackAnalysis.setCodeVersion(jsonObject.getDouble("code_version"));
		trackAnalysis.setEchoPrintString(jsonObject.getString("echoprintstring"));
		trackAnalysis.setEchoPrintVersion(jsonObject.getDouble("echoprint_version"));
		trackAnalysis.setEndOfFadeIn(jsonObject.getDouble("end_of_fade_in"));
		trackAnalysis.setNumSamples(jsonObject.getInt("num_samples"));
		trackAnalysis.setOffsetSeconds(jsonObject.getInt("offset_seconds"));
		trackAnalysis.setRythmString(jsonObject.getString("rhythmstring"));
		trackAnalysis.setRythmVersion(jsonObject.getDouble("rhythm_version"));
		trackAnalysis.setSampleMd5(jsonObject.getString("sample_md5"));
		trackAnalysis.setStartOfFadeOut(jsonObject.getDouble("start_of_fade_out"));
		trackAnalysis.setSynchString(jsonObject.getString("synchstring"));
		trackAnalysis.setSynchVersion(jsonObject.getDouble("synch_version"));
		trackAnalysis.setWindowSeconds(jsonObject.getInt("window_seconds"));
		return trackAnalysis;
	}

}
