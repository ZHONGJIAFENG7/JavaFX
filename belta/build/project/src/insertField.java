import java.util.Comparator;

/**
 * This class is used for compare and reduce the conflict of hashmap
 */
public class insertField implements Comparator<insertField> {

	private String name;
	private int score;

	public insertField() {
	}

	// get the usename and score
	public insertField(String name, int score) {
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

	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + score;
		result = 37 * result + name.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof insertField))
			return false;
		insertField field = (insertField) o;
		return (score == field.score) && (field.equals(field.name));
	}

	@Override
	public int compare(insertField a, insertField b) {
		return b.getScore() - a.getScore();
	}
}