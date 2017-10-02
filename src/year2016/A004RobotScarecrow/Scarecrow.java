package year2016.A004RobotScarecrow;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileInputStream;

public class Scarecrow
{
	public static void main(String[] args)
	{
		Scanner reader = null;
		
		String file = "scarecrow.txt";
		
		int numberOfPosts = 0;
		int numberOfCommands = 0;
		int specificPost = 0;
		
		int[] commands = null;
		int[] posts = null;
		
		int index = 0;
		int position = 0;
		int command = 0;
		
		try
		{
			reader = new Scanner(new FileInputStream(file));
			
			numberOfPosts = reader.nextInt();
			numberOfCommands = reader.nextInt();
			specificPost = reader.nextInt();
			
			commands = new int[numberOfCommands];
			posts = new int[numberOfPosts];
			
			while(reader.hasNextInt())
			{
				commands[index] = reader.nextInt();
				index++;
			}
			
			reader.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		
		// Start at post 1
		posts[0]++;
		
		for(int i = 0; i < commands.length; i++)
		{
			command = commands[i];
			
			if(command == 1)
			{
				position = (position + 1) % numberOfPosts;
				posts[position]++;
			}
			else if(command == -1)
			{
				position = (position - 1) % numberOfPosts;
				posts[position]++;
			}
		}
		
		System.out.println(posts[specificPost - 1]);
	}
}