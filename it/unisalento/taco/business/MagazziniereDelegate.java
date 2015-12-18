package it.unisalento.taco.business;

import it.unisalento.taco.dao.MagazzinoDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.unisalento.taco.model.Magazzino;
import it.unisalento.taco.model.Prodotto;
import java.util.LinkedHashSet;
import java.util.Set;

public class MagazziniereDelegate {
	
    private static MagazziniereDelegate instance;
    public static MagazziniereDelegate getInstance(){
            if(instance == null)
                    instance = new MagazziniereDelegate();
            return instance;
    }
    private MagazziniereDelegate(){};

    public void rifornisciProdotto(Magazzino magazzino, Prodotto prodotto, int quantita){
            magazzino.addProdotto(prodotto, quantita);
    };

    public void spedisciProdotto(Magazzino magazzino, Prodotto prodotto, int quantita){
            magazzino.removeProdotto(prodotto, quantita);
    };

    
    public Map<Prodotto,Integer> chiediInventario(Magazzino magazzino){
            return magazzino.getInventario();
    }

    //Chiede prodotto al magazzino
    public int chiediProdotto(Magazzino magazzino, Prodotto prodotto, int quantita){
            return magazzino.cercaProdotto(prodotto, quantita);
    }
        
	
}
