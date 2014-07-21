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

package com.liferay.mobile.widgets.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.service.BaseService;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service interface for MobileWidgetsUser. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Jose M. Navarro
 * @see MobileWidgetsUserServiceUtil
 * @see com.liferay.mobile.widgets.service.base.MobileWidgetsUserServiceBaseImpl
 * @see com.liferay.mobile.widgets.service.impl.MobileWidgetsUserServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface MobileWidgetsUserService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MobileWidgetsUserServiceUtil} to access the mobile widgets user remote service. Add custom service methods to {@link com.liferay.mobile.widgets.service.impl.MobileWidgetsUserServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this interface directly. Always use
	* {@link com.liferay.mobile.widgets.service.MobileWidgetsUserServiceUtil}
	* to access the mobile widgets user remote service.
	*/
	public java.util.Map<java.lang.String, java.lang.Object> sendRememberPasswordEmailByEmailAddress(
		long companyId, java.lang.String emailAddress)
		throws java.lang.Exception;

	public java.util.Map<java.lang.String, java.lang.Object> sendRememberPasswordEmailByScreenName(
		long companyId, java.lang.String screenName) throws java.lang.Exception;

	public java.util.Map<java.lang.String, java.lang.Object> sendRememberPasswordEmailByUserId(
		long userId) throws java.lang.Exception;
}