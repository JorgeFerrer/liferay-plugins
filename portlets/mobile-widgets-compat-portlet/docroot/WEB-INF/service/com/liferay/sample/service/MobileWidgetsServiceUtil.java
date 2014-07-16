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

package com.liferay.sample.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for MobileWidgets. This utility wraps
 * {@link com.liferay.sample.service.impl.MobileWidgetsServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Jose M. Navarro
 * @see MobileWidgetsService
 * @see com.liferay.sample.service.base.MobileWidgetsServiceBaseImpl
 * @see com.liferay.sample.service.impl.MobileWidgetsServiceImpl
 * @generated
 */
public class MobileWidgetsServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.sample.service.impl.MobileWidgetsServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static java.util.Map<java.lang.String, java.lang.Object> resetPasswordByEmailAddress(
		long companyId, java.lang.String emailAddress) {
		return getService().resetPasswordByEmailAddress(companyId, emailAddress);
	}

	public static java.util.Map<java.lang.String, java.lang.Object> resetPasswordByScreenName(
		long companyId, java.lang.String screenName) {
		return getService().resetPasswordByScreenName(companyId, screenName);
	}

	public static java.util.Map<java.lang.String, java.lang.Object> resetPasswordByUserId(
		long userId) {
		return getService().resetPasswordByUserId(userId);
	}

	public static void clearService() {
		_service = null;
	}

	public static MobileWidgetsService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					MobileWidgetsService.class.getName());

			if (invokableService instanceof MobileWidgetsService) {
				_service = (MobileWidgetsService)invokableService;
			}
			else {
				_service = new MobileWidgetsServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(MobileWidgetsServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(MobileWidgetsService service) {
	}

	private static MobileWidgetsService _service;
}