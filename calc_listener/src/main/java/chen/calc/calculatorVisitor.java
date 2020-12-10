package chen.calc;

// Generated from calculator.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link calculatorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface calculatorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link calculatorParser#equation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquation(calculatorParser.EquationContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(calculatorParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#multiplyingExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyingExpression(calculatorParser.MultiplyingExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#powExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowExpression(calculatorParser.PowExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#signedAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignedAtom(calculatorParser.SignedAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(calculatorParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#scientific}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScientific(calculatorParser.ScientificContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(calculatorParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(calculatorParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(calculatorParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#funcname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncname(calculatorParser.FuncnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link calculatorParser#relop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelop(calculatorParser.RelopContext ctx);
}