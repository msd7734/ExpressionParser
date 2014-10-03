
public class Variable extends Symbol {
	private String name;
	public Variable(String name) {
		super('&');
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
