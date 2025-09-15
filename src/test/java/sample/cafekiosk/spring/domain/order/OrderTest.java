package sample.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    @DisplayName("주문 생성 시 상품 리스트에서 주문의 총 금액을 계산한다.")
    void calculateTotalPrice() {
        // given
        List<Product> product = List.of(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );

        // when
        Order order = Order.create(product, LocalDateTime.now());

        // then
        assertThat(order.getTotalPrice()).isEqualTo(3000);
    }

    @Test
    @DisplayName("주문 생성 시 주문 상태는 INIT 상태이다.")
    void init() {
        // given
        List<Product> product = List.of(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );

        // when
        Order order = Order.create(product, LocalDateTime.now());

        // then
        assertThat(order.getOrderStatus()).isEqualByComparingTo(OrderStatus.INIT);
    }

    @Test
    @DisplayName("주문 생성 시 주문 등록 시간을 기록한다.")
    void registeredAt() {
        // given
        LocalDateTime now = LocalDateTime.now();
        List<Product> product = List.of(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );

        // when
        Order order = Order.create(product, now);

        // then
        assertThat(order.getRegisteredAt()).isEqualTo(now);
    }

    private Product createProduct(String productNumber, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(ProductType.HANDMADE)
                .price(price)
                .sellingStatus(ProductSellingStatus.SELLING)
                .name("메뉴 이름")
                .build();
    }
}