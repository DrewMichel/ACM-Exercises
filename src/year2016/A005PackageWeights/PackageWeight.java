package year2016.A005PackageWeights;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.ArrayList;

/*
 * Sample output is reversed?
 *
 */

public class PackageWeight
{
	public static void main(String[] args)
	{
		ArrayList<Fastener> types = new ArrayList<>();
		ArrayList<Package> packages = new ArrayList<>();
		ArrayList<String> out = new ArrayList<>();
		
		ArrayList<String> packageName = new ArrayList<>();
		ArrayList<Double> packageWeight = new ArrayList<>();
		
		Scanner reader = null;
		
		String file = "info.txt";
		String[] input = null;
		
		int stage = 0;
		int index = -1;
		int counter = 0;
		
		double currentWeight = 0;
		double expectedWeight = 0;
		double minRange = 0, maxRange = 0;
		
		String typeName = "";
		String packName = "";
		
		try
		{
			reader = new Scanner(new FileInputStream(file));
			
			while(reader.hasNextLine())
			{
				if(stage == 0)
				{
					input = reader.nextLine().split(",");
					
					if(input[0].length() == 0)
					{
						stage++;
					}
					else
					{
						types.add(new Fastener(input[0], Double.parseDouble(input[1]), Double.parseDouble(input[2])));
					}
				}
				else if(stage == 1)
				{
					input = reader.nextLine().split(",");
					
					if(input[0].length() == 0)
					{
						stage++;
					}
					else if(input.length == 1)
					{
						packages.add(new Package(input[0]));
						index++;
					}
					else
					{
						typeName = input[0];
						
						// EVALUATE
						for(int i = 0; i < types.size(); i++)
						{
							if(typeName.equalsIgnoreCase(types.get(i).name))
							{
								packages.get(index).fasteners.add(new Fastener(types.get(i)));
							}
						}
						
						packages.get(index).quantities.add(Integer.parseInt(input[1]));
					}
				}
				else if(stage == 2)
				{
					input = reader.nextLine().split(",");
					
					if(input[0].length() == 0)
					{
						stage++;
					}
					else
					{
						packageName.add(input[0]);
						packageWeight.add(Double.parseDouble(input[1]));
					}
				}
			}
			
			reader.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		// Calculates expected weights for each package
		for(int i = 0; i < packages.size(); i++)
		{
			packages.get(i).calculateWeight();
		}
		
		for(int i = 0; i < packages.size(); i++)
		{
			for(int k = 0; k < packageName.size(); k++)
			{
				if(packageName.get(k).equals(packages.get(i).name))
				{
					System.out.println("REAL: " + packageName.get(k) + " " + packageWeight.get(k));
					System.out.println(packages.get(i));
					
					if(packages.get(i).matches(packageWeight.get(k)))
					{
						System.out.println("pass");
					}
					else if(packages.get(i).isWithinRange(packageWeight.get(k)))
					{
						System.out.println("ambiguous");
					}
					else
					{
						System.out.println("fail");
					}
					
					System.out.println("\n\n");
				}
			}
		}
		
		/*
		for(int i = 0; i < packages.size(); i++)
		{
			currentWeight = 0;
			expectedWeight = 0;
			minRange = 0;
			maxRange = 0;
			counter = 0;
			
			packName = "";
			
			// Calculate weights
			for(int k = 0; k < packages.get(i).fasteners.size(); k++)
			{
				expectedWeight += packages.get(i).fasteners.get(k).weight * packages.get(i).quantities.get(k);
				
				maxRange += expectedWeight + packages.get(i).quantities.get(k) * packages.get(i).fasteners.get(k).tolerance;
				
				minRange += expectedWeight - packages.get(i).quantities.get(k) * packages.get(i).fasteners.get(k).tolerance;
			}
			
			while(!packName.equals(packages.get(counter).name))
			{
				counter++;
				packName = packageName.get(counter)
				currentWeight = 
			}
		}
		*/
		
	}
	
	private static class Fastener
	{
		public String name;
		public double weight;
		public double tolerance;
		
		public Fastener()
		{
			name = "BAD";
			weight = Double.MIN_VALUE;
			tolerance = Double.MIN_VALUE;
		}
		
		public Fastener(Fastener f)
		{
			this.name = f.name;
			this.weight = f.weight;
			this.tolerance = f.tolerance;
		}
		
		public Fastener(String n, double w, double t)
		{
			name = n;
			weight = w;
			tolerance = t;
		}
		
		public boolean equals(Object other)
		{
			if(other == null)
			{
				return false;
			}
			
			if(other.getClass() != this.getClass())
			{
				return false;
			}
			
			Fastener temp = (Fastener) other;
			
			return temp.name.equals(this.name);
		}
		
		public String toString()
		{
			return String.format("%s,%.1f,%.1f", name, weight, tolerance);
		}
	}
	
	private static class Package
	{
		public String name;
		public ArrayList<Fastener> fasteners;
		public ArrayList<Integer> quantities;
		public double expectedWeight;
		public double range;
		
		public Package(String n)
		{
			name = n;
			fasteners = new ArrayList<>();
			quantities = new ArrayList<>();
		}
		
		public boolean equals(String other)
		{
			if(other == null)
			{
				return false;
			}
			
			return this.name.equals(other);
		}
		
		public void calculateWeight()
		{
			expectedWeight = 0;
			range = 0;
			
			for(int i = 0; i < fasteners.size(); i++)
			{
				expectedWeight += fasteners.get(i).weight * quantities.get(i);
				
				range += quantities.get(i) * fasteners.get(i).tolerance;
			}
		}
		
		public boolean matches(double real)
		{
			return (real == expectedWeight);
		}
		
		public boolean isWithinRange(double real)
		{
			return (real >= expectedWeight - range && expectedWeight + range >= real);
		}
		
		public String toString()
		{
			//return "Package: " + name + " Expected: " + expectedWeight + " Range: " + range + " Real Range: " + (expectedWeight - range) + "-" + (expectedWeight + range);
			
			String out = name + "," + expectedWeight + "\n";
			
			for(int i = 0; i < fasteners.size(); i++)
			{
				out += fasteners.get(i) + "," + quantities.get(i) + "\n";
			}
			
			return out;
		}
	}
}