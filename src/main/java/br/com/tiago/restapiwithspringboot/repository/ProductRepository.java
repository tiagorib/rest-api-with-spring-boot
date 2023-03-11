package br.com.tiago.restapiwithspringboot.repository;

import br.com.tiago.restapiwithspringboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}