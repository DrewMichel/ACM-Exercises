package year2016.A001DimWeights;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DimWeights
{
    public static void main(String[] args)
    {
        Scanner reader = null;
        
        int numCompanies = 0;
        
        String[] names = null;
        
        int[] minVolumes = null;
        
        int[] divisors = null;
        
        ArrayList<Double> volumes = new ArrayList<>();
        
        ArrayList<Double> pounds = new ArrayList<>();
        
        double length, width, height;
        
        int index = 0, counter = 0;
        
        int best = Integer.MAX_VALUE;
        
        int dm = 0;
        
        boolean beyond = false;
                
        try
        {
            // input start
            reader = new Scanner(new FileInputStream("dimweights.txt"));
            
            numCompanies = reader.nextInt();
            
            names = new String[numCompanies];
            
            minVolumes = new int[numCompanies];
            
            divisors = new int[numCompanies];
            
            for(int i = 0; i < numCompanies; i++)
            {
                names[i] = reader.next();
                minVolumes[i] = reader.nextInt();
                divisors[i] = reader.nextInt();
            }
            
            while(reader.hasNextInt())
            {
                length = reader.nextDouble();
                width = reader.nextDouble();
                height = reader.nextDouble();
                
                volumes.add(length * width * height);
                pounds.add(reader.nextDouble());
            }
            
            // input complete
            
            for(int i = 0; i < volumes.size(); i++)
            {
                index = 0;
                
                best = Integer.MAX_VALUE;
                
                beyond = false;
                
                for(int k = 0; k < names.length; k++)
                {
                    beyond = volumes.get(i) >= minVolumes[k];
                    
                    if(beyond == true)
                    {
                        dm = (int) Math.ceil(volumes.get(i) / divisors[k]);
                        
                        if(best > dm)
                        {
                            best = dm;
                            index = k;
                        }
                    } 
                    else
                    {
                        dm = pounds.get(i).intValue();
                        
                        if(best > dm)
                        {
                            best = dm;
                            index = k;
                        }
                    }
                }
                
                System.out.println(names[index] + " " + best);
                
                // index
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        finally
        {
            if(reader != null)
            {
                reader.close();
            }
        }
    }
}