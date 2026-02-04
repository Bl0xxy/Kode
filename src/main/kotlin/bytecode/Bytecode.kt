package io.bl0xxy.bytecode

enum class BytecodeValueType {
    INT,
    STRING,
    BOOLEAN
}

data class BytecodeValue(
    val value: Any,
    val type: BytecodeValueType
)
