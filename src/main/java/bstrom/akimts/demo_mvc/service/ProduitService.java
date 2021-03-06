package bstrom.akimts.demo_mvc.service;

import bstrom.akimts.demo_mvc.models.Produit;
import bstrom.akimts.demo_mvc.models.ProduitForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProduitService {

    private final List<Produit> produits;
    private int nextId = 5;

    public ProduitService() {

        produits = new ArrayList<>();
        produits.add(
                Produit.builder()
                        .id(1)
                        .marque("marque 1")
                        .modele("1p1")
                        .prix(10)
                        .enVente(true)
                        .build()
        );
        produits.add(
                Produit.builder()
                        .id(2)
                        .marque("marque 1")
                        .modele("1p2")
                        .prix(12)
                        .enVente(true)
                        .build()
        );
        produits.add(
                Produit.builder()
                        .id(3)
                        .marque("marque 2")
                        .modele("2p1")
                        .prix(5)
                        .enVente(false)
                        .build()
        );
        produits.add(
                Produit.builder()
                        .id(4)
                        .marque("marque 2")
                        .modele("2p2")
                        .prix(50)
                        .enVente(true)
                        .build()
        );

    }

    public Produit getOne(int id){
        return produits.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(); // NoSuchElementException
    }

    public List<Produit> getAll(){
        return new ArrayList<>(produits);
    }

    public List<Produit> getAllByBrand(String marque){
        return produits.stream()
                .filter((e) -> e.getMarque().equals(marque))
                .toList();
    }

    public Produit insert(ProduitForm form){
        Produit produit = Produit.builder()
                .id(nextId++)
                .marque(form.getMarque())
                .modele(form.getModele())
                .prix(form.getPrix())
                .enVente(true)
                .build();

        produits.add(produit);
        return produit;
    }


}
