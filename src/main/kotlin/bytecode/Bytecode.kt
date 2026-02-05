package io.bl0xxy.bytecode

import io.bl0xxy.metadata.FunctionMeta

enum class BytecodeValueType {
    INT,
    STRING,
    BOOLEAN,
    DYNAMIC_VALUE
}

data class ClassMeta(
    val name: String,
    val fields: Map<String, BytecodeValueType>,
    val methods: Map<String, FunctionMeta>
)

data class ClassInstance(
    val meta: ClassMeta,
    val fieldValues: Map<String, BytecodeValueType> = mutableMapOf()
)

sealed class BytecodeValue(open val type: BytecodeValueType) {
    data class IntValue(val value: Int) : BytecodeValue(BytecodeValueType.INT)
    data class StringValue(val value: String) : BytecodeValue(BytecodeValueType.STRING)
    data class BooleanValue(val value: Boolean) : BytecodeValue(BytecodeValueType.BOOLEAN)
    data class ClassInstance(val value: ClassInstance?) : BytecodeValue(BytecodeValueType.DYNAMIC_VALUE) {}
}