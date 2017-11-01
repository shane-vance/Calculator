import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.EmptyStackException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Client {

	public static void main(String[] args)  {
		
		JFrame gui = new JFrame();
		
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gui.setResizable(false);
		gui.setIconImage(null);
		gui.setSize(372, 431);
		gui.setLayout(new FlowLayout());
		gui.setTitle("Calculator");
		
		// Text box
		JTextField txt = new JTextField();	
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 30);
		txt.setFont(font);
		txt.setHorizontalAlignment(JTextField.RIGHT);		
		txt.setPreferredSize(new Dimension(352, 96));
		txt.setMaximumSize(new Dimension(352, 96));		
		gui.add(txt);
		
		// Equals button
		JButton equals = new JButton("=");		
		equals.setPreferredSize(new Dimension(45, 23));
		gui.add(equals);
		
		equals.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				String exp = txt.getText();

				exp = Calculator.convertToProperExpression(exp);

				if (Calculator.isEquation(exp)) {
					
					try {
					
						BigDecimal result = Calculator.eval(exp);
						txt.setText(result.toString());
					
					} catch (NumberFormatException e ) {
					
						txt.setText("Invalid input");
					
					} catch (EmptyStackException e) {
						
						txt.setText("Invalid input");
						
					} catch (UnsupportedOperationException e) {
					
						txt.setText("Cannot divide by zero!");
						
					}
					
				} else if (exp.length() == 0) {
					
					txt.setText("Nothing to evaluate");
						
				} else {
					
					txt.setText("Invalid input");
					
				}
			}
		});

		//Sets the GUI to be visible
		gui.setVisible(true);
	
	}
	
}