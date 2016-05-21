package score;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Yves Longchamp and David Ringayen
 * 
 *         A class to operate, and test our highscores.
 *
 */
public class TestHighScore4 {

	static final String PATH_SCORES = "scoreSamples.txt";

	/**
	 * 
	 * The main method, where we can print on the console the previous scores,
	 * and a score of a player who enter his name.
	 * 
	 * @param args
	 *            is useless here.
	 * 
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		String name = " ";
		String Score= " ";
		boolean toSend = false;

		Path path = Paths.get(PATH_SCORES);

		//Path path = Paths.get(PATH_SCORES);
		
		HighScore3 highscore = new HighScore3();
		BestPlayer[] top10 = new BestPlayer[10];
		
		try {
			System.out.println("enter your name:");
			if (sc.hasNext()) {
				name = sc.next();
				sc.close();
			}

			List<String> Topscores = highscore.getScores();
			top10 = new BestPlayer[10];
			top10 = highscore.tenBestScores(Topscores);

			String games = " ";
			while (games != "yes"){
				
				
				
					for(int j = 0 ; j < top10.length && top10[j] != null ; j++) {
						System.out.println("The player : " + top10[j].getPlayer() + " ,has the score of : "
								+ top10[j].getScore());
				}
					
					System.out.println("Do  you want to start a new game ? (yes/no) : ");
					if (sc1.hasNext()) {
						games = sc1.next();
 
					}
					sc1.close();
				if (games == "yes"){
					List<String> scores = Files.readAllLines(path);
					int random = (int) (Math.random() * scores.size());
					Score = scores.get(random);
					System.out.println("The player : " + name + " has the score of : "+ Score);
					for (int j = 0 ; j < top10.length && top10[j] != null ; j++){
						if (Integer.parseInt(Score) >= (top10[j].getScore())){
							toSend = true;
						}
					}
				if ( toSend){
					BestPlayer p = new BestPlayer(Integer.parseInt(Score), name);
					highscore.sendPlayer(p);
				}
					
				}
					
			}
			
			
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	
		
	}

}

