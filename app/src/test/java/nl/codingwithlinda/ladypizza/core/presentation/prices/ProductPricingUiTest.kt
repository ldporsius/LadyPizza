package nl.codingwithlinda.ladypizza.core.presentation.prices

import assertk.assertThat
import assertk.assertions.isEqualTo
import nl.codingwithlinda.ladypizza.core.domain.model.prices.DollarProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.EuroProductPricing
import org.junit.Assert.*
import org.junit.Test
import java.util.Locale

class ProductPricingUiTest {

    @Test
    fun `test price is formatted in right locale`() {
        val euroPricing = EuroProductPricing()
        val nl = Locale.forLanguageTag("nl")

        val price = euroPricing.formatLocale(
            locale = nl,
            price = 10.0)


        assertThat(price).isEqualTo("€10,00")
    }

    @Test
    fun `test price is formatted in right locale en`() {
        val euroPricing = EuroProductPricing()
        val nl = Locale.forLanguageTag("en")

        val price = euroPricing.formatLocale(
            locale = nl,
            price = 10.0)


        assertThat(price).isEqualTo("€10.00")
    }
    @Test
    fun `test price is formatted in right locale en dollar`() {
        val euroPricing = DollarProductPricing(1.0)
        val nl = Locale.forLanguageTag("en")

        val price = euroPricing.formatLocale(
            locale = nl,
            price = 10.0)


        assertThat(price).isEqualTo("$10.00")
    }
}