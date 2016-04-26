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
public class TestHighScore1 {

	static final String PATH_SCORES = "/Users/yves.longchamp/Desktop/scoreSamples.txt";

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
		String name = "";
		String score = "";

		Path path = Paths.get(PATH_SCORES);

		try {

			if (sc.hasNext()) {
				name = sc.next();
			}

			List<String> scores = Files.readAllLines(path);
			int random = (int) (Math.random() * scores.size());
			score = scores.get(random);

		} catch (IOException e) {

			e.printStackTrace();
		}
		System.out.println("The player : " + name + " has the score of : "
				+ score);
		sc.close();
	}

}
