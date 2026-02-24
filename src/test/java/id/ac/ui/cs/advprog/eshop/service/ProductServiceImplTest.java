package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCreate() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);
        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAll() {
        Product product = new Product();
        Iterator<Product> iterator = Arrays.asList(product).iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> products = productService.findAll();
        assertEquals(1, products.size());
        assertEquals(product, products.get(0));
    }

    @Test
    void testGet() {
        Product product = new Product();
        when(productRepository.get("test-id")).thenReturn(product);

        Product foundProduct = productService.get("test-id");
        assertEquals(product, foundProduct);
    }

    @Test
    void testUpdate() {
        Product product = new Product();
        when(productRepository.update(product)).thenReturn(true);

        boolean isUpdated = productService.update(product);
        assertTrue(isUpdated);
    }

    @Test
    void testDelete() {
        when(productRepository.delete("test-id")).thenReturn(true);

        boolean isDeleted = productService.delete("test-id");
        assertTrue(isDeleted);
    }
}