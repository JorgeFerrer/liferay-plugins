/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.settings;

import java.io.IOException;

import javax.portlet.ValidatorException;

/**
 * @author Iván Zaera
 */
public class BaseServiceSettings implements Settings {

	public BaseServiceSettings(Settings settings, FallbackKeys fallbackKeys) {
		_fallbackSettings = new FallbackSettings(settings, fallbackKeys);

		localizedSettings = new LocalizedSettings(_fallbackSettings);
		typedSettings = new TypedSettings(_fallbackSettings);
	}

	@Override
	public String getValue(String key, String defaultValue) {
		return _fallbackSettings.getValue(key, defaultValue);
	}

	@Override
	public String[] getValues(String key, String[] defaultValue) {
		return _fallbackSettings.getValues(key, defaultValue);
	}

	@Override
	public void reset(String key) {
		_fallbackSettings.reset(key);
	}

	@Override
	public Settings setValue(String key, String value) {
		return _fallbackSettings.setValue(key, value);
	}

	@Override
	public Settings setValues(String key, String[] values) {
		return _fallbackSettings.setValues(key, values);
	}

	@Override
	public void store() throws IOException, ValidatorException {
		_fallbackSettings.store();
	}

	protected LocalizedSettings localizedSettings;
	protected TypedSettings typedSettings;

	private FallbackSettings _fallbackSettings;

}