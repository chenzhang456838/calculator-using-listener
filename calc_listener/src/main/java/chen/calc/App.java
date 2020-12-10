package chen.calc;

import java.math.BigDecimal;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.TerminalBuilder;
import org.jline.terminal.Terminal;
import org.jline.reader.EndOfFileException;

public class App {
	public static void main(String[] args) throws Exception {

		LineReader read = LineReaderBuilder.builder().build();
		String equation = "calculator>";
		while (read != null) {
			String line = null;
			try {
				line = read.readLine(equation);
				CharStream input = CharStreams.fromString(line);
				calculatorLexer lexer = new calculatorLexer(input);
				CommonTokenStream tokenStream = new CommonTokenStream(lexer);
				calculatorParser parser = new calculatorParser(tokenStream);
				ParseTree parseTree = parser.expression();

				ParseTreeWalker walker = new ParseTreeWalker();
				listener listener = new listener();

				walker.walk(listener, parseTree);
				BigDecimal res = listener.map.get(parseTree.getText());
				if(res != null)
				     System.out.println(res);
				else
					 System.out.println("Semantic Error - " + listener.sematicErrorMsg);

				/*
				 * visitor visit = new visitor(); Double result = visit.visit(parseTree);
				 * if(result != null) { System.out.println(result); }
				 */
			} catch (UserInterruptException e) {
				return;
			} catch (EndOfFileException e) {
				return;
			}
			catch(Exception e) {
				
				return;
			}
			
		}

	}

}
