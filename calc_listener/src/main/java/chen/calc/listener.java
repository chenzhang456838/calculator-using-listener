package chen.calc;

import java.math.BigDecimal;

import java.math.RoundingMode;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeProperty;


import java.util.*;
import java.lang.*;

public class listener extends calculatorBaseListener {
	
	Map<String, BigDecimal> map = new HashMap<>();

	String sematicErrorMsg = "";
	boolean validinput = true;

	void setErrorMsg(String msg) {
		if (sematicErrorMsg.equals(""))
			sematicErrorMsg = msg;
	}
	
	// check whether the input string is a number
	private boolean isNumeric(String strNum) {		
		if (strNum == null) {
	        return false; 
	    }
	    return strNum.matches("-?\\d+(\\.\\d+)?"); 
	}
	
	
	@Override
	public void enterExpression(calculatorParser.ExpressionContext ctx) {
		//System.out.println("enterExpression");
		
		
		//System.out.println(ctx.getText());
	}

	@Override
	public void exitExpression(calculatorParser.ExpressionContext ctx) {
		if(!validinput)
			return;
		//System.out.println("exitExpression");
		
		boolean valid = true;


		BigDecimal left = map.get(ctx.getChild(0).getText());
		if(left == null ||!isNumeric(left.toString())) {
			 setErrorMsg("Invlid Expression : " + ctx.getText());
			 valid = false;
			 return;
		}
		
		
		if(ctx.getChildCount() == 1)
			return;

		// check if size is odd
		if (ctx.children.size() % 2 != 1) {
			setErrorMsg("Invlid Expression : " + ctx.getText());

			valid = false;
		}

		for (int i = 1; i < ctx.children.size() && valid; i = i + 2) {
			
			BigDecimal right = map.get(ctx.getChild(i + 1).getText());

			if (right == null || !isNumeric(right.toString())) {
				setErrorMsg("Invlid Expression : " + ctx.getText());
			
				valid = false;
				break;
			}

			//System.out.println(i + "=>" + left + " vs " + right);

			if (ctx.getChild(i).getText().equals("+")) {

				left = left.add(right);
			} else if (ctx.getChild(i).getText().equals("-")) {

				left = left.subtract(right);
			} else {
				setErrorMsg("Invalid Opertor in Expression : " + ctx.getText());

				valid = false;
				break;
			}
		}

		// BigDecimal right = map.get(ctx.getChild(2).getText());

		// System.out.println(ctx.getChild(0).hashCode());
		// System.out.println(ctx.getChild(2).hashCode());

		//System.out.println("temp res is " + left);

		map.put(ctx.getText(), valid ? left : null);

	}

	@Override
	public void enterMultiplyingExpression(calculatorParser.MultiplyingExpressionContext ctx) {

		//System.out.println("enterMultiplyingExpression");

	}

	@Override
	public void exitMultiplyingExpression(calculatorParser.MultiplyingExpressionContext ctx) {
		//System.out.println("exitMultiplyingExpression");
		
		boolean valid = true;
		BigDecimal left = map.get(ctx.getChild(0).getText());
		


		// check if size is odd
		if(left == null ||!isNumeric(left.toString())) {
			 setErrorMsg("Invlid MultiplyingExpression : " + ctx.getText());
			 valid = false;
			 return;
		}
		
		//System.out.println("left : " + left );
		
		if(ctx.getChildCount() == 1)
			return;
		
		if (ctx.children.size() % 2 != 1) {
			setErrorMsg("Invalid MultiplyingExpression : " + ctx.getText());

			valid = false;
		}

		for (int i = 1; i < ctx.children.size() && valid; i = i + 2) {
		
			BigDecimal right = map.get(ctx.getChild(i + 1).getText());

			if (right == null || !isNumeric(right.toString()) ) {
				setErrorMsg("Invalid MultiplyingExpression : " + ctx.getText());
				valid = false;
				break;
			}

			if (right.equals(BigDecimal.ZERO)) {
				valid = false;
				setErrorMsg("Division by zero : " + ctx.getChild(i + 1).getText() + " is evaluated to be 0.");
				break;
			}
			//System.out.println("right : " + right );

			// System.out.println(i + "=>" + left + " vs " + right);

			if (ctx.getChild(i).getText().equals("/")) {
         
				left = left.divide(right);
			} else if (ctx.getChild(i).getText().equals("*")) {

				left = left.multiply(right);
			} else {
				setErrorMsg("Invlid Opertor in MultiplyingExpression : " + ctx.getText());

				valid = false;
				break;
			}
		}

		//System.out.println("temp res is " + left);

		map.put(ctx.getText(), valid ? left : null);

	}

	@Override
	public void enterPowExpression(calculatorParser.PowExpressionContext ctx) {
		//System.out.println("enterPowExpression");

	}

	@Override
	public void exitPowExpression(calculatorParser.PowExpressionContext ctx) {
		//System.out.println("exitPowExpression");
		
		boolean valid = true;
		
		//System.out.println("test here " + ctx.getChildCount());
		
	   


		// check if size is odd
		BigDecimal left = map.get(ctx.getChild(0).getText());
		//System.out.println("left" + left);
		//System.out.println("is number" + !isNumeric(left.toString()) );
		if(left == null || !isNumeric(left.toString()) ) {
			 setErrorMsg("Invlid PowExpression : " + ctx.getText());
			 valid = false;
			 return;
		}
		
		 if(ctx.getChildCount() == 1)
			 return;


		if (ctx.children.size() % 2 != 1) {
			setErrorMsg("Invalid PowExpression : " + ctx.getText());
			valid = false;
		}

		for (int i = 1; i < ctx.children.size() && valid; i = i + 2) {
			
			BigDecimal right = map.get(ctx.getChild(i + 1).getText());

			if (right == null || !isNumeric(right.toString())) {
				valid = false;
				break;
			}

			//System.out.println(i + "=>" + left + " vs " + right);

			if (ctx.getChild(i).getText().equals("^")) {
				//System.out.println(right.intValue());

				left = left.pow(Math.abs(right.intValue()));

				if (right.intValue() < 0) {
					left = BigDecimal.ONE.divide(left);
				}

				// left = left.pow(right.intValue());
			} else {
				setErrorMsg("Invlid Opertor in PowExpression : " + ctx.getText());

				valid = false;
				break;
			}
		}
		
		map.put(ctx.getText(), valid ? left : null);
		

	}

	
	@Override
	public void enterSignedAtom(calculatorParser.SignedAtomContext ctx) {
		//System.out.println("enterSignedAtom");

//		System.out.println(ctx.getText());
//		System.out.println(ctx.getText().length());
//		if(ctx.getText().length() >= 2 && !Character.isDigit(ctx.getText().charAt(1))) {
//			validinput = false;
//			setErrorMsg("Invlid Opertor in singedAtom : " + ctx.getText());
//		}
//		
		
		
	}

	@Override
	public void exitSignedAtom(calculatorParser.SignedAtomContext ctx) {
		
		if(!validinput)
			return;
		
		
	
		//System.out.println("exitSignedAtom");
		
		//System.out.println(ctx.getText());
		//System.out.println("children num:" + ctx.getChildCount());
		
//		for(int i = 0;i < ctx.getChildCount();i++) {
//			System.out.println(i + "child" + ctx.getChild(i).getText());
//		}
		if(ctx.getChildCount() == 2 && (ctx.getChild(0).getText().equals("+") || ctx.getChild(0).getText().equals("-") )
				&& ctx.getChild(1).getText().length() >= 1&& !Character.isDigit(ctx.getChild(1).getText().charAt(0))) {
			
			setErrorMsg("Invalid Signed Atom: " + ctx.getText());
			return;
		}
		
		boolean positive = ctx.getChild(0).getText().equals("+");
		boolean negative = ctx.getChild(0).getText().equals("-");

		if (ctx.children.size() != 2 || (!positive && !negative )) {
			//setErrorMsg("Invalid Signed Atom: " + ctx.getText());
			return;
		}

		
		BigDecimal right = map.get(ctx.getChild(1).getText());
//
		map.put(ctx.getText(), (right != null ? (positive ? right : BigDecimal.ZERO.subtract(right)): null));
		//System.out.println("value is" + (right != null ? (positive ? right : BigDecimal.ZERO.subtract(right)): null) );
				
	}
	

	
	@Override
	public void enterAtom(calculatorParser.AtomContext ctx) {

		//System.out.println("enterAtom");
		
		//System.out.println(ctx.getText());
	}

	@Override
	public void exitAtom(calculatorParser.AtomContext ctx) {
	//	System.out.println("exitAtom");
		
		
//		
//		System.out.println("atom is " + ctx.getText());
//		System.out.println( map.containsKey(ctx.getText()));
		
		if(isNumeric(ctx.getText()))
		    map.put(ctx.getText(), new BigDecimal(ctx.getText()));
		else if(ctx.getChildCount() == 3 && ctx.getChild(0).getText().equals("(") && ctx.getChild(2).getText().equals(")")) {
			//System.out.println("value is" + map.get(ctx.getChild(1).getText()));
			map.put(ctx.getText(), map.get(ctx.getChild(1).getText()));
		}
		else if(!map.containsKey(ctx.getText())) {
			setErrorMsg("Invalid atom: " + ctx.getText());
			//map.put(ctx.getText(), null);
			return;
		}
		
//		
//		
//		System.out.println(ctx.getText() + " value is" +  new BigDecimal(ctx.getText() ) 
//				+" "+ new BigDecimal(ctx.getText() ).intValue());
		
	}


	
	@Override 
	public void enterConstant(calculatorParser.ConstantContext ctx) {
		//System.out.println("enterConstant");

	}

    
    //PI, EULER, I
	@Override public void exitConstant(calculatorParser.ConstantContext ctx) { 
		//System.out.println("exitConstant");
		
		//System.out.println("constant: "+ ctx.getText());
		
		if(ctx.getChild(0).getText().equals("pi") || ctx.getChild(0).getText().equals("PI")) {
			//System.out.println("deal with pi");
			map.put(ctx.getText(),new BigDecimal(Math.PI));
		}
		else if(ctx.getChild(0).getText().equals("e") || ctx.getChild(0).getText().equals("E")) {
			map.put(ctx.getText(), new BigDecimal(Math.E));
		}
		else {
			setErrorMsg("Invalid Constant: " + ctx.getText());
			return;
		}
		
		//System.out.println("constant in map :" + map.get(ctx.getText()));
	}
	
	
	@Override 
	public void enterFunc( calculatorParser.FuncContext ctx) {
		//System.out.println("enterFunc");
	}
	
	@Override 
	public void exitFunc( calculatorParser.FuncContext ctx) {
		//System.out.println("exitFunc");	
		if(ctx.getChildCount()!=4) {
			setErrorMsg("Invalid input func: " + ctx.getText());
			return;
		}
		
		if(!ctx.getChild(1).getText().equals("(") || !ctx.getChild(3).getText().equals(")")) {
			setErrorMsg("Invalid brackets: " + ctx.getText());
			return;
		}
		
		Double num = 0.0;
//		System.out.println(ctx.getChild(2).getText());
//		System.out.println("test + :" + map.containsKey(ctx.getChild(2).getText()));
		
		BigDecimal left = map.get(ctx.getChild(2).getText());
		
		if(left != null) {
		   num = map.get(ctx.getChild(2).getText()).doubleValue();
		  // System.out.println("num is " + num);
		}
		else {
			setErrorMsg("Invalid input " + ctx.getText());
			return;
		}
		
		//System.out.println("res + " + Math.sin(num));
		
		//System.out.println(num);
		
		if(ctx.getChild(0).getText().equals("cos")) {
			map.put(ctx.getText(), new BigDecimal(Math.cos(num)));
		}
		else if(ctx.getChild(0).getText().equals("tan")) {
			map.put(ctx.getText(), new BigDecimal(Math.tan(num)));
		}
		else if(ctx.getChild(0).getText().equals("sin")) {
			map.put(ctx.getText(), new BigDecimal(Math.sin(num)));
		}
		else if(ctx.getChild(0).getText().equals("acos")) {
			map.put(ctx.getText(), new BigDecimal(Math.acos(num)));
		}
		else if(ctx.getChild(0).getText().equals("atan")) {
			map.put(ctx.getText(), new BigDecimal(Math.atan(num)));
		}
		else if(ctx.getChild(0).getText().equals("asin")) {
			map.put(ctx.getText(), new BigDecimal(Math.asin(num)));
		}
		else if(ctx.getChild(0).getText().equals("log")) {
			map.put(ctx.getText(), new BigDecimal(Math.log(num)));
		}
		else if(ctx.getChild(0).getText().equals("ln")) {
			map.put(ctx.getText(), new BigDecimal(Math.log(num)));
		}
		else if(ctx.getChild(0).getText().equals("sqrt")) {
			map.put(ctx.getText(), new BigDecimal(Math.sqrt(num)));
		}
		else {
			setErrorMsg("Invalid Func name: " + ctx.getText());
			return;
		}
		
		//System.out.println("in map :" + map.get(ctx.getText()));
		
	}
	
	@Override 
	public void enterVariable(calculatorParser.VariableContext ctx) {
		//System.out.println("enterVariable");
	}

	@Override 
	public void exitVariable( calculatorParser.VariableContext ctx) {
		//System.out.println("exitVariable");
	}
	



}
