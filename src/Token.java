import java.util.Arrays;

public class Token {
	private char ident;
	
	public static Token parse(String str) {
		try {
			int ival = Integer.parseInt(str);
			return Token.parse(ival);
		}
		catch(NumberFormatException nfe) {
			if (str.length() == 1 &&
				Arrays.asList(Operator.getValidSymb()).contains(str.charAt(0))) {
				return Token.parse(str.charAt(0));
			}
			else {
				return new Variable(str);
			}
		}
		
	}
	public static Token parse(int i) {
		return new Literal(i);
	}
	public static Token parse(char c) {
		if (Arrays.asList(Operator.getValidSymb()).contains(c)) {
			return new Operator(c);
		}
		else {
			System.err.println("Tried to parse invalid character " + c + " to token.");
			return new Token('x');
		}
	}
	public static Token parse(Object o) {
		if (o instanceof Character) {
			return Token.parse((char)o);
		}
		else if (o instanceof Number) {
			Number n = (Number) o;
			//don't overflow 
			if (n.longValue() > Integer.MAX_VALUE)
				o = (Number) Integer.MAX_VALUE;
			return Token.parse((int) o);
		}
		else if (o instanceof String) {
			return Token.parse((String) o);	
		}
		return new Token('x');
	}
	
	public Token(char i) {
		this.ident = i;
	}
	
	public char getIdent() {
		return this.ident;
	}
	
	public boolean isFailToken() {
		return ident == 'x';
	}
	
	@Override
	public String toString() {
		return String.valueOf(ident);
	}
}
