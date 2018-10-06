/* Project Description
 * Name: Andrew Sison
 * Class: CS-4800 Software Engineering
 * Professor: Hussain Zaidi
 * Calculator that can add, subtract, multiply and divide numbers
 */

/* Project Documentation
 * Estimated Total Time : 5hrs
 *
 * Worked on it:
 * 9/22 8pm - 9pm; 				Requirements Analysis & Design, Development
 * 9/28 8pm - 9pm; 				Development
 * 		9:15pm -9:50pm; 		Development
 * 9/29 1:00pm-2:20pm; 			Development
 * 		3:00pm - 3:30pm; 		Testing
 * Bugs Found During Testing:
 * Negative numbers don't work as operands
 * Division by zero give infinity
 * Total Time Spent: 5 hrs, 25 mins
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.*;

public class Calculator extends JFrame {
	private final int buttonSize = 50;
	private final int frameWidth = buttonSize * 6, frameHeight = buttonSize * 6;
	private int x = (1920/2) - (frameWidth / 2), y = (1080/2) - (frameHeight / 2) ;
	
	
	private String operandText[] = {"7","8","9","4","5","6","1","2","3","0","."};
	private String operatorText[] = {"/","*","-","+"};
	
	private JTextField textField= new JTextField();
	private OperandButton[] operandButtons = new OperandButton[11];
	private OperatorButton[] operatorButtons = new OperatorButton[4];
	
	private Double firstNumber, secondNumber;
	private String operation = "";
	
	private EqualsButton equalsButton = new EqualsButton();
	private ClearButton clearButton = new ClearButton();
	
	Calculator(){
		super();
		this.setBounds(x, y, frameWidth, frameHeight);
		
		//adding textField
		textField.setBounds(0, 0, frameWidth, buttonSize);
		
		this.add(textField);
		
		
		int operandLocationX = 0;
		int operandLocationY = buttonSize;
		int i = 0;
		//add numbers and "."
		for(String str: operandText) {
			OperandButton operandButton = new OperandButton(str);
			operandButton.setBounds(operandLocationX, operandLocationY, buttonSize, buttonSize);
			operandLocationX += buttonSize;
			if (operandLocationX % (buttonSize * 3) == 0) {
				operandLocationX = 0;
				operandLocationY += buttonSize;
			}
			operandButtons[i] = operandButton;
			i++;
			this.add(operandButton);
		}
		
		int operatorLocationX = buttonSize * 3;
		int operatorLocationY = buttonSize;
		int j = 0;
		//add operator buttons
		for(String str: operatorText) {
			OperatorButton operatorButton = new OperatorButton(str);
			operatorButton.setBounds(operatorLocationX,operatorLocationY, buttonSize, buttonSize);
			operatorLocationY += buttonSize;
			operatorButtons[j] = operatorButton;
			j++;
			this.add(operatorButton);
		}
		
		//add equals button
		equalsButton.setBounds( 4 * buttonSize, buttonSize, buttonSize, 4 * buttonSize);
		this.add(equalsButton);
		//add clear button
		clearButton.setBounds(2 * buttonSize, 4*buttonSize, buttonSize, buttonSize);
		this.add(clearButton);
		
	}
	
	public static void main (String[] args) {
		Calculator calculator = new Calculator();

		calculator.setLayout(null);
		calculator.setVisible(true);
	}
	
	public class OperandButton extends JButton implements ActionListener{
		double number;
		String numberAsString;
		OperandButton(String str){
			super(str);
			numberAsString = str;
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			textField.setText(textField.getText() + numberAsString);
			
		}
		
	}
	
	public class OperatorButton extends JButton implements ActionListener{
		String operator;
		
		OperatorButton(String str){
			super(str);
			operator = str;
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			operation = operator;
			if(firstNumber == null)
				firstNumber = Double.parseDouble(textField.getText());

			textField.setText("");
		}
			
	}
	
	@SuppressWarnings("serial")
	public class EqualsButton extends JButton implements ActionListener{
		EqualsButton(){
			super("=");
			addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e) {
			
			try {
				secondNumber = Double.parseDouble(textField.getText());
				if(Objects.equals(operation, "+")) {
					textField.setText(Double.toString(firstNumber + secondNumber));
					System.out.println(firstNumber + operation + secondNumber + "=" + (firstNumber + secondNumber ));
				}
				else if(Objects.equals(operation, "-")) {
					textField.setText(Double.toString(firstNumber - secondNumber));
				}
				else if(Objects.equals(operation, "*")) {
					textField.setText(Double.toString(firstNumber * secondNumber));
				}
				else if(Objects.equals(operation, "/")) {
					textField.setText(Double.toString(firstNumber / secondNumber));
				}
				firstNumber = null;
				secondNumber = null;
				
			} catch (ArithmeticException ae) {
				System.out.println(ae);
			} catch (NullPointerException npe) {
				System.out.println(npe);
			} catch(NumberFormatException nfe) {
				System.out.println(nfe);
			}
			
		}
	}
	
	public class ClearButton extends JButton implements ActionListener{
		ClearButton(){
			super("C");
			addActionListener(this);

		}			
		public void actionPerformed(ActionEvent e) {
				firstNumber = null;
				secondNumber = null;
				operation = null;
				textField.setText("");
		}
	}
}
/*
 * Estimated Total Time : 5hrs
 *
 * Worked on it:
 * 9/22 8pm - 9pm; Requirements Analysis & Design, Development
 * 9/28 8pm - 9pm; Development
 * 		9:15pm -9:50pm; Development
 * 9/29 1:00pm-2:20pm; Development
 * 		3:00pm - 3:30pm; Testing
 * Bugs Found During Testing:
 * Negative Numbers Don't work
 * Division by zero give infinity
 * Total Time Spent: 5 hrs, 25 mins
 */
