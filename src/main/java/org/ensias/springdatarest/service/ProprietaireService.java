package org.ensias.springdatarest.service;

import org.ensias.springdatarest.modele.Proprietaire;
import org.ensias.springdatarest.repository.ProprietaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProprietaireService {

    @Autowired
    private ProprietaireRepo proprietaireRepo;

    // Récupérer tous les propriétaires
    public List<Proprietaire> getAllProprietaires() {
        return (List<Proprietaire>) proprietaireRepo.findAll();
    }

    // Récupérer un propriétaire par id
    public Optional<Proprietaire> getProprietaireById(Long id) {
        return proprietaireRepo.findById(id);
    }

    // Ajouter un nouveau propriétaire
    public Proprietaire saveProprietaire(Proprietaire proprietaire) {
        return proprietaireRepo.save(proprietaire);
    }

    // Mettre à jour un propriétaire
    public Proprietaire updateProprietaire(Long id, Proprietaire proprietaireDetails) {
        return proprietaireRepo.findById(id).map(proprietaire -> {
            proprietaire.setNom(proprietaireDetails.getNom());
            proprietaire.setPrenom(proprietaireDetails.getPrenom());
            return proprietaireRepo.save(proprietaire);
        }).orElseThrow(() -> new RuntimeException("Propriétaire non trouvé avec l'id " + id));
    }

    // Supprimer un propriétaire
    public void deleteProprietaire(Long id) {
        proprietaireRepo.deleteById(id);
    }
}
