grammar Calc;

@header {
package dsl;
}

expression
   : expr EOF
   ;

expr
   : left=INT op=OP right=INT       # ii
   | left=INT op=OP right=qexpr     # iq
   | left=qexpr op=OP right=INT     # qi
   | left=qexpr op=OP right=qexpr   # qq
   ;

qexpr
   : '(' expr ')'
   ;

INT
   : [0-9]+
   ;

OP
   : ('+' | '-')
   ;


WS
   : [ \r\n\t] + -> skip
   ;
