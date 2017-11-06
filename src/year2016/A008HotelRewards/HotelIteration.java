package year2016.A008HotelRewards;

import java.util.*;
import java.io.*;

public class HotelIteration
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> data;
        ArrayList<Integer> out;
        
        String[] lineTokens = null;
        String line = null;
        
        int numberOfNights = 0, freePrice = 0, bestPrice = 0;
        
        int currentPrice = 0, totalPrice = 0;
        
        int totalTickets = 0, currentTickets= 0;

        try
        {
            while((line = stdin.readLine()) != null)
            {
                currentPrice = totalPrice = totalTickets = currentTickets = 0;
                data = new ArrayList<>();
                out = new ArrayList<>();

                lineTokens = line.split(" ");

                numberOfNights = Integer.parseInt(lineTokens[0]);
                freePrice = Integer.parseInt(lineTokens[1]);

                for(int i = 0; i < numberOfNights; i++)
                {
                    data.add(Integer.parseInt(stdin.readLine()));
                }

                // data acquired

                for(int i = 0; i < data.size(); i++)
                {
                    totalPrice += data.get(i);
                    currentPrice = totalPrice;
                    totalTickets = i + 1;
                    currentTickets = totalTickets;

                    for(int k = i + 1; k < data.size(); k++)
                    {
                        if(currentTickets >= freePrice)
                        {
                            currentTickets -= freePrice;
                            //System.out.println("NOT ADDING: " + data.get(k));
                        }
                        else
                        {
                            //System.out.println("ADDING: " + data.get(k));
                            currentPrice += data.get(k);
                            currentTickets++;
                        }

                    }

                    out.add(currentPrice);
                }

                bestPrice = getBestPrice(out);

                //displayArrayList(out);

                System.out.println(bestPrice);
            }
        }
        catch(Exception e)
        {

        }
    }
    
    public static void displayArrayList(ArrayList<Integer> arrayList)
    {
        for(int i = 0; i < arrayList.size(); i++)
        {
            System.out.println(arrayList.get(i));
        }
    }
    
    public static int getBestPrice(ArrayList<Integer> arrayList)
    {
        int lowest = Integer.MAX_VALUE;
        int current = 0;
        
        for(int i = 0; i < arrayList.size(); i++)
        {
            current = arrayList.get(i);
            if(lowest >  current)
            {
                lowest = current;
            }
        }
        
        return lowest;
    }
}