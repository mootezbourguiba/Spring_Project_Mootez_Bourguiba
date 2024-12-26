package tn.esprit.tp_foyer.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tp_foyer.entity.Bloc;
import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.repository.BlocRepository;
import tn.esprit.tp_foyer.repository.ChambreRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class BlocServiceImpl implements IBlocService{

    private static final Logger log = LoggerFactory.getLogger(BlocServiceImpl.class);
    private ChambreRepository chambreRepository;
    private BlocRepository blocRepository;

    @Override
    public List<Bloc> retrieveBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc retrieveBloc(Long id) {
        return blocRepository.findById(id).isPresent() ? blocRepository.findById(id).get() : null;
    }

    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public void removeBloc(Long id) {
        blocRepository.deleteById(id);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambres, long idBloc) {
        Bloc b=blocRepository.findById(idBloc).orElse(null);
        Set<Chambre> chambres=chambreRepository.findAllByNumeroChambre(numChambres);
        if(chambres.size()!=numChambres.size()){
            throw new RuntimeException("Une ou plusieurs chambres sont introuvables");
        }
        for(Chambre c:chambres){
            if(c.getBloc()!=null && c.getBloc().getIdBloc()!=idBloc) {
                throw new RuntimeException("Le chambre "+c.getNumeroChambre()+" est déjà affecté dans un autre bloc");
            }
        }
        for(Chambre c:chambres){
            c.setBloc(b);
        }
        //b.getChambres().addAll(chambres);
        //blocRepository.save(b);
        chambreRepository.saveAll(chambres);
        return b;
    }
    @Scheduled(cron = "0 * * * * * ")
    public void listeChambresParBloc() {
        List<Bloc> blocs = blocRepository.findAll();
        for (Bloc bloc : blocs){
            log.info("************************");
            log.info("Bloc => " + bloc.getNomBloc() + " ayant une capacité " + bloc.getCapaciteBloc());
            if (bloc.getChambres()==null || bloc.getChambres().isEmpty()) {
                log.info("Pas de chambre disponible dans ce bloc");
            } else {
                log.info("La liste des chambres pour ce bloc:");
                List<Chambre> chambresList = bloc.getChambres();
                chambresList.forEach(chambre -> {
                    log.info("NumChambre:"
                    + chambre.getNumeroChambre()
                    + " type: " + chambre.getTypeC());
                });
                log.info("*************************************");



            }


        }
    }
    @Scheduled(cron = "0 */2 * * * *")
    public void pourcentageChambreParTypeChambre(){
        Map<String, Integer>countByTypeC());
        countByTypeC.put(type, countByType.get)

    }
}