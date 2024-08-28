package com.company.app1.ctrl;

import com.company.app1.biz.ProductService;
import com.company.app1.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    private final String UPLOAD_DIR = "uploads/";

    @GetMapping("/list")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/insert")
    public Product createProduct(@RequestParam String cate,
                                 @RequestParam String pname,
                                 @RequestParam String pcontent,
                                 @RequestParam MultipartFile img1,
                                 @RequestParam MultipartFile img2,
                                 @RequestParam MultipartFile img3) throws IOException {
        String img1Path = saveFile(img1);
        String img2Path = saveFile(img2);
        String img3Path = saveFile(img3);

        Product product = new Product();
        product.setCate(cate);
        product.setPname(pname);
        product.setPcontent(pcontent);
        product.setImg1(img1Path);
        product.setImg2(img2Path);
        product.setImg3(img3Path);
        return productService.saveProduct(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id,
                                                 @RequestParam String cate,
                                                 @RequestParam String pname,
                                                 @RequestParam String pcontent,
                                                 @RequestParam MultipartFile img1,
                                                 @RequestParam MultipartFile img2,
                                                 @RequestParam MultipartFile img3) throws IOException {
        deleteFiles(productService.getProductById(id).orElseThrow().getImg1(), productService.getProductById(id).orElseThrow().getImg2(), productService.getProductById(id).orElseThrow().getImg3());

        String img1Path = saveFile(img1);
        String img2Path = saveFile(img2);
        String img3Path = saveFile(img3);

        Product updatedProduct = new Product();
        updatedProduct.setCate(cate);
        updatedProduct.setPname(pname);
        updatedProduct.setPcontent(pcontent);
        updatedProduct.setImg1(img1Path);
        updatedProduct.setImg2(img2Path);
        updatedProduct.setImg3(img3Path);

        return ResponseEntity.ok(productService.updateProduct(id, updatedProduct));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        deleteFiles(productService.getProductById(id).orElseThrow().getImg1(), productService.getProductById(id).orElseThrow().getImg2(), productService.getProductById(id).orElseThrow().getImg3());
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }
        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return filePath;
    }

    private void deleteFiles(String... filePaths) {
        for (String filePath : filePaths) {
            try {
                Files.deleteIfExists(Paths.get(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}