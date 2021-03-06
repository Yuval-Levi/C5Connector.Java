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
package codes.thischwa.c5c;

import static org.junit.Assert.*;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.testing.HttpTester;
import org.junit.Test;

public class ContextGetRequestsTest extends GenericRequestTest {

	@Override
	protected void initTester() {
		servletTester.setContextPath("/context");
	}

	@Test
	public void testGetInfoFile() throws Exception {
		HttpTester request = buildInitialRequest(); 
		request.setURI("/context/filemanager/connectors/java/filemanager.java?mode=getinfo&path=%2Ffilemanager%2Fuserfiles%2Fpic01.png&time=244");
		String requestStr = request.generate();
		
		String responseStr = servletTester.getResponses(requestStr);
		HttpTester response = new HttpTester();
		response.parse(responseStr);
		
		assertEquals(200, response.getStatus());
		String actual = cleanResponse(response.getContent());
		String expected = cleanResponse("{\"Capabilities\":[\"select\",\"delete\",\"rename\",\"download\",\"replace\"],\"Code\":0,\"Error\":\"\",\"File Type\":\"png\",\"Filename\":\"pic01.png\",\"Path\":\"\\/filemanager\\/userfiles\\/pic01.png\",\"Preview\":\"\\/context\\/filemanager\\/connectors\\/java?mode=preview&path=%2Ffilemanager%2Fuserfiles%2Fpic01.png\",\"Properties\":{\"Date Created\":null,\"Height\":70,\"Size\":2250,\"Width\":110},\"Protected\":0}");
		assertEquals(expected, actual);
	}

	@Test
	public void testGetInfoFolder() throws Exception {
		HttpTester request = buildInitialRequest(); 
		request.setURI("/context/filemanager/connectors/java/filemanager.java?mode=getinfo&path=%2Ffilemanager%2Fuserfiles%2F");
		String requestStr = request.generate();
		
		String responseStr = servletTester.getResponses(requestStr);
		HttpTester response = new HttpTester();
		response.parse(responseStr);
		
		assertEquals(200, response.getStatus());
		String actual = cleanResponse(response.getContent());
		String expected = cleanResponse("{\"Capabilities\":[\"select\",\"delete\",\"rename\",\"download\",\"replace\"],\"Code\":0,\"Error\":\"\",\"File Type\":\"dir\",\"Filename\":\"userfiles\",\"Path\":\"\\/filemanager\\/userfiles\\/\",\"Preview\":\"\\/filemanager\\/images\\/fileicons\\/_Open.png\",\"Properties\":{\"Date Created\":null,\"Height\":null,\"Size\":null,\"Width\":null},\"Protected\":0}");
		assertEquals(expected, actual);
	}

	@Test
	public void testGetInfo() throws Exception {
		HttpTester request = buildInitialRequest(); 
		request.setURI("/context/filemanager/connectors/java/filemanager.java?mode=getinfo&path=%2Ffilemanager%2Fuserfiles%2Fpic01.png&time=244");
		String requestStr = request.generate();
		
		String responseStr = servletTester.getResponses(requestStr);
		HttpTester response = new HttpTester();
		response.parse(responseStr);
		
		assertEquals(HttpStatus.OK_200, response.getStatus());
		String actual = cleanResponse(response.getContent());
		String expected = cleanResponse("{\"Capabilities\":[\"select\",\"delete\",\"rename\",\"download\",\"replace\"],\"Code\":0,\"Error\":\"\",\"File Type\":\"png\",\"Filename\":\"pic01.png\",\"Path\":\"\\/filemanager\\/userfiles\\/pic01.png\",\"Preview\":\"\\/context\\/filemanager\\/connectors\\/java?mode=preview&path=%2Ffilemanager%2Fuserfiles%2Fpic01.png\",\"Properties\":{\"Date Created\":null,\"Height\":70,\"Size\":2250,\"Width\":110},\"Protected\":0}");
		assertEquals(expected, actual);
	}
}
