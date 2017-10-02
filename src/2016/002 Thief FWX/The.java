import java.util.Scanner;
public class The
{
    public static void main(String[] args)
    {
        Scanner ke = new Scanner(System.in);
        
        int in = ke.nextInt() + 1;
        
        boolean[][] g = new boolean[in][in];
        
        for(int i = 1; i < g.length; i++)
        {
            for(int k = 1; k < g[i].length; k++)
            {
                g[i][k] = true;
            }
        }
        
        for(int i = 0; i < 2; i++)
        {
            for(int k = 0; k < 2; k++)
            {
                g[i][k] = true;
            }
        }
        
        for(int i = 1; i < g.length; i++)
        {
            for(int k = 1; k < g[i].length; k++)
            {
                for(int j = 2; j < g[i].length; j++)
                {
                    if(i % j == 0 && k % j == 0)
                    {
                        g[i][k] = false;
                    }
                }
            }
        }
        
        
        for(int i = 0; i < g.length; i++)
        {
            for(int k = 0; k < g[i].length; k++)
            {
                if(g[i][k])
                {
                    System.out.printf("*");
                }
                else
                {
                    System.out.printf(" ");
                }
            }
            
            System.out.println();
        }
        
        int blind = 0, bad = 0;
        
        for(int i = 0; i < g.length; i++)
        {
            for(int k = 0; k < g[i].length; k++)
            {
                if(g[i][k])
                {
                    blind++;
                }
                else
                {
                    bad++;
                }
            }
        }
        
        System.out.println("BAD: " + bad);
        System.out.println("BLIND: " + blind);
    }
    
    
}