package mtgmesaocommandzone.visoes;

import mtgmesaocommandzone.dominios.Carta;

import java.util.List;

public class DeckBudget {

    private Integer idDeck;
    private String nomeCommandante;
    private Integer cmcCommander;

    public  DeckBudget() {}

    public DeckBudget(Integer idDeck, String nomeCommandante, Integer cmcCommander) {
        this.idDeck = idDeck;
        this.nomeCommandante = nomeCommandante;
        this.cmcCommander = cmcCommander;
    }

    public Integer getIdDeck() {
        return idDeck;
    }

    public void setIdDeck(Integer idDeck) {
        this.idDeck = idDeck;
    }

    public String getNomeCommandante() {
        return nomeCommandante;
    }

    public void setNomeCommandante(String nomeCommandante) {
        this.nomeCommandante = nomeCommandante;
    }

    public Integer getCmcCommander() {
        return cmcCommander;
    }

    public void setCmcCommander(Integer cmcCommander) {
        this.cmcCommander = cmcCommander;
    }
}
