package br.com.tiago.restapiwithspringboot.service;

import br.com.tiago.restapiwithspringboot.entity.Category;
import br.com.tiago.restapiwithspringboot.exception.ResponseGenericException;
import br.com.tiago.restapiwithspringboot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public HashMap<String, Object> deleteCategory(Long idCategory) {
        Optional<Category> category = categoryRepository.findById(idCategory);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "Categoria excluída com sucesso!");
            return result;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!");
        }
    }

    public Category getCategoryById(Long idCategory) {
        return categoryRepository.findById(idCategory)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!"));
    }

    public Category updateCategory(Category category) {
        if (category.getIdCategory() == null || category.getIdCategory() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O ID da categoria é obrigatório na atualização!");
        }
        if (categoryRepository.existsById(category.getIdCategory())) {
            return categoryRepository.save(category);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!");
        }
    }
}
