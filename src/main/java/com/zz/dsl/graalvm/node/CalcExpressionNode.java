package com.zz.dsl.graalvm.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;
import com.zz.dsl.graalvm.CalcLanguage;

public class CalcExpressionNode extends RootNode {

    @Child CalcExprNode expr;

    public CalcExpressionNode(CalcLanguage language, CalcExprNode expr) {
        super(language);
        this.expr = expr;
    }

    public Integer execute(VirtualFrame frame) {
        return expr.execute(frame);
    }
}
