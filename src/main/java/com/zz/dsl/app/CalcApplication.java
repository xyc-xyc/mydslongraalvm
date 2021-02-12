package com.zz.dsl.app;

import com.zz.dsl.graalvm.CalcLanguage;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.PrintStream;

public class CalcApplication {

    public static void main(String[] args) {
        PrintStream err = System.err;
        try (Context context = Context.newBuilder(CalcLanguage.ID).build()) {
            File file = new File("expression.mycalc");
            Value result = context.eval(Source.newBuilder("mycalc", file).build());
            System.out.println("result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
