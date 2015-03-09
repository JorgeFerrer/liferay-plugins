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

package com.liferay.opensocial.service.permission;

import com.liferay.opensocial.model.Gadget;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.BaseResourcePermission;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Michael Young
 */
public class GadgetPermission extends BaseResourcePermission {

	public static final String RESOURCE_NAME = "com.liferay.opensocial";

	public static void check(
			PermissionChecker permissionChecker, long groupId, long gadgetId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, gadgetId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, long classPK,
		String actionId) {

		return contains(
			permissionChecker, groupId, Gadget.class.getName(), null, classPK,
			actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long classPK, String actionId) {

		return contains(
			permissionChecker, RESOURCE_NAME, null, classPK, actionId);
	}

	@Override
	public Boolean checkResource(
		PermissionChecker permissionChecker, long classPK, String actionId) {

		return contains(permissionChecker, classPK, actionId);
	}

}