package io.bl0xxy

import io.bl0xxy.lexer.Lexer
import io.bl0xxy.lexer.Token

fun main() {
    val lexer = Lexer("let x = 53 == 83;")
    val tokens = mutableListOf<Token>()

    while (true) {
        val token = lexer.getNextToken()
        tokens.add(token)
        if (token is Token.EOF) break
    }

    println("$tokens")
}
