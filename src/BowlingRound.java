public class BowlingRound {
	private int throw1;
	private int throw2;
	
	public BowlingRound(String str) {
		String[] numbers = str.split(",");
		throw1 = Integer.parseInt(numbers[0]);
		if (numbers.length == 2) throw2 = Integer.parseInt(numbers[1]);
		else throw2 = -1;
	}
	
	public int getThrow1() {
		return throw1;
	}
	
	public int getThrow2() {
		return throw2;
	}
	
	public int getScoreWithoutBonus() {
		return throw1 + throw2;
	}
	
	public boolean isStrike() {
		return (throw1 == 10 && throw2 == 0);
	}
	
	public boolean isSpare() {
		return (throw1 != 10 && throw1 + throw2 == 10);
	}
}
