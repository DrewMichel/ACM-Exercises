package year2016.A002ThiefFWX;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;

/**
 * Created by Andrew Michel on 10/27/2017.
 */
public class ThiefGenerator
{
    public static final long NANOSECONDS_IN_SECOND = 1000000000;
    public static final long NANOSECONDS_IN_MINUTE = NANOSECONDS_IN_SECOND * 60;
    public static final long NANOSECONDS_IN_HOUR = NANOSECONDS_IN_MINUTE * 60;
    public static final int MAXIMUM_RANGE = 1000000;
    public static final String MARKER = "*", EMPTY = " ";

    public static void main(String[] args)
    {
        boolean[][] grid;

        int size = 1, modifier = 0;
        long blindSpots = 0, badSpots = 0;
        double startTime = 0, finishTime = 0;

        double hours = 0, minutes = 0, seconds = 0;

        PrintWriter pw = null;

        File outFile = new File("thief.txt");

        try
        {
            pw = new PrintWriter(new FileOutputStream(outFile));
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        while(size <= MAXIMUM_RANGE)
        {
            // Example grid goes from 0-16 where n is 16
            startTime = System.nanoTime();
            grid = new boolean[size+1][size+1];

            populateGrid(grid);

            fillGrid(grid);

            long sum = 0;

            boolean goodValue = false;

            for(int i = 0; i < grid.length; i++)
            {
                for(int k = 0; k < grid[i].length; k++)
                {
                    for(int j = 0; j < i && j < k; j++)
                    {
                        if((j != 0) && (k % j != 0) && (i % j != 0))
                        {
                            goodValue = true;
                        }

                        //System.out.println("I: " + i + " K: " + k + " J: " + j);
                    }

                    if(goodValue)
                    {
                        sum++;
                    }

                    goodValue = false;
                }
            }

            //System.out.println("Original: " + (size - 1) + " sum: " + sum );

        /*
        fillGridDiag(grid);
        //fillGridVert(grid);

        fillPrimeGrid(grid);
        */
            //mirrorGrid(grid);

            //printArray(grid);

            blindSpots = getBlindSpots(grid);
            badSpots = getBadSpots(grid);
            finishTime = (System.nanoTime() - startTime);

            hours = finishTime / NANOSECONDS_IN_HOUR;
            finishTime = finishTime % NANOSECONDS_IN_HOUR;

            minutes = finishTime / NANOSECONDS_IN_MINUTE;
            finishTime = finishTime % NANOSECONDS_IN_MINUTE;

            seconds = finishTime / NANOSECONDS_IN_SECOND;
            finishTime = finishTime % NANOSECONDS_IN_SECOND;

            pw.println(String.format("INPUT: %7d, OUTPUT: %13d, VALID SPOTS: %13d, HOURS: %3.0f, MINUTES: %3.0f, SECONDS: %14.10f", size, badSpots, blindSpots, hours, minutes, seconds));


            pw.flush();

            size++;
        }

        pw.close();

        System.out.println("Output to: " + outFile);
    }

    public static void fillGrid(boolean[][] grid)
    {
        for(int i = 1; i < grid.length; i++)
        {
            for(int k = 1; k < grid[i].length; k++)
            {
                for(int j = 2; j < grid[i].length; j++)
                {
                    if((j != 0) && (k % j == 0) && (i % j == 0))
                    {
                        insert(grid, k, i, true);
                    }
                }
            }
        }
    }

    public static void fillPrimeGrid(boolean[][] grid)
    {
        boolean insert = false;

        for(int i = 0; i < grid.length; i++)
        {
            for(int k = 0; k < grid[i].length; k++)
            {
                insert = false;

                insert(grid, k, i, insert);
            }
        }
    }

    public static boolean isPrime(long number)
    {
        if(number == 2)
        {
            return true;
        }
        else if(2 > number)
        {
            return false;
        }
        else if(number % 2 == 0)
        {
            return false;
        }

        for(int i = 3; i * i <= number; i += 2)
        {
            if(number % i == 0)
            {
                return false;
            }
        }


        return true;
    }

    /*
    public static void fillGridVert(boolean[][] grid)
    {
        boolean insert;

        for(int i = 0; i < grid.length; i++)
        {
            for(int k = 0; k < grid[i].length; k++)
            {
                insert = false;

                if(k != 0)
                {
                    if(i % k == 1)
                    {
                        insert = true;
                    }
                }

                if(insert(grid,k, i, insert))
                {
                    //break;
                }
            }
        }
    }
    */
    public static void fillGridDiag(boolean[][] grid)
    {
        boolean insert;

        // Start at (1, 1) or (0,0)?
        for(int i = 0; i < grid.length; i++)
        {
            for(int k = 0; k < grid[i].length; k++)
            {
                insert = false;

                if(i != 0)
                {
                    if(k % i == 1)
                    {
                        insert = true;
                    }
                }

                if(insert(grid, k, i, insert))
                {
                    //break;
                }
            }
        }
    }

    public static void populateGrid(boolean[][] grid)
    {
        for(int i = 1; i < grid.length; i++)
        {
            for(int k = 1; k < grid[i].length; k++)
            {
                grid[i][k] = true;
            }
        }

        for(int i = 0; i < 2; i++)
        {
            for(int k = 0; k < 2; k++)
            {
                grid[i][k] = true;
            }
        }
    }

    public static void mirrorGrid(boolean[][] grid)
    {
        for(int i = 0; i < grid.length; i++)
        {
            for(int k = 0; k < grid[i].length; k++)
            {
                if(grid[i][k])
                {
                    grid[k][i] = true;
                }
            }
        }
    }

    public static boolean insert(boolean[][] grid, int x, int y, boolean insert)
    {
        boolean value = isInsideGrid(grid, x, y);

        if(value && insert)
        {
            grid[y][x] = false;
        }

        return value;
    }

    public static void printArray(boolean[][] grid)
    {


        for(int i = grid.length - 1; i >= 0; i--)
        {
            System.out.printf("%-4d|", i);

            for(int k = 0; k < grid[i].length; k++)
            {
                if(grid[i][k] == true)
                {
                    System.out.printf("%-5s", MARKER);
                }
                else
                {
                    System.out.printf("%-5s", EMPTY);
                }


            }

            System.out.println("\n");
        }

        for(int i = 0; i < grid.length; i++)
        {
            System.out.print("_____");
        }

        System.out.println();

        System.out.printf("%-5s","");

        for(int i = 0; i < grid.length; i++)
        {
            System.out.printf("%-5d", i);
        }




        System.out.println("\n");
    }

    public static long getBadSpots(boolean[][] grid)
    {
        long bad = 0;

        for(int i = 0; i < grid.length; i++)
        {
            for(int k = 0; k < grid[i].length; k++)
            {
                if(grid[i][k] == true)
                {
                    bad++;
                }
            }
        }

        return bad;
    }

    public static long getBlindSpots(boolean[][] grid)
    {
        long blind = 0;

        for(int i = 0; i < grid.length; i++)
        {
            for(int k = 0; k < grid[i].length; k++)
            {
                if(grid[i][k] == false)
                {
                    blind++;
                }
            }
        }

        return blind;
    }

    public static boolean isInsideGrid(boolean[][] grid, int x, int y)
    {
        return ((y >= 0 && y < grid.length) && (x >= 0 && x < grid[y].length));
    }
}
