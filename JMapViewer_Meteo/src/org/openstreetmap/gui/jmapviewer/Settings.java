package org.openstreetmap.gui.jmapviewer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
	//-----------------------------------------------------------------------------
	private static Properties props;
	private static String loginGeomod    = null;
	private static String passwordGeomod = null;
	//-----------------------------------------------------------------------------
	static
	{
		props = new Properties();
	    try { props.load(new FileInputStream("geomod.properties")); }
	    catch (IOException e) { e.printStackTrace(); }
	}
	//-----------------------------------------------------------------------------
	public static String getLoginGeomod()
	{
		loginGeomod = props.getProperty("LOGIN_GEOMOD");
	    if(loginGeomod.length() == 0 || loginGeomod.equalsIgnoreCase("null"))
	      return null;
	    else
	      return loginGeomod;
	}
	//-----------------------------------------------------------------------------
	public static String getPasswordGeomod()
	{
		passwordGeomod = props.getProperty("PASSWORD_GEOMOD");
	    if(passwordGeomod.length() == 0 || passwordGeomod.equalsIgnoreCase("null"))
	      return null;
	    else
	      return passwordGeomod;
	}
	//-----------------------------------------------------------------------------
}
