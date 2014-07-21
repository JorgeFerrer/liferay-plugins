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

package com.liferay.mobile.widgets.service.impl;

import com.liferay.mobile.widgets.service.base.MobileWidgetsUserServiceBaseImpl;
import com.liferay.portal.security.ac.AccessControlled;

import java.util.Map;

/**
 * The implementation of the mobile widgets user remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.mobile.widgets.service.MobileWidgetsUserService}
 * interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author Jose M. Navarro
 * @see com.liferay.mobile.widgets.service.base.MobileWidgetsUserServiceBaseImpl
 * @see com.liferay.mobile.widgets.service.MobileWidgetsUserServiceUtil
 */
public class MobileWidgetsUserServiceImpl
	extends MobileWidgetsUserServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use
	 * {@link com.liferay.mobile.widgets.service.MobileWidgetsUserServiceUtil}
	 * to access the mobile widgets user remote service.
	 */
	@AccessControlled(guestAccessEnabled = true)
	public Map<String, Object> resetPasswordByEmailAddress(
			long companyId, String emailAddress)
		throws Exception {

		return null;
	}

	@AccessControlled(guestAccessEnabled = true)
	public Map<String, Object> resetPasswordByScreenName(
			long companyId, String screenName)
		throws Exception {

		return null;
	}

	@AccessControlled(guestAccessEnabled = true)
	public Map<String, Object> resetPasswordByUserId(long userId)
		throws Exception {

		return null;
	}

}