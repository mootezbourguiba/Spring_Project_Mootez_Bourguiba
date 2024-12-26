package tn.esprit.tp_foyer.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tp_foyer.entity.Bloc;
import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.entity.TypeChambre;
import tn.esprit.tp_foyer.repository.BlocRepository;
import tn.esprit.tp_foyer.repository.ChambreRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreService{

    private ChambreRepository chambreRepository;

    private BlocRepository blocRepository;

    @Override
    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre retrieveChambre(Long id) {
        return chambreRepository.findById(id).isPresent() ? chambreRepository.findById(id).get() : null;
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre updateChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    //Keyword
    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        return chambreRepository.findByBlocIdBlocAndTypeC(idBloc, typeC);
    }

    //JPQL
    //@Override
    //public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
    //    return chambreRepository.getChambresParBlocEtType(idBloc,typeC);
    //}
    @Scheduled (cron = "0 * * * * *")//Execution toutes les 2 minutes
    public void pourcentageChambreParTypeChambre() {
        //Récupérer la liste de toutes les chambres (à adapter à votre repository)
        List<Chambre> chambres = chambreRepository.findAll();

        //Nombre Total de chambres
        int totalChambres = chambres.size();
        log.info("Nombre total des chambres: " + totalChambres);

        if (totalChambres > 0) {
            //Initialisation d'une map pour compter les chambres par type
            Map<String, Integer> countByType = new HashMap<>();
            // Parcourir la liste des chambres pour compter les types
            for (Chambre chambre : chambres) {

            }
        }
        for (Map.Entry<String, Integer> entry : countByType.entrySet()) {
            String type = entry.getKey();
            int count = entry.getValue();
            double pourcentage = (count * 100.0) / totalChambres;
            log.info("Le pourcentage des chambres pour le type " + type + " est égal à ")
        }
    }else {
        log.info("Aucune chambre disponible pour le calcul des pourcentages.");
        }
    }

}