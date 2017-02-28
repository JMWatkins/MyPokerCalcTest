//import java.io.File;
//import java.io.FileNotFoundException;
import java.util.*;

public class SortHands {


	public static void main(String arg[]){

		Scanner in = new Scanner(System.in);
		/*File file = new File("poker-hands.txt");
		Scanner in;
		try {
		in = new Scanner(file);*/

		int counter = 0;
		boolean player1Active = true;
		String[] newHand = new String[5];

		ArrayList<MyHand> player1 = new ArrayList<MyHand>();
		ArrayList<MyHand> player2 = new ArrayList<MyHand>();

		while(in.hasNext()){
			String input = in.next();
			//System.out.println(input);
			if (counter  < 5){
				newHand[counter] = input;
				counter ++;
			}
			if (counter >= 5){
				counter = 0;
				if (player1Active)
					player1.add(new MyHand(newHand));
				else
					player2.add(new MyHand(newHand));
				player1Active = !player1Active;
			}
			//System.out.print(".");
		}
		in.close();
		//System.out.println("Hands built");

		int scoreP1 = 0;
		int scoreP2 = 0;

		// possibility for threading if computation takes too long
		// --------------- COMPARING HANDS -----------------
		if (player1.size() != player2.size())
			System.out.println("Not matching number of hands");
		for (int i = 0; i<player1.size(); i++){
			MyHand p1 = player1.get(i);
			MyHand p2 = player2.get(i);

			float p1S = p1.getHandVal();
			float p2S = p2.getHandVal();
			float[] print = {p1S, p2S};
			//printHands(print);

			if (p1S > p2S){
				scoreP1 ++;
			}
			else if (p1S < p2S){
				scoreP2 ++;
			}

			else{
				boolean winnerDecided = false;
				counter = 1;
				while (!winnerDecided){
					if (p1.NthHighestCard(counter) > p2.NthHighestCard(counter)){
						scoreP1 ++;
						winnerDecided = true;
					}
					else if (p1.NthHighestCard(counter) < p2.NthHighestCard(counter)){
						scoreP2 ++;
						winnerDecided = true;
					}
					else if (counter>5){
						System.out.println("All characters matched?!");
					}
					else{
						counter++;
					}
				}
			}
			//System.out.println(scoreP1 + "/" + scoreP2);
		}

		System.out.println("Player 1 wins: " + scoreP1 + " times");
		System.out.println("Player 2 wins: " + scoreP2 + " times");

		/*} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	// Troubleshoot tool;
	public static void printHands(float[] in){
		String ret [] = new String[2];
		for (int i = 0; i<2; i++){
			if (in[i]>=1000)
				ret[i]="Royal Flush";
			else if (in[i]>900)
				ret[i]="Straight Flush";
			else if (in[i]>800)
				ret[i]="Four of a Kind";
			else if (in[i]>700)
				ret[i]="Full House";
			else if (in[i]>600)
				ret[i]="Flush";
			else if (in[i]>500)
				ret[i]="Straight";
			else if (in[i]>400)
				ret[i]="Three of a Kind";
			else if (in[i]>300)
				ret[i]="Two Pair";
			else if (in[i]>200)
				ret[i]="Pair";
			else if (in[i]>100)
				ret[i]="High Card";
		}

		System.out.println(in[0] + "/" + in [1] + "   |   " + ret[0] + "/" +ret[1]);
	}
}
