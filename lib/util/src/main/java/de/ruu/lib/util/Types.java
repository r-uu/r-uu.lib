package de.ruu.lib.util;

public final class Types
{
	public static String getPackageNameFromQualifiedTypeName(String qualifiedTypeName)
	{
		int lastIndexOfPackageName = qualifiedTypeName.lastIndexOf(".");

		if (lastIndexOfPackageName < 0) return "";

		return qualifiedTypeName.substring(0, qualifiedTypeName.lastIndexOf("."));
	}

	public static String getSimpleNameFromQualifiedTypeName(String qualifiedTypeName)
	{
		return qualifiedTypeName.substring(qualifiedTypeName.lastIndexOf(".") + 1);
	}
}