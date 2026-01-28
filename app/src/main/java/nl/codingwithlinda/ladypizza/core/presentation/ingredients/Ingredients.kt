package nl.codingwithlinda.ladypizza.core.presentation.ingredients

import nl.codingwithlinda.ladypizza.R
import nl.codingwithlinda.ladypizza.core.domain.model.Product
import nl.codingwithlinda.ladypizza.design.util.UiText


object tomatoSauce : Ingredient {
    override val id = "tomatoSauce"
    override fun toUi(): UiText {
        return UiText.StringResourceText(R.string.tomato_sauce)
    }
}
object mozzarellaCheese : Ingredient{
    override val id = "mozzarellaCheese"
    override fun toUi(): UiText {
        return UiText.StringResourceText(R.string.mozzarella_cheese)
    }
}

object basil : Ingredient{
    override val id: String
        get() = "basil"
    override fun toUi(): UiText {
        return UiText.StringResourceText(R.string.basil)
    }
}
object oliveOil : Ingredient{
    override val id: String
        get() = "oliveOil"
    override fun toUi(): UiText {
        return UiText.StringResourceText(R.string.olive_oil)
    }
}
