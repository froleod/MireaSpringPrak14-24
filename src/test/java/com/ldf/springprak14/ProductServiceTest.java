package com.ldf.springprak14;

import com.ldf.springprak14.Entity.Product;
import com.ldf.springprak14.Repo.MarketRepository;
import com.ldf.springprak14.Repo.ProductRepository;
import com.ldf.springprak14.Service.EmailService;
import com.ldf.springprak14.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private MarketRepository marketRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ProductService productService;

    @Test
    void testCreateProduct() {
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.createProduct(product);

        assertEquals(product, savedProduct);
        verify(emailService, times(1)).sendEmail(anyString(), anyString(), anyString());
    }

}