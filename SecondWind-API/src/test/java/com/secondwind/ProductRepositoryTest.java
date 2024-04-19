package com.secondwind;

import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.secondwind.entity.Product;
import com.secondwind.entity.ProductStatus;
import com.secondwind.entity.User;
import com.secondwind.repository.ProductRepository;
import com.secondwind.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(OrderAnnotation.class)
class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
	public void setUp() {
		User user = new User();
		user.setFirstName("Juan");
		user.setLastName("García");
		user.setDni("12345678E");
		user.setEmail("juanga@gmail.com");
		user.setPassword("secretpassword123");
		user = userRepository.save(user);
		Product product = new Product();
		product.setTitle("Producto de pruebas");
		product.setDescription("Descripción para el test");
		product.setPrice((float)2500.00);
		product.setStatus(ProductStatus.ONSALE);
		product.setSeller(user);
		product = productRepository.save(product);
	}
	
	@Test
	@Order(1)
	void testFindById() {
		Optional<Product> optProd = productRepository.findById((long)1);
		assertTrue(optProd.isPresent());
	}
	
	@Test
	@Order(2)
	void testFindAll() {
		List<Product> products = productRepository.findAll();
		assertFalse(products.isEmpty());
	}
	
	@Test
	@Order(3)
	void testDeleteById() {
		Optional<Product> optProd = productRepository.findById((long)3);
		assertTrue(optProd.isPresent());
		productRepository.deleteById((long)3);
		optProd = productRepository.findById((long)3);
		assertFalse(optProd.isPresent());
	}
	
	@Test
	@Order(4)
	void testUpdateById() {
		Optional<Product> optProd = productRepository.findById((long)4);
		assertTrue(optProd.isPresent());
		Product prod = optProd.get();
		var oldTitle = prod.getTitle();
		var newTitle = oldTitle + "difference";
		prod.setTitle(newTitle);
		Product newProd = productRepository.save(prod);
		assertEquals(newProd.getTitle(), newTitle);
	}

	@Test
	@Order(5)
	void testFindByStatus() {
		List<Product> products = productRepository.findByStatus(ProductStatus.ONSALE);
		assertFalse(products.isEmpty());
		products = productRepository.findByStatus(ProductStatus.REFUNDED);
		assertTrue(products.isEmpty());
	}
}
