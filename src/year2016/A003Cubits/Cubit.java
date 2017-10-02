package year2016.A003Cubits;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

public class Cubit
{
    // mm
    public static final double CUBIT = 526.35;
    public static final double PALM = CUBIT / 7;
    public static final double DIGIT = PALM / 4;
    public static final double INCH = 25.4;
    public static final double CONVERTER = 12 / INCH;
    public static final double FOOT = INCH * 12;
    
    public static final int MM_IN_METER = 1000;
    
    public static void main(String[] args)
    {
        //ArrayList<Double> cubits = new ArrayList<>();
        //ArrayList<Double> palms = new ArrayList<>();
        //ArrayList<Double> digits = new ArrayList<>();
        
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> out = new ArrayList<>();
        
        Scanner reader = null;
        Scanner lineReader = null;
        
        String file = "cubit.txt";
        
        String type;
        
        double cub = 0;
        double pal = 0;
        double dig = 0;
        double temp = 0;
        
        double meters = 0;
        
        double inches = 0, feet = 0;
        double value = 0;
        
        String fe = "";
        String in = "";
        String input = "";
        
        try
        {
            reader = new Scanner(new FileInputStream(file));
            
            while(reader.hasNextLine())
            {
                lines.add(reader.nextLine());
            }
            
            reader.close();
            
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        
        for(int i = 0; i < lines.size(); i++)
        {
            lineReader = new Scanner(lines.get(i));
            
            cub = 0;
            pal = 0;
            dig = 0;
            inches = 0;
            feet = 0;
            meters = 0;
            temp = 0;
            value = 0;
            fe = "";
            in = "";
            input = "";
            
            if(lineReader.hasNext())
            {
                value = lineReader.nextDouble();
                input = lineReader.next();
                
                if(input.toLowerCase().contains("cubit"))
                {
                	cub = value;
                }
                else if(input.toLowerCase().contains("palm"))
                {
                	pal = value;
                }
                else if(input.toLowerCase().contains("digit"))
                {
                	dig = value;
                }
            }
            
            if(lineReader.hasNext())
            {
                value = lineReader.nextDouble();
                input = lineReader.next();
                
                if(input.toLowerCase().contains("cubit"))
                {
                	cub = value;
                }
                else if(input.toLowerCase().contains("palm"))
                {
                	pal = value;
                }
                else if(input.toLowerCase().contains("digit"))
                {
                	dig = value;
                }
            }
            
            if(lineReader.hasNext())
            {
                value = lineReader.nextDouble();
                input = lineReader.next();
                
                if(input.toLowerCase().contains("cubit"))
                {
                	cub = value;
                }
                else if(input.toLowerCase().contains("palm"))
                {
                	pal = value;
                }
                else if(input.toLowerCase().contains("digit"))
                {
                	dig = value;
                }
            }
            
            // cubits
            
            // meters
            meters = (cub * CUBIT + pal * PALM + dig * DIGIT) / MM_IN_METER;
            
            // feet
            temp = meters;
            
            feet = (int) (meters * 3.28);
            temp = temp - (feet / 3.28);
            // inches
            inches = (temp * 3.28 * 12) % 12;
            
            in = String.format("%.2f\"", inches);
            
            if(feet >= 1)
            {
                fe = String.format("%.0f' ", feet);
            }
            
            out.add(String.format("%.3fm %s%s", meters, fe, in));
        }
        
        // meters good
        // feet good
        // inches good
        for(int i = 0; i < out.size(); i++)
        {
            System.out.println(out.get(i));
        }
    }
}