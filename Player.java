package hackathon_project;

//Import Statements
import java.net.URL;
import javax.json.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.Arrays;



public class Player{
	private int[] champ_id = {266,412,23,79,69,136,13,78,14,1,202,9,43,111,240,99,103,2,164,112,34,27,86,127,57,25,28,105,74,238,68,82,37,96,55,117,22,30,12,122,67,110,77,89,126,134,80,92,121,42,268,51,76,85,3,45,432,150,90,104,254,10,39,64,420,60,106,20,4,24,102,429,36,427,131,223,63,113,8,154,421,133,84,163,18,120,15,236,107,19,72,54,157,101,17,75,58,119,35,50,91,40,115,245,61,114,31,33,7,16,26,56,222,83,6,203,21,62,53,98,201,5,29,11,44,32,41,48,38,161,143,267,59,81};
	private long id;
	private String tier;
	private String division;
	private int wins;
	private int losses;
	private String queue;
	private boolean isSeries = false;
	private int wincount = 0;
	private int losscount = 0;
	private String mainChamp;
	private int mainChampID;
	
	public Player(String summoner_name){
		System.out.println(champ_id.length);
		try
		{
			URL myURL = new URL("https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/" + summoner_name + "?api_key=RGAPI-115fc5c0-f84f-4014-8dc1-c6162cbf652f");
			
			try 
			{

				InputStream is = myURL.openStream();
				javax.json.stream.JsonParser parser = Json.createParser(is);
				while (parser.hasNext()) {
					javax.json.stream.JsonParser.Event e = parser.next();
					if (e == javax.json.stream.JsonParser.Event.KEY_NAME){
						switch (parser.getString()) {
						 	case "id":
						 		parser.next();
						 		id = parser.getLong();
						}
					}	
				}
				getSummonerStats();
				findWinRates();
				
			}
			catch(IOException e)
			{
				System.out.println("Invalid API Key");
			}
		}
		catch(MalformedURLException e)
		{
			System.out.println("Invalid URL");
		}
		
	}
	long getID()
	{
		return id;
	}
	
	String getTier()
	{
		return tier;
	}
	
	boolean isSeries()
	{
		return isSeries;
	}
	
	String getDivision()
	{
		return division;
	}
	
	String getWinLoss()
	{
		return wins+"/"+losses;
	}
	
	String getQueue()
	{
		return queue;
	}
	
	double getWinRate()
	{
		return 100.0 * ((double)wins / (double)(wins + losses));
	}
	
	void getSummonerStats()
	{
		try
		{
			URL myURL = new URL("https://na.api.pvp.net/api/lol/na/v2.5/league/by-summoner/" + id + "/entry?api_key=RGAPI-115fc5c0-f84f-4014-8dc1-c6162cbf652f");
			try 
			{
				InputStream is = myURL.openStream();
				javax.json.stream.JsonParser parser = Json.createParser(is);
				while (parser.hasNext()) {
					javax.json.stream.JsonParser.Event e = parser.next();
					if (e == javax.json.stream.JsonParser.Event.KEY_NAME){
						switch (parser.getString()) {
							case "queue":
								parser.next();
								String temp1 = parser.getString();
								if (temp1.equals("RANKED_SOLO_5x5"))
								{
									queue = temp1;
									break; 
								}
								else
									return;
							case "miniSeries":
								isSeries = true;
								break;
						 	case "tier":
						 		parser.next();
						 		tier = parser.getString();
						 		break;
						 	case "division":
						 		parser.next();
						 		division = parser.getString();
						 		break;
						 	case "losses":
						 		if(isSeries)
						 		{
						 			if(losscount != 0)
						 			{
						 				parser.next();
								 		int temp2 = parser.getInt();
								 		if(temp2 != 0)
								 		{
								 			losses = temp2;
								 		}
								 		break;
						 			}
						 			losscount++;
						 			parser.next();
						 			break;
						 		}
						 		else
						 		{
							 		parser.next();
							 		int temp2 = parser.getInt();
							 		if(temp2 != 0)
							 		{
							 			losses = temp2;
							 		}
							 		break;
						 		}
						 	case "wins":
						 		if(isSeries)
						 		{	
						 			if(wincount != 0)
						 			{
						 				parser.next();
								 		int temp3 = parser.getInt();
								 		if(temp3 != 0)
								 		{
								 			wins = temp3;
								 		}
								 		break;
						 			}
						 			wincount++;
						 			parser.next();
						 			break;
						 		}
						 		else
						 		{
							 		parser.next();
							 		int temp3 = parser.getInt();
							 		if(temp3 != 0)
							 		{
							 			wins = temp3;
							 		}
							 		break;
						 		}
						}
					}	
				}
				
			}
			catch(IOException e)
			{
				System.out.println("Invalid API Key");
			}
		}
		catch(MalformedURLException e)
		{
			System.out.println("Invalid URL");
		}

	}
	
	void findWinRates()
	{
		int champion = 0;
		playerData pd = new playerData(id);
	}
	
	String getMain()
	{
		return mainChamp;
	}
		
	}
