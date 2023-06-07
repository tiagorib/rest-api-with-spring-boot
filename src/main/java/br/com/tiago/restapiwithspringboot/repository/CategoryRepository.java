package br.com.tiago.restapiwithspringboot.repository;

import br.com.tiago.restapiwithspringboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
