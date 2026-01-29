package io.bl0xxy.lexer

class Lexer(private val input: String) {
    private var pos = 0
    private val currentChar: Char? get() = input.getOrNull(pos)

    private fun peek(): Char? {
        return input.getOrNull(pos + 1)
    }

    private fun advance(offset: Int = 1) {
        pos += offset
    }

    private fun skipWhitespace() {
        while (currentChar?.isWhitespace() == true)
            advance()
    }

    private val singleCharTokens = mapOf(
        '+' to Token.Plus,
        '-' to Token.Minus,
        '*' to Token.Star,
        '/' to Token.Slash,
        '=' to Token.Equal,
        '!' to Token.Bang,
        '<' to Token.Less,
        '>' to Token.Greater,
        '(' to Token.LeftParen,
        ')' to Token.RightParen,
        '{' to Token.LeftBrace,
        '}' to Token.RightBrace,
        ',' to Token.Comma,
        '.' to Token.Dot,
        ';' to Token.Semicolon
    )

    private val doubleCharTokens = mapOf(
        "==" to Token.EqualEqual,
        "!=" to Token.BangEqual,
        "<=" to Token.LessEqual,
        ">=" to Token.GreaterEqual
    )

    fun getNextToken(): Token {
        skipWhitespace()

        val current = currentChar ?: return Token.EOF

        val next = peek()
        val twoChars = next?.let { "$current$it" }
        doubleCharTokens[twoChars]?.let {
            advance(2)
            return it
        }

        singleCharTokens[current]?.let {
            advance()
            return it
        }

        return when {
            current.isDigit() -> number()
            current.isLetter() || current == '_' -> alphanumeric()
            current == '"' -> string()
            else -> throw IllegalArgumentException("Illegal character: $current")
        }
    }

    private fun string(): Token {
        advance()
        val start = pos
        while (currentChar != null && currentChar != '"')
            advance()

        val end = pos - 1
        advance()

        return Token.StringLiteral(input.substring(start, end))
    }

    private fun number(): Token {
        val start = pos
        while (currentChar?.isDigit() == true)
            advance()

        return Token.NumberLiteral(input.substring(start, pos))
    }

    private fun alphanumeric(): Token {
        val start = pos
        while (currentChar?.let { it.isLetterOrDigit() || it == '_' } == true)
            advance()

        return when (val value = input.substring(start, pos)) {
            "var" -> Token.Var
            "let" -> Token.Let
            "component" -> Token.Component
            "thru" -> Token.Thru
            "func" -> Token.Func
            "if" -> Token.If
            "else" -> Token.Else
            "while" -> Token.While
            else -> Token.Identifier(value)
        }
    }
}
