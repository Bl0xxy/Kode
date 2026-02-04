package io.bl0xxy.metadata

import io.bl0xxy.bytecode.BytecodeValueType

data class FunctionParameter(
    val name: String,
    val type: BytecodeValueType
)

data class FunctionMeta(
    val name: String,
    val parameters: List<FunctionParameter>,
    val returnType: BytecodeValueType,
    val entry: Int
)
