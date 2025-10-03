package sample.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sample.cafekiosk.spring.IntegrationTestSupport;
import sample.cafekiosk.spring.domain.orderproduct.OrderProductRepository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

class OrderRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Test
    @DisplayName("주문 상태와 기간을 입력하면 해당하는 주문들을 조회한다.")
    void findOrdersBy() {
        // given
        Product product1 = createProduct("001", ProductSellingStatus.SELLING, ProductType.HANDMADE, "아메리카노", 4000);
        Product product2 = createProduct("002", ProductSellingStatus.HOLD, ProductType.HANDMADE, "카페라떼", 4500);
        Product product3 = createProduct("003", ProductSellingStatus.STOP_SELLING, ProductType.HANDMADE, "팥빙수", 7000);
        productRepository.saveAll(List.of(product1, product2, product3));

        Order order1 = Order.create(List.of(product1, product2), LocalDateTime.of(2025, 9, 1, 1, 0));
        Order order2 = Order.create(List.of(product1, product2), LocalDateTime.of(2025, 9, 30, 23, 0));
        orderRepository.saveAll(List.of(order1, order2));

        LocalDateTime startDateTime = LocalDateTime.of(2025, 9, 1, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2025, 9, 30, 0, 0);

        // when
        List<Order> orders = orderRepository.findOrdersBy(startDateTime, endDateTime, OrderStatus.COMPLETED);

        // then
    }

    private Product createProduct(String productNumber, ProductSellingStatus sellingStatus, ProductType type, String name, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .sellingStatus(sellingStatus)
                .type(type)
                .name(name)
                .price(price)
                .build();
    }
}