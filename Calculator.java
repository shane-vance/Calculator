import java.util.ArrayList;
import java.util.Stack;

public class Calculator {

	
	public static String convertToLatex(String s) {
		
		return s;
		
		
		
	}
	
	
	public static String convertToJavaExpression(String e) {

		String str = "";

		ArrayList<String> temp = new ArrayList<String>();

		ArrayList<Character> c = new ArrayList<Character>();

		for (char ch : e.toCharArray()) {
			
			if (isOpen(ch)) {
				
				c.add('(');
				
			} else if (isClose(ch)) {
				
				c.add(')');
				
			} else {
				
				c.add(ch);
				
			}
			
		}
		
		c = removeOpen(c);

		for (int i = 0; i < c.size(); i++) {

			temp.add(Character.toString(c.get(i)));

			if (i + 1 < c.size()) {
				
				if (isClose(c.get(i)) && isOpen(c.get(i + 1))) {

					temp.add("*");

				}

				if (isNumber(c.get(i)) && isOpen(c.get(i + 1))) {

					temp.add("*");

				}

				if (c.get(i) == '^') {

					temp.remove(temp.size() - 1);
					temp.remove(temp.size() - 1);
					temp.add("Math.pow(" + c.get(i - 1) + "," + c.get(i + 1) + ")");
					i++;

				}

			}

		}

		for (int i = 0; i < temp.size(); i++) {

			str += temp.get(i);

		}

		return str;

	}

	private static ArrayList<Character> removeOpen(ArrayList<Character> c) {

		for (int i = 0; i < c.size(); i++) {

			if (isOpen(c.get(i)) && isClose(c.get(i + 1))) {

				c.remove(i);
				c.remove(i);
				return removeOpen(c);

			}

		}

		return c;

	}

	public static boolean isEquation(String e) {

		return isEquationHelper(e.toCharArray(), 0, new Stack<Character>(), false);

	}

	private static boolean isEquationHelper(char[] c, int index, Stack<Character> open, boolean result) {

		if (index == c.length && open.empty()) {

			return result;

		} else if (index == c.length && !open.empty() || !(isDecimal(c[index]) || isNumber(c[index])
				|| isOperator(c[index]) || isOpen(c[index]) || isClose(c[index]))) {

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

			if (c[index] == '-'
					&& !(c[index + 1] == '/' || c[index + 1] == '*' || c[index + 1] == '+' || c[index + 1] == '/')) {

				return isEquationHelper(c, index + 1, open, true);

			} else if (index == 0 || index == (c.length - 1) || isOperator(c[index + 1]) && c[index + 1] != '-'
					|| isOpen(c[index - 1]) && isNumber(c[index + 1])
					|| isClose(c[index + 1]) && isNumber(c[index - 1])) {

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
