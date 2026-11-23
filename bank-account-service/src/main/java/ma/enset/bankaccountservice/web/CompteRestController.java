package ma.enset.bankaccountservice.web;

import ma.enset.bankaccountservice.entities.Compte;
import ma.enset.bankaccountservice.repositories.CompteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CompteRestController {

    private final CompteRepository compteRepository;

    public CompteRestController(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    // Lister tous les comptes
    @GetMapping("/comptes")
    public List<Compte> comptes() {
        return compteRepository.findAll();
    }

    // Consulter un compte par son id
    @GetMapping("/comptes/{id}")
    public Compte compte(@PathVariable String id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Compte %s introuvable", id)));
    }

    // Ajouter un compte
    @PostMapping("/comptes")
    public Compte save(@RequestBody Compte compte) {
        if (compte.getId() == null) compte.setId(UUID.randomUUID().toString());
        if (compte.getDateCreation() == null) compte.setDateCreation(new Date());
        return compteRepository.save(compte);
    }

    // Modifier un compte
    @PutMapping("/comptes/{id}")
    public Compte update(@PathVariable String id, @RequestBody Compte compte) {
        Compte compteExistant = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Compte %s introuvable", id)));
        if (compte.getSolde() != null) compteExistant.setSolde(compte.getSolde());
        if (compte.getDevise() != null) compteExistant.setDevise(compte.getDevise());
        if (compte.getType() != null) compteExistant.setType(compte.getType());
        if (compte.getDateCreation() != null) compteExistant.setDateCreation(compte.getDateCreation());
        return compteRepository.save(compteExistant);
    }

    // Supprimer un compte
    @DeleteMapping("/comptes/{id}")
    public void delete(@PathVariable String id) {
        compteRepository.deleteById(id);
    }
}