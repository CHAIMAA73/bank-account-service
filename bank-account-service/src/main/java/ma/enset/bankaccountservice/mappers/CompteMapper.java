package ma.enset.bankaccountservice.mappers;

import ma.enset.bankaccountservice.dto.CompteRequestDTO;
import ma.enset.bankaccountservice.dto.CompteResponseDTO;
import ma.enset.bankaccountservice.entities.Compte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {

    // Entité -> DTO de réponse
    public CompteResponseDTO fromCompte(Compte compte) {
        CompteResponseDTO compteResponseDTO = new CompteResponseDTO();
        BeanUtils.copyProperties(compte, compteResponseDTO);
        return compteResponseDTO;
    }

    // DTO de requête -> Entité
    public Compte fromCompteRequestDTO(CompteRequestDTO compteRequestDTO) {
        Compte compte = new Compte();
        BeanUtils.copyProperties(compteRequestDTO, compte);
        return compte;
    }
}