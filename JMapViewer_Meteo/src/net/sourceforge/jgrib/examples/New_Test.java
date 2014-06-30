package net.sourceforge.jgrib.examples;



import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.IOException;
 




import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jgrib.GribFile;
import net.sourceforge.jgrib.GribRecord;
import net.sourceforge.jgrib.GribRecordGDS;
import net.sourceforge.jgrib.GribRecordPDS;
import net.sourceforge.jgrib.NoValidGribException;
import net.sourceforge.jgrib.NotSupportedException;

import org.openstreetmap.*;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
 
public class New_Test
{
    

	
	public static double[] grib1;
	public static String description;

	
	
	public static void main(String[] args) {
		
		
	}

	
	public static List<MeteoCoordinate> grib(int zoom, double x1, double x2, double y1, double y2) {

		
		
		
		try {
			List<MeteoCoordinate> meteoCoordList = new ArrayList<MeteoCoordinate>();
			
			
			
			
			// Coordonnées de la zone d'affichage des données météo
		    System.out.println ("GRIB BOX = " + x1 + " " + x2+ " " + y1 + " "+ y2);
			
		    // Chargement du fichier GRIB
			GribFile grb = new GribFile(
					"C:/Users/mtanguy/Desktop/grib/20140625_101130_.grb");

			
			int recordCount = grb.getRecordCount();
            System.out.println("gribFile reports " + recordCount + " records,");
			
			//Chargement des données Uwind et Vwind 
			GribRecordGDS r2 = grb.getGrids()[0];
			GribRecord ventU = grb.getRecord(1);
			GribRecord ventV = grb.getRecord(2);
	
			//Description des enregistrements
			GribRecordPDS PDS1 = ventU.getPDS();
			GribRecordPDS PDS2 = ventV.getPDS();
			System.out.println(PDS1.getDescription() + "---"
					+ PDS2.getDescription());

			
			// Calcul de la taille de la grille Grib
			int nbx = r2.getGridNX();
			int nby = r2.getGridNY();
	
		

			// Coordonnées géographiques du fichier GRIB
			double lat_init = r2.getGridLat1();
			double lon_init = r2.getGridLon1();
			double lat_final = r2.getGridLat2();
			double lon_final = r2.getGridLon2();
			
			
		
			// Calcul des coordonnées de chaque maille
			double dx = (lat_init-lat_final)/nby;
			double dy = (lon_init-lon_final)/nbx;
			double abs_dy = Math.abs(dy);
			
			//Definition de la densité de la donnée ( plus le zoom est fort, plus la donnée est dense)
			int zoom_moy = 8;
			
			// Calcul des variables pour adapter le maillage au zoom
			
			double zoom_dif = (Math.abs(zoom_moy-zoom));
			double b = Math.pow(2, zoom_dif);
			double a_x = Math.abs(dx/b);
			double a_y = Math.abs(dy/b);
			

			// Recherche de positions à afficher
			
			for (int i = 0; i < nbx; i++) {
				for (int j = 0; j < nby; j++) {
					// affichage des la donnee (i,j)

					try {
						double vU = ventU.getValue(i, j);
						double vV = ventV.getValue(i, j);
							if (zoom == zoom_moy) {
								
								// Calcul coordonnées de chaque point
								double lon = lon_init + (i * dx);
								double lat = lat_init + (j * dy); 
								
								// Calcul de la direction et de la vitesse de chaque point
								double speed = Math.sqrt((vU * vU) + (vV * vV));
								double dir = (57.29578 * Math.atan2(vU, vV)) + 180;

									// Ajout des points à afficher
									if (x1 > lat && lat > y1 && x2 < lon && lon < y2) {
										meteoCoordList.add(new MeteoCoordinate(lat, lon, speed,
									dir));																			
						}
						
					 	
						}
						
						else if (zoom > zoom_moy) {
						double lon = lon_init + (i * dx);
						double lat = lat_init + (j * dy); 

						
						double speed = Math.sqrt((vU * vU) + (vV * vV));
						double dir = (57.29578 * Math.atan2(vU, vV)) + 180;
					
						
						for (double k = 0; k < dx; k = k+a_x) {
							double v1U = ventU.getValue((int) (i-1), j);
							double v1V = ventV.getValue((int) (i-1), j);
							double lon_bis = lon_init + ((i*dx) + k);
							double i1 = 1 - ((1/dx)*k);
							double i2 = 1 - i1;
							double speed2 = ((i1)*(Math.sqrt((vU * vU) + (vV * vV)))) + ((i2)*(Math.sqrt((v1U * v1U) + (v1V * v1V))));
							double dir2 = ((i1)*(57.29578 * Math.atan2(vU, vV)) + ((i2)*(57.29578 * Math.atan2(v1U, v1V)) ));
				

							
							if (x1 > lat && lat > y1 && x2 < lon_bis && lon_bis < y2) {
								System.out.println("POINT ADD : " + "--" + lat +"--" + lon_bis +"--" + speed2 +"--" + dir +"--" + k +"--" + a_x +"--" + i1);
//							meteoCoordList.add(new MeteoCoordinate(lat, lon_bis, speed2,
//									dir));				
							}
						for (double n = 0; n < abs_dy; n = n+a_y) {
//							System.out.println("n:"+n+"dx:"+dy+"ggg:"+ggg+"a:"+a_y);
							
							double j1 = 1 - ((1/abs_dy)*n);
							double j2 = 1 - j1;
							
							double v2U = ventU.getValue(i, (int) (j-1));
							double v2V = ventV.getValue(i, (int) (j-1));
							
							double speed3 = ((j1)*(Math.sqrt((vU * vU) + (vV * vV)))) + ((j2)*(Math.sqrt((v2U * v2U) + (v2V * v2V))));
							double dir3 = ((j1)*(57.29578 * Math.atan2(vU, vV)) + ((j2)*(57.29578 * Math.atan2(v2U, v2V)) ));
							double lat_bis = lat_init + ((j*dy) + n);
							double speed_final = (speed2+speed3)/2;
							double dir_final = ((dir2+dir3)/2)+180;
							if (x1 > lat_bis && lat_bis > y1 && x2 < lon_bis && lon_bis < y2) {
							meteoCoordList.add(new MeteoCoordinate(lat_bis, lon_bis, speed_final,
									dir_final));
			
						}
						}
						}
//						meteoCoordList.add(new MeteoCoordinate(lat, lon, speed,
//								dir)); 
						}
						
						
						
						
						
						
//						meteoCoordList.add(new MeteoCoordinate(lat, lon2, speed,
//								dir));
//						meteoCoordList.add(new MeteoCoordinate(lat2, lon, speed,
//								dir));
//						meteoCoordList.add(new MeteoCoordinate(lat2, lon2, speed,
//								dir));
						
						
						
						
						
//						System.out.println("zoom : " + zoom + "zoom_init : " + zoom_moy);
						
						else if (zoom < zoom_moy) {
							double lon = lon_init + ((i * dx) + (zoom_dif/4));
							double lat = lat_init + ((j * dy) + (zoom_dif/4));
						double speed = Math.sqrt((vU * vU) + (vV * vV));
						double dir = (57.29578 * Math.atan2(vU, vV)) + 180;
						if (x1 > lat && lat > y1 && x2 < lon && lon < y2) {
						meteoCoordList.add(new MeteoCoordinate(lat, lon, speed,
								dir)); 
						System.out.println("zoom : " + zoom + "zoom_init : " + zoom_moy);
						} }
						else System.out.println("--|HEY|--"); {}
						
						
						
//						double halflon = lon + dx*(zoom_moy-zoom);
//						double halflat = lat + dy*(zoom_moy-zoom); }
						
//						if (zoom > zoom_moy) {
//						double halflon = lon + dx/(zoom_moy-zoom);
//						double halflat = lat + dy/(zoom_moy-zoom); }
//						else if (zoom < zoom_moy) {
//						double halflon = lon + dx*(zoom_moy-zoom);
//						double halflat = lat + dy*(zoom_moy-zoom); }
//						else {
//						double halflon = lon + dx/(zoom_moy-zoom);
//						double halflat = lat + dy/(zoom_moy-zoom); }
//						
//						
//						double speed = Math.sqrt((vU * vU) + (vV * vV));
//						double speed1 = ( Math.sqrt((vU * vU) + (vV * vV)) + Math.sqrt((v1U * v1U) + (v1V * v1V)) )/ 2;
//						double speed2 = ( Math.sqrt((vU * vU) + (vV * vV)) + Math.sqrt((v2U * v2U) + (v2V * v2V)) )/ 2;
//						double dir = (57.29578 * Math.atan2(vU, vV)) + 180;
//						System.out.println("DIR : " + dir);
//						double dir2 = (57.29578 * Math.atan2(v1U, v1V)) + 180;				
//						System.out.println("D2R : " + dir2);
//						double dir3 = (((57.29578 * Math.atan2(vU, vV)) + 180) + ((57.29578 * Math.atan2(v1U, v1V)) + 180)) / 2;	
//						System.out.println("D3R : " + dir3);
//						double dir4 = (((57.29578 * Math.atan2(vU, vV)) + 180) + ((57.29578 * Math.atan2(v2U, v2V)) + 180)) / 2;	
//						System.out.println("D4R : " + dir4);
						
						
				
						
						
//						meteoCoordList.add(new MeteoCoordinate(lat, lon, speed,
//								dir));
//						meteoCoordList.add(new MeteoCoordinate(lat, halflon, speed2,
//								dir4));
//						meteoCoordList.add(new MeteoCoordinate(halflat, lon, speed1,
//								dir3));
//						meteoCoordList.add(new MeteoCoordinate(halflat, halflon, 0,
//								dir));
						
						
//						System.out.println(meteoCoordList.toString());
//						MeteoCoordinate meteoCoord = new MeteoCoordinate(lat,
//								lon, speed, dir);
//
//						System.out.println("GRID: " + i + "-" + j
//								+ "  Point n°");
////						System.out.println(meteoCoord);
//						System.out.println("Uwind: " + ventU.getValue(i, j)
//								+ " ");
//						System.out.println("Vwind: " + ventV.getValue(i, j)
//								+ " ");
//						System.out.println("------------------");

					} catch (NoValidGribException e) {
						e.printStackTrace();
					}
				}
				System.out.println();
			}
			// affichage de l'unite des donnees
			System.out.println(ventV.getUnit());
			// description de la donnee
			System.out.println(ventU.getGDS());
			

			return meteoCoordList;
			
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotSupportedException e) {
			e.printStackTrace();
		} catch (NoValidGribException e) {
//			e.printStackTrace();
		}
		return null;
	}

	
}