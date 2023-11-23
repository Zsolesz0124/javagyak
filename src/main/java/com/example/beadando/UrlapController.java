package com.example.beadando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UrlapController {
    @Autowired
    private uzenetekRepo UzenetekRepo;
    @GetMapping("/urlap1")
    public String urlapForm(Model model) {
        model.addAttribute("uzenetOsztaly", new UzenetOsztaly());
        return "urlap";
    }
    @PostMapping(value = "/feladat2")
    public String urlapSubmit(@Valid @ModelAttribute UzenetOsztaly uzenetOsztaly,RedirectAttributes redirAttr ,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "urlap";
        // Ha nincs ilyen dolgozó, akkor felvisszük az adatbázisba:
        UzenetekRepo.save(uzenetOsztaly);
        redirAttr.addFlashAttribute("uzenet","Új dolgozó hozzá lett adva! ID="+uzenetOsztaly.getId());
        model.addAttribute("attr2", uzenetOsztaly);
        return "eredmeny";
    }
}

