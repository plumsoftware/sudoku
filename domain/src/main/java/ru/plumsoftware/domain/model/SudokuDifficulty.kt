package ru.plumsoftware.domain.model

import ru.plumsoftware.domain.model.SudokuDifficulty.None.arrayNames

sealed class SudokuDifficulty {
    protected val arrayNames = arrayOf(
        "None",
        "Легко",
        "Среднее",
        "Сложно",
        "Очень сложно"
    )

    data object None : SudokuDifficulty() {
        override fun toString(): String {
            return arrayNames[0]
        }
    }

    data class Easy(val numberChance: Int = 80) : SudokuDifficulty() {
        override fun toString(): String {
            return arrayNames[1]
        }
    }

    data class Medium(val numberChance: Int = 60) : SudokuDifficulty() {
        override fun toString(): String {
            return arrayNames[2]
        }
    }

    data class Hard(val numberChance: Int = 40) : SudokuDifficulty() {
        override fun toString(): String {
            return arrayNames[3]
        }
    }

    data class ExtraHard(val numberChance: Int = 20) : SudokuDifficulty() {
        override fun toString(): String {
            return arrayNames[4]
        }
    }

    companion object {
        fun fromString(from: String): SudokuDifficulty {
            return when (from) {
                arrayNames[0] -> None
                arrayNames[1] -> Easy()
                arrayNames[2] -> Medium()
                arrayNames[3] -> Hard()
                arrayNames[4] -> ExtraHard()
                else -> None
            }
        }
    }
}