import junit.framework.TestCase;

public class BowlingRoundTest extends TestCase {
	public void testConstructor() {
		BowlingRound round = new BowlingRound("2,3");
		assertEquals("The first throw must be equal to 2!",2,round.getThrow1());
		assertEquals("The second throw must be equal to 3!",3,round.getThrow2());
		
		round = new BowlingRound("2");
		assertEquals("The first throw musr be equal to 2!",2,round.getThrow1());
		assertEquals("The second throw must be equal to -1!",-1,round.getThrow2());
	}
	
	public void testGetScoreWithoutBonus() {
		BowlingRound round = new BowlingRound("4,5");
		assertEquals("The score should be 9!",9,round.getScoreWithoutBonus());
		
		round = new BowlingRound("10,10");
		assertEquals("The score should be 20!",20,round.getScoreWithoutBonus());
		
		round = new BowlingRound("10,0");
		assertEquals("The score should be 10!",10,round.getScoreWithoutBonus());
	}
	
	public void testIsStrike() {
		BowlingRound round = new BowlingRound("10,0");
		assertTrue("This round should be a strike!",round.isStrike());
		
		round = new BowlingRound("1,9");
		assertFalse("This round should not be a strike!",round.isStrike());
		
		round = new BowlingRound("10,10");
		assertFalse("This round should not be a strike!",round.isStrike());
	}
	
	public void testIsSpare() {
		BowlingRound round = new BowlingRound("2,8");
		assertTrue("This round should be a spare!",round.isSpare());
		
		round = new BowlingRound("10,0");
		assertFalse("This round should be a strike, not a spare!",round.isSpare());
		
		round = new BowlingRound("1,3");
		assertFalse("This round is not a spare!",round.isSpare());
	}
}
