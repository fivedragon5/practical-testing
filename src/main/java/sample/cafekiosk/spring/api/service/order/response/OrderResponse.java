package sample.cafekiosk.spring.api.service.order.response;

import lombok.Builder;
import lombok.Getter;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponse {

    private Long id;
    private OrderStatus orderStatus;
    private int totalPrice;
    private LocalDateTime registeredAt;
    private List<ProductResponse> products;

    @Builder
    public OrderResponse(Long id, OrderStatus orderStatus, int totalPrice, LocalDateTime registeredAt, List<ProductResponse> products) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.registeredAt = registeredAt;
        this.products = products;
    }

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderStatus(order.getOrderStatus())
                .totalPrice(order.getTotalPrice())
                .registeredAt(order.getRegisteredAt())
                .products(order.getOrderProducts().stream()
                        .map(orderProduct -> ProductResponse.of(orderProduct.getProduct()))
                        .toList())
                .build();
    }
}
