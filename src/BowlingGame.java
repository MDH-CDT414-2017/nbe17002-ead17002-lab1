/** BowlingGame Score calculator 
 *
 * @author CDT414 Student: 
 * @version 1.0 
 * @date 2016-11-24
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BowlingGame {
	/** BowlingGame Score calculator constructor which require string as input 
	 * @param game Expected format "[n,n][n,n]..[n,n]"
	 * 
	 */	
	
	private String game;
	public BowlingGame(String strGame) {
		game = strGame;
	}
	
	/** getScore method returns a score of current Bowling game or -1 if error
	 * 
	 * @return Integer value of Bowling score, in case of error return value is -1 
	 */
	public int getScore() {
		//TODO: calculate the score of game and return correct value
		return(-1);
	}
	public boolean isValid() {
		Pattern p = Pattern.compile("(\\[(\\d|10),(\\d|10)\\]){9}(\\[\\d,(\\d|10)\\]\\[(\\d|10)\\]|\\[10,0\\]\\[(\\d|10),(\\d|10)\\]|\\[(\\d|10),(\\d|10)\\])");
		String stringToParse = new String(game);
		Matcher patternMatcher = p.matcher(stringToParse);
		if (patternMatcher.matches()) {
			//To elaborate, right now it doesn't check the content of the rounds
			return true;
		}
		else return false;
	}
}
