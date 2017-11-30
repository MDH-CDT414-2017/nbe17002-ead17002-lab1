/** BowlingGame Score calculator 
 *
 * @author CDT414 Student: 
 * @version 1.0 
 * @date 2016-11-24
 */

import java.util.ArrayList;
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
			aGame = aGame.substring(0, aGame.length()-1); //To remove the "]" at the end
			if (gamesParsed == 10) {
				if (isFinalGameSpare) {
					Integer.parseInt(aGame); //Try to see if the final game is a single throw
					return true;
				}
				if (isFinalGameStrike) { //No need to check the content of the strike, it has been catched by the pattern matching
					return true;
				}
			}
			String[] throwsList = aGame.split(",");
			if (throwsList.length != 2) return false;
			int valueRound = 0;
			for(String aThrow : throwsList) {
				if (valueRound == 0 && Integer.parseInt(aThrow) == 10 && gamesParsed == 9) {
					isFinalGameStrike = true;
				}
				valueRound += Integer.parseInt(aThrow);
				if (!(isFinalGameStrike) && gamesParsed == 9 && valueRound == 10) {
					isFinalGameSpare = true;
				}
			}
			if (valueRound > 10) {
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
	
	private int calculateScoreFromValidString() {
		String[] roundsToParse = game.split("\\["); //First string of games is empty
		ArrayList<BowlingRound> rounds = new ArrayList<BowlingRound>(); 
		for(int i = 1; i < roundsToParse.length; i++) {
			String roundToParse = roundsToParse[i].substring(0, roundsToParse[i].length()-1); //Remove the ] at the end of the string
			rounds.add(new BowlingRound(roundToParse));
		}
		int totalScore = 0;
		for(int i = 0; i < 10; i++) {
			BowlingRound roundToProcess = rounds.get(i);
			if (roundToProcess.isStrike()) {
				 if (rounds.get(i+1).isStrike() && i != 9) { //If the round after is a strike, we should not take the second throw into account which is 0 unless it is the final round
					 totalScore += roundToProcess.getScoreWithoutBonus() + rounds.get(i+1).getThrow1() + rounds.get(i+2).getThrow1();
				 }
				 else totalScore += roundToProcess.getScoreWithoutBonus() + rounds.get(i+1).getScoreWithoutBonus();
			}
			else if (roundToProcess.isSpare()) {
				totalScore += roundToProcess.getScoreWithoutBonus() + rounds.get(i+1).getThrow1();
			}
			else totalScore += roundToProcess.getScoreWithoutBonus();
		}
		return totalScore;
	}
	
	public int getScore() {
		if (game == null) return -1;
		if (isValid()) {
			return calculateScoreFromValidString();
		}
		else return(-1);
	}
}
