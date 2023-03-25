import java.util.Map;

public class PitiInterpreterVisitor extends PitiBaseVisitor<Integer> {
    private final Map<String, Integer> variables;

    public PitiInterpreterVisitor(Map<String, Integer> variables) {
        this.variables = variables;
    }

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
}
