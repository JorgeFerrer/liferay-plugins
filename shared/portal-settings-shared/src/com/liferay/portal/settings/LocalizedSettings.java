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

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Locale;

import javax.portlet.ValidatorException;

/**
 * @author Iván Zaera
 */
public class LocalizedSettings implements Settings {

	public LocalizedSettings(Settings settings) {
		this(
			settings, LocaleUtil.getSiteDefault(),
			LanguageUtil.getAvailableLocales());
	}

	public LocalizedSettings(
		Settings settings, Locale defaultLocale, Locale... availableLocales) {

		_settings = settings;
		_defaultLocale = defaultLocale;
		_availableLocales = availableLocales;
	}

	public LocalizedValue getLocalizedValue(String key) {
		LocalizedValue localizedValue = new LocalizedValue(
			key, _defaultLocale, _availableLocales);

		for (Locale locale : _availableLocales) {
			String localizedPreference = LocalizationUtil.getLocalizedName(
				key, LocaleUtil.toLanguageId(locale));

			localizedValue.put(locale, getValue(localizedPreference, null));
		}

		String defaultValue = localizedValue.get(_defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return localizedValue;
		}

		localizedValue.put(_defaultLocale, getValue(key, null));

		return localizedValue;
	}

	@Override
	public String getValue(String key, String defaultValue) {
		return _settings.getValue(key, defaultValue);
	}

	@Override
	public String[] getValues(String key, String[] defaultValue) {
		return _settings.getValues(key, defaultValue);
	}

	@Override
	public void reset(String key) {
		_settings.reset(key);
	}

	@Override
	public Settings setValue(String key, String value) {
		return _settings.setValue(key, value);
	}

	@Override
	public Settings setValues(String key, String[] values) {
		return _settings.setValues(key, values);
	}

	@Override
	public void store() throws IOException, ValidatorException {
		_settings.store();
	}

	private Locale[] _availableLocales;
	private Locale _defaultLocale;
	private Settings _settings;

}