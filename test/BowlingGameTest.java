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
		
	}
	
	//TODO: Add more test cases below...
	
}
