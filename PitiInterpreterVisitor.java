import org.antlr.v4.runtime.tree.TerminalNode;

public class PitiInterpreterVisitor extends PitiBaseVisitor<Integer> {

    @Override
    public Integer visitProgram(PitiParser.ProgramContext ctx) {
        for (PitiParser.StatementContext statementContext : ctx.statement()) {
            visit(statementContext);
        }
        return 0;
    }

    @Override
    public Integer visitPrintStatement(PitiParser.PrintStatementContext ctx) {
        Integer value = visit(ctx.expression());
        System.out.println(value);
        return value;
    }

    @Override
    public Integer visitAssignStatement(PitiParser.AssignStatementContext ctx) {
        String variableName = ctx.ID().getText();
        Integer value = visit(ctx.expression());
        PitiMemory.setVariable(variableName, value);
        return value;
    }

    @Override
    public Integer visitNumberExpression(PitiParser.NumberExpressionContext ctx) {
        return Integer.parseInt(ctx.NUMBER().getText());
    }

    @Override
    public Integer visitVariableExpression(PitiParser.VariableExpressionContext ctx) {
        String variableName = ctx.ID().getText();
        return PitiMemory.getVariable(variableName);
    }

    @Override
    public Integer visitAdditiveExpression(PitiParser.AdditiveExpressionContext ctx) {
        Integer left = visit(ctx.expression(0));
        Integer right = visit(ctx.expression(1));
        if (ctx.ADD() != null) {
            return left + right;
        } else {
            return left - right;
        }
    }

    @Override
    public Integer visitMultiplicativeExpression(PitiParser.MultiplicativeExpressionContext ctx) {
        Integer left = visit(ctx.expression(0));
        Integer right = visit(ctx.expression(1));
        if (ctx.MULTIPLY() != null) {
            return left * right;
        } else {
            return left / right;
        }
    }

}
