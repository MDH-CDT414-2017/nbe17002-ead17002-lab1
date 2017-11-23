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
	public void testIsValid5() {
		BowlingGame bowlingGame = new BowlingGame("[10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10]");
		assertTrue("The Format is valid, because the maximum score is 300  ", bowlingGame.isValid());
	}
	
}
