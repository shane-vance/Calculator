import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Client {
	
	public static void main(String[] args) throws ScriptException {

		String e = "(-(5*28)+2+3(2-2^2)/(3))((2))"; // infix notation		
		
		if (Calculator.isEquation(e)) {
			
			e = Calculator.convertToJavaExpression(e);
			System.out.println(e);
			
			try {
				
				ScriptEngineManager sem = new ScriptEngineManager();
				
				String s = (String) sem.getEngineByName("JavaScript").eval(e).toString();
				
				if (s.toLowerCase().equals("infinity") || s.toLowerCase().equals("-infinity")) {
				
					System.out.println("NaN");
				
				} else {
					
					System.out.println(s);
					
				}
				
			} catch (ScriptException x) {
				
				System.out.println("NaN");
				
			}

		} else {

			System.out.println("Expression incorrect!");

		}

	}
	
}