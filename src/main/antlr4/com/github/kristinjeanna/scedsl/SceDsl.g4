grammar SceDsl;

expression
    : function
    | explicitBoolean
    | quotedString
    ;

function
    : and
    | or
    | xor
    | not
    | contains
    | startsWith
    | endsWith
    ;

explicitBoolean
    : booleanTrue
    | booleanFalse
    ;

and
    : AND LPAREN expression (COMMA expression)* RPAREN
    ;

or
    : OR LPAREN expression (COMMA expression)* RPAREN
    ;

xor
    : XOR LPAREN expression (COMMA expression)* RPAREN
    ;

not
    : NOT LPAREN expression RPAREN
    ;

contains
    : CONTAINS LPAREN quotedString RPAREN
    ;

startsWith
    : STARTSWITH LPAREN quotedString RPAREN
    ;

endsWith
    : ENDSWITH LPAREN quotedString RPAREN
    ;

booleanTrue
    : TRUE
    ;

booleanFalse
    : FALSE
    ;

quotedString
    : SINGLE_QUOTE string SINGLE_QUOTE
    ;

string
    : STRING
    ;

AND : 'and' ;

OR : 'or' ;

XOR : 'xor' ;

NOT : 'not' ;

CONTAINS : 'contains' ;

STARTSWITH : 'startsWith' ;

ENDSWITH : 'endsWith' ;

TRUE : 'true' ;

FALSE : 'false' ;

STRING
    : [A-Za-z]+
    ;

SINGLE_QUOTE
    : '\''
    ;

COMMA
    : ','
    ;

LPAREN
    : '('
    ;

RPAREN
    : ')'
    ;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
