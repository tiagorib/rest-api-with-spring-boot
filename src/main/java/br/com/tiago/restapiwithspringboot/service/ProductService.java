package br.com.tiago.restapiwithspringboot.service;

import br.com.tiago.restapiwithspringboot.entity.Product;
import br.com.tiago.restapiwithspringboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getInfoProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        if (validateProduct(product)) {         // se a validação for verdadeira, cadastre!
            return productRepository.saveAndFlush(product); // saveandflush confirma o que ele fez, é mais rápido que o método só save
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O preço de custo e preço de venda do produto são " +
                            "obrigatórios e devem ser maiores que 0 (zero)!");
        }
    }

    public HashMap<String, Object> deleteProduct(Long productId) {
        Optional<Product> product = // criei uma variavel chamada product. O optional é uma classe do java que procura os objetos
                Optional.ofNullable(productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, //Optional.ofNullable ---> encontre o produto pelo id, se não for nulo ele pega o id, se for nulo, ele da a mensagem
                        "Produto não encontrado!")));                           // o throw é o tratamento de exceções

        productRepository.delete(product.get());
        HashMap<String, Object> result = new  HashMap<String, Object> (); // HashMap --- > monto uma variável da forma que eu quiser
        result.put("result", "Produto: " + product.get().getNameProduct() + " excluído com sucesso!");
        return result;
    }

    public Product findProductById(Long idProduct){
        return productRepository.findById(idProduct)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

    public Product updateProduct(Product product) {

        if (product.getIdProduct() == null || product.getIdProduct() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O ID do produto é obrigatório na atualização!");
        }

        if (validateProduct(product)) {
            if(findProductById(product.getIdProduct()) != null) {
                return productRepository.saveAndFlush(product);
            } else{
                return null;
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O preço de custo e preço de venda do produto são obrigatórios e devem ser maiores que 0 (zero)!");
        }

    }

    public Boolean validateProduct (Product product) {
        if (product.getAmountProduct() != null &&
                product.getAmountProduct().compareTo(BigDecimal.valueOf(0)) == 1 &&
                product.getCostPriceProduct() != null &&
                product.getCostPriceProduct().compareTo(BigDecimal.valueOf(0)) == 1) {
            return true;
        } else {
            return false;
        }
    }

}