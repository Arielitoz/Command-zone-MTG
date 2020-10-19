package mtgmesaocommandzone.repository;

import mtgmesaocommandzone.dominios.Deck;
import mtgmesaocommandzone.visoes.DeckBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeckRepository extends JpaRepository<Deck, Integer> {

    @Query("select new mtgmesaocommandzone.visoes.DeckBudget(db.idDeck, db.nomeCommandante, db.cmcCommander)from Deck db where db.cmcCommander <= 3")
    List<DeckBudget> findAllBaixos();

    @Query("select count (d.idDeck) from Deck d")
    Integer contarReg();
}