package hackathon_project;
//Unfinished
//Import Statements
import java.net.URL;
import javax.json.*;
import java.io.*;
import java.net.MalformedURLException;

public class playerData {
	
	private int[][] playerD = new int[6][134];
	private int id = 0;
	private int numPlayed = 2;
	private int numWin = 1;
	private int Kills = 3;
	private int Deaths = 4;
	private int Assists = 5;
	private int champ1ID;
	private int champ2ID;
	private int champ3ID;
	private double winrate1;
	private double winrate2;
	private double winrate3;
	
	
	public playerData(long summoner_id){
		int i = -1;
		try
		{
			URL myURL = new URL("https://na.api.pvp.net/api/lol/na/v1.3/stats/by-summoner/" + summoner_id + "/ranked?season=SEASON2017&api_key=RGAPI-115fc5c0-f84f-4014-8dc1-c6162cbf652f");
			try 
			{	InputStream is = myURL.openStream();
				javax.json.stream.JsonParser parser = Json.createParser(is);
				while (parser.hasNext()) {
					
					javax.json.stream.JsonParser.Event e = parser.next();
					if (e == javax.json.stream.JsonParser.Event.KEY_NAME){
						switch (parser.getString()) {
						 	case "id":
						 		parser.next();
						 		int temp1 = parser.getInt();
						 		i++;
						 		playerD[id][i] = temp1;
						 		break;
						 	case "totalSessionsPlayed":
						 		parser.next(); 
						 		int temp2 = parser.getInt();
						 		playerD[numPlayed][i] = temp2;
						 		break;
						 	case "totalSessionsWon":
						 		parser.next();
						 		int temp3 = parser.getInt();
						 		playerD[numWin][i] = temp3;
						 		break;
						 	case "totalChampionKills":
						 		parser.next();
						 		int temp4 = parser.getInt();
						 		playerD[Kills][i]=temp4;
						 		break;
						 	case "totalAssists":
						 		parser.next();
						 		int temp5 = parser.getInt();
						 		playerD[Assists][i]=temp5;
						 		break;
						 	case "totalDeathsPerSession":
						 		parser.next();
						 		int temp6 = parser.getInt();
						 		playerD[Deaths][i]=temp6;
						 		break;
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
	
	int getPlayed()
	{
		return numPlayed;
	}
	int getWins()
	{
		return numWin;
	}
	double getWinrate()
	{
		if(numWin == 0 && numPlayed == 0)
			return 0.0;
		return 100 * (double) numWin / (double) (numPlayed);
	}

	int[][] getData()
	{
		return playerD;
	}
	String getKDA()
	{
		return Kills + "/" + Deaths + "/" + Assists;
	}
	
	void findMain()
	{
		
	}
	double getKDARatio()
	{
		return (double)( Kills+Assists ) / (double) Deaths;
	}
}
