package com.crudapi.CRUDSpring;

import com.crudapi.CRUDSpring.controller.ProductController;
import com.crudapi.CRUDSpring.entity.ProductEntity;
import com.crudapi.CRUDSpring.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProductController.class)
class CrudSpringApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Test
	void testAddProduct() throws Exception {
		ProductEntity product = new ProductEntity();
		product.setId(1);
		product.setName("Product1");
		product.setPrice(100.0);

		when(productService.saveProduct(any(ProductEntity.class))).thenReturn(product);

		mockMvc.perform(post("/api/addProduct")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\": \"Product1\", \"price\": 100.0}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Product1")))
				.andExpect(jsonPath("$.price", is(100.0)))
				.andExpect(jsonPath("$.id", is(1))); // Ensure ID is also checked
	}


	@Test
	void testFindAllProducts() throws Exception {
		ProductEntity product1 = new ProductEntity();
		product1.setId(1);
		product1.setName("Product1");
		product1.setPrice(100.0);

		ProductEntity product2 = new ProductEntity();
		product2.setId(2);
		product2.setName("Product2");
		product2.setPrice(200.0);

		when(productService.getProducts()).thenReturn(Arrays.asList(product1, product2));

		mockMvc.perform(get("/api/products"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is("Product1")))
				.andExpect(jsonPath("$[0].price", is(100.0)))
				.andExpect(jsonPath("$[0].id", is(1))) // Ensure ID is also checked
				.andExpect(jsonPath("$[1].name", is("Product2")))
				.andExpect(jsonPath("$[1].price", is(200.0)))
				.andExpect(jsonPath("$[1].id", is(2))); // Ensure ID is also checked
	}


	@Test
	void testFindProductById() throws Exception {
		ProductEntity product = new ProductEntity();
		product.setId(1);
		product.setName("Product1");
		product.setPrice(100.0);

		when(productService.getProductById(anyInt())).thenReturn(product);

		mockMvc.perform(get("/api/productById/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Product1")))
				.andExpect(jsonPath("$.price", is(100.0)))
				.andExpect(jsonPath("$.id", is(1))); // Ensure ID is also checked
	}


	@Test
	void testUpdateProduct() throws Exception {
		ProductEntity product = new ProductEntity();
		product.setId(1);
		product.setName("UpdatedProduct");
		product.setPrice(150.0);

		when(productService.updateProduct(any(ProductEntity.class))).thenReturn(product);

		mockMvc.perform(put("/api/update")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"id\": 1, \"name\": \"UpdatedProduct\", \"price\": 150.0}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("UpdatedProduct")))
				.andExpect(jsonPath("$.price", is(150.0)))
				.andExpect(jsonPath("$.id", is(1))); // Ensure ID is also checked
	}


	@Test
	void testDeleteProduct() throws Exception {
		when(productService.deleteProduct(anyInt())).thenReturn("Product removed!");

		mockMvc.perform(delete("/api/delete/1"))
				.andExpect(status().isOk())
				.andExpect(content().string("Product removed!"));
	}


}
