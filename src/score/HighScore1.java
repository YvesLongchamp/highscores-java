package score;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Yves Longchamp and David Ringayen
 * 
 *         A class to operate our highscores but with an access to a server.
 *
 */
public class HighScore1 {

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
		HighScore1 HighScore1 = new HighScore1();

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
}
