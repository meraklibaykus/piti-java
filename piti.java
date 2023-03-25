import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class PitiInterpreter {
    private static final Map<String, Integer> variables = new HashMap<>();

    public static void main(String[] args) {
        String input = "deger x = 5;\n" +
                "deger y = 3;\n" +
                "deger z = x + y;\n" +
                "yazdir z;";

        ANTLRInputStream inputStream = new ANTLRInputStream(input);
        PitiLexer lexer = new PitiLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        PitiParser parser = new PitiParser(tokenStream);
        ParseTree tree = parser.program();

        PitiInterpreterVisitor visitor = new PitiInterpreterVisitor(variables);
        visitor.visit(tree);
    }
}
