package io.bl0xxy.lexer

sealed class Token {
    // Single-character operators
    data object Plus    : Token()
    data object Minus   : Token()
    data object Star    : Token()
    data object Slash   : Token()
    data object Equal   : Token()
    data object Bang    : Token()
    data object Less    : Token()
    data object Greater : Token()

    // Multi-character operators
    data object EqualEqual   : Token()
    data object BangEqual    : Token()
    data object LessEqual    : Token()
    data object GreaterEqual : Token()

    // Punctuation
    data object LeftParen  : Token()
    data object RightParen : Token()
    data object LeftBrace  : Token()
    data object RightBrace : Token()
    data object Comma      : Token()
    data object Dot        : Token()
    data object Semicolon  : Token()

    // Literals
    data class Identifier(val name: String)     : Token()
    data class NumberLiteral(val value: String) : Token()
    data class StringLiteral(val value: String) : Token()

    // Keywords
    data object Var       : Token()
    data object Let       : Token()
    data object Component : Token()
    data object Thru      : Token()
    data object Func      : Token()
    data object If        : Token()
    data object Else      : Token()
    data object While     : Token()

    // End of file
    data object EOF : Token()
}