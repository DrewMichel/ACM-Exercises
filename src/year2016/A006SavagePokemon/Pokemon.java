package year2016.A006SavagePokemon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.Map.Entry;

public class Pokemon
{
	public static final String FAILED = "Oh, oh!";
	public static final String SUCCESS = "Gotta Catch Them All!";
	
	public static void main(String[] args) throws IOException
	{
		// "poke.txt"
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
		HashMap<Integer, ArrayList<Integer>> locations = null;
		String line = null;
		String[] lineTokens = null;
		HashSet<Integer> vertices = null;
		boolean reached = true;
		boolean firstIteration = true;
		StringBuilder space = new StringBuilder(" ");
		StringBuilder sb;// = new StringBuilder();
		
		int numberOfParks = 0, numberOfSections = 0, numberOfConnections = 0;
		
		while((line = stdin.readLine()) != null)
		{
			reached = true;
			locations = new HashMap<>();
			vertices = new HashSet<>();
			
			if(firstIteration)
			{
				lineTokens = line.split(" ");
				firstIteration = false;
				numberOfParks = Integer.parseInt(lineTokens[0]);
				
				lineTokens = stdin.readLine().split(" ");
			
				numberOfSections = Integer.parseInt(lineTokens[0]);
				numberOfConnections = Integer.parseInt(lineTokens[1]);
			}
			else
			{
				lineTokens = line.split(" ");
				
				numberOfSections = Integer.parseInt(lineTokens[0]);
				numberOfConnections = Integer.parseInt(lineTokens[1]);
			}

			
			
			//System.out.println("SECTIONS: " + numberOfSections + " CONNECTIONS: " + numberOfConnections);
			
			for(int i = 0; i < numberOfConnections; i++)
			{
				lineTokens = stdin.readLine().split(" ");
				
				if(locations.get(Integer.parseInt(lineTokens[0])) == null)
				{
					ArrayList<Integer> temp = new ArrayList<>();
					
					temp.add(Integer.parseInt(lineTokens[1]));
					locations.put(Integer.parseInt(lineTokens[0]), temp);
				}
				else
				{
					locations.get(Integer.parseInt(lineTokens[0])).add(Integer.parseInt(lineTokens[1]));
				}
				
				vertices.add(Integer.parseInt(lineTokens[0]));
				vertices.add(Integer.parseInt(lineTokens[1]));
			}
			
			for(Entry<Integer, ArrayList<Integer>> entry: locations.entrySet())
			{
				reached = reached && canReach(locations, entry.getKey(), vertices);
				sb = new StringBuilder();
				
				for(int i = 0; i < entry.getValue().size(); i++)
				{
					sb.append(entry.getValue().get(i) + space.toString());
				}
				
				//System.out.println("KEY: " + entry.getKey() + " VERTICES: " + sb.toString());
			}
			
			if(reached == true)
			{
				System.out.println(SUCCESS);
			}
			else
			{
				System.out.println(FAILED);
			}
			
			/*
			for(int i = 0; i < lineTokens.length; i++)
			{
				System.out.print(lineTokens[i] + " ");
			}
			
			System.out.println();
			*/
		}
		
		stdin.close();
	}
	
	public static boolean canReach(HashMap<Integer, ArrayList<Integer>> locations, int startPoint, HashSet<Integer> vertices)
	{
		HashSet<Integer> visited = new HashSet<>();
		
		Stack<Integer> traversal = new Stack<>();
		
		ArrayList<Integer> currentEdges = new ArrayList<>();
		
		int iterator = 0;
		boolean ongoing = true;
		int currentPoint = startPoint;
		
		// driver
		while(ongoing == true)
		{
			currentEdges = locations.get(currentPoint);
			
			//if(!visited.contains(currentPoint))
			{
				visited.add(currentPoint);
				
				for(int i = 0; currentEdges != null && i < currentEdges.size(); i++)
				{
					if(!visited.contains(currentEdges.get(i)))
					{
						//System.out.println("ADDING: " + currentEdges.get(i));
						traversal.add(currentEdges.get(i));
					}
				}
			}
			
			if(traversal.isEmpty())
			{
				ongoing = false;
			}
			else
			{
				if(!visited.contains(traversal.peek()))
				{
					currentPoint = traversal.pop();
				}
				else
				{
					traversal.pop();
				}
				
			}
		}
		
		return visited.size() == vertices.size();
	}	
}