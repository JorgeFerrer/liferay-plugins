/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.settings.impl.archive;

import com.liferay.portal.settings.Settings;
import com.liferay.portal.settings.UnmodifiableSettings;

import java.util.Set;

/**
 * @author Iván Zaera
 */
public class UnmodifiableSettingsImpl implements UnmodifiableSettings {

	public UnmodifiableSettingsImpl(Settings settings) {
		_settings = settings;
	}

	public Settings getDefaultSettings() {
		return _settings.getDefaultSettings();
	}

	public Set<String> getNames() {
		return _settings.getNames();
	}

	public String getValue(String key, String defaultValue) {
		return _settings.getValue(key, defaultValue);
	}

	public String[] getValues(String key, String[] defaultValue) {
		return _settings.getValues(key, defaultValue);
	}

	private Settings _settings;

}