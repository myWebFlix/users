package com.webflix.users.services.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("app-config")
public class AppConfig {
	@ConfigValue("google-client-id")
	private String googleClientID;

	public String getGoogleClientID() {
		return googleClientID;
	}

	public void setGoogleClientID(String googleClientID) {
		this.googleClientID = googleClientID;
	}
}
