package year2016.A009Ellipse;

import java.util.*;
import java.io.*;

// x(theta) = xc + a * cos(theta) * cos(phi) - b * sin(theta) * sin(phi)

// y(theta) = yc + a * cos(theta) * sin(phi) + b * sin(theta) * cos(phi)


public class EllipseDriver
{
	public static void main(String[] args) throws Exception
	{
		String file = "ellipse.txt";
		Scanner reader = new Scanner(new FileInputStream(file));
		
		ArrayList<Ellipse> uno = new ArrayList<>();
		ArrayList<Ellipse> dos = new ArrayList<>();
		
		String[] line = null;
		
		while(reader.hasNextLine())
		{
			line = reader.nextLine().split(" ");
			
			uno.add(new Ellipse(Double.parseDouble(line[0]),
								Double.parseDouble(line[1]),
								Double.parseDouble(line[2]),
								Double.parseDouble(line[3]),
								Double.parseDouble(line[4])));
			
			dos.add(new Ellipse(Double.parseDouble(line[5]),
								Double.parseDouble(line[6]),
								Double.parseDouble(line[7]),
								Double.parseDouble(line[8]),
								Double.parseDouble(line[9])));
		}
		
		reader.close();
		
		
		for(int i = 0; i < uno.size(); i++)
		{
			System.out.println(uno.get(i) + " " + dos.get(i));
			System.out.printf("%.5f\n\n", determinePerimeterDistance(uno.get(i), dos.get(i)));
		}
		
		//System.out.println();
	}
	
	// x(theta) = xc + a * cos(theta) * cos(phi) - b * sin(theta) * sin(phi)

	// y(theta) = yc + a * cos(theta) * sin(phi) + b * sin(theta) * cos(phi)

	// Round to 5 decimal spaces
	public static double determinePerimeterDistance(Ellipse one, Ellipse two)
	{
		double theta1 = one.theta;
		double phi1 = one.phi;
		
		double theta2 = two.theta;
		double phi2 = two.phi;
		
		// Check radian vs degrees
		double x1 = one.center + one.majorAxis * Math.cos(theta1) * Math.cos(phi1) - one.majorAxis * Math.sin(theta1) * Math.sin(phi1);
		
		double y1 = one.center + one.majorAxis * Math.cos(theta1) * Math.sin(phi1) + one.minorAxis * Math.sin(theta1) * Math.cos(phi1);
		
		double x2 = two.center + two.majorAxis * Math.cos(theta2) * Math.cos(phi2) - two.majorAxis * Math.sin(theta2) * Math.sin(phi2);
		
		double y2 = two.center + two.majorAxis * Math.cos(theta2) * Math.sin(phi2) + two.minorAxis * Math.sin(theta2) * Math.cos(phi2);
		
		// Sample shows seperate x center and y center
		// However, input only has 10 lines while ellipse uses 5-6
		
		// Incorrect
		double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		
		return distance;
	}
	
	private static class Ellipse
	{
		double center, majorAxis, minorAxis, phi, theta;
		
		public Ellipse()
		{
			center = 5;
			majorAxis = 5;
			minorAxis = 5;
			phi = 5;
			theta = 5;
		}
		
		public Ellipse(double center, double majorAxis, double minorAxis, double phi, double theta)
		{
			this.center = center;
			this.majorAxis = majorAxis;
			this.minorAxis = minorAxis;
			this.phi = phi;
			this.theta = theta;
		}
		
		public String toString()
		{
			return center + " " + majorAxis + " " + minorAxis + " " + phi + " " + theta;
		}
	}
}