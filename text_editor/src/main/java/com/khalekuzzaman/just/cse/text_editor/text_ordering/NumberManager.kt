package com.khalekuzzaman.just.cse.text_editor.text_ordering

data class NumberManager(
    val text: String,
    val start: Int,
    val end: Int,
) {
    companion object {
        private const val NEW_LINE = '\n'
    }

}