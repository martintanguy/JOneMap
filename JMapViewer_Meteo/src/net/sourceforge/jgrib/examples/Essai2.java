package net.sourceforge.jgrib.examples;

import java.io.FileNotFoundException;
import java.io.IOException;
 










import net.sourceforge.jgrib.GribFile;
import net.sourceforge.jgrib.GribRecord;
import net.sourceforge.jgrib.GribRecordGDS;
import net.sourceforge.jgrib.GribRecordBMS;
import net.sourceforge.jgrib.GribGDSLatLon;
import net.sourceforge.jgrib.GribRecordIS;
import net.sourceforge.jgrib.GribRecordLight;
import net.sourceforge.jgrib.GribRecordPDS;
import net.sourceforge.jgrib.NoValidGribException;
import net.sourceforge.jgrib.NotSupportedException;
 
public class Essai2
{
    @SuppressWarnings("null")
	public static void main(String[] args)
    {
        try
        {
            GribFile grb = new GribFile("C:/Users/mtanguy/Desktop/usETsEOEEoSYmtRzKDl0e75I4HAjqDApvbb.grb");
            grb.listRecords(System.out);
            
            int recordCount = grb.getRecordCount();
            System.out.println("gribFile reports " + recordCount + " records,");
            
            
//            GribRecordLight[] gribLight = grb.getLightRecords();
//            GribRecordGDS r2 = grb.getGrids()[0];
//            GribRecordIS gribIS = gribLight[0].getIS();
//            System.out.println(gribIS.toString());
//  
            GribRecord ventU = grb.getRecord(1);
//            GribRecord ventV = grb.getRecord(2);
            
            GribRecordPDS test = grb.
            test.getLevelValue(31);
//           
//            
//            GribRecord gribLat = null;
//            GribRecord gribLon = null;
//            GribRecordLight[] latRecords = grb.getRecordForType("Lat");
//            GribRecordLight[] lonRecords = grb.getRecordForType("Lon");
//            GribRecord gribLat = null;
//            GribRecord gribLon = null;
//            double[] latCoords;
//            double[] lonCoords;
//            float[] latValues;
//            float[] lonValues;
            
//            compareCoords(grb);
//            
//            
//          
// 
//            int nbx = r2.getGridNX();
//            double cx = null;
//            System.out.println(nbx);
//            
//            int nby = r2.getGridNY();
//            double cy = null;
//            System.out.println(nby);
//         
//           
//            double a = r2.getGridDX();
//            double b = r2.getGridDY();
//
//
//            
//            System.out.println(a + "  "+  b);
            
            for (int i = 0; i < nbx; i++)
            {
                for (int j = 0; j < nby; j++)
                {
                    // affichage des la donnee (i,j)
                    try
                    {
                        System.out.print(ventU.getValue(i, j) + " (" + cx +") "+" (" + cy +")" );
                        gribLat.getValues();
                        
                    }
                    catch (NoValidGribException e)
                    {
                        e.printStackTrace();
                    }
                }
                System.out.println();
            }
            // affichage de l'unite des donnees
            System.out.println(ventV.getUnit());
            // description de la donnee
            System.out.println(ventU.getPDS());
            System.out.println();
            
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (NotSupportedException e)
        {
            e.printStackTrace();
        }
        catch (NoValidGribException e)
        {
            e.printStackTrace();
        }
    }


public static void compareCoords(GribFile gribFile) {
    try {
       // compare computed latitude and longitude to records in file
       GribRecordLight[] latRecords = gribFile.getRecordForType("Lat");
       GribRecordLight[] lonRecords = gribFile.getRecordForType("Lon");
       GribRecord gribLat = null;
       GribRecord gribLon = null;
       double[] latCoords;
       double[] lonCoords;
       float[] latValues;
       float[] lonValues;

       double error, sumErrors = 0, rmse, maxError=0;

       //int latLength;
       int lonLength;
       System.out.println("COORD :");
       for (int i=0;i<latRecords.length;i++){
          gribLat = new GribRecord(latRecords[i]);
          gribLon = new GribRecord(lonRecords[i]);
          lonLength = gribLat.getGDS().getGridNX();
          //latLength = gribLon.getGDS().getGridNY();
          latCoords = gribLat.getGridCoords();
          lonCoords = gribLon.getGridCoords();
          System.out.println("\nLats/Lons for level: " + gribLat.getLevel());
          latValues = gribLat.getBDS().getValues();
          lonValues = gribLon.getBDS().getValues();

          for (int n=0;n<latValues.length;n++){
             error = latValues[n] - latCoords[n*2+1];
             sumErrors += error*error;
             if (error > maxError) maxError = error;
          }
          rmse = Math.sqrt(sumErrors/latValues.length);
          System.out.println("lat RMSE = " + rmse + "Max error = " + maxError);

          sumErrors = 0;
          maxError = 0;
          for (int n=0;n<lonValues.length;n++){
             error = lonValues[n] - lonCoords[n*2];
             sumErrors += error*error;
             if (error > maxError) maxError = error;
          }
          rmse = Math.sqrt(sumErrors/latValues.length);
          System.out.println("lon RMSE = " + rmse + "Max error = " + maxError);


          for (int n=0;n<latValues.length;n=n+lonLength){
             System.out.println("lat[" + n + "]: " + latValues[n] +
                                " latCoords[" + (n*2+1) +"]: " + latCoords[n*2+1]);
          }
          for (int n=0;n<lonLength;n++){
             System.out.println("lon[" + n + "]: " + lonValues[n] +
                                " lonCoords[" + (2*n) +"]: " + lonCoords[2*n]);
          }
       }
    } catch (FileNotFoundException noFileError) {
       System.err.println("FileNotFoundException : " + noFileError);
    } catch (IOException ioError) {
       System.err.println("IOException : " + ioError);
    } catch (NoValidGribException noGrib) {
       System.err.println("NoValidGribException : " + noGrib);
    } catch (NotSupportedException noSupport) {
       System.err.println("NotSupportedException : " + noSupport);
    }
 }
}