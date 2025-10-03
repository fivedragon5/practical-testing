package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTypeTest {

    @Test
    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    void containsStockType() {
        // given
        ProductType givenType = ProductType.HANDMADE;

        // when
        boolean result = ProductType.containsStockType(givenType);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    void containsStockType2() {
        // given
        ProductType givenType = ProductType.BAKERY;

        // when
        boolean result = ProductType.containsStockType(givenType);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 확인한다.")
    @CsvSource({"HANDMADE,false", "BAKERY,true", "BOTTLE,true"})
    @ParameterizedTest
    void containsStockType4(ProductType givenType, boolean expected) {
        // when
        boolean result = ProductType.containsStockType(givenType);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> providedProductTypesForCheckingStockType() {
        return Stream.of(
                Arguments.of(ProductType.HANDMADE, false),
                Arguments.of(ProductType.BAKERY, true),
                Arguments.of(ProductType.BOTTLE, true)
        );
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 확인한다.")
    @MethodSource("providedProductTypesForCheckingStockType")
    @ParameterizedTest
    void containsStockType5(ProductType givenType, boolean expected) {
        // when
        boolean result = ProductType.containsStockType(givenType);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
