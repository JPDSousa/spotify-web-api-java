package com.wrapper.spotify.models.audio;

import com.wrapper.spotify.json.AbstractJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class MetadataJsonFactory extends AbstractJsonFactory<Metadata> {

	private static final String ANALYSIS_TIME = "analysis_time";
	private static final String ANALYZER_VERSION = "analyzer_version";
	private static final String DETAILED_STATUS = "detailed_status";
	private static final String INPUT_PROCESS = "input_process";
	private static final String PLATFORM = "platform";
	private static final String STATUS_CODE = "status_code";
	private static final String TIMESTAMP = "timestamp";

	@Override
	public Metadata fromJson(JSONObject jsonObject) {
		final Metadata metadata = new Metadata();
		metadata.setAnalysisTime(jsonObject.getDouble(ANALYSIS_TIME));
		metadata.setAnalyzerVersion(jsonObject.getString(ANALYZER_VERSION));
		metadata.setDetailedStatus(jsonObject.getString(DETAILED_STATUS));
		metadata.setInputProcess(jsonObject.getString(INPUT_PROCESS));
		metadata.setPlatform(jsonObject.getString(PLATFORM));
		metadata.setStatusCode(jsonObject.getInt(STATUS_CODE));
		metadata.setTimestamp(jsonObject.getLong(TIMESTAMP));
		return metadata;
	}

}
