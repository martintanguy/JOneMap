package org.openstreetmap.gui.jmapviewer.tilesources;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.openstreetmap.gui.jmapviewer.Settings;

public abstract class AbstractGeomodTileSource extends TMSTileSource {

	private static final int[] correctionGrisetId = new int[20];
	
	public AbstractGeomodTileSource(String name, String url) {
		super(name, url, 4, 18);

		for (int i = 0; i < correctionGrisetId.length; i++)
			correctionGrisetId[i] = (int) (Math.pow(2, i) - 1);

		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Settings.getLoginGeomod(), Settings.getPasswordGeomod().toCharArray());
			}
		});
	}
	
	@Override
	public String getTileUrl(int zoom, int tilex, int tiley) throws IOException {
//		if(zoom < 5 || zoom > 5) //4 -> 18
//			return super.getTileUrl(zoom, tilex, tiley);
		return super.getTileUrl(zoom, tilex, correctionGrisetId[zoom]-tiley);
	}
	
}
