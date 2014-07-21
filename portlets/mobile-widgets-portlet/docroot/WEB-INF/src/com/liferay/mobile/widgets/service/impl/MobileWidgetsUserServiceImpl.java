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
import com.liferay.mobile.widgets.service.exception.RememberPasswordException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortletKeys;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletPreferences;

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

		User user = userLocalService.getUserByEmailAddress(
			companyId, emailAddress);

		RememberPasswordResult sendPasswordResult = sendRememberEmail(user);

		return getResultMap(user, sendPasswordResult);
	}

	@AccessControlled(guestAccessEnabled = true)
	public Map<String, Object> resetPasswordByScreenName(
			long companyId, String screenName)
		throws Exception {

		User user = userLocalService.getUserByScreenName(companyId, screenName);

		RememberPasswordResult sendPasswordResult = sendRememberEmail(user);

		return getResultMap(user, sendPasswordResult);
	}

	@AccessControlled(guestAccessEnabled = true)
	public Map<String, Object> resetPasswordByUserId(long userId)
		throws Exception {

		User user = userLocalService.getUserById(userId);

		RememberPasswordResult sendPasswordResult = sendRememberEmail(user);

		return getResultMap(user, sendPasswordResult);
	}

	protected PortletPreferences getPortalPreferences(long companyId)
		throws SystemException {

		long ownerId = companyId;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;

		return portalPreferencesLocalService.getPreferences(ownerId, ownerType);
	}

	protected Map<String, Object> getResultMap(
		User user, RememberPasswordResult sendPasswordResult) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("email", user.getEmailAddress());
		resultMap.put("success", StringUtil.toLowerCase(
			sendPasswordResult.toString()));

		return resultMap;
	}

	protected ServiceContext getServiceContext() {

		ServiceContext serviceContext = new ServiceContext();

		// TODO what values set here?

		serviceContext.setPortalURL("portal_url");
		serviceContext.setPathMain("path_main");
		serviceContext.setPlid(999);
		serviceContext.setRemoteAddr("remote_addr");
		serviceContext.setRemoteHost("remote_host");

		return serviceContext;
	}

	protected RememberPasswordResult sendRememberEmail(User user)
		throws PortalException, SystemException {

		Company company = companyLocalService.getCompanyById(
			user.getCompanyId());

		if (!company.isSendPassword() && !company.isSendPasswordResetLink()) {
			throw new RememberPasswordException();
		}

		PortletPreferences portalPreferences = getPortalPreferences(
			user.getCompanyId());

		String emailFromName = portalPreferences.getValue(
			"emailFromName", null);
		String emailFromAddress = portalPreferences.getValue(
			"emailFromAddress", null);

		String emailParam = "emailPasswordSent";
		RememberPasswordResult emailType = RememberPasswordResult.SENT_PASSWORD;

		if (company.isSendPasswordResetLink()) {
			emailParam = "emailPasswordReset";
			emailType = RememberPasswordResult.SENT_RESET_LINK;
		}

		String languageId = user.getLanguageId();

		String subject =
			portalPreferences.getValue(
				emailParam + "Subject_" + languageId, null);
		String body = portalPreferences.getValue(
			emailParam + "Body_" + languageId, null);

		ServiceContext serviceContext = getServiceContext();

		userLocalService.sendPassword(
			user.getCompanyId(), user.getEmailAddress(), emailFromName,
			emailFromAddress, subject, body, serviceContext);

		return emailType;
	}

	protected enum RememberPasswordResult {SENT_PASSWORD, SENT_RESET_LINK}

}