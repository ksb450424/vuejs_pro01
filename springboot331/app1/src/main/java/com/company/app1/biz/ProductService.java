package com.company.app1.biz;

import com.company.app1.entity.Product;
import com.company.app1.per.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int pno) {
        return productRepository.findById(pno);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(int pno) {
        productRepository.deleteById(pno);
    }

    public Product updateProduct(int pno, Product updatedProduct) {
        return productRepository.findById(pno)
                .map(product -> {
                    product.setCate(updatedProduct.getCate());
                    product.setPname(updatedProduct.getPname());
                    product.setPcontent(updatedProduct.getPcontent());
                    product.setImg1(updatedProduct.getImg1());
                    product.setImg2(updatedProduct.getImg2());
                    product.setImg3(updatedProduct.getImg3());
                    product.setHits(updatedProduct.getHits());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    updatedProduct.setPno(pno);
                    return productRepository.save(updatedProduct);
                });
    }
}