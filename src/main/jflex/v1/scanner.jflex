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

BooleanTrue = yes|ok|right|true
BooleanFalse = no|lies|wrong|false
Number = \d*\.?\d+
StringIdentifier = [^\n\r\"\\]+

%state STRING

%%

<YYINITIAL> {
    /* keywords */
    (Let|let)                           { return symbol(ParserSym.LET); }
    (Put|put)                           { return symbol(ParserSym.PUT); }
    (Now|now)                           { return symbol(ParserSym.NOW); }
    "into"                              { return symbol(ParserSym.INTO); }
    "be"                                { return symbol(ParserSym.BE); }
    "will be"                           { return symbol(ParserSym.WILL_BE); }
    \"                                  { string.setLength(0); yybegin(STRING); }

    (with|plus)                         { return symbol(ParserSym.ADD); }
    (without|minus)                     { return symbol(ParserSym.SUB); }
    (times|of)                          { return symbol(ParserSym.MUL); }
    over                                { return symbol(ParserSym.DIV); }

    "is higher than"                    { return symbol(ParserSym.GE); }
    "is greater than"                   { return symbol(ParserSym.GE); }
    "is bigger than"                    { return symbol(ParserSym.GE); }
    "is stronger than"                  { return symbol(ParserSym.GE); }
    "is lower than"                     { return symbol(ParserSym.LE); }
    "is less than"                      { return symbol(ParserSym.LE); }
    "is smaller than"                   { return symbol(ParserSym.LE); }
    "is weaker than"                    { return symbol(ParserSym.LE); }
    "is as high as"                     { return symbol(ParserSym.GEQ); }
    "is as great as"                    { return symbol(ParserSym.GEQ); }
    "is as big as"                      { return symbol(ParserSym.GEQ); }
    "is as strong as"                   { return symbol(ParserSym.GEQ); }
    "is as low as"                      { return symbol(ParserSym.LEQ); }
    "is as little as"                   { return symbol(ParserSym.LEQ); }
    "is as small as"                    { return symbol(ParserSym.LEQ); }
    "is as weak as"                     { return symbol(ParserSym.LEQ); }
    "is exactly the same as"            { return symbol(ParserSym.EQ); }
    "is not the same as"                { return symbol(ParserSym.NOT_EQ); }

    {LineTerminator}                    { return symbol(ParserSym.LINE_TERMINATOR); }
    {Number}                            { return symbol(ParserSym.NUMBER, yytext()); }
    {BooleanTrue}                       { return symbol(ParserSym.TRUE, true); }
    {BooleanFalse}                      { return symbol(ParserSym.FALSE, false); }
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
