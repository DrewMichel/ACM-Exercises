package year2015.A001MagicalThree;

import java.util.*;
import java.io.*;

public class MagicThree
{
    public static final int MAX_BASE = 10;
    public static final char DESIRED_CHAR = '3';
    public static final int DESIRED_INTEGER = DESIRED_CHAR;
    
    public static void main(String[] args) throws Exception
    {
        // 2,147,483,648 --- 2^31
        //System.out.println((long)Math.pow(2, 31));
        
        // 2,147,483,647 --- 1 less than 2^31
        //System.out.println(Integer.MAX_VALUE);
        
        String file = "magic.txt";
        
        Scanner reader = new Scanner(new FileInputStream(file));
        
        ArrayList<Long> numbers = new ArrayList<>();
        
        ArrayList<Long> out = new ArrayList<>();
        
        int index = -1;
        
        boolean found = false;
        
        String convert = "";
        
        while(reader.hasNextLong())
        {
            numbers.add(reader.nextLong());
        }
        
        
        for(int i = 0; i < numbers.size(); i++)
        {
            found = false;
            index = -1;
            convert = "";
            
            
            for(int k = 4; k <= MAX_BASE && found == false; k++)
            {
                convert = Long.toString(numbers.get(i), k);
                
                if(convert.charAt(convert.length() - 1) == DESIRED_INTEGER)
                {
                    found = true;
                    
                    index = k;
                }
            }
            
            if(found == true)
            {
                //out.add(Long.parseLong(Long.toString(numbers.get(i), index)));
                out.add((long)index);
            }
            else
            {
                out.add(-1L);
            }
        }
        
        for(int i = 0; i < out.size(); i++)
        {
            if(out.get(i) == -1L)
            {
                System.out.println("No such base");
            }
            else
            {
                System.out.println(out.get(i));
            }
        }
        
        //System.out.println(Double.toString(104D, 101));
        
        //System.out.println(Long.toString(104, 101));
         
         // 01111101000
         //System.out.println(convertBase(1000L, 2));
         //Long value = 1000L;
         
         //System.out.println(Long.toString(value, 2));
    }
    
    // Return String for charAt( length() -1) ?
    public static Long convertBase(Long number, int base)
    {
        Long num = null;
        
        String medium = number.toString();
        
        String converted = "";
        
        Long currentValue = null;
        
        int difference = MAX_BASE - base;
        
        Long carry = 0L, real = 0L;
        
        for(int i = medium.length() - 1; i >= 0; i--)
        {
            currentValue = Long.parseLong(medium.substring(i, i + 1));
            
            // real = ((currentValue + carry) % base);
            real = (currentValue % base + carry);
            
            carry = currentValue / base;
            
            converted = real + converted;
        }
        
        converted = carry + converted;
        
        System.out.println(converted);
        
        num = Long.parseLong(converted);
        
        return num;
    }
}