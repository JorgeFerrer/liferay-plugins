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

import java.util.Map;

import javax.portlet.PortletPreferences;

import com.liferay.portal.NoSuchPreferencesException;
import com.liferay.portal.UserEmailAddressException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.PortalPreferences;
import com.liferay.portal.model.User;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.service.CompanyServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.service.persistence.PortalPreferencesUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.sample.service.base.MobileWidgetsServiceBaseImpl;

/**
 * The implementation of the mobile widgets remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.sample.service.MobileWidgetsService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Jose M. Navarro
 * @see com.liferay.sample.service.base.MobileWidgetsServiceBaseImpl
 * @see com.liferay.sample.service.MobileWidgetsServiceUtil
 */
public class MobileWidgetsServiceImpl extends MobileWidgetsServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.sample.service.MobileWidgetsServiceUtil} to access the mobile widgets remote service.
	 */
	
	@AccessControlled(guestAccessEnabled=true)
	public boolean resetPasswordByEmailAddress(long companyId, String emailAddress) {
		return false;
	}
	
	@AccessControlled(guestAccessEnabled=true)
	public boolean resetPasswordByScreenName(long companyId, String screenName) {
		try {
			try {
				User user = UserServiceUtil.getUserByScreenName(companyId, screenName);
				return true;
				//_sendPassword(user);

			} catch (UserEmailAddressException e) {
				e.printStackTrace();
				return false;
			} catch (PortalException e) {
				e.printStackTrace();
				return false;
			}
		} catch (SystemException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@AccessControlled(guestAccessEnabled=true)
	public boolean resetPasswordByUserId(long userId) {
		return false;
	}
	
	/*
	private Map<String, String> _sendPassword(User user) {
		try {
			try {
				Company company = CompanyServiceUtil.getCompanyById(user.getCompanyId());
				
				if (!company.isSendPassword() && !company.isSendPasswordResetLink()) {
					return null;
				}
				
				PortalPreferences portalPreferences = _getPortalPreferences(user.getCompanyId());
				
				portalPreferences.get
				
				String emailFromName = portalPreferences.getValue(
						"emailFromName", null);
					String emailFromAddress = portalPreferences.getValue(
						"emailFromAddress", null);
					String emailToAddress = user.getEmailAddress();

					String emailParam = "emailPasswordSent";

					if (company.isSendPasswordResetLink()) {
						emailParam = "emailPasswordReset";
					}

				
				portalPreferences.get
				

			} catch (UserEmailAddressException e) {
				e.printStackTrace();
				return null;
			} catch (PortalException e) {
				e.printStackTrace();
				return null;
			}
		} catch (SystemException e) {
			e.printStackTrace();
			return null;
		}

		


		
	}
	
	private PortalPreferences _getPortalPreferences(long companyId) 
			throws NoSuchPreferencesException, SystemException {
		
		long ownerId = companyId;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;
		
		return PortalPreferencesUtil.findByO_O(ownerId, ownerType);
	}
	
	*/
	
}