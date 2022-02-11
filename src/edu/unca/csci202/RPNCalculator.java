package edu.unca.csci202;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class RPNCalculator {

	 private Stack<Double> calcStack;
	    
	    public RPNCalculator() {
	        calcStack = new Stack<Double>();
	    }

	    public void run() {
	        Scanner inputLineReader = new Scanner(System.in);
	        String line = "";
	        boolean quit = false;
	        
	        while (!quit) {
	            while (line.equals("")) {
	                System.out.print(":::> ");
	                line = inputLineReader.nextLine().trim();
	            }
	            quit = interpretExpression(line);
	            //if proper expression, result will be only element on stack
	            printResult(quit);
	            line = "";
	        }
	    
	    }
	    
	    private boolean interpretExpression(String line) {
	        Scanner lineParser = new Scanner(line);
	        String token = "";
	        boolean quit = false;
	        double operand = 0.0;
	        
	        while (lineParser.hasNext()) {
	            token = lineParser.next();
	            try {
	            	
	            	operand = Double.parseDouble(token);
		            calcStack.push(operand);
	            } catch (Exception e) {
	            	
	            }
	            
	            try {
		            switch(token) {
		            case("+"): {
		            	
		            	double x = calcStack.pop();
		            	double y = calcStack.pop();
		            	operand = x + y;
		            	calcStack.push(operand);
		            	break;
		            }
		            case("-"): {
		            	
		            	double x = calcStack.pop();
		            	double y = calcStack.pop();
		            	operand = y - x;
		            	calcStack.push(operand);
		            	break;
		            }
		            case("*"): {
		            	
		            	double x = calcStack.pop();
		            	double y = calcStack.pop();
		            	operand = x * y;
		            	calcStack.push(operand);
		            	break;
		            }
		            case("/"): {
		            	
		            	double x = calcStack.pop();
		            	double y = calcStack.pop();
		            	operand = y / x;
		            	calcStack.push(operand);
		            	break;
		            }
		            }
	            } catch (EmptyStackException e) {
	            	
	            	System.out.println("Invalid input: not enough operands");
	            	calcStack.push(Double.NaN);
	            }
	            
	            if (token.equals("q")) {
	                quit=true;
	            }
	            
	            
	        }
	        try {
	        	
	        	if (calcStack.size() > 1) {
	        		
	        		throw new tooManyOperandsException();
	        	}
	        } catch (Exception e) {
	        	
	        	System.out.println("Invalid input: too many operands");
            	calcStack.push(Double.NaN);
	        }
	        return quit;
	    }
	    
	    private void printResult(boolean quit) {
	        if (!quit) {
	            System.out.println(calcStack.pop());
	        }
	    
	    }

	    public static void main(String [] args) {
	        RPNCalculator calc = new RPNCalculator();
	        calc.run();
	    }

}
