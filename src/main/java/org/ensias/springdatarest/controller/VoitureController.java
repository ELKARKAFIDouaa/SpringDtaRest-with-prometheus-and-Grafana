package org.ensias.springdatarest.controller;

import org.ensias.springdatarest.modele.Voiture;
import org.ensias.springdatarest.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:3000/")
@RequestMapping("/voitures")
public class VoitureController {

    @Autowired
    private VoitureService voitureService;

    // GET /voitures
    @GetMapping
    public List<Voiture> getAllVoitures() {
        return voitureService.getAllVoitures();
    }

    // GET /voitures/{id}
    @GetMapping("/{id}")
    public Voiture getVoitureById(@PathVariable Long id) {
        return voitureService.getVoitureById(id)
                .orElseThrow(() -> new RuntimeException("Voiture non trouvée avec l'id " + id));
    }

    // POST /voitures
    @PostMapping
    public Voiture createVoiture(@RequestBody Voiture voiture) {
        return voitureService.saveVoiture(voiture);
    }

    // PUT /voitures/{id}
    @PutMapping("/{id}")
    public Voiture updateVoiture(@PathVariable Long id, @RequestBody Voiture voiture) {
        return voitureService.updateVoiture(id, voiture);
    }

    // DELETE /voitures/{id}
    @DeleteMapping("/{id}")
    public String deleteVoiture(@PathVariable Long id) {
        voitureService.deleteVoiture(id);
        return "Voiture supprimée avec succès !";
    }
}
