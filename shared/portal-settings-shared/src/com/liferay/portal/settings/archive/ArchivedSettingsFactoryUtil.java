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

package com.liferay.portal.settings.archive;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Iván Zaera
 */
public class ArchivedSettingsFactoryUtil {

	public static List<ArchivedSettings> getArchivedSettingsList(
			long groupId, String portletId)
		throws PortalException, SystemException {
		
		return getArchivedSettingsFactory().getArchivedSettingsList(
			groupId, portletId);
	}

	public static ArchivedSettingsFactory getArchivedSettingsFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			ArchivedSettingsFactoryUtil.class);

		return _archivedSettingsFactory;
	}

	public static ArchivedSettings getArchivedSettings(
			long groupId, String portletId, String name)
		throws PortalException, SystemException {
		
		return getArchivedSettingsFactory().getArchivedSettings(
			groupId, portletId, name);
	}

	public void setArchivedSettingsFactory(
		ArchivedSettingsFactory archivedSettingsFactory) {
		
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_archivedSettingsFactory = archivedSettingsFactory;
	}

	private static ArchivedSettingsFactory _archivedSettingsFactory;

}