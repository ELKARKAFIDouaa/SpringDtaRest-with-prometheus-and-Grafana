package org.ensias.springdatarest;

import static org.assertj.core.api.Assertions.assertThat;

import org.ensias.springdatarest.modele.Voiture;
import org.ensias.springdatarest.modele.Proprietaire;
import org.ensias.springdatarest.repository.VoitureRepo;
import org.ensias.springdatarest.repository.ProprietaireRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class VoitureRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VoitureRepo voitureRepo;

    @Autowired
    private ProprietaireRepo proprietaireRepo;

    @Test
    public void ajouterVoiture() {
        Proprietaire proprietaire = new Proprietaire("Amin", "Hassan");
        entityManager.persist(proprietaire); // on enregistre d'abord le propriétaire

        Voiture voiture = new Voiture("MiolaCar", "Uber", "Blanche", "M-2020", 2021, 180000, proprietaire);
        entityManager.persistAndFlush(voiture); // ensuite la voiture

        assertThat(voiture.getId()).isNotNull();
    }

    @Test
    public void supprimerVoiture() {
        Proprietaire proprietaire2 = new Proprietaire("Sara", "Youssef");
        Proprietaire proprietaire3 = new Proprietaire("Amin", "Hassan");
        entityManager.persist(proprietaire2);
        entityManager.persist(proprietaire3);

        Voiture v1 = new Voiture("MiolaCar", "Uber", "Blanche", "M-2020", 2021, 180000, proprietaire3);
        Voiture v2 = new Voiture("MiniCooper", "Uber", "Rouge", "C-2020", 2021, 180000, proprietaire2);
        entityManager.persist(v1);
        entityManager.persistAndFlush(v2);

        voitureRepo.deleteAll();
        assertThat(voitureRepo.findAll()).isEmpty();
    }
}
