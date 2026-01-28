package io.bl0xxy.lexer

enum class TokenType {
    // Symbols
    PLUS, MINUS, MUL, DIV,
    LPAREN, RPAREN,

    // Literals
    NUMBER, STRING,

    // Other
    IDENTIFIER,

    // Special
    EOL, EOF
}

data class Token(val type: TokenType, val value: String? = null)

class Lexer(private val input: String) {
    private val symbols = charArrayOf('+', '-', '*', '/')

    private var pos = 0
    private val len = input.length
    private val currentChar: Char? get() = input.getOrNull(pos)

    private fun advance() {
        pos++
    }

    private fun skipWhitespace() {
        while (currentChar?.isWhitespace() == true)
            advance()
    }

    fun getNextToken(): Token {
        skipWhitespace()

        val current = currentChar ?: return Token(TokenType.EOF)

        return when {
            current.isDigit() -> number()
            current.isLetter() || current == '_' -> identifier()
//            symbols.contains(current) -> symbol()
            else -> throw IllegalArgumentException("Illegal character: $current")
        }
    }

//    private fun symbol(): Token {
//
//    }

    private fun number(): Token {
        val start = pos
        while (currentChar?.isDigit() == true)
            advance()

        return Token(TokenType.NUMBER, input.substring(start, pos))
    }

    private fun identifier(): Token {
        val start = pos
        while (currentChar?.let { it.isLetterOrDigit() || it == '_' } == true)
            advance()

        return Token(TokenType.IDENTIFIER, input.substring(start, pos))
    }
}
