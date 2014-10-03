import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Program {
	//implement factory pattern to build expressions :DDDD
	public static void main(String[] args) {
		Expression e = new Expression(new Variable("name"));
		Expression e2 = new Expression(new Literal(7));
		Expression e3 = new Expression(
				new Expression(new Variable("age")),
				new Operator('+'),
				new Expression(new Literal(4))
		);
		Expression e4 = new Expression(
				new Expression(
						new Expression(
								new Expression(new Literal(5)),
								new Operator('*'),
								new Expression(new Literal(13))
						),
						new Operator('+'),
						new Expression(
								new Expression(new Variable("x")),
								new Operator('-'),
								new Expression(new Literal(125))
						)
				),
				new Operator('*'),
				new Expression(new Literal(2))
		);
		
		//name
		System.out.println(e);
		//7
		System.out.println(e2);
		//age+4
		System.out.println(e3);
		//((5*13)+(x-125))*2
		System.out.println(e4);
		
		Expression e5 = Expression.parse("1+2+3");
		System.out.println(e5);
		Expression e6 = Expression.parse("a - b * 5");
		System.out.println(e6);
	}
}
