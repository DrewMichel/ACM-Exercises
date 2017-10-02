package year2016.A010Calendar;

import java.util.*;
import java.io.*;

public class ICalendar
{
	// PUBLISH creates new calendar events (new UID),
	// or completely supersedes the definition of existing
	// events (matching apreviously defined UID).
	
	// ADD adds new occurrences to events with matching UIDs.
	
	// CANCEL removes either occurrences from events,
	// or entire events, for existing UIDs.
	
	//BEGIN:VEVENT
	//event details
	//END:VEVENT
	
	// A year is a leap year if it is a multiple of 400, or
	// if it is a multiple of 4 and not a multiple of 100
	
	// Constants
	public static final String BEGIN = "BEGIN", METHOD = "METHOD",
								VCALENDAR = "VCALENDAR", VEVENT = "VEVENT",
								SUMMARY = "SUMMARY", END = "END",
								PUBLISH = "PUBLISH", ADD = "ADD",
								CANCEL = "CANCEL", UID = "UID",
								LOCATION = "LOCATION",
								DTSTART = "DTSTART", DTEND = "DTEND",
								DURATION = "DURATION",
								SEQUENCE = "SEQUENCE", RDATE = "RDATE",
								RECURRENCE = "RECURRENCE";
	
	public static void main(String[] args) throws Exception
	{
		String file = "calendar.txt";
		
		Scanner reader = new Scanner(new FileInputStream(file));
		
		ArrayList<Data> data = new ArrayList<>();
		
		String[] line = null;
		
		boolean event = false;
		boolean running = false;
		
		String id = "";
		
		String summary = "";
		
		String location = "";
		
		String dtstart = "";
		
		String dtend = "";
		
		String duration = "";
		
		String sequence = "";
		
		String rdate = "";
		
		String recurrence = "";
		
		String command = "";
		
		while(reader.hasNextLine())
		{
			line = reader.nextLine().split(":");
			
			// Unintended nextlines
			if(line.length > 0)
			{
				// If length == 1
				// Date to search for events - occurs at end of file
				// Otherwise, command branching
				if(line.length == 1)
				{
					// Catch date value
				}
				else
				{
					if(line[0].equals(BEGIN))
					{
						running = line[1].equals(VCALENDAR);
					}
					
					while(running == true && reader.hasNextLine())
					{
						line = reader.nextLine().split(":");
						
						if(line[0].equals(METHOD))
						{
							// publish
							// add
							// remove
							if(line[1].equals(PUBLISH))
							{
								command = PUBLISH;
							}
							else if(line[1].equals(ADD))
							{
								command = ADD;
							}
							else if(line[1].equals(CANCEL))
							{
								command = CANCEL;
							}
						}
						else if(line[0].equals(BEGIN))
						{
							if(line[1].equals(VEVENT))
							{
								id = "";
		
								summary = "";
								
								location = "";
								
								dtstart = "";
								
								dtend = "";
								
								duration = "";
								
								sequence = "";
								
								rdate = "";
								
								recurrence = "";
							}
						}
						else if(line[0].equals(END))
						{
							if(line[1].equals(VCALENDAR))
							{
								running = false;
							}
							else if(line[1].equals(VEVENT))
							{
								// NEEDS TO CHECK COMMAND TO PUBLISH, ADD, OR CANCEL
								if(command.equals(ADD))
								{
									data.add(new Data(id, summary, location, dtstart, dtend, duration, sequence, rdate, recurrence));
								}
								else if(command.equals(CANCEL))
								{
									Data temp = new Data(id);
									
									if(data.contains(temp))
									{
										data.remove(temp);
									}
								}
								else if(command.equals(PUBLISH))
								{
									Data temp = new Data(id);
									
									if(data.contains(temp))
									{
										data.remove(temp);
									}
									
									data.add(new Data(id, summary, location, dtstart, dtend, duration, sequence, rdate, recurrence));
								}
							}
						}
						else if(line[0].equals(UID))
						{
							id = line[1];
						}
						else if(line[0].equals(SUMMARY))
						{
							summary = line[1];
						}
						else if(line[0].equals(LOCATION))
						{
							location = line[1];
						}
						else if(line[0].contains(DTSTART))
						{
							// CHECK FOR SEMI-COLON
							dtstart = line[1];
						}
						else if(line[0].contains(DTEND))
						{
							// CHECK FOR SEMI-COLON
							dtend = line[1];
						}
						else if(line[0].equals(DURATION))
						{
							duration = line[1];
						}
						else if(line[0].equals(SEQUENCE))
						{
							sequence = line[1];
						}
						else if(line[0].equals(RDATE))
						{
							rdate = line[1];
						}
						else if(line[0].equals(RECURRENCE))
						{
							recurrence = line[1];
						}
					}
				}
			}
		}
		
		System.out.println("TESTING:");
		
		for(int i = 0; i < data.size(); i++)
		{
			System.out.println(data.get(i) + "\n");
		}
	}
	
	private static class Data
	{
		String id;
		
		String summary;
		
		String location;
		
		String dtstart;
		
		String dtend;
		
		String duration;
		
		String sequence;
		
		String rdate;
		
		String recurrence;
		
		public Data(String id, String summary, String location,
					String dtstart, String dtend, String duration,
					String sequence, String rdate, String recurrence)
		{
			this.id = id;
			this.summary = summary;
			this.location = location;
			this.dtstart = dtstart;
			this.dtend = dtend;
			this.duration = duration;
			this.sequence = sequence;
			this.rdate = rdate;
			this.recurrence = recurrence;
		}
		
		public Data(String id)
		{
			this.id = id;
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
			
			Data temp = (Data) other;
			
			return temp.id.equals(this.id);
		}
		
		public String toString()
		{
			return "UID: " + id + "\nSUMMARY: " + summary +
			"\nLOCATION:" + location + "\nDTSTART: " + dtstart +
			"\nDTEND: " + dtend + "\nDURATION: " + duration +
			"\nSEQUENCE: " + sequence + "\nRDATE: " + rdate +
			"\nRECURRENCE: " + recurrence;
		}
	}
}