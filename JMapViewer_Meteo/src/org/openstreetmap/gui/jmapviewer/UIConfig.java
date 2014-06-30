package org.openstreetmap.gui.jmapviewer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.util.Hashtable;

/**
 * Centralisation des données de config de l'application.
 * 
 * @author Benoit ROUXEL
 * 
 */
public class UIConfig {

	// TODO écrire les données dans un fichier qui sera chargé un démarage de
	// l'appli.

	/**
	 * Largeur de l'UI de l'application en pixel
	 */
	public final static int mapWidth = 1400;

	/**
	 * Hauteur de l'UI de l'application en pixel
	 */
	public final static int mapHeight = 1050;

	/**
	 * Echelle d'affichage de la carte. (valeur possible: 1,2,4).
	 */
	public final static int mapScale = 1;

	/**
	 * Domaine du bus ivy
	 */
	public final static String domain = "192.168.42:3456";//"192.168.56:3456";

	public final static Color borderColor = new Color(0, 0, 153);
	public final static Color foregroundColor = new Color(153, 204, 255, 200);

	public final static Color darkGreyAlpha = new Color(64, 64, 64, 175);
	public final static Color lightGreyAlpha = new Color(192, 192, 192, 200);
	public final static Color darkOrange = new Color(255, 200, 0);
	public final static Color blueSusie = new Color(.3f, .6f, 1.f, 1.f);
	public final static Color blackSusie = new Color(0.f, 0.f, 0.f, .35f);

	public final static Color greenAlpha = new Color(20,209,0,100);
	public final static Color greenOpaque = new Color(20,209,0);
	public final static Color darkGreenAlpha = new Color(13,136,0,125);
	
	private final static Hashtable<Integer, Stroke> strokeHashTable = new Hashtable<>();

	public static Stroke getStroke(int thickness) {
		Stroke stroke = strokeHashTable.get(thickness);
		if (stroke != null)
			return stroke;
		stroke = new BasicStroke(thickness, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND);
		strokeHashTable.put(thickness, stroke);
		return stroke;
	}

	private final static Hashtable<Color, Hashtable<Float, Color>> colorHashTable = new Hashtable<>();

	public static Color getColor(Color color, float red, float green,
			float blue, float alpha) {
		Hashtable<Float, Color> alphaColors = colorHashTable.get(color);
		if (alphaColors != null) {
			if (alphaColors.get(alpha) == null) {
//				try{
				colorHashTable.get(color).put(
						alpha,
						new Color(red, green, blue, alpha));
//				}catch(Exception e) {
//					System.out.println(alpha);
//				}
			}
		} else {
			colorHashTable.put(color, new Hashtable<Float, Color>());
			colorHashTable.get(color).put(
					alpha,
					new Color(red, green, blue, alpha));
		}

		return colorHashTable.get(color).get(alpha);
	}

}
