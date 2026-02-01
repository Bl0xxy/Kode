package io.bl0xxy

import io.bl0xxy.lexer.Lexer
import io.bl0xxy.lexer.Token

fun main() {
    val input = """
        component MyPlugin thru Plugin {
            name: "MyPlugin"
            description: "Does things"
            authors: ["Bl0xxy", "Other"]
            commands: [HelloCommand]
        }
        
        component HelloCommand thru Command {
            name: "hello"
            display_name: "Hello"
            description: "Greet a player with a friendly message!"
        
            func execute(Executor executor, List<String> args) -> optional CommandResult {
                executor.message("Hello, enjoy your stay!");
            }
        }       
    """.trimIndent()

    val lexer = Lexer(input)
    val tokens = mutableListOf<Token>()

    while (true) {
        val token = lexer.getNextToken()
        tokens.add(token)
        if (token is Token.EOF) break
    }

    println("$tokens")
}
