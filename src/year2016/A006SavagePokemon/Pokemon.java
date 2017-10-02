package year2016.A006SavagePokemon;

import java.util.*;
import java.io.*;

/**
 * Needs multi-noded list or tree
 * Check MST - Minimum Spanning Tree
 *
 */
public class Pokemon
{
    public static void main(String[] args)
    {
        Scanner reader = null;
        
        String file = "pokemon.txt";
        
        int numberOfParks = 0;
        
        int numberOfSections = 0, numberOfConnections = 0;
        
        try
        {
            reader = new Scanner(new FileInputStream(file));
            
            numberOfParks = reader.nextInt();
            
            while(reader.hasNextLine())
            {
                numberOfSections = reader.nextInt();
                numberOfConnections = reader.nextInt();
                
                for(int i = 0; i < numberOfConnections; i++)
                {
                    
                }
            }
            
            reader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}