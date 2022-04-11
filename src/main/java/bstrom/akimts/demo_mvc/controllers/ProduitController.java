package bstrom.akimts.demo_mvc.controllers;

import bstrom.akimts.demo_mvc.models.Produit;
import bstrom.akimts.demo_mvc.models.ProduitForm;
import bstrom.akimts.demo_mvc.service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/produit")
public class ProduitController {

    private final ProduitService service;

    public ProduitController(ProduitService service) {
        this.service = service;
    }

    @ModelAttribute("get_all")
    public List<Produit> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("permitAll()")
    public String displayOne(@PathVariable int id, Model model){
        Produit p = service.getOne(id);
        model.addAttribute("produit", p);
        return "pages/displayOne";
    }

    @GetMapping
//    @PreAuthorize("permitAll()")
    public String displayAll(){
        return "pages/displayAll";
    }

    @GetMapping("/by_brand")
//    @PreAuthorize("permitAll()")
    public String displayByBrand( @RequestParam String marque, Model model ){
        model.addAttribute("list", service.getAllByBrand(marque) );
        model.addAttribute("brand", marque);
        return "pages/displayByBrand";
    }

    @GetMapping("/add")
//    @PreAuthorize("isAuthenticated()")
    public String displayInsertForm(@ModelAttribute("produit") ProduitForm form){
        return "forms/produitForm";
    }

    @PostMapping("/add")
//    @PreAuthorize("isAuthenticated()")
    public String processInsert(@Valid @ModelAttribute("produit") ProduitForm form, BindingResult binding){
        if( binding.hasErrors() )
            return "forms/produitForm";

        Produit rslt = service.insert(form);
        return "redirect:/produit/" + rslt.getId();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handle(NoSuchElementException ex){
        return "pages/404";
    }

}
