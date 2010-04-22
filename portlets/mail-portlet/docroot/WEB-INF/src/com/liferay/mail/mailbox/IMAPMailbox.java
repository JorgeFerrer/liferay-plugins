/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.mailbox;

import com.liferay.mail.MailFile;
import com.liferay.mail.model.Account;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.Message;

import java.io.InputStream;

import java.util.List;

import javax.mail.internet.InternetAddress;

/**
 * <a href="IMAPMailbox.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 */
public class IMAPMailbox extends BaseMailbox {

	public IMAPMailbox() {
	}

	public Account addAccount(
		String address, String protocol, String incomingHostName,
		int incomingPort, boolean incomingSecure, String outgoingHostName,
		int outgoingPort, boolean outgoingSecure, String folderPrefix,
		String password, boolean savePassword, String login,
		String personalName, String signature, boolean useSignature) {

		return null;
	}

	public Folder addFolder(String displayName) {
		return null;
	}

	public void deleteAccount() {
	}

	public void deleteAttachment(long attachmentId) {
	}

	public void deleteFolder(long folderId) {
	}

	public void deleteMessages(long folderId, long[] messageIds) {
	}

	public long getAccountUnreadMessagesCount() {
		return 0;
	}

	public InputStream getAttachment(long attachmentId) {
		return null;
	}

	public int getFolderLocalMessagesCount(long folderId) {
		return 0;
	}

	public int getFolderRemoteMessagesCount(long folderId) {
		return 0;
	}

	public List<Folder> getFolders() {
		return null;
	}

	public long getFolderUnreadMessagesCount(long folderId) {
		return 0;
	}

	public Message getMessage(long messageId) {
		return null;
	}

	public Message getMessage(
		long folderId, String keywords, int messageNumber, int offset,
		String orderByField, String orderByType) {

		return null;
	}

	public List<Message> getMessages(
		long folderId, String keywords, int pageNumber, int messagesPerPage,
		String orderByField, String orderByType) {

		return null;
	}

	public void moveMessages(
		long sourceFolderId, long desintationFolderId, long[] messageIds) {
	}

	public InternetAddress[] parseAddresses(String addresses) {
		return null;
	}

	public int populateMessages(
		List<Message> messages, long folderId, String keywords, int pageNumber,
		int messagesPerPage, String orderByField, String orderByType) {

		return 0;
	}

	public Message saveDraft(
		long accountId, long messageId, String to, String cc, String bcc,
		String subject, String body, List<MailFile> mailFiles) {

		return null;
	}

	public void sendMessage(long accountId, long messageId) {
	}

	public void synchronize() {
	}

	public void synchronize(long folderId) {
	}

	public void updateFolder(long folderId, String displayName) {
	}

	public void updateMessagesFlag(
		long folderId, long[] messageIds, int flag, boolean value) {
	}

	public void validateAccount(
		String incomingHostName, int incomingPort, boolean incomingSecure,
		String outgoingHostName, int outgoingPort, boolean outgoingSecure,
		String login, String password) {
	}

	public void validateAddresses(String addresses) {
	}

}