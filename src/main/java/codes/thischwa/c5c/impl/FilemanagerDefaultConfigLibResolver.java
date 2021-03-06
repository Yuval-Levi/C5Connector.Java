/*
 * C5Connector.Java - The Java backend for the filemanager of corefive.
 * It's a bridge between the filemanager and a storage backend and 
 * works like a transparent VFS or proxy.
 * Copyright (C) Thilo Schwarz
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package codes.thischwa.c5c.impl;

import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import codes.thischwa.c5c.DefaultConfigResolver;
import codes.thischwa.c5c.filemanager.FilemanagerConfig;

/**
 * This is the default implementation of {@link DefaultConfigResolver} reads the default configuration file
 * of the filemanager from the resource folder.
 */
public class FilemanagerDefaultConfigLibResolver implements DefaultConfigResolver {
	
	protected static String configFile = "/filemanager/scripts/filemanager.config.default.json";

	@Override
	public void initContext(ServletContext servletContext) {
		
	}
	
	@Override
	public FilemanagerConfig read() throws RuntimeException {
		InputStream configIn = null;
		 
		try {
			// load the object
			configIn = getClass().getResourceAsStream(configFile);
			ObjectMapper mapper = new ObjectMapper();
			FilemanagerConfig filemanagerDefaultConfig = mapper.readValue(configIn, FilemanagerConfig.class);
			return filemanagerDefaultConfig;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(configIn);
		}
	}

}
