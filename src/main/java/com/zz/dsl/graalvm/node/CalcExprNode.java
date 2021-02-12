package com.zz.dsl.graalvm.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

public abstract class CalcExprNode extends Node {

    abstract public Integer execute(VirtualFrame frame);

    protected Integer calc(Object v0, Object v1, String op) {
        Integer i0 = v0 instanceof Integer ? (Integer) v0 : Integer.parseInt(v0.toString());
        Integer i1 = v1 instanceof Integer ? (Integer) v1 : Integer.parseInt(v1.toString());
        return "+".equals(op) ? i0 + i1 : i0 - i1;
    }

}
