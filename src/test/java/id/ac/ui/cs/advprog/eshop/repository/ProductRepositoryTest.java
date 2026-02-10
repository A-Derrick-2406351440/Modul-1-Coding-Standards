package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp(){
    }

    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(),savedProduct.getProductId());
        assertEquals(product.getProductName(),savedProduct.getProductName());
        assertEquals(product.getProductQuantity(),savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de45-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(),savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(),savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCreateAndEdit(){
        Product beforeProduct = new Product();

        beforeProduct.setProductName("Nama Lama");
        beforeProduct.setProductQuantity(100);
        productRepository.create(beforeProduct);

        Product productUpdate = new Product();
        productUpdate.setProductName("Nama Baru");
        productUpdate.setProductQuantity(50);
        productUpdate.setProductId(beforeProduct.getProductId());
        productRepository.update(productUpdate);

        assertEquals(beforeProduct.getProductName(),"Nama Baru");
        assertEquals(beforeProduct.getProductQuantity(),50);

    }

    @Test
    void testCreateAndDelete(){
        Product productDeleted = new Product();
        productDeleted.setProductName("Mau Delete");
        productDeleted.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productDeleted.setProductQuantity(100);

        productRepository.create(productDeleted);

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCreateAndEditNotFound(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productRepository.create(product);

        Product product2 = new Product();
        assertFalse(productRepository.update(product2));
    }
    @Test
    void testCreateAndDeleteNotFound(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productRepository.create(product);

        assertFalse(productRepository.delete("a0f9de45-90b1-437d-a0bf-d0821dde9096"));
    }

    @Test
    void testCreateEditDelete(){
        Product beforeProduct  = new Product();
        beforeProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        beforeProduct.setProductName("Barang Pertama");
        beforeProduct.setProductQuantity(100);
        productRepository.create(beforeProduct);

        Product productUpdate = new Product();
        productUpdate.setProductName("Barang Pertama diedit");
        productUpdate.setProductQuantity(50);
        productUpdate.setProductId(beforeProduct.getProductId());
        productRepository.update(productUpdate);

        assertEquals(beforeProduct.getProductName(),"Barang Pertama diedit");
        assertEquals(beforeProduct.getProductQuantity(),50);

        assertTrue(productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6"));

        Iterator<Product> productIterator = productRepository.findAll();

        assertFalse(productIterator.hasNext());

    }
    @Test
    void testUpdateNullProduct() {
        assertFalse(productRepository.update(null));
    }
    @Test
    void testDeleteEmptyProductId() {
        assertFalse(productRepository.delete(""));
    }

    @Test
    void testDeleteBlankProductId() {
        assertFalse(productRepository.delete("   "));
    }
}