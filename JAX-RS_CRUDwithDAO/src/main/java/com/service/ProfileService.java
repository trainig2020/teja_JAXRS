package com.service;

import java.util.List;

import com.dao.ProfileDAO;
import com.model.Profile;

public class ProfileService {
	private ProfileDAO profileDAO = new ProfileDAO();

	public List<Profile> getAllProfile() {

		return profileDAO.getAllProfile();

	}

	public Profile getProfile(String profileName) {
		return profileDAO.getProfile(profileName);
	}

	public Profile addProfile(Profile profile) {

		return profileDAO.insertProfile(profile);
	}

	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		return profileDAO.updateProfile(profile);
	}

	public void removeProfile(String profileName) {
		 profileDAO.deleteProfile(profileName);
	}

}
