package org.ensias.springdatarest.controller;

import org.ensias.springdatarest.modele.Proprietaire;
import org.ensias.springdatarest.service.ProprietaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proprietaires")
public class ProprietaireController {

    @Autowired
    private ProprietaireService proprietaireService;

    // GET /proprietaires
    @GetMapping
    public List<Proprietaire> getAllProprietaires() {
        return proprietaireService.getAllProprietaires();
    }

    // GET /proprietaires/{id}
    @GetMapping("/{id}")
    public Proprietaire getProprietaireById(@PathVariable Long id) {
        return proprietaireService.getProprietaireById(id)
                .orElseThrow(() -> new RuntimeException("Propriétaire non trouvé avec l'id " + id));
    }

    // POST /proprietaires
    @PostMapping
    public Proprietaire createProprietaire(@RequestBody Proprietaire proprietaire) {
        return proprietaireService.saveProprietaire(proprietaire);
    }

    // PUT /proprietaires/{id}
    @PutMapping("/{id}")
    public Proprietaire updateProprietaire(@PathVariable Long id, @RequestBody Proprietaire proprietaire) {
        return proprietaireService.updateProprietaire(id, proprietaire);
    }

    // DELETE /proprietaires/{id}
    @DeleteMapping("/{id}")
    public String deleteProprietaire(@PathVariable Long id) {
        proprietaireService.deleteProprietaire(id);
        return "Propriétaire supprimé avec succès !";
    }
}
