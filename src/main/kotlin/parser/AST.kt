package io.bl0xxy.parser

enum class Assoc {
    LEFT, RIGHT
}

enum class BinaryOp(val symbol: String, val precedence: Int, val assoc: Assoc) {
    MUL("*", 20, Assoc.LEFT),
    DIV("/", 20, Assoc.LEFT),

    ADD("+", 15, Assoc.LEFT),
    SUB("-", 15, Assoc.LEFT),

    GT(">", 10, Assoc.LEFT),
    LT("<", 10, Assoc.LEFT),
    GTE(">=", 10, Assoc.LEFT),
    LTE("<=", 10, Assoc.LEFT),

    EQ("==", 5, Assoc.LEFT),
    NEQ("!=", 5, Assoc.LEFT),

    SET("=", 3, Assoc.RIGHT),

    AND("&&", 2, Assoc.LEFT),
    OR("||", 1, Assoc.LEFT)
}

enum class UnaryOp(val symbol: String) {
    NEG("-"),
    NOT("!")
}

data class DeclBlock(val declarations: List<ASTNode.StmtNode.TopLevel>)
data class StmtBlock(val statements: List<ASTNode.StmtNode.BlockLevel>)

sealed class ASTNode {

    sealed class ExprNode : ASTNode() {
        data class NumberNode(val number: Double) : ExprNode()
        data class StringNode(val string: String) : ExprNode()
        data class BinaryOpNode(val left: ExprNode, val op: BinaryOp, val right: ExprNode) : ExprNode()
        data class UnaryOpNode(val op: UnaryOp, val expr: ExprNode) : ExprNode()
    }

    sealed class StmtNode : ASTNode() {

        sealed class TopLevel : StmtNode() {
            data class ComponentDeclaration(val name: String, val body: DeclBlock) : TopLevel()
            data class FunctionDeclaration(val name: String, val body: StmtBlock) : TopLevel()
        }

        sealed class BlockLevel : StmtNode() {
            data class ExprStmt(val value: ExprNode) : StmtNode()
        }

    }

}