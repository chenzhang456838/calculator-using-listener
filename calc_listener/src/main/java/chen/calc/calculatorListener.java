package chen.calc;

// Generated from calculator.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link calculatorParser}.
 */
public interface calculatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link calculatorParser#equation}.
	 * @param ctx the parse tree
	 */
	void enterEquation(calculatorParser.EquationContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#equation}.
	 * @param ctx the parse tree
	 */
	void exitEquation(calculatorParser.EquationContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(calculatorParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(calculatorParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#multiplyingExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyingExpression(calculatorParser.MultiplyingExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#multiplyingExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyingExpression(calculatorParser.MultiplyingExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#powExpression}.
	 * @param ctx the parse tree
	 */
	void enterPowExpression(calculatorParser.PowExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#powExpression}.
	 * @param ctx the parse tree
	 */
	void exitPowExpression(calculatorParser.PowExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#signedAtom}.
	 * @param ctx the parse tree
	 */
	void enterSignedAtom(calculatorParser.SignedAtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#signedAtom}.
	 * @param ctx the parse tree
	 */
	void exitSignedAtom(calculatorParser.SignedAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(calculatorParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(calculatorParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#scientific}.
	 * @param ctx the parse tree
	 */
	void enterScientific(calculatorParser.ScientificContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#scientific}.
	 * @param ctx the parse tree
	 */
	void exitScientific(calculatorParser.ScientificContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(calculatorParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(calculatorParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(calculatorParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(calculatorParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(calculatorParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(calculatorParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#funcname}.
	 * @param ctx the parse tree
	 */
	void enterFuncname(calculatorParser.FuncnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#funcname}.
	 * @param ctx the parse tree
	 */
	void exitFuncname(calculatorParser.FuncnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#relop}.
	 * @param ctx the parse tree
	 */
	void enterRelop(calculatorParser.RelopContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#relop}.
	 * @param ctx the parse tree
	 */
	void exitRelop(calculatorParser.RelopContext ctx);
}