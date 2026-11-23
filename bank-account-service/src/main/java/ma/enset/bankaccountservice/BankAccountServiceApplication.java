package ma.enset.bankaccountservice;

import ma.enset.bankaccountservice.entities.Compte;
import ma.enset.bankaccountservice.enums.TypeCompte;
import ma.enset.bankaccountservice.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CompteRepository compteRepository) {
		return args -> {
			// Insertion de 10 comptes de test
			for (int i = 0; i < 10; i++) {
				Compte compte = Compte.builder()
						.id(UUID.randomUUID().toString())
						.dateCreation(new Date())
						.solde(10000 + Math.random() * 90000)
						.devise("MAD")
						.type(Math.random() > 0.5 ? TypeCompte.COURANT : TypeCompte.EPARGNE)
						.build();
				compteRepository.save(compte);
			}

			// Affichage de tous les comptes
			System.out.println("===== Tous les comptes =====");
			compteRepository.findAll().forEach(c -> {
				System.out.println(c.getId() + " | " + c.getType() + " | " + c.getSolde() + " " + c.getDevise());
			});

			// Test de la méthode dérivée findByType
			System.out.println("===== Comptes EPARGNE =====");
			compteRepository.findByType(TypeCompte.EPARGNE).forEach(c -> {
				System.out.println(c.getId() + " | " + c.getSolde());
			});
		};
	}
}