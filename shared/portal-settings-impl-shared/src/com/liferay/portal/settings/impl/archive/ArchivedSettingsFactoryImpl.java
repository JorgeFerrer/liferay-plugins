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
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.model.PortletItem;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.PortletItemLocalServiceUtil;
import com.liferay.portal.settings.archive.ArchivedSettings;
import com.liferay.portal.settings.archive.ArchivedSettingsFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Iván Zaera
 */
@DoPrivileged
public class ArchivedSettingsFactoryImpl implements ArchivedSettingsFactory {

	@Override
	public ArchivedSettings getArchivedSettings(
			long groupId, String portletId, String name)
		throws PortalException, SystemException {

		List<ArchivedSettings> archivedSettingsList = getArchivedSettingsList(
			groupId, portletId);

		for (ArchivedSettings archivedSettings : archivedSettingsList) {
			if (archivedSettings.getName().equals(name)) {
				return archivedSettings;
			}
		}

		long userId = PrincipalThreadLocal.getUserId();

		PortletItem portletItem = PortletItemLocalServiceUtil.updatePortletItem(
			userId, groupId, name, portletId,
			PortletPreferences.class.getName());

		return new ArchivedSettingsImpl(portletItem);
	}

	@Override
	public List<ArchivedSettings> getArchivedSettingsList(
			long groupId, String portletId)
		throws PortalException, SystemException {

		List<ArchivedSettings> archivedSettingsList =
			new ArrayList<ArchivedSettings>();

		List<PortletItem> portletItems =
			PortletItemLocalServiceUtil.getPortletItems(
				groupId, portletId,
				com.liferay.portal.model.PortletPreferences.class.getName());

		for (PortletItem portletItem : portletItems) {
			archivedSettingsList.add(new ArchivedSettingsImpl(portletItem));
		}

		return archivedSettingsList;
	}

}