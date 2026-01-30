package nl.codingwithlinda.ladypizza.core.presentation.prices

import nl.codingwithlinda.ladypizza.core.domain.model.prices.DollarProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.EuroProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import java.util.Locale

fun ProductPricing.symbol() = when(this){
    is EuroProductPricing -> "€"
    is DollarProductPricing -> "$"
}

fun ProductPricing.formatLocale(
    locale: Locale = Locale.getDefault(),
    price: Double) : String{

    return String.format(locale, symbol() + "%.2f", price)


}