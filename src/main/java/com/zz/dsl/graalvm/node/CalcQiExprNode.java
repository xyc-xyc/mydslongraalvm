package com.zz.dsl.graalvm.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CalcQiExprNode extends CalcExprNode {

    @Child private CalcQexprNode left;
    private String right;
    private String op;

    public Integer execute(VirtualFrame frame) {
        return calc(left.execute(frame), right, op);
    }

}
