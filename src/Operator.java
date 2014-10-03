import java.util.Arrays;


public class Operator extends Symbol {
	private static final Character[] validSymbols = {'+', '-', '*', '/'};
	
	public static Character[] getValidSymb() {
		return validSymbols;
	}
	
	public static boolean validOp(char c) {
		return Arrays.asList(validSymbols).contains(c);
	}
	
	public Operator(char i) {
		super(i);
	}
}
