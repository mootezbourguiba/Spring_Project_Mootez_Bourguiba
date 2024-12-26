package tn.esprit.tp_foyer.service;

import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.entity.TypeChambre;

import java.util.List;

public interface IChambreService {
    List<Chambre> retrieveAllChambres();
    Chambre retrieveChambre(Long id);
    Chambre addChambre(Chambre c);
    Chambre updateChambre(Chambre c);
    List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC);
}