package com.zz.dsl.calc.antlr.visitor;

import com.zz.dsl.graalvm.CalcLanguage;
import com.zz.dsl.graalvm.node.*;
import dsl.CalcBaseVisitor;
import dsl.CalcParser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class AstVisitor extends CalcBaseVisitor<Object> {

    private CalcLanguage language;

    @Override
    public Object visitExpression(CalcParser.ExpressionContext ctx) {
        CalcExprNode exprNode = (CalcExprNode) visitExpr(ctx.expr());

        return new CalcExpressionNode(this.language, exprNode);
    }

    private Object visitExpr(CalcParser.ExprContext ctx) {
        if (ctx instanceof CalcParser.IiContext) {
            return this.visitIi((CalcParser.IiContext) ctx);
        } else if (ctx instanceof CalcParser.IqContext) {
            return this.visitIq((CalcParser.IqContext) ctx);
        } else if (ctx instanceof CalcParser.QiContext) {
            return this.visitQi((CalcParser.QiContext) ctx);
        } else {
            return this.visitQq((CalcParser.QqContext) ctx);
        }
    }

    @Override
    public Object visitIi(CalcParser.IiContext ctx) {
        return new CalcIiExprNode(ctx.left.getText(), ctx.right.getText(), ctx.op.getText());
    }

    @Override
    public Object visitIq(CalcParser.IqContext ctx) {
        CalcQexprNode right = (CalcQexprNode) visitQexpr(ctx.right);
        return new CalcIqExprNode(ctx.left.getText(), right, ctx.op.getText());
    }

    @Override
    public Object visitQi(CalcParser.QiContext ctx) {
        CalcQexprNode left = (CalcQexprNode) visitQexpr(ctx.left);
        return new CalcQiExprNode(left, ctx.right.getText(), ctx.op.getText());
    }

    @Override
    public Object visitQq(CalcParser.QqContext ctx) {
        CalcQexprNode left = (CalcQexprNode) visitQexpr(ctx.left);
        CalcQexprNode right = (CalcQexprNode) visitQexpr(ctx.right);
        return new CalcQqExprNode(left, right, ctx.op.getText());
    }

    @Override
    public Object visitQexpr(CalcParser.QexprContext ctx) {
        CalcExprNode calcExprNode = (CalcExprNode) visitExpr(ctx.expr());
        return new CalcQexprNode(calcExprNode);
    }


}
