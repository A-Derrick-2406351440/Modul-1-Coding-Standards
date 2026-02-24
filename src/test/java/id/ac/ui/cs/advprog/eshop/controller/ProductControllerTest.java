package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService service;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController controller;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testHomePage() {
        HomePageController controller = new HomePageController();
        String viewName = controller.HomePage();
        assertEquals("HomePage", viewName);
    }

    @Test
    void testCreateProductPage() {
        String viewName = controller.createProductPage(model);
        assertEquals("CreateProduct", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = controller.createProductPost(product, model);
        assertEquals("redirect:list", viewName);
        verify(service).create(product);
    }

    @Test
    void testProductListPage() {
        List<Product> products = Arrays.asList(new Product());
        when(service.findAll()).thenReturn(products);

        String viewName = controller.productListPage(model);
        assertEquals("ProductList", viewName);
        verify(model).addAttribute("products", products);
    }

    @Test
    void testEditProductPage() {
        Product product = new Product();
        when(service.get("test-id")).thenReturn(product);

        String viewName = controller.editProductPage(model, "test-id");
        assertEquals("EditProduct", viewName);
        verify(model).addAttribute("product", product);
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        String viewName = controller.editProduct(product, "test-id");

        assertEquals("test-id", product.getProductId());
        assertEquals("redirect:/product/list", viewName);
        verify(service).update(product);
    }

    @Test
    void testDeleteProduct() {
        String viewName = controller.deleteProduct("test-id");
        assertEquals("redirect:/product/list", viewName);
        verify(service).delete("test-id");
    }
}