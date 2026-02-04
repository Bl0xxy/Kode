package io.bl0xxy.metadata

data class CommandMeta(
    val name: String,
    val description: String,
    val usage: String = "/$name",
    val permission: String = "kode.default",
    val aliases: List<String> = emptyList()
)
