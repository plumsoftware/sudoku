package ru.plumsoftware.domain.model

data class SudokuItem(
    val isVisible: Boolean,
    val number: Int,
    val isCorrect: Boolean? = null
) {
    companion object {
        fun empty() : SudokuItem {
            return SudokuItem(isVisible = false, number = -1, isCorrect = null)
        }
    }
}
