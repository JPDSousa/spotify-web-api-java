package com.wrapper.spotify.models;

import com.wrapper.spotify.json.AbstractJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class SnapshotResultJsonFactory extends AbstractJsonFactory<SnapshotResult> {

	@Override
	public SnapshotResult fromJson(JSONObject jsonObject) {
		final SnapshotResult result = new SnapshotResult();
		result.setSnapshotId(jsonObject.getString("snapshot_id"));
		return result;
	}

}
