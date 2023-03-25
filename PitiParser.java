import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class PitiParser extends PitiBaseListener {
    public static void main(String[] args) throws Exception {
        // Input expression to be parsed
        String expression = "3 + 4 * 2";

        // Create a CharStream from the input expression
        CharStream charStream = CharStreams.fromString(expression);

        // Create a lexer that feeds off of the input CharStream
        PitiLexer lexer = new PitiLexer(charStream);

        // Create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that feeds off the tokens buffer
        PitiParser parser = new PitiParser(tokens);

        // Parse the expression
        ParseTree tree = parser.expression();

        // Walk the parse tree and print the result
        EvalVisitor evalVisitor = new EvalVisitor();
        System.out.println(evalVisitor.visit(tree));
    }

    static class EvalVisitor extends PitiBaseVisitor<Integer> {
        @Override
        public Integer visitAdd(PitiParser.AddContext ctx) {
            int left = visit(ctx.expression(0));
            int right = visit(ctx.expression(1));
            return left + right;
        }

        @Override
        public Integer visitMultiply(PitiParser.MultiplyContext ctx) {
            int left = visit(ctx.expression(0));
            int right = visit(ctx.expression(1));
            return left * right;
        }

        @Override
        public Integer visitNumber(PitiParser.NumberContext ctx) {
            return Integer.parseInt(ctx.getText());
        }
    }
}
