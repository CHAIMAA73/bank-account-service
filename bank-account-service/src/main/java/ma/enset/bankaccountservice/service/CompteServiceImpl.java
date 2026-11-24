package ma.enset.bankaccountservice.service;

import jakarta.transaction.Transactional;
import ma.enset.bankaccountservice.dto.CompteRequestDTO;
import ma.enset.bankaccountservice.dto.CompteResponseDTO;
import ma.enset.bankaccountservice.entities.Compte;
import ma.enset.bankaccountservice.mappers.CompteMapper;
import ma.enset.bankaccountservice.repositories.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;

    public CompteServiceImpl(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    @Override
    public List<CompteResponseDTO> getComptes() {
        return compteRepository.findAll()
                .stream()
                .map(compteMapper::fromCompte)
                .toList();
    }

    @Override
    public CompteResponseDTO getCompte(String id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Compte %s introuvable", id)));
        return compteMapper.fromCompte(compte);
    }

    @Override
    public CompteResponseDTO addCompte(CompteRequestDTO compteRequestDTO) {
        Compte compte = compteMapper.fromCompteRequestDTO(compteRequestDTO);
        compte.setId(UUID.randomUUID().toString());
        compte.setDateCreation(new Date());
        Compte savedCompte = compteRepository.save(compte);
        return compteMapper.fromCompte(savedCompte);
    }

    @Override
    public CompteResponseDTO updateCompte(String id, CompteRequestDTO compteRequestDTO) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Compte %s introuvable", id)));
        if (compteRequestDTO.getSolde() != null) compte.setSolde(compteRequestDTO.getSolde());
        if (compteRequestDTO.getDevise() != null) compte.setDevise(compteRequestDTO.getDevise());
        if (compteRequestDTO.getType() != null) compte.setType(compteRequestDTO.getType());
        Compte updatedCompte = compteRepository.save(compte);
        return compteMapper.fromCompte(updatedCompte);
    }

    @Override
    public void deleteCompte(String id) {
        compteRepository.deleteById(id);
    }
}