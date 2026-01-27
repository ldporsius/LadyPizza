package nl.codingwithlinda.ladypizza.design.util

import android.content.Context

sealed interface UiText {
    data class DynamicText(val text: String): UiText
    data class StringResourceText(val resId: Int, val args: List<*>): UiText
}

fun UiText.asString(context: Context): String{
    return when(this){
        is UiText.DynamicText -> this.text
        is UiText.StringResourceText -> {
            context.getString(this.resId, this.args)
        }
    }
}