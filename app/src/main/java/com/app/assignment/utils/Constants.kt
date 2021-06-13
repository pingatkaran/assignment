package com.app.assignment.utils

import androidx.annotation.ColorRes
import com.app.assignment.R
import kotlin.math.roundToInt

object Constants {

    private const val AQI_GRADE_UNKNOWN = 0
    private const val AQI_GRADE_GOOD = 1
    private const val AQI_GRADE_SATISFACTORY = 2
    private const val AQI_GRADE_MODERATE = 3
    private const val AQI_GRADE_POOR = 4
    private const val AQI_GRADE_VERY_POOR = 5
    private const val AQI_GRADE_SEVERE = 6


    private fun getAQIGrade(aqiValue: String): Int {
        try {
            val value = aqiValue.toFloat().roundToInt()
            return if (value in 1..50) {
                AQI_GRADE_GOOD
            } else if (value in 51..100) {
                AQI_GRADE_SATISFACTORY
            } else if (value in 101..200) {
                AQI_GRADE_MODERATE
            } else if (value in 201..300) {
                AQI_GRADE_POOR
            } else if (value in 301..400) {
                AQI_GRADE_VERY_POOR
            } else if (value in 401..500) {
                AQI_GRADE_SEVERE
            } else {
                AQI_GRADE_UNKNOWN
            }
        } catch (e: NumberFormatException) {
            return AQI_GRADE_UNKNOWN
        }
    }

    @ColorRes
    fun getAQIColorHighlights(aqiValue: String): Int {
        return when (getAQIGrade(aqiValue)) {
            AQI_GRADE_GOOD -> R.color.good
            AQI_GRADE_SATISFACTORY -> R.color.satisfactory
            AQI_GRADE_MODERATE -> R.color.moderate
            AQI_GRADE_POOR -> R.color.poor
            AQI_GRADE_VERY_POOR -> R.color.verypoor
            AQI_GRADE_SEVERE -> R.color.severe
            else -> R.color.good
        }
    }

    fun getAQIHintHighlights(aqiValue: String): String {
        return when (getAQIGrade(aqiValue)) {
            AQI_GRADE_GOOD -> "good"
            AQI_GRADE_SATISFACTORY -> "satisfactory"
            AQI_GRADE_MODERATE -> "moderate"
            AQI_GRADE_POOR -> "poor"
            AQI_GRADE_VERY_POOR -> "very poor"
            AQI_GRADE_SEVERE -> "severe"
            else -> "unknown"
        }
    }

}