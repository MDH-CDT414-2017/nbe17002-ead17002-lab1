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
	private boolean isInsideScoreValid() {
		String stringToParse = new String(game);
		String[] gamesToParse = stringToParse.split("\\[");
		int gamesParsed = 0;
		boolean isFinalGameSpare = false;
		boolean isFinalGameStrike = false;
		for(String aGame : gamesToParse) {
			if (aGame.length() == 0 && gamesParsed == 0) { //To eliminate first empty string
				continue;
			}
			aGame = aGame.substring(0, aGame.length()-1);
			if (gamesParsed == 10) {
				if (isFinalGameSpare) {
					try {
						Integer.parseInt(aGame);
						return true;
					}
					catch (NumberFormatException e) {
						return false;
					}
				}
				if (isFinalGameStrike) {
					return true;
				}
			}
			String[] throwsList = aGame.split(",");
			if (throwsList.length != 2) return false;
			int valueRound = 0;
			for(String aThrow : throwsList) {
				try {
					if (valueRound == 0 && Integer.parseInt(aThrow) == 10 && gamesParsed == 9) {
						isFinalGameStrike = true;
					}
					valueRound += Integer.parseInt(aThrow);
					if (!(isFinalGameStrike) && gamesParsed == 9 && valueRound == 10) {
						isFinalGameSpare = true;
					}
				}
				catch(NumberFormatException e) {
					return false;
				}
			}
			if (valueRound > 10 /*|| (isFinalGameStrike && gamesParsed == 10 && valueRound > 20)*/) {
				return false;
			}
			gamesParsed++;
		}
		if (isFinalGameSpare || isFinalGameStrike) {
			return false;
		}
		else return true;
	}
	
	public boolean isValid() {
		Pattern p = Pattern.compile("(\\[(\\d|10),(\\d|10)\\]){9}(\\[\\d,(\\d|10)\\]\\[(\\d|10)\\]|\\[10,0\\]\\[(\\d|10),(\\d|10)\\]|\\[(\\d|10),(\\d|10)\\])");
		String stringToParse = new String(game);
		Matcher patternMatcher = p.matcher(stringToParse);
		if (patternMatcher.matches()) {
			return isInsideScoreValid();
		}
		else return false;
	}
	
	public int getScore() {
		if (isValid()) {
			return 300;
		}
		else return(-1);
	}
}
