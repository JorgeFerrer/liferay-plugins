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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.PortletItem;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesServiceUtil;
import com.liferay.portal.settings.PortletPreferencesSettings;
import com.liferay.portal.settings.Settings;
import com.liferay.portal.settings.UnmodifiableSettings;
import com.liferay.portal.settings.archive.ArchivedSettings;
import com.liferay.portal.util.PortletKeys;

import java.io.IOException;

import java.util.Date;

import javax.portlet.PortletPreferences;
import javax.portlet.ValidatorException;

/**
 * @author Iván Zaera
 */
public class ArchivedSettingsImpl implements ArchivedSettings {

	public ArchivedSettingsImpl(PortletItem portletItem) {
		_portletItem = portletItem;
	}

	public void delete() throws IOException {
		try {
			PortletPreferencesServiceUtil.deleteArchivedPreferences(
				_portletItem.getPortletItemId());
		}
		catch (PortalException pe) {
			throw new IOException("Unable to delete archived settings", pe);
		}
		catch (SystemException se) {
			throw new IOException("Unable to delete archived settings", se);
		}
	}

	public Date getModifiedDate() {
		return _portletItem.getModifiedDate();
	}

	public String getName() {
		return _portletItem.getName();
	}

	public UnmodifiableSettings getSettings() throws IOException {
		if (_unmodifiableSettings == null) {
			loadSettings();
		}

		return _unmodifiableSettings;
	}

	public String getUserName() {
		return _portletItem.getUserName();
	}

	@Override
	public void restore(Settings targetSettings)
		throws IOException, ValidatorException {

		loadSettings();

		copySettings(_settings, targetSettings);
	}

	@Override
	public void update(Settings sourceSettings)
		throws IOException, ValidatorException {

		loadSettings();

		copySettings(sourceSettings, _settings);
	}

	private void copySettings(Settings sourceSettings, Settings targetSettings)
		throws IOException, ValidatorException {

		for (String name : targetSettings.getNames()) {
			targetSettings.reset(name);
		}

		for (String name : sourceSettings.getNames()) {
			String[] values = sourceSettings.getValues(
				name, StringPool.EMPTY_ARRAY);

			if (values.length == 1) {
				targetSettings.setValue(name, values[0]);
			}
			else {
				targetSettings.setValues(name, values);
			}
		}

		targetSettings.store();
	}

	private void loadSettings() throws IOException {
		PortletPreferences portletPreferences = null;

		try {
			long ownerId = _portletItem.getPortletItemId();
			int ownerType = PortletKeys.PREFS_OWNER_TYPE_ARCHIVED;
			long plid = 0;
			String portletId = _portletItem.getPortletId();

			portletPreferences =
				PortletPreferencesLocalServiceUtil.getPreferences(
					_portletItem.getCompanyId(), ownerId, ownerType, plid,
					PortletConstants.getRootPortletId(portletId));
		}
		catch (SystemException se) {
			throw new IOException("Unable to load named settings", se);
		}

		_settings = new PortletPreferencesSettings(portletPreferences);

		_unmodifiableSettings = new UnmodifiableSettingsImpl(_settings);
	}

	private PortletItem _portletItem;
	private Settings _settings;
	private UnmodifiableSettings _unmodifiableSettings;

}