package de.ruu.lib.util;

public final class SystemProperties
{
	public static String userName()      { return System.getProperty("user.name"); }
	public static String userHome()      { return System.getProperty("user.home"); }
	public static String lineSeparator() { return System.getProperty("line.separator"); }
}