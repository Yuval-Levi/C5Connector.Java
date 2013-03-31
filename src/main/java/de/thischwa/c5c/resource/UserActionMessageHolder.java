/*
 * C5Connector.Java - The Java backend for the filemanager of corefive.
 * It's a bridge between the filemanager and a storage backend and 
 * works like a transparent VFS or proxy.
 * Copyright (C) Thilo Schwarz
 * 
 * == BEGIN LICENSE ==
 * 
 * Licensed under the terms of any of the following licenses at your
 * choice:
 * 
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 * 
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 * 
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 * 
 * == END LICENSE ==
 */
package de.thischwa.c5c.resource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.thischwa.c5c.Constants;
import de.thischwa.c5c.requestcycle.UserAction;

/**
 * It provides localized messages for failed user actions of the {@link UserAction} implementation. <br/>
 * In the backend it utilizes the regular {@link ResourceBundle} class.
 */
public class UserActionMessageHolder {
	private static Logger logger = LoggerFactory.getLogger(UserActionMessageHolder.class);

	private static Map<String, ResourceBundle> cache = new HashMap<String, ResourceBundle>();

	private static String messagesBaseName = "actionMessages";
	private static String baseName = String.format("%s/%s", UserActionMessageHolder.class.getPackage().getName(), messagesBaseName);
	
	static {
		// load known locales
		try {
			cache.put(Locale.GERMAN.getLanguage(), ResourceBundle.getBundle(baseName, Locale.GERMAN));
			cache.put(Locale.ENGLISH.getLanguage(), ResourceBundle.getBundle(baseName, Locale.ENGLISH));
		}  catch (MissingResourceException e) {
			throw new IllegalArgumentException("No resource bundle found!");
		}
	}

	public static String get(Locale locale, Key key) {
		ResourceBundle rb;
		if (cache.containsKey(locale.getLanguage()))
			rb = cache.get(locale.getLanguage());
		else 
			rb = cache.get(Constants.DEFAULT_LOCALE.getLanguage());
		
		try {
			return rb.getString(key.getPropertyName());
		} catch (MissingResourceException e) {
			logger.error("Missinig key for locale [{}]: {}", locale.toString(), key);
			return String.format("MISSING KEY | %s |", key);
		}
	} 

	public enum Key {
		UploadNotAllowed("upload.notallowed"),
		CreateFolderNotAllowed("createfolder.notallowed");
		
		private String propertyName;
		
		private Key(String propertyName) {
			this.propertyName = propertyName;
		}
		
		public String getPropertyName() {
			return propertyName;
		}
	}
}

