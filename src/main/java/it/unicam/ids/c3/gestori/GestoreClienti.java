package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.gestorispecifici.GestoreMerci;
import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.Negozio;
import it.unicam.ids.c3.persistenza.NegozioRepository;
import it.unicam.ids.c3.personale.Cliente;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class GestoreClienti {

    private Cliente cliente;
    private NegozioRepository negozioRepository;
    private GestoreMerci gestoreMerci;

    public GestoreClienti(NegozioRepository negozioRepository, GestoreMerci gestoreMerci) {
        this.negozioRepository = negozioRepository;
        this.gestoreMerci = gestoreMerci;
    }

    /*****************Ricerca Prodotto******************/

    /**
     * Il metodo restiusce la lista dei negozi che contengono un determinato prodotto
     *
     * @param nomeProdotto nome del prodotto che deve essere ricercato
     * @return list lista dei negozi che lo contengono
     */
    public List<Negozio> ricercaProdotto(String nomeProdotto) {
        List<Negozio> listaNegozi = new ArrayList<>();
        Iterator<Negozio> negozioIterator = negozioRepository.findAll().iterator();
        while (negozioIterator.hasNext()) {
            Negozio negozio = negozioIterator.next();
            Iterator<MerceInventarioNegozio> merceInventarioNegozioIterator = negozio.getMerceInventarioNegozio().iterator();
            while (merceInventarioNegozioIterator.hasNext()) {
                MerceInventarioNegozio min = merceInventarioNegozioIterator.next();
                if (min.getMerceAlPubblico().getMerce().getNome().contains(nomeProdotto)) {
                    listaNegozi.add(negozio);
                }
            }
        }
        return listaNegozi;
    }

    /*****************Consulta Promozioni******************/

    /**
     * Il metodo restituisce la lista delle merci che hanno le promozioni attive
     *
     * @return list lista della promozoioni attive
     */
    public List<MerceInventarioNegozio> getPromozioni() {
        List<MerceInventarioNegozio> min = new ArrayList<>();
        for(Negozio negozio : negozioRepository.findAll()){
            min.addAll(gestoreMerci.getPromozioniAttive(negozio));
        }
        return min;
    }

    /**
     * Il metodo serve per filtrare le promozioni attiva in base alla categoria selezionata
     *
     * @param categoria categoria selezionata che funge da filtro
     * @return list lista delle promozioni filtrate
     */
    public List<MerceInventarioNegozio> filtraPromozioniPerCategoria(Categoria categoria) {
        return gestoreMerci.filtraPromozioniPerCategoria(categoria, getPromozioni());
    }


    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
}
