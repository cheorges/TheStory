package v1;

import java_cup.runtime.*;
import java_cup.runtime.Symbol;

%%
%public
%class Scanner
%unicode
%line
%column
%cupsym ParserSym
%cup
%cupdebug

%{
    StringBuilder string = new StringBuilder();
    ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();

    private Symbol symbol(int type) {
        // return new Symbol(type, yyline, yycolumn);
        return symbolFactory.newSymbol(
                String.format("%d", type),
                type,
                new ComplexSymbolFactory.Location(yyline, yycolumn),
                new ComplexSymbolFactory.Location(yyline, yycolumn + 1));
    }

    private Symbol symbol(int type, Object value) {
        // return new Symbol(type, yyline, yycolumn, yytext());
        return symbolFactory.newSymbol(
                String.format("%d", type),
                type,
                new ComplexSymbolFactory.Location(yyline, yycolumn),
                new ComplexSymbolFactory.Location(yyline, yycolumn + 1),
                (value != null ? value : yytext()));
    }
%}

%eofval{
  return symbol(ParserSym.EOF);
%eofval}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace = {LineTerminator} | [ \t\f]

Prefix = a|an|the|my|your
Identifier = [a-zA-Z]+
Number = \d*\.?\d+
StringIdentifier = [^\n\r\"\\]+

%state STRING

%%

<YYINITIAL> {
    /* keywords */
    (Let|let)                           { return symbol(ParserSym.LET); }
    (Put|put)                           { return symbol(ParserSym.PUT); }
    "into"                              { return symbol(ParserSym.INTO); }
    "be"                                { return symbol(ParserSym.BE); }
    \"                                  { string.setLength(0); yybegin(STRING); }

    (with|plus)                         { return symbol(ParserSym.ADD); }
    (without|minus)                     { return symbol(ParserSym.SUB); }
    (times|of)                          { return symbol(ParserSym.MUL); }
    over                                { return symbol(ParserSym.DIV); }

    {LineTerminator}                    { return symbol(ParserSym.LINE_TERMINATOR); }
    {Number}                            { return symbol(ParserSym.NUMBER, yytext()); }
    {WhiteSpace}                        { /* ignore */ }

    {Prefix}                            { return symbol(ParserSym.PREFIX, yytext()); }
    {Identifier}                        { return symbol(ParserSym.IDENTIFIER, yytext()); }
}

<STRING> {
    \"                                  { yybegin(YYINITIAL); return symbol(ParserSym.STRING_LITERAL, string.toString()); }
    {StringIdentifier}                  { string.append( yytext() ); }
    \\t                                 { string.append('\t'); }
    \\n                                 { string.append('\n'); }
    \\r                                 { string.append('\r'); }
    \\\"                                { string.append('\"'); }
    \\                                  { string.append('\\'); }
    {LineTerminator}                    { throw new RuntimeException("Unterminated string at end of line"); }
}

[^]                                     { throw new RuntimeException("Illegal character \""+yytext()+ "\" at line "+yyline+", column "+yycolumn); }
