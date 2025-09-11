package sample.cafekiosk.unit.beverage;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AmericanoTest {

    @Test
    void getName() {
        // given
        Americano americano = new Americano();

        // when
        String name = americano.getName();

        // then
        assertEquals("아메리카노", name); // Junit
        assertThat(name).isEqualTo("아메리카노"); // AssertJ
    }

    @Test
    void getPrice() {
        // given
        Americano americano = new Americano();

        // when
        int price = americano.getPrice();

        // then
        assertEquals(4000, price);
        assertThat(price).isEqualTo(4000);
    }
}