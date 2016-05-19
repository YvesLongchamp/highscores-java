package score;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Yves Longchamp and David Ringayen
 * 
 *         A class to operate our highscores but with an access to a server.
 * 
 */
public class HighScore3 {

	static final String URL_SCORES = "https://thingspeak.com/channels/111603/feed.csv";

	/**
	 * The main display all the scores stored in the server with the URL right
	 * above.
	 * 
	 * @param args
	 *            , useless here.
	 * 
	 */
	public static void main(String[] args) {
		int index = 0;
		List<String> newScores;
		HighScore3 HighScore1 = new HighScore3();

		try {
			newScores = HighScore1.getScores();
			while (index < newScores.size()) {
				System.out.println(newScores.get(index));
				index++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * The getScores method allows to get the scores from the URL of our
	 * ThingSpeak.
	 * 
	 * @return A list of Strings, the scores, and all the others informations.
	 * 
	 * @throws IOException
	 *             , if the BufferedReader has a problem.
	 * 
	 */
	public List<String> getScores() throws IOException {

		List<String> finalScores = new ArrayList<>();
		try {
			URL scoreURL = new URL(URL_SCORES);
			InputStream scores = scoreURL.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					scores));
			// Skipping the first line.
			reader.readLine();
			String readString = reader.readLine();
			while (readString != null) {

				if (readString.length() > 0) {
					finalScores.add(readString);
				}

				readString = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return finalScores;

	}

	/**
	 * 
	 * The tenBestScores take a list as a parameter, and return a array of the
	 * best players.
	 * 
	 * @param scores
	 *            , the list of the scores obtained by the getscores method.
	 * 
	 * @return BestPlayer[], an array of the 10 best players.
	 */
	public BestPlayer[] tenBestScores(List<String> scores) {
		List<BestPlayer> allBest = new ArrayList<>();
		String[] scorePostSplit;
		for (int i = 0; i < scores.size(); i++) {
			scorePostSplit = scores.get(i).split(",");
			allBest.add(new BestPlayer(Integer.parseInt(scorePostSplit[1]),
					scorePostSplit[2]));
		}
		Collections.sort(allBest);
		BestPlayer[] top10 = new BestPlayer[10];
		for (int j = 0; j < allBest.size() && j < 10; j++) {
			top10[j] = allBest.get(j);
		}
		return top10;
	}

	/**
	 * sendPlayer send a player to the "ThingSpeak server", and add it to the 10
	 * best.
	 * 
	 * @param p
	 *            , the BestPlayer we want to add.
	 */
	public void sendPlayer(BestPlayer p) {

		try {
			
			URL obj = new URL(URL_SCORES+"scores?="+Integer.toString(p.score) + "name?=" + p.player);
			HttpURLConnection con;
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
