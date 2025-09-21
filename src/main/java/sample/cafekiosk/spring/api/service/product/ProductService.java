package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.api.controller.product.dto.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {
        String latestProductNumber = productRepository.findLatestProductNumber();
        Product savedProduct = request.toEntity(generateProductNumber(latestProductNumber));
        productRepository.save(savedProduct);
        return ProductResponse.of(savedProduct);
    }

    private String generateProductNumber(String latestProductNumber) {
        if (latestProductNumber == null) {
            return "001";
        }

        int latestNumber = Integer.parseInt(latestProductNumber);
        int newProductNumber = latestNumber + 1;
        return String.format("%03d", newProductNumber);
    }
}
