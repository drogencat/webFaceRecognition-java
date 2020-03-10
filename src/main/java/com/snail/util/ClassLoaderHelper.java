package com.snail.util;

public final class ClassLoaderHelper {

	public static ClassLoader overridenClassLoader;

	public static ClassLoader getContextClassLoader() {
		return overridenClassLoader != null ? overridenClassLoader : Thread.currentThread().getContextClassLoader();
	}
	
}
