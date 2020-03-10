package com.snail.util;

import java.io.InputStream;

public final class ConfigHelper {

	public static InputStream getResourceAsStream(String resource) {
		String stripped = resource.startsWith("/") ? resource.substring(1) : resource;

		InputStream stream = null;
		ClassLoader classLoader = ClassLoaderHelper.getContextClassLoader();
		if (classLoader != null)
			stream = classLoader.getResourceAsStream(stripped);
		if (stream == null)
			stream = ConfigHelper.class.getResourceAsStream(resource);
		if (stream == null)
			stream = ConfigHelper.class.getClassLoader().getResourceAsStream(stripped);
		return stream;
	}
}
