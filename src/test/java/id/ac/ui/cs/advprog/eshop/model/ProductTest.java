package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    Product product;
    @BeforeEach
    void setUp(){
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    @Test
    void testGetProductId(){
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6",this.product.getProductId());
    }

    @Test
    void testGetProductName(){
        assertEquals("Sampo Cap Bambang",this.product.getProductName());
    }

    @Test
    void testGetProductQuantity(){
        assertEquals(100,this.product.getProductQuantity());
    }

    @Test
    void testSettersAndGetters() {
        Product newProduct = new Product();
        newProduct.setProductId("new-id");
        newProduct.setProductName("Nama Baru");
        newProduct.setProductQuantity(200);

        assertEquals("new-id", newProduct.getProductId());
        assertEquals("Nama Baru", newProduct.getProductName());
        assertEquals(200, newProduct.getProductQuantity());
    }
}