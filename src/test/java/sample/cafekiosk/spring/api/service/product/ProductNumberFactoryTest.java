package sample.cafekiosk.spring.api.service.product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sample.cafekiosk.spring.IntegrationTestSupport;
import sample.cafekiosk.spring.domain.product.ProductRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProductNumberFactoryTest extends IntegrationTestSupport {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductNumberFactory productNumberFactory;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }
    
    @Test
    @DisplayName("상품이 하나도 없을 경우, 001을 반환한다.")
    void createNextProductNumber() {
        // when
        String nextProductNumber = productNumberFactory.createNextProductNumber();
        
        // then
        assertThat(nextProductNumber).isEqualTo("001");
    }

    @Test
    @DisplayName("상품이 존재할 경우, 가장 최근 상품의 상품번호에서 1 증가한 값을 반환한다.")
    void createNextProductNumber2() {
        // given
        productRepository.save(sample.cafekiosk.spring.domain.product.Product.builder()
                .productNumber("001")
                .type(sample.cafekiosk.spring.domain.product.ProductType.HANDMADE)
                .sellingStatus(sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING)
                .name("아메리카노")
                .price(4000)
                .build());

        // when
        String nextProductNumber = productNumberFactory.createNextProductNumber();

        // then
        assertThat(nextProductNumber).isEqualTo("002");
    }
}