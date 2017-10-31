import java.util.Stack;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculator {

	public static void main(String[] args) throws ScriptException {

		String e = "((5*28)+2+3*(2-Math.pow(2,2))/(3))";

		
		if (isEquation(e)) {	
	        System.out.println(new ScriptEngineManager().getEngineByName("JavaScript").eval(e));
		} else {
			
			System.out.println("Expression incorrect!");
		}
		

	}

	public static boolean isEquation(String e) {

		return isEquationHelper(e.toCharArray(), 0, new Stack<Character>(), false);

	}

	private static boolean isEquationHelper(char[] c, int index, Stack<Character> open, boolean result) {

		if (index == c.length && open.empty()) {
			
			return result;
			
		} else if (index == c.length && !open.empty() || !(isDecimal(c[index]) || isNumber(c[index]) || isOperator(c[index]) || isOpen(c[index]) || isClose(c[index]))) {
			
			return false;
			
		} else if (isDecimal(c[index]) && (isOperator(c[index + 1]) || isOpen(c[index + 1]) || isClose(c[index + 1]))) { 
		
			return false;
			
		} else if (isOpen(c[index])) {

			open.push(c[index]);
			return isEquationHelper(c, index + 1, open, result);
			
		} else if (open.empty() && isClose(c[index])) {
			
			return false;
			
		} else if (!open.empty() && isClose(open, c[index])) {
			
			open.pop();
			return isEquationHelper(c, index + 1, open, true);
			
		} else if (isOperator(c[index])) {
			
			if (c[index] == '-' && !(c[index + 1] == '/' || c[index + 1] == '*' || c[index + 1] == '+' || c[index + 1] == '/' )) {
				
				return isEquationHelper(c, index + 1, open, true);
				
			} else if (index == 0 || index == (c.length - 1) || isOperator(c[index + 1]) && c[index + 1] != '-' || isOpen(c[index - 1]) && isNumber(c[index + 1]) || isClose(c[index + 1]) && isNumber(c[index - 1])) {
				
				return false;
				
			} else {
				
				return isEquationHelper(c, index + 1, open, true);
				
			}
			
		} else {
			
			return isEquationHelper(c, index + 1, open, true);
			
		}

	}

	private static boolean isDecimal(char c) {

		return c == '.';

	}

	private static boolean isNumber(char c) {

		return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8'
				|| c == '9';

	}

	private static boolean isOpen(char c) {

		return c == '(' || c == '{' || c == '[';

	}

	private static boolean isClose(char c) {

		return c == ')' || c == '}' || c == ']';

	}

	private static boolean isClose(Stack<Character> open, char c) {

		return open.peek() == '(' && c == ')' || open.peek() == '{' && c == '}' || open.peek() == '[' && c == ']';

	}

	private static boolean isOperator(char c) {

		return c == '+' || c == '-' || c == '/' || c == '*' || c == '^';

	}

}
