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

package com.liferay.adaptive.media.image.item.selector.internal.resolver;

import com.liferay.adaptive.media.image.item.selector.AdaptiveMediaImageFileEntryItemSelectorReturnType;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.item.selector.ItemSelectorReturnTypeResolver;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;

/**
 * @author Roberto Díaz
 */
@Component(
	immediate = true, property = {"service.ranking:Integer=100"},
	service = {
		ItemSelectorReturnTypeResolver.class,
		FileEntryAdaptiveMediaImageFileEntryItemSelectorReturnTypeResolver.class
	}
)
public class FileEntryAdaptiveMediaImageFileEntryItemSelectorReturnTypeResolver
	implements ItemSelectorReturnTypeResolver
		<AdaptiveMediaImageFileEntryItemSelectorReturnType, FileEntry> {

	@Override
	public Class<AdaptiveMediaImageFileEntryItemSelectorReturnType>
		getItemSelectorReturnTypeClass() {

		return AdaptiveMediaImageFileEntryItemSelectorReturnType.class;
	}

	@Override
	public Class<FileEntry> getModelClass() {
		return FileEntry.class;
	}

	@Override
	public String getValue(FileEntry fileEntry, ThemeDisplay themeDisplay)
		throws Exception {

		JSONObject fileEntryJSONObject = JSONFactoryUtil.createJSONObject();

		String previewURL = DLUtil.getPreviewURL(
			fileEntry, fileEntry.getFileVersion(), themeDisplay,
			StringPool.BLANK, false, false);

		fileEntryJSONObject.put("fileEntryId", fileEntry.getFileEntryId());
		fileEntryJSONObject.put("url", previewURL);

		return fileEntryJSONObject.toString();
	}

}