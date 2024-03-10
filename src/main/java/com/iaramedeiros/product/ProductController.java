package com.iaramedeiros.product;

import com.iaramedeiros.user.UserNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired ProductService service;

    @GetMapping(value="/products")
    public String showProductList(Model model){
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);

        return "products";
    }

    @GetMapping("/product/image/{id}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Integer id) throws ProductNotFoundException {
        Product product = service.get(id);

        byte[] imgBytes = product.getImg();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imgBytes);

    }

    @GetMapping("product/delete/{id}")
    public String deleteProduct(@PathVariable Integer id, RedirectAttributes ra ){
        try {
            service.delete(id);
        }catch (ProductNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/products/filter")
    public ResponseEntity<List<Product>> filterByPriceRange(@RequestParam(name = "minPrice") Double minPrice, @RequestParam(name = "maxPrice") Double maxPrice) {
        List<Product> productList = service.ListByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping("/products/orderBy")
    public ResponseEntity<List<Product>> orderByCriteria(@RequestParam(name = "type") String type) {
        List<Product> productList = service.orderByCriteria(type);
        return ResponseEntity.ok().body(productList);
    }

}
