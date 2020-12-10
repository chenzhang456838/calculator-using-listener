package chen.calc;

import java.util.*;

public class visitor extends calculatorBaseVisitor<Double> {
	 HashMap<String, Double> variable = new HashMap<String, Double>();

	@Override 
	public Double visitExpression(calculatorParser.ExpressionContext ctx) { 
		
		List<calculatorParser.MultiplyingExpressionContext> list = ctx.multiplyingExpression();
		
		calculatorParser.MultiplyingExpressionContext d0 = ctx.multiplyingExpression(0);
		
		Double res = visit(d0);
		
		if (list.size() == 1)
			return res;
		
		
		for (int i = 1; i < list.size(); i++) {
			calculatorParser.MultiplyingExpressionContext di = ctx.multiplyingExpression(i);
			res = (ctx.PLUS(i - 1) != null) ? res + visit(di) : res - visit(di);
		}
		
		return res;
		
	}
	
	@Override 
	public Double visitMultiplyingExpression(calculatorParser.MultiplyingExpressionContext ctx) { 
	   
		List<calculatorParser.PowExpressionContext> list = ctx.powExpression();
		
		calculatorParser.PowExpressionContext d0 = ctx.powExpression(0);
		
		Double res = visit(d0);
		
		if (list.size() == 1)
			return res;
		
		
		for (int i = 1; i < list.size(); i++) {
			calculatorParser.PowExpressionContext di = ctx.powExpression(i);
			res = (ctx.DIV(i - 1) != null) ? res / visit(di) : res * visit(di);
		}
		
		return res;
	 
	}

	@Override 
	public Double visitPowExpression(calculatorParser.PowExpressionContext ctx) { 
	  	   
		List<calculatorParser.SignedAtomContext> list = ctx.signedAtom();
		
		calculatorParser.SignedAtomContext d0 = ctx.signedAtom(0);
		
		Double res = visit(d0);
		
		if (list.size() == 1)
			return res;
		
		
		for (int i = 1; i < list.size(); i++) {
			calculatorParser.SignedAtomContext di = ctx.signedAtom(i);
			res = Math.pow(res, visit(di));
		}
		
		return res;
		
   }
	
	@Override 
	public Double visitSignedAtom(calculatorParser.SignedAtomContext ctx) { 
		
		if(ctx.PLUS() != null) {
			return visit(ctx.atom());
		}
		else if(ctx.MINUS() != null) {
			return -1 * visit(ctx.atom());
		}
		else if(ctx.func() != null) {
			return visit(ctx.func());
		}
	
		return visit(ctx.atom());
	}
	
	@Override 
	public Double visitAtom(calculatorParser.AtomContext ctx) {
		
		if(ctx.scientific() != null) {
			return visit(ctx.scientific());
		}
		else if(ctx.variable() != null) {
			return visit(ctx.variable());
		}
		else if(ctx.constant() != null) {
			return visit(ctx.constant());
		}
	
	    return visit(ctx.expression());
	}

	@Override 
	public Double visitScientific(calculatorParser.ScientificContext ctx) { 
		return Double.valueOf(ctx.getText());

	}

	@Override 
	public Double visitConstant(calculatorParser.ConstantContext ctx) { 
	    if(ctx.getText().equals("PI")) {
	    	return Math.PI;
	    }
	    else if(ctx.getText().equals("EULER")) {
	    	return Math.E;
	    }
	   
	    return 0.0;
	}

	@Override 
	public Double visitVariable(calculatorParser.VariableContext ctx) { 
		 String key = ctx.VARIABLE().getText();
	        if (variable.containsKey(key)) 
	            return variable.get(key);
	        else 
	            return 0.0;
		
	}

	@Override 
	public Double visitFunc(calculatorParser.FuncContext ctx) { 
		String type = ctx.funcname().getText();
		
		switch(type) {
		
		case "cos": 
			return Math.cos(visit(ctx.expression(0)));	
		case "tan":
			return Math.tan(visit(ctx.expression(0)));
		case "sin":
			return Math.sin(visit(ctx.expression(0)));
		case "acos": 
			return Math.acos(visit(ctx.expression(0)));	
		case "atan":
			return Math.atan(visit(ctx.expression(0)));
		case "asin":
			return Math.sin(visit(ctx.expression(0)));
		case "log": 
			return Math.log(visit(ctx.expression(0)));	
		case "ln":
			return Math.log(visit(ctx.expression(0)));	
		case "sqrt":
			return Math.sqrt(visit(ctx.expression(0)));
		}
		
		return visit(ctx.expression(0));
		
	}

	
	


}



