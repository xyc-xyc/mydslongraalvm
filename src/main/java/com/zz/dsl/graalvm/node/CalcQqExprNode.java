package com.zz.dsl.graalvm.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CalcQqExprNode extends CalcExprNode {

    @Child
    private CalcQexprNode left;
    @Child
    private CalcQexprNode right;
    private String op;

    public Integer execute(VirtualFrame frame) {

        return calc(left.execute(frame),
                right.execute(frame), op);
    }

}
