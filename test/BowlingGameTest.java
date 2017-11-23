import junit.framework.TestCase;

public class BowlingGameTest extends TestCase {
	/** test01 
	 * 	
	 *  If no game is provided, score should be -1 (error)   
	 */	     
	public void test01() {
        BowlingGame bowlingGame = new BowlingGame("");
        assertEquals(-1, bowlingGame.getScore());
    }	
	
	public void testIsValid() {
		BowlingGame bowlingGame = new BowlingGame("[1,2][3,4]");
		// Only 2 games, not 10, must be invalid
		assertFalse("Error, there should be 10 games!",bowlingGame.isValid());
		
		bowlingGame = new BowlingGame("[2,3][2,1][5,3][8,1][3,2][9,0][2,1][3,1][2,5][6,1][2,1][3,2][2,1][3,1]");
		//14 games, not 10, must be invalid
		assertFalse("Error, there should be 10 games!",bowlingGame.isValid());
		
		bowlingGame = new BowlingGame("[3,4][4,5][6,4][3,5[[3,10]");
		//Error in formating
		assertFalse("Error, some games are not formated in a good way!",bowlingGame.isValid());
		
		bowlingGame = new BowlingGame("[3,4][6,5][3,5][10,0][6,2][6,4][7,3][2,4][1,2][3,5]");
		//Error, some games put down more than 10 pins
		assertFalse("Error, some games have more than 10 pins down!",bowlingGame.isValid());
		
		bowlingGame = new BowlingGame("Bonjour, j'�cris en fran�ais!");
		//Error, not a game score
		assertFalse("Error, this is not a game score!",bowlingGame.isValid());
		
		bowlingGame = new BowlingGame("[1,2][3,4][5,4][3,1][5,5][1,2][3,1][3,2][1,2][10,0]");
		//Game finishing with a strike but no bonus!
		assertFalse("Error, a game finishing with a strike should have a bonus score!",bowlingGame.isValid());
		
		bowlingGame = new BowlingGame("[3,2][5,2][1,2][5,3][4,2][2,3][8,1][1,2][3,2][10,0],[9]");
		//Game finishing with a strike but only 1 bonus!
		assertFalse("Error, a game finishing with a strike should have 2 bonus instead of 1!",bowlingGame.isValid());
		
		bowlingGame = new BowlingGame("[3,1][3,2][4,3][1,2][3,2][8,1][8,2][9,1][1,2][8,2]");
		//Game finishing with a spare without any bonuses!
		assertFalse("Error, a game finishing with a spare should have 1 bonus!",bowlingGame.isValid());
		
		bowlingGame = new BowlingGame("[4,2][3,1][5,2][3,2][8,1][10,0][2,8][3,1][2,1][7,3][8,1]");
		//Game finishing with a spare with 2 bonuses
		assertFalse("Error, a game finishing with a spare should not have 2 bonuses!",bowlingGame.isValid());
	}
	
	public void testIsValid0() {
		BowlingGame bowlingGame = new BowlingGame("[1,5][2,8][6,2][10,0][1,5][4,1][6,3][10,0][1,5][4,1][8,2][10]");
		assertTrue("The Format is valid , because it after spare in last frame and a bonus which is Strike , there is no more throw", bowlingGame.isValid());
	}
	
	public void testIsValid1() {
		BowlingGame bowlingGame = new BowlingGame("[1,5][2,8][6,2][10,0][1,5][4,1][6,3][10,0][1,5][4,6][7]");
		assertTrue("The Format is valid, because in the last frame Spare happend and the player has one bonus throw ", bowlingGame.isValid());
	}
	
	public void testIsValid2() {
		BowlingGame bowlingGame = new BowlingGame("[1,5][2,8][6,2][10,0][1,5][4,1][6,3][10,0][1,5][10,0][7,3]");
		assertTrue("The Format is valid, because in the last frame Strike happend and the player has two bonus throw ", bowlingGame.isValid());
	}
	public void testIsValid3() {
		BowlingGame bowlingGame = new BowlingGame("[5,5][2,8][6,4][10,0][5,5][9,1][7,3][10,0][1,5][10,0]");
		assertTrue("The Format is valid, because multiple Spares can happen  ", bowlingGame.isValid());
	}
	public void testIsValid4() {
		BowlingGame bowlingGame = new BowlingGame("[10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10]");
		assertTrue("The Format is valid, because the maximum score is 300  ", bowlingGame.isValid());
	}
	
	//testing valid scores
	
	public void testScoreIsValid0() {
		BowlingGame bowlingGame = new BowlingGame("[10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10]");
		assertEquals("Score is valid ,the maximum score is 300",300, bowlingGame.getScore());
	}
	public void testScoreIsValid1() {
		BowlingGame bowlingGame = new BowlingGame("[1,5][3,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6]");
		assertEquals("Score is valid , score should be equal to 81",81, bowlingGame.getScore());
		
	}
	public void testScoreIsValid2() {
		BowlingGame bowlingGame = new BowlingGame("[1,9][5,3][7,1][3,1][4,4][5,3][3,3][4,5][8,1][3,4]");
		assertEquals("Score is valid, it is equal to 74 because we have one spare and count only next ball throw",74, bowlingGame.getScore());
		
	}
	public void testScoreIsValid3() {
		BowlingGame bowlingGame = new BowlingGame("[10,0][5,5][7,2][3,4][2,1][4,0][0,5][1,1][8,0][1,8]");
		assertEquals("Score is valid, it is equal to 82, one srtike and one spare",82, bowlingGame.getScore());

	}
}

