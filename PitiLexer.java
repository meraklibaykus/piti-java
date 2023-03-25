lexer grammar PitiLexer;

// keywords
IF: 'if';
ELSE: 'else';
WHILE: 'while';
FOR: 'for';

// operators
PLUS: '+';
MINUS: '-';
MULTIPLY: '*';
DIVIDE: '/';

// literals
INTEGER_LITERAL: [0-9]+;
FLOAT_LITERAL: [0-9]+'.'[0-9]+;

// identifiers
IDENTIFIER: [a-zA-Z]+;

// separators
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
SEMI: ';';
COMMA: ',';

// ignore whitespaces
WS: [ \t\r\n]+ -> skip;

// ignore comments
COMMENT: '//' ~[\r\n]* -> skip;
