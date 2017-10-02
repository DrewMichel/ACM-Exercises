import java.util.Scanner;

public class Thief
{
    public static final String MARKER = "*", EMPTY = " ";
    
    public static void main(String[] args)
    {
        boolean[][] grid;
        
        Scanner keyboard = new Scanner(System.in);
        
        int size, modifier = 0;
        long blindSpots = 0, badSpots = 0;
        
        System.out.print("Enter size of grid: ");
        
        // Example grid goes from 0-16 where n is 16
        size = keyboard.nextInt() + 1;
        
        grid = new boolean[size][size];
        
        populateGrid(grid);
        
        fillGridDiag(grid);
        //fillGridVert(grid);
        
        fillPrimeGrid(grid);
        
        mirrorGrid(grid);
        
        printArray(grid);
        
        blindSpots = getBlindSpots(grid);
        badSpots = getBadSpots(grid);
        
        System.out.println("Number of blindspots: " + blindSpots);
        System.out.println("Number of badspots: " + badSpots);
    }
    
    public static void fillPrimeGrid(boolean[][] grid)
    {
        boolean insert = false;
        
        for(int i = 0; i < grid.length; i++)
        {
            for(int k = 0; k < grid[i].length; k++)
            {
                insert = false;
                
                if(i != 0 && k % i != 0 && isPrime((long) i))
                {
                    insert = true;
                }
                
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
        for(int i = 0; i < grid.length; i++)
        {
            for(int k = 0; k < grid[i].length; k++)
            {
                grid[i][k] = false;
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
            grid[y][x] = true;
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