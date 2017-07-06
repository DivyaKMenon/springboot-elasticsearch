package com.tr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tr.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	Page<Product> findByName(String name, Pageable pageable);

	List<Product> findByVendorId(Integer vendorId);

	Product findById(Integer id);

	@Override
	List<Product> findAll();

	@Query("select p from Product p where Name Like :data")
	List<Product> findByNameLike(@Param("data") String data);
}