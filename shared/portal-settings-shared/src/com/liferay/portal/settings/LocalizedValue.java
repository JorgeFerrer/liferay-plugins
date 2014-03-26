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

import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author Iván Zaera
 */
public class LocalizedValue implements Map<Locale, String> {

	public LocalizedValue(
		String key, Locale defaultLocale, Locale... availableLocales) {

		_key = key;
		_defaultLocale = defaultLocale;
		_availableLocales = availableLocales;
	}

	@Override
	public void clear() {
		_map.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return _map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return _map.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<Locale, String>> entrySet() {
		return _map.entrySet();
	}

	@Override
	public boolean equals(Object o) {
		return _map.equals(o);
	}

	@Override
	public String get(Object key) {
		return _map.get(key);
	}

	public Locale[] getAvailableLocales() {
		return _availableLocales;
	}

	public Locale getDefaultLocale() {
		return _defaultLocale;
	}

	public String getDefaultValue() {
		return get(_defaultLocale);
	}

	public String getKey() {
		return _key;
	}

	public String getLocalizationXml() {
		XMLStreamWriter xmlStreamWriter = null;

		try {
			UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();

			xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(
				unsyncStringWriter);

			xmlStreamWriter.writeStartDocument();
			xmlStreamWriter.writeStartElement(_ROOT);

			xmlStreamWriter.writeAttribute(
				_AVAILABLE_LOCALES, StringUtil.merge(_availableLocales));
			xmlStreamWriter.writeAttribute(
				_DEFAULT_LOCALE, _defaultLocale.toString());

			for (Locale locale : _availableLocales) {
				String value = get(locale);

				if (value != null) {
					xmlStreamWriter.writeStartElement(_key);

					xmlStreamWriter.writeAttribute(
						_LANGUAGE_ID, locale.toString());

					xmlStreamWriter.writeCharacters(value);

					xmlStreamWriter.writeEndElement();
				}
			}

			xmlStreamWriter.writeEndElement();
			xmlStreamWriter.writeEndDocument();

			return unsyncStringWriter.toString();
		}
		catch (XMLStreamException xmlse) {
			throw new RuntimeException(xmlse);
		}
		finally {
			if (xmlStreamWriter != null) {
				try {
					xmlStreamWriter.close();
				}
				catch (XMLStreamException xmlse) {
				}
			}
		}
	}

	@Override
	public int hashCode() {
		return _map.hashCode();
	}

	@Override
	public boolean isEmpty() {
		return _map.isEmpty();
	}

	@Override
	public Set<Locale> keySet() {
		return _map.keySet();
	}

	@Override
	public String put(Locale key, String value) {
		return _map.put(key, value);
	}

	@Override
	public void putAll(Map<? extends Locale, ? extends String> m) {
		_map.putAll(m);
	}

	@Override
	public String remove(Object key) {
		return _map.remove(key);
	}

	@Override
	public int size() {
		return _map.size();
	}

	@Override
	public Collection<String> values() {
		return _map.values();
	}

	private static final String _AVAILABLE_LOCALES = "available-locales";

	private static final String _DEFAULT_LOCALE = "default-locale";

	private static final String _LANGUAGE_ID = "language-id";

	private static final String _ROOT = "root";

	private Locale[] _availableLocales;
	private Locale _defaultLocale;
	private String _key;
	private Map<Locale, String> _map = new HashMap<Locale, String>();

}