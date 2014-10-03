
public class Literal extends Token {
	private int val;
	
	public Literal(int val) {
		super('#');
		this.val = val;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.val);
	}
}
