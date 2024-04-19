package com.secondwind.dto.converter;

import org.springframework.stereotype.Component;

import com.secondwind.dto.ProductDTO;
import com.secondwind.entity.Product;
import com.secondwind.entity.ProductStatus;

@Component
public class ProductConverter extends BaseConverterImpl<ProductDTO, Product> {
	
	@Override
	public Product dtoToEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setTitle(productDTO.getTitle());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setStatus(ProductStatus.ONSALE);
		return product;	
	}
	
	@Override
	public Product dtoToEntity(ProductDTO productDTO, Product product) {
		product.setTitle(productDTO.getTitle());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		return product;	
	}
	
	@Override
	public ProductDTO entityToDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setTitle(product.getTitle());
		productDTO.setDescription(product.getDescription());
		productDTO.setPrice(product.getPrice());
		productDTO.setCreatedOn(product.getCreatedOn().toString());
		productDTO.setLastUpdatedOn(product.getLastUpdatedOn().toString());
		return productDTO;
	}
}
