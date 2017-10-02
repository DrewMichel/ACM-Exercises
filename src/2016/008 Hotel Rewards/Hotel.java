import java.util.*;
import java.io.*;

public class Hotel
{
    public static void main(String[] args)
    {
        ArrayList<Integer> data = new ArrayList<>();
        
        String[] line = null;
        
        Scanner reader = null;
        
        String file = "hotel.txt";
        
        int numberOfNights = 0;
        
        int freePrice = 0;
        
        int bestPrice = 0;
        
        try
        {
            reader = new Scanner(new FileInputStream(file));
            
            if(reader.hasNextLine())
            {
                line = reader.nextLine().split(" ");
                
                numberOfNights = Integer.parseInt(line[0]);
                
                freePrice = Integer.parseInt(line[1]);
                
                for(int i = 0; i < numberOfNights; i++)
                {
                    data.add(reader.nextInt());
                }
            }
            else
            {
                System.out.println("No lines detected");
            }
            
            reader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        
        //calcPrice(data, out, freePrice,0,0,0);
        
        bestPrice = getBestPrice(data, freePrice);
        
        System.out.println(bestPrice);
    }
    
    /*
    // Deprecated - see calcPrice
    public static int calculatePrice(ArrayList<Integer> array, int cost)
    {
        ArrayList<Integer> range = new ArrayList<>();
        
        int best = 0, current = 0, total = 0, carry = 0, sum = 0, index = 0;
        
        while(cost > sum && index < array.size())
        {
            sum += array.get(index);
            
            index++;
        }
        
        carry = sum - cost;
        
        for(int i = index; i < array.size(); i++)
        {
            range.add(array.get(i));
        }
        
        return best;
    }
    */
    
    // wrapper method
    public static int getBestPrice(ArrayList<Integer> array, int cost)
    {
        ArrayList<Integer> out = new ArrayList<>();
        
        int bestPrice = 0;
        
        calcPrice(array, out, cost, 0, 0, 0);
        
        bestPrice = calcBestPrice(out);
        
        return bestPrice;
    }
    
    // recursive calculate
    public static void calcPrice(ArrayList<Integer> array, ArrayList<Integer> out, int cost, int total, int points, int index)
    {
       // Inserts at end
       if(!(index < array.size()))
       {
           out.add(total);
           return;
       }
       
       // Uses free night
       if(points >= cost)
       {
           calcPrice(array, out, cost, total, points - cost, index + 1);
       }
       
       // Ignores free night
       calcPrice(array, out, cost, total + array.get(index), points + 1, index + 1);
    }
    
    // Iterates through arraylist and returns lowest value
    public static int calcBestPrice(ArrayList<Integer> array)
    {
        boolean firstIteration = true;
        
        int bestPrice = Integer.MIN_VALUE;
        
        for(int i = 0; i < array.size(); i++)
        {
            if(firstIteration == true)
            {
                firstIteration = false;
                bestPrice = array.get(i);
            }
            else if(bestPrice > array.get(i))
            {
                bestPrice = array.get(i);
            }
        }
        
        return bestPrice;
    }
     
}