package com.michalszekalski.bootcamp_zad21;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private ProductsRepo productsRepo;
    private Category[] catValues = Category.values();


    public MainController(ProductsRepo productsRepo) {
        this.productsRepo = productsRepo;
    }

    @RequestMapping("/")
    public String mainPage (Model model) {
        model.addAttribute("categories", catValues);
        return "index";
    }

    @GetMapping("/lista")
    public String listGet(@RequestParam (name = "kategoria", required = false) String categoryStr, Model model) {

        if (categoryStr == null) {
            model.addAttribute("products", productsRepo.getAll());
            model.addAttribute("pricesSum", productsRepo.pricesSumAllProducts());
        } else {
            Category category = null;
            try {
                category = Category.valueOf(categoryStr);
            } catch (IllegalArgumentException e) {
                return "wrongCategory";
            }
            model.addAttribute("products", productsRepo.findByCategory(category));
            model.addAttribute("pricesSum", productsRepo.pricesSumByCategory(category));
        }
        return "lista";
    }

    @RequestMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("categories", catValues);
        model.addAttribute("product", new Product());
        return "add";
    }

    @PostMapping("/addProduct")
    public String addProduct (Product product) {
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getCategory());
        productsRepo.add(product);
        return "redirect:/lista";
    }

}
