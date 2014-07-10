package net.sourceforge.jgrib.examples;

import java.io.FileNotFoundException;
import java.io.IOException;
 






import net.sourceforge.jgrib.GribFile;
import net.sourceforge.jgrib.GribRecord;
import net.sourceforge.jgrib.GribRecordGDS;
import net.sourceforge.jgrib.GribRecordBMS;
import net.sourceforge.jgrib.GribGDSLatLon;
import net.sourceforge.jgrib.GribRecordLight;
import net.sourceforge.jgrib.GribRecordPDS;
import net.sourceforge.jgrib.NoValidGribException;
import net.sourceforge.jgrib.NotSupportedException;
 
public class Essai
{
    public static void main(String[] args)
    {
        try
        {
            GribFile grb = new GribFile("C:/Users/mtanguy/Desktop/grib/20140710_142919_.grb");
            

            GribRecordGDS[] r2 = grb.getGrids();
         
            
            
//            GribRecord ventV = grb.getRecord(2);
//            GribRecord lol = grb.getRecord(3);
//            
//            GribRecordLight[] light = grb.getLightRecords();
            

            int recordCount = grb.getRecordCount();
            System.out.println("gribFile reports " + recordCount + " records,");
           
            
           
            
            
            for (int i = 1; i < recordCount; i++)
              {
                      try
                      {
                    	  GribRecord ventU = grb.getRecord(i);
                          GribRecordPDS pds = ventU.getPDS();
                          System.out.println(i + " " + pds.getParameter() + " " + pds.getGMTForecastTime());
                          
                      }
                      catch (NoValidGribException e)
                      {
                          e.printStackTrace();
                      }
                  }
                  System.out.println();
            
     
//            GribRecordPDS pds2 = ventV.getPDS();
//            System.out.println(pds2.getParameter());
//            GribRecordPDS lolz = lol.getPDS();
//            System.out.println(lolz.getParameter());
           
            
 
//            int nbx = r2.getGridNX();
//            double cx = r2.getGridDX();
//            System.out.println(nbx);
//            
//            int nby = r2.getGridNY();
//            double cy = r2.getGridDY();
//            System.out.println(nby);
//         
//           
//            double a = r2.getGridDX();
//            double b = r2.getGridDY();
//
//
//            
//            System.out.println(a + "  "+  b);
//            
//            for (int i = 0; i < nbx; i++)
//            {
//                for (int j = 0; j < nby; j++)
//                {
//                    // affichage des la donnee (i,j)
//                    try
//                    {
//                        System.out.print(ventU.getValue(i, j) + " (" + cx +") "+" (" + cy +")" );
//                        
//                        
//                    }
//                    catch (NoValidGribException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println();
//            }
//            // affichage de l'unite des donnees
//            System.out.println(ventV.getUnit());
//            // description de la donnee
//            System.out.println(ventU.getPDS());
//            System.out.println();
            
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
}