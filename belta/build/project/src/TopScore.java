public class TopScore {

	private String name;
	private int score;

	public TopScore() {
		this.name = "";
		this.score = 0;
	}

	public TopScore(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
