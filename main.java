package hackathon_project;


import java.util.Scanner;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your Summoner Name");
		Player player = new Player(scan.next());
		System.out.println("Summoner ID: " + player.getID());
		System.out.println("Queue: " + player.getQueue());
		System.out.println("Tier: " + player.getTier().toLowerCase());
		System.out.println("In Series? " + player.isSeries());
		System.out.println("Win Rate: " + player.getWinRate());
		System.out.println("Win/Loss: " + player.getWinLoss());
		System.out.println("Highest Win Rate Champ: " + player.getMain());
		scan.close();
	}

}
