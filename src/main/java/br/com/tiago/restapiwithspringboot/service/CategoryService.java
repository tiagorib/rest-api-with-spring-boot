package br.com.tiago.restapiwithspringboot.service;

import br.com.tiago.restapiwithspringboot.entity.Category;
import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.entity.Product;
import br.com.tiago.restapiwithspringboot.repository.CategoryRepository;
import br.com.tiago.restapiwithspringboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getInfoCategory() {

        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        if (validateCategory(category)) {
            return categoryRepository.saveAndFlush(category);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O nome da categoria é obrigatório!");
        }
    }

    public HashMap<String, Object> deleteCategory(Long categoryId) {
        Optional<Category> category =
                Optional.ofNullable(categoryRepository.findById(categoryId).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Categoria não encontrada!")));

        categoryRepository.delete(category.get());
        HashMap<String, Object> result = new  HashMap<String, Object> ();
        result.put("result", "Categoria: " + category.get().getNameCategory() + " excluída com sucesso!");
        return result;
    }

    public Category findCategoryById(Long idCategory){
        return categoryRepository.findById(idCategory)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoria não encontrada!"));
    }

    public Category updateCategory(Category category) {

        if (category.getIdCategory() == null || category.getIdCategory() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "O ID da categoria é obrigatório na atualização!");
        }

        if (validateCategory(category)) {
            if (findCategoryById(category.getIdCategory()) != null) {
                return categoryRepository.saveAndFlush(category);
            } else {
                return null;
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O nome da categoria é obrigatório!");
        }
    }

    public Boolean validateCategory (Category category) {
        if (category.getNameCategory() != null &&
                category.getDescriptionCategory() != null) {
            return true;
        } else {
            return false;
        }
    }

}
