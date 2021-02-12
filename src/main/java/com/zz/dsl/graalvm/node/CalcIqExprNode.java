package com.zz.dsl.graalvm.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CalcIqExprNode extends CalcExprNode {

    private String left;
    @Child private CalcQexprNode right;
    private String op;

    public Integer execute(VirtualFrame frame) {
        return calc(left, right.execute(frame), op);
    }

}
