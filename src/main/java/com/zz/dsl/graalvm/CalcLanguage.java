package com.zz.dsl.graalvm;

import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.zz.dsl.calc.antlr.visitor.AstVisitor;
import com.zz.dsl.graalvm.node.CalcExpressionNode;
import dsl.CalcLexer;
import dsl.CalcParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

@TruffleLanguage.Registration(id = CalcLanguage.ID, name = CalcLanguage.ID,
        defaultMimeType = CalcLanguage.MIME_TYPE,
        characterMimeTypes = CalcLanguage.MIME_TYPE,
        fileTypeDetectors = FileDetector.class)
public class CalcLanguage extends TruffleLanguage<State> {
    public static final String ID = "mycalc";
    public static final String MIME_TYPE = "applications/x-mycalc";

    @Override
    protected State createContext(Env env) {
        return new State(this, env, 0);
    }

    private final Assumption singleContext = Truffle.getRuntime().createAssumption("My Calc context.");

    @Override
    protected CallTarget parse(ParsingRequest request) throws Exception {
        String expr = request.getSource().getCharacters().toString();
        CalcLexer lexer = new CalcLexer(CharStreams.fromString(expr));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalcParser parser = new CalcParser(tokens);

        ParseTree tree = parser.expression();
        System.out.println("tree:" + tree.toStringTree(parser));

        // rootNode's language instance has to be this !!!
        AstVisitor visitor = new AstVisitor(this);
        CalcExpressionNode rootNode = (CalcExpressionNode) visitor.visit(tree);

        //
        return Truffle.getRuntime().createCallTarget(rootNode);
    }
}
