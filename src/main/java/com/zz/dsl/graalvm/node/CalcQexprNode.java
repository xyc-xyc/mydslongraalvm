package com.zz.dsl.graalvm.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CalcQexprNode extends Node {

    @Child
    private CalcExprNode exprNode;

    public Integer execute(VirtualFrame frame) {
        return exprNode.execute(frame);
    }
}
