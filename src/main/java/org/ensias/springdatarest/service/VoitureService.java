package org.ensias.springdatarest.service;

import org.ensias.springdatarest.modele.Voiture;
import org.ensias.springdatarest.repository.VoitureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoitureService {

    @Autowired
    private VoitureRepo voitureRepo;

    // Récupérer toutes les voitures
    public List<Voiture> getAllVoitures() {
        return (List<Voiture>) voitureRepo.findAll();
    }

    // Récupérer une voiture par id
    public Optional<Voiture> getVoitureById(Long id) {
        return voitureRepo.findById(id);
    }

    // Ajouter une nouvelle voiture
    public Voiture saveVoiture(Voiture voiture) {
        return voitureRepo.save(voiture);
    }

    // Mettre à jour une voiture
    public Voiture updateVoiture(Long id, Voiture voitureDetails) {
        return voitureRepo.findById(id).map(voiture -> {
            voiture.setMarque(voitureDetails.getMarque());
            voiture.setModele(voitureDetails.getModele());
            voiture.setCouleur(voitureDetails.getCouleur());
            voiture.setAnnee(voitureDetails.getAnnee());
            return voitureRepo.save(voiture);
        }).orElseThrow(() -> new RuntimeException("Voiture non trouvée avec l'id " + id));
    }

    // Supprimer une voiture
    public void deleteVoiture(Long id) {
        voitureRepo.deleteById(id);
    }
}
