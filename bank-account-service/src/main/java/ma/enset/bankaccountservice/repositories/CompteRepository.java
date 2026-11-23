package ma.enset.bankaccountservice.repositories;

import ma.enset.bankaccountservice.entities.Compte;
import ma.enset.bankaccountservice.enums.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, String> {
    List<Compte> findByType(TypeCompte type);
}