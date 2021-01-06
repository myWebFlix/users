package com.webflix.users.services.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("rest-config")
public class RestConfig {

	@ConfigValue(watch = true)
	private boolean disableLogin;

	public boolean isDisableLogin() {
		return disableLogin;
	}

	public void setDisableLogin(boolean disableLogin) {
		this.disableLogin = disableLogin;
	}
}
