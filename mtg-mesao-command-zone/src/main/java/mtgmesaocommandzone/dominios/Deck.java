package mtgmesaocommandzone.dominios;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ManyToAny;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //id do Deck
    private Integer idDeck;

    //nome do comandante, carta principal do deck;
    @Size(max = 12)
    private String nomeCommandante;

    //Tribo Commandante - cor
    private String triboCommandante;

    //Custo de Mana do Comandante
    private Integer cmcCommander;

    private Double precoTotalDeck;

    @OneToMany(mappedBy = "fkDeck", orphanRemoval = true)
    private List<Carta> cartas;

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

    public String getTriboCommandante() {
        return triboCommandante;
    }

    public void setTriboCommandante(String triboCommandante) {
        this.triboCommandante = triboCommandante;
    }

    public Integer getCmcCommander() {
        return cmcCommander;
    }

    public void setCmcCommander(Integer cmcCommander) {
        this.cmcCommander = cmcCommander;
    }

    public Double getPrecoTotalDeck() {
        return precoTotalDeck;
    }

    public void setPrecoTotalDeck(Double precoTotalDeck) {
        this.precoTotalDeck = precoTotalDeck;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }
}