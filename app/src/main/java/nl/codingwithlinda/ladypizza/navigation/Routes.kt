package nl.codingwithlinda.ladypizza.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
object Menu: NavKey

@Serializable
data class ProductDetail(val id: String): NavKey