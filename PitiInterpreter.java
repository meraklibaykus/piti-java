import java.util.*;

public class PitiInterpreter extends PitiBaseVisitor<Integer> {
    private Map<String, Integer> variables = new HashMap<>();

    @Override
    public Integer visitAssign(PitiParser.AssignContext ctx) {
        String varName = ctx.ID().getText();
        int value = visit(ctx.expression());
        variables.put(varName, value);
        return value;
    }

    @Override
    public Integer visitPrint(PitiParser.PrintContext ctx) {
        int value = visit(ctx.expression());
        System.out.println(value);
        return value;
    }

    @Override
    public Integer visitNumber(PitiParser.NumberContext ctx) {
        return Integer.parseInt(ctx.getText());
    }

    @Override
    public Integer visitId(PitiParser.IdContext ctx) {
        String varName = ctx.ID().getText();
        if (!variables.containsKey(varName)) {
            throw new RuntimeException("Undefined variable: " + varName);
        }
        return variables.get(varName);
    }

    @Override
    public Integer visitAdd(PitiParser.AddContext ctx) {
        int left = visit(ctx.expression(0));
        int right = visit(ctx.expression(1));
        return left + right;
    }

    @Override
    public Integer visitSubtract(PitiParser.SubtractContext ctx) {
        int left = visit(ctx.expression(0));
        int right = visit(ctx.expression(1));
        return left - right;
    }

    @Override
    public Integer visitMultiply(PitiParser.MultiplyContext ctx) {
        int left = visit(ctx.expression(0));
        int right = visit(ctx.expression(1));
        return left * right;
    }

    @Override
    public Integer visitDivide(PitiParser.DivideContext ctx) {
        int left = visit(ctx.expression(0));
        int right = visit(ctx.expression(1));
        if (right == 0) {
            throw new RuntimeException("Division by zero");
        }
        return left / right;
    }

    public static void main(String[] args) {
        String input = "x = 3 + 4\n" +
                       "print x * 2\n";

        PitiLexer lexer = new PitiLexer(CharStreams.fromString(input));
        PitiParser parser = new PitiParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.program();

        PitiInterpreter interpreter = new PitiInterpreter();
        interpreter.visit(tree);
    }
}
