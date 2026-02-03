package io.bl0xxy.parser

enum class Assoc {

}

enum class BinaryOp(val symbol: String, val precedence: Int) {
    MUL("*", 3),
    DIV("/", 3),

    ADD("+", 2),
    SUB("-", 2),

    GT(">", 1),
    LT("<", 1),
    GTE(">=", 1),
    LTE("<=", 1),

    EQ("==", 0),
    NEQ("!=", 0)
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