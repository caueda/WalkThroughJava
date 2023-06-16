package com.example.walkthroughjava.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/produto")
public class ProdutoPageController {

    @Value("${microAppProdutoHost}")
    private String microAppProdutoHost;

    public ProdutoPageController() {
    }

    @GetMapping
    public String home(Model model)  {
        model.addAttribute("styles", microAppProdutoHost + "/styles.css");
        model.addAttribute("polyfills", microAppProdutoHost + "/polyfills.js");
        model.addAttribute("scripts", microAppProdutoHost + "/scripts.js");
        model.addAttribute("runtime", microAppProdutoHost + "/runtime.js");
        model.addAttribute("vendor", microAppProdutoHost + "/vendor.js");
        model.addAttribute("main", microAppProdutoHost + "/main.js");
        return "produto";
    }

    @GetMapping({"cadastrar", "consultar"})
    public String handleMicroAppCadastrarRouting(Model model) {
        return "redirect:/produto";
    }
}
