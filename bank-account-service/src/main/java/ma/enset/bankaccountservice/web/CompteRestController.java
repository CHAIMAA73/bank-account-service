package ma.enset.bankaccountservice.web;

import ma.enset.bankaccountservice.dto.CompteRequestDTO;
import ma.enset.bankaccountservice.dto.CompteResponseDTO;
import ma.enset.bankaccountservice.service.CompteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompteRestController {

    private final CompteService compteService;

    public CompteRestController(CompteService compteService) {
        this.compteService = compteService;
    }

    // Lister tous les comptes
    @GetMapping("/comptes")
    public List<CompteResponseDTO> comptes() {
        return compteService.getComptes();
    }

    // Consulter un compte par son id
    @GetMapping("/comptes/{id}")
    public CompteResponseDTO compte(@PathVariable String id) {
        return compteService.getCompte(id);
    }

    // Ajouter un compte
    @PostMapping("/comptes")
    public CompteResponseDTO save(@RequestBody CompteRequestDTO compteRequestDTO) {
        return compteService.addCompte(compteRequestDTO);
    }

    // Modifier un compte
    @PutMapping("/comptes/{id}")
    public CompteResponseDTO update(@PathVariable String id, @RequestBody CompteRequestDTO compteRequestDTO) {
        return compteService.updateCompte(id, compteRequestDTO);
    }

    // Supprimer un compte
    @DeleteMapping("/comptes/{id}")
    public void delete(@PathVariable String id) {
        compteService.deleteCompte(id);
    }
}