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

package com.liferay.portal.settings;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Iván Zaera
 */
public class TypedSettingsTest extends PowerMockito {

	public TypedSettingsTest() {
		_settings = mock(Settings.class);

		when(
			_settings.getValue(Matchers.anyString(), Matchers.anyString())
		).thenAnswer( new Answer<String>() {
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				return (String)invocation.getArguments()[1];
			}
		});

		_typedSettings = new TypedSettings(_settings);
	}

	@Test
	public void testGetBooleanValue() {
		Assert.assertEquals(false, _typedSettings.getBooleanValue(_KEY, false));
		Assert.assertEquals(true, _typedSettings.getBooleanValue(_KEY, true));

		when(
			_settings.getValue(Matchers.eq(_KEY), Matchers.anyString())
		).thenReturn(
			String.valueOf(true)
		);

		Assert.assertEquals(true, _typedSettings.getBooleanValue(_KEY, false));
		Assert.assertEquals(true, _typedSettings.getBooleanValue(_KEY, true));
	}

	@Test
	public void testGetIntValue() {
		Assert.assertEquals(13, _typedSettings.getIntegerValue(_KEY, 13));

		when(
			_settings.getValue(Matchers.eq(_KEY), Matchers.anyString())
		).thenReturn(
			String.valueOf(27)
		);

		Assert.assertEquals(27, _typedSettings.getIntegerValue(_KEY, 13));
	}

	@Test
	public void testGetLongValue() {
		Assert.assertEquals(13L, _typedSettings.getLongValue(_KEY, 13L));

		when(
			_settings.getValue(Matchers.eq(_KEY), Matchers.anyString())
		).thenReturn(
			String.valueOf(27L)
		);

		Assert.assertEquals(27L, _typedSettings.getLongValue(_KEY, 13L));
	}

	@Test
	public void testSetBooleanValue() {
		_typedSettings.setBooleanValue(_KEY, true);

		Mockito.verify(_settings).setValue(_KEY, String.valueOf(true));

		_typedSettings.setBooleanValue(_KEY, false);

		Mockito.verify(_settings).setValue(_KEY, String.valueOf(false));
	}

	@Test
	public void testSetIntValue() {
		_typedSettings.setIntegerValue(_KEY, 17);

		Mockito.verify(_settings).setValue(_KEY, String.valueOf(17));
	}

	@Test
	public void testSetLongValue() {
		_typedSettings.setLongValue(_KEY, 17L);

		Mockito.verify(_settings).setValue(_KEY, String.valueOf(17L));
	}

	private static final String _KEY = "the_test_key";

	private Settings _settings;
	private TypedSettings _typedSettings;

}