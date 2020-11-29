package mtgmesaocommandzone.dominios;
import mtgmesaocommandzone.dominios.Deck;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mtgmesaocommandzone.dominios.enums.raridadeCarta;
import mtgmesaocommandzone.dominios.enums.tipoCarta;

import javax.persistence.*;

@Entity
public class Carta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //idCarta - Unica(Individual)
    private Integer idCarta;

    private String nomeCarta;

    private String corTribo;

//    private tipoCarta tipo;
//
//    private raridadeCarta raridade;

    private String habilidade;

    private Double precoCarta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "fk_deck")
    private Deck fkDeck;

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

    public Double getPrecoCarta() {
        return precoCarta;
    }

    public void setPrecoCarta(Double precoCarta) {
        this.precoCarta = precoCarta;
    }

    public Deck getFkDeck() {
        return fkDeck;
    }

    public void setFkDeck(Deck fkDeck) {
        this.fkDeck = fkDeck;
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

    @Override
    public String toString() {
        return "Carta: " +
                "\nNome Carta:'" + nomeCarta + '\'' +
                "\nCor Tribo; '" + corTribo + '\'' +
                "\nHabilidade: " + habilidade + '\'' +
                "\nPreço Carta: " + precoCarta + "\n";
    }
}