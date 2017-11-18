package com.wrapper.spotify.models.playlist;

import com.wrapper.spotify.models.image.ImageHolderJsonFactory;
import com.wrapper.spotify.models.user.UserJsonFactory;

import net.sf.json.JSONObject;

abstract class AbstractPlaylistJsonFactory<T extends AbstractPlaylist> extends ImageHolderJsonFactory<T> {

	private static final String PUBLIC = "public";
	private static final String OWNER = "owner";
	private static final String NAME = "name";
	private static final String COLLABORATIVE = "collaborative";
	
	protected final UserJsonFactory userFactory;
	
	protected AbstractPlaylistJsonFactory() {
		userFactory = new UserJsonFactory();
	}
	
	@Override
	protected void fillObject(T baseObject, JSONObject jsonObject) {
		super.fillObject(baseObject, jsonObject);
		baseObject.setCollaborative(jsonObject.getBoolean(COLLABORATIVE));
		baseObject.setName(jsonObject.getString(NAME));
		baseObject.setOwner(userFactory.fromJson(jsonObject.getJSONObject(OWNER)));
		if (existsAndNotNull(PUBLIC, jsonObject)) {
			baseObject.setPublicAccess(jsonObject.getBoolean(PUBLIC));
		}
	}
	
	

}
