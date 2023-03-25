grammar Piti;

/* Lexer rules */

INT: '-'? [0-9]+; // integer
FLOAT: '-'? [0-9]+ '.' [0-9]* | '-'? '.' [0-9]+; // float
BOOL: 'true' | 'false'; // boolean
ID: [a-zA-Z]+; // identifier
WS: [ \t\r\n]+ -> skip; // whitespace

PLUS: '+'; // addition
MINUS: '-'; // subtraction
MULT: '*'; // multiplication
DIV: '/'; // division
MOD: '%'; // modulo

GT: '>'; // greater than
LT: '<'; // less than
GTE: '>='; // greater than or equal to
LTE: '<='; // less than or equal to
EQ: '=='; // equal to
NEQ: '!='; // not equal to

AND: 'and'; // logical and
OR: 'or'; // logical or
NOT: 'not'; // logical not

LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
COMMA: ',';
SEMI: ';';

/* Parser rules */

program: statement+;

statement: assignment_statement
         | if_statement
         | while_statement
         | print_statement
         | expression_statement
         ;

assignment_statement: ID '=' expression SEMI;

if_statement: 'if' LPAREN expression RPAREN LBRACE statement* RBRACE ('else' LBRACE statement* RBRACE)?;

while_statement: 'while' LPAREN expression RPAREN LBRACE statement* RBRACE;

print_statement: 'print' LPAREN expression RPAREN SEMI;

expression_statement: expression SEMI;

expression: term ((PLUS | MINUS) term)*;

term: factor ((MULT | DIV | MOD) factor)*;

factor: (PLUS | MINUS) factor
      | primary
      ;

primary: INT
       | FLOAT
       | BOOL
       | ID
       | LPAREN expression RPAREN
       ;

/* End of grammar */
