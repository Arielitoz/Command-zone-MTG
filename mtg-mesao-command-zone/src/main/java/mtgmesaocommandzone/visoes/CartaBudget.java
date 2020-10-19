package mtgmesaocommandzone.visoes;

import mtgmesaocommandzone.dominios.Carta;
import mtgmesaocommandzone.dominios.Deck;

public class CartaBudget {
    private Integer idCarta;
    private String nomeCarta;
    private String corTribo;
    private String habilidade;
    private Double precoCarta;

    public CartaBudget(){}

    public CartaBudget(Integer idCarta, String nomeCarta, String corTribo, String habilidade, Double precoCarta) {
        this.idCarta = idCarta;
        this.nomeCarta = nomeCarta;
        this.corTribo = corTribo;
        this.habilidade = habilidade;
        this.precoCarta = precoCarta;
    }

    public Integer getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(Integer idCarta) {
        this.idCarta = idCarta;
    }

    public String getNomeCarta() {
        return nomeCarta;
    }

    public void setNomeCarta(String nomeCarta) {
        this.nomeCarta = nomeCarta;
    }

    public String getCorTribo() {
        return corTribo;
    }

    public void setCorTribo(String corTribo) {
        this.corTribo = corTribo;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public Double getPrecoCarta() {
        return precoCarta;
    }

    public void setPrecoCarta(Double precoCarta) {
        this.precoCarta = precoCarta;
    }
}
