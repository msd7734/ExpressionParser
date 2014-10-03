
/**
 * An expression is defined as follows:
 *  - 1 literal
 *  - 1 variable
 *  - expression operator expression
 *  
 *  This means valid expressions include, for example:
 *   - 5 eval to 5
 *   - x eval to x
 *   - Expression(5) + Expression(4) eval to 5 + 4
 *   - Expression(5) * Expression(x) eval to 5 * x or 5x
 *   - Expression(5 * x) + Expression(10) eval to (5x) + 10
 *   - Expression(
 *   		Expression(x + 4) - Expression(y)
 *    ) eval to (x + 4) - y
 * @author Matt
 *
 */
public class Expression {
	
	private boolean isTerminator;
	private Token t;
	private Expression e1;
	private Expression e2;
	private Operator op;
	
	public static Expression parse(String exp) {
		//tricky case: 1+2*2
		//to start, assume 
		exp = exp.replace(" ", "");
		char[] cs = exp.toCharArray();
		int i = 0;
		while (i < cs.length && !Operator.validOp(cs[i])) {
			++i;
		}
		if (i == cs.length)
			return new Expression(Token.parse(exp));
		else {
			//to preserve order of operations here, flip order accordingly
			//if the operator is * or /
			return new Expression(
					Expression.parse(exp.substring(0,i)),
					(Operator)Token.parse(cs[i]),
					Expression.parse(exp.substring(i+1, exp.length()))
			);
		}
	}
	
	public Expression(Token t) {
		this.t = t;
		this.isTerminator = true;
	}
	
	public Expression(Expression e1, Operator op, Expression e2) {
		this.e1 = e1;
		this.op = op;
		this.e2 = e2;
	}
	
	public boolean isTerminatingExpr() {
		return isTerminator;
	}
	
	public Token getTerminator() {
		return t;
	}
	
	public String eval() {
		if (this.isTerminatingExpr())
			return this.t.toString();
		else {
			String res = "";
			
			String left = e1.eval();
			String right = e2.eval();
			
			if (isNumeric(left)) {
				if (isNumeric(right)) {
					return String.valueOf(
						compute(Integer.parseInt(left),
							Integer.parseInt(right),
							this.op
					));
				}
			}
			
			return res;
		}
		
	}
	
	private int compute(int n1, int n2, Operator op) {
		switch (op.getIdent()) {
		case '+':
			return n1 + n2;
		case '-':
			return n1 - n2;
		case '*':
			return n1 * n2;
		case '/':
			return n1 / n2;
		default:
			System.err.println("Tried to compute with invalid operator " + op.getIdent());
			return Integer.MIN_VALUE;
		}
	}
	
	private boolean isNumeric(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}
		catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	@Override
	public String toString() {
		if (this.isTerminatingExpr())
			return t.toString();
		else {
			String res = "";
			if (e1.isTerminatingExpr())
				res += e1.getTerminator().toString();
			else
				res += e1.toString();
				//res += "(" + e1.toString() + ")";
			
			res += op.toString();
			
			if (e2.isTerminatingExpr())
				res += e2.getTerminator().toString();
			else
				res += e2.toString();
				//res += "(" + e2.toString() + ")";
			
			return res;
		}
	}
}
