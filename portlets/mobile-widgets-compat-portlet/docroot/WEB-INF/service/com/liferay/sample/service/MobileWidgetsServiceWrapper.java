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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MobileWidgetsService}.
 *
 * @author Jose M. Navarro
 * @see MobileWidgetsService
 * @generated
 */
public class MobileWidgetsServiceWrapper implements MobileWidgetsService,
	ServiceWrapper<MobileWidgetsService> {
	public MobileWidgetsServiceWrapper(
		MobileWidgetsService mobileWidgetsService) {
		_mobileWidgetsService = mobileWidgetsService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _mobileWidgetsService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_mobileWidgetsService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _mobileWidgetsService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> resetPasswordByEmailAddress(
		long companyId, java.lang.String emailAddress) {
		return _mobileWidgetsService.resetPasswordByEmailAddress(companyId,
			emailAddress);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> resetPasswordByScreenName(
		long companyId, java.lang.String screenName) {
		return _mobileWidgetsService.resetPasswordByScreenName(companyId,
			screenName);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> resetPasswordByUserId(
		long userId) {
		return _mobileWidgetsService.resetPasswordByUserId(userId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public MobileWidgetsService getWrappedMobileWidgetsService() {
		return _mobileWidgetsService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedMobileWidgetsService(
		MobileWidgetsService mobileWidgetsService) {
		_mobileWidgetsService = mobileWidgetsService;
	}

	@Override
	public MobileWidgetsService getWrappedService() {
		return _mobileWidgetsService;
	}

	@Override
	public void setWrappedService(MobileWidgetsService mobileWidgetsService) {
		_mobileWidgetsService = mobileWidgetsService;
	}

	private MobileWidgetsService _mobileWidgetsService;
}