package ma.enset.bankaccountservice.service;

import ma.enset.bankaccountservice.dto.CompteRequestDTO;
import ma.enset.bankaccountservice.dto.CompteResponseDTO;

import java.util.List;

public interface CompteService {
    List<CompteResponseDTO> getComptes();
    CompteResponseDTO getCompte(String id);
    CompteResponseDTO addCompte(CompteRequestDTO compteRequestDTO);
    CompteResponseDTO updateCompte(String id, CompteRequestDTO compteRequestDTO);
    void deleteCompte(String id);
}