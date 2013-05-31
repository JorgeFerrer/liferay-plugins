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

package com.liferay.socialnetworking.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.AuditedModel;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the MeetupsRegistration service. Represents a row in the &quot;SN_MeetupsRegistration&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.socialnetworking.model.impl.MeetupsRegistrationModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.socialnetworking.model.impl.MeetupsRegistrationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsRegistration
 * @see com.liferay.socialnetworking.model.impl.MeetupsRegistrationImpl
 * @see com.liferay.socialnetworking.model.impl.MeetupsRegistrationModelImpl
 * @generated
 */
public interface MeetupsRegistrationModel extends AuditedModel,
	BaseModel<MeetupsRegistration> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a meetups registration model instance should use the {@link MeetupsRegistration} interface instead.
	 */

	/**
	 * Returns the primary key of this meetups registration.
	 *
	 * @return the primary key of this meetups registration
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this meetups registration.
	 *
	 * @param primaryKey the primary key of this meetups registration
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the meetups registration ID of this meetups registration.
	 *
	 * @return the meetups registration ID of this meetups registration
	 */
	public long getMeetupsRegistrationId();

	/**
	 * Sets the meetups registration ID of this meetups registration.
	 *
	 * @param meetupsRegistrationId the meetups registration ID of this meetups registration
	 */
	public void setMeetupsRegistrationId(long meetupsRegistrationId);

	/**
	 * Returns the company ID of this meetups registration.
	 *
	 * @return the company ID of this meetups registration
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this meetups registration.
	 *
	 * @param companyId the company ID of this meetups registration
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this meetups registration.
	 *
	 * @return the user ID of this meetups registration
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this meetups registration.
	 *
	 * @param userId the user ID of this meetups registration
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this meetups registration.
	 *
	 * @return the user uuid of this meetups registration
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this meetups registration.
	 *
	 * @param userUuid the user uuid of this meetups registration
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this meetups registration.
	 *
	 * @return the user name of this meetups registration
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this meetups registration.
	 *
	 * @param userName the user name of this meetups registration
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this meetups registration.
	 *
	 * @return the create date of this meetups registration
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this meetups registration.
	 *
	 * @param createDate the create date of this meetups registration
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this meetups registration.
	 *
	 * @return the modified date of this meetups registration
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this meetups registration.
	 *
	 * @param modifiedDate the modified date of this meetups registration
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the meetups entry ID of this meetups registration.
	 *
	 * @return the meetups entry ID of this meetups registration
	 */
	public long getMeetupsEntryId();

	/**
	 * Sets the meetups entry ID of this meetups registration.
	 *
	 * @param meetupsEntryId the meetups entry ID of this meetups registration
	 */
	public void setMeetupsEntryId(long meetupsEntryId);

	/**
	 * Returns the status of this meetups registration.
	 *
	 * @return the status of this meetups registration
	 */
	public int getStatus();

	/**
	 * Sets the status of this meetups registration.
	 *
	 * @param status the status of this meetups registration
	 */
	public void setStatus(int status);

	/**
	 * Returns the comments of this meetups registration.
	 *
	 * @return the comments of this meetups registration
	 */
	@AutoEscape
	public String getComments();

	/**
	 * Sets the comments of this meetups registration.
	 *
	 * @param comments the comments of this meetups registration
	 */
	public void setComments(String comments);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(MeetupsRegistration meetupsRegistration);

	@Override
	public int hashCode();

	@Override
	public CacheModel<MeetupsRegistration> toCacheModel();

	@Override
	public MeetupsRegistration toEscapedModel();

	@Override
	public MeetupsRegistration toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}