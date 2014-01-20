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
package de.thischwa.c5c.resource.filemanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object to hold the configuration of the filemanager (filemanager.config.js). All sections in the configuration file 
 * have a corresponding type, e.g. 'options', 'images'.
 * 
 * @see <a href="https://github.com/simogeo/Filemanager/wiki/Filemanager-configuration-file">filemanager-wiki</a>
 */
public class FilemanagerConfig {
	public static final Logger logger = LoggerFactory.getLogger(FilemanagerConfig.class);

	@JsonProperty("_comment")
	private String comment;

	private Options options = new Options();
	private Security security = new Security();
	private Upload upload = new Upload(); 
	private Exclude exclude = new Exclude();
	private Images images = new Images();
	private Videos videos = new Videos();
	private Audios audios = new Audios();
	private Extras extras = new Extras();
	private Icons icons = new Icons();

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Options getOptions() {
		return options;
	}

	public Security getSecurity() {
		return security;
	}

	public Upload getUpload() {
		return upload;
	}

	public Exclude getExclude() {
		return exclude;
	}

	public Images getImages() {
		return images;
	}

	public Videos getVideos() {
		return videos;
	}

	public Audios getAudios() {
		return audios;
	}

	public Extras getExtras() {
		return extras;
	}

	public Icons getIcons() {
		return icons;
	}
}
