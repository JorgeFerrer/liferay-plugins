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

package com.liferay.sample.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletPreferences;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.PortalPreferencesLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.sample.service.base.MobileWidgetsServiceBaseImpl;

/**
 * The implementation of the mobile widgets remote service. <p> All custom
 * service methods should be put in this class. Whenever methods are added,
 * rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.sample.service.MobileWidgetsService} interface. <p> This
 * is a remote service. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely. </p>
 *
 * @author Jose M. Navarro
 * @see com.liferay.sample.service.base.MobileWidgetsServiceBaseImpl
 * @see com.liferay.sample.service.MobileWidgetsServiceUtil
 */
public class MobileWidgetsServiceImpl extends MobileWidgetsServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link com.liferay.sample.service.MobileWidgetsServiceUtil} to access the
	 * mobile widgets remote service.
	 */

	static enum SentEmailForgotPasswordType {
		SENT_PASSWORD, SENT_RESET_LINK, ERROR_WRONG_CONFIGURATION;
	}

	@AccessControlled(guestAccessEnabled = true)
	public Map<String, Object> resetPasswordByEmailAddress(
		long companyId, String emailAddress)
		throws Exception {

		User user =
			UserLocalServiceUtil.getUserByEmailAddress(companyId, emailAddress);

		SentEmailForgotPasswordType sendPasswordResult = _sendPassword(user);

		return _getResultMap(user, sendPasswordResult);
	}

	@AccessControlled(guestAccessEnabled = true)
	public Map<String, Object> resetPasswordByScreenName(
		long companyId, String screenName)
		throws Exception {

		User user =
			UserLocalServiceUtil.getUserByScreenName(companyId, screenName);

		SentEmailForgotPasswordType sendPasswordResult = _sendPassword(user);

		return _getResultMap(user, sendPasswordResult);
	}

	@AccessControlled(guestAccessEnabled = true)
	public Map<String, Object> resetPasswordByUserId(long userId)
		throws Exception {

		User user = UserLocalServiceUtil.getUser(userId);

		SentEmailForgotPasswordType sendPasswordResult = _sendPassword(user);

		return _getResultMap(user, sendPasswordResult);
	}

	private Map<String, Object> _getResultMap(
		User user, SentEmailForgotPasswordType sendPasswordResult) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String resultKey;

		if (sendPasswordResult == SentEmailForgotPasswordType.SENT_PASSWORD ||
			sendPasswordResult == SentEmailForgotPasswordType.SENT_RESET_LINK) {

			resultKey = "success";
			resultMap.put("email", user.getEmailAddress());
		}
		else {
			resultKey = "error";
		}

		resultMap.put(resultKey, sendPasswordResult.toString().toLowerCase());

		return resultMap;
	}

	private Map<String, Object> _resultMapForException(Exception exception) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		_fillResultMapForException(resultMap, exception);

		return resultMap;
	}

	private void _fillResultMapForException(
		Map<String, Object> resultMap, Exception exception) {

		resultMap.put("exception.className", exception.getClass().getName());
		resultMap.put("exception.message", exception.getMessage());
		resultMap.put(
			"exception.localizedMessage", exception.getLocalizedMessage());
	}

	private SentEmailForgotPasswordType _sendPassword(User user)
		throws PortalException, SystemException {

		Company company =
			CompanyLocalServiceUtil.getCompanyById(user.getCompanyId());

		if (!company.isSendPassword() && !company.isSendPasswordResetLink()) {
			return SentEmailForgotPasswordType.ERROR_WRONG_CONFIGURATION;
		}

		PortletPreferences portalPreferences =
			_getPortalPreferences(user.getCompanyId());

		String emailFromName =
			portalPreferences.getValue("emailFromName", null);
		String emailFromAddress =
			portalPreferences.getValue("emailFromAddress", null);

		String emailParam = "emailPasswordSent";
		SentEmailForgotPasswordType emailType =
			SentEmailForgotPasswordType.SENT_PASSWORD;

		if (company.isSendPasswordResetLink()) {
			emailParam = "emailPasswordReset";
			emailType = SentEmailForgotPasswordType.SENT_RESET_LINK;
		}

		String languageId = user.getLanguageId();

		String subject =
			portalPreferences.getValue(
				emailParam + "Subject_" + languageId, null);
		String body =
			portalPreferences.getValue(emailParam + "Body_" + languageId, null);

		ServiceContext serviceContext = _getServiceContext();

		UserLocalServiceUtil.sendPassword(
			user.getCompanyId(), user.getEmailAddress(), emailFromName,
			emailFromAddress, subject, body, serviceContext);

		return emailType;
	}

	private ServiceContext _getServiceContext() {

		ServiceContext serviceContext = new ServiceContext();

		// TODO what values set here?
		serviceContext.setPortalURL("portal_url");
		serviceContext.setPathMain("path_main");
		serviceContext.setPlid(999);
		serviceContext.setRemoteAddr("remote_addr");
		serviceContext.setRemoteHost("remote_host");

		return serviceContext;
	}

	private PortletPreferences _getPortalPreferences(long companyId)
		throws SystemException {

		long ownerId = companyId;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;

		return PortalPreferencesLocalServiceUtil.getPreferences(
			ownerId, ownerType);
	}

}
