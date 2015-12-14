/*
 * Progetto Taco - Progettazione Software
 * Autori: Giulio Albanese, Tommaso Paladini
 * Professore: Luca Mainetti
 */

package it.unisalento.taco.dao;

import it.unisalento.taco.dbconnections.DBConnection;
import it.unisalento.taco.model.Carrello;
import it.unisalento.taco.model.Categoria;
import it.unisalento.taco.model.Dipendente;
import it.unisalento.taco.model.Prodotto;
import it.unisalento.taco.model.Produttore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class CarrelloDAO {
    
    private static CarrelloDAO instance;
    
    public static CarrelloDAO getInstance() {
        if(instance == null)
            instance = new CarrelloDAO();
        return instance;
    }
    
    private CarrelloDAO(){}

    //RESTITUISCE SOLO LA LISTA DEI PRODOTTI NEL CARRELLO DEL DIPENDENTE
    public Map<Prodotto,Integer> getListaProdotti(Dipendente dipendente){
        
        String uberQuery = "SELECT produttori.nome,prodotti.*,carrelli.quantita FROM prodotti,carrelli,produttori,dipendenti WHERE carrelli.id_prodotto = prodotti.id AND dipendenti.id_utente = " + dipendente.getID() + " AND produttori.id_prodotto = prodotti.id";
        ArrayList<String[]> result = DBConnection.getInstance().queryDB(uberQuery);
        Iterator<String[]> i = result.iterator();
        Map<Prodotto,Integer> listaProdotti = new LinkedHashMap<>();
        
        while(i.hasNext()) {
            String[] riga = i.next();
            Prodotto prodotto = new Prodotto.Builder(Integer.parseInt(riga[1]), riga[2], Double.parseDouble(riga[5]), Produttore.parseProduttore(riga[0])).categoria(Categoria.parseCategoria(riga[3])).descrizione(riga[4]).build();
            int quantita = Integer.parseInt(riga[6]);
            if(quantita == 0)
                continue;
            listaProdotti.put(prodotto,quantita);
        }
        
        return listaProdotti;
    }
    
    //RESTITUISCE IL CARRELLO
    public Carrello getCarrello(Dipendente dipendente) {
        Carrello carrello = new Carrello(getListaProdotti(dipendente));
        return carrello;
    }
    
    public void updateCarrello(Carrello carrello) {
        //LEGGERE REQUEST
    }
    
}
