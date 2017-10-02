package year2016.A007SpectralComposition;

import java.util.*;
import java.io.*;

public class Spectral
{
    
    public static void main(String[] args)
    {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> out = new ArrayList<>();
        //ArrayList<Integer> updates = new ArrayList<>();
        //ArrayList<Integer> queries = new ArrayList<>();
        
        int[] elementRange = null;
        
        Scanner reader = null;
        
        String file = "spectral.txt";
        
        String[] line = null;
        
        int index = 0, temp = 0;
        
        int min = 0, max = 0, pos = 0;
        
        try
        {
            reader = new Scanner(new FileInputStream(file));
            
            while(reader.hasNextInt())
            {
                list.add(reader.nextInt());
            }
            
            /*
            // Outputs first line integers
            for(int i = 0; i < list.size(); i++)
            {
                System.out.println(list.get(i));
            }
            */
            // Clears empty line
            reader.nextLine();
            
            // read in updates and queries
            // HERE
            
            while(reader.hasNextLine())
            {
                line = reader.nextLine().split(" ");
                
                if(line[0].equalsIgnoreCase("A"))
                {
                    index = Integer.parseInt(line[1]);
                    
                    temp = list.get(index);
                    
                    list.set(index, list.get(index + 1));
                    
                    list.set(index + 1, temp);
                }
                else if(line[0].equalsIgnoreCase("Q"))
                {
                    min = Integer.parseInt(line[1]);
                    
                    max = Integer.parseInt(line[2]);
                    
                    pos = Integer.parseInt(line[3]);
                    
                    elementRange = new int[max + 1 - min];
                    
                    for(int i = 0; i < elementRange.length; i++)
                    {
                        elementRange[i] = list.get(min);
                        min++;
                    }
                    
                    sortArray(elementRange);
                    /*
                    for(int i = 0; i < elementRange.length; i++)
                    {
                        System.out.print(elementRange[i] + " ");
                    }
                    
                    System.out.println();
                    */
                    
                    out.add(elementRange[pos]);
                }
            }
            
            // Prints list elements
            // Should use separate list for changes?
            for(int i = 0; i < out.size(); i++)
            {
                if(out.get(i) != null && out.get(i) > 0)
                {
                    System.out.println(out.get(i));
                }
            }
            
            // Closes input stream
            reader.close();
            
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public static void sortArray(int[] array)
    {
        int temp = 0;
        
        int offset = 0;
        
        for(int i = 0; i < array.length; i++)
        {
            for(int k = 0; k < array.length - 1; k++)
            {
                if(array[k] > array[k + 1])
                {
                    temp = array[k];
                    
                    array[k] = array[k + 1];
                    
                    array[k + 1] = temp;
                }
            }
        }
    }
}