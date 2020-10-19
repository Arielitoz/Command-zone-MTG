package mtgmesaocommandzone.repository;

import mtgmesaocommandzone.dominios.Carta;
import mtgmesaocommandzone.visoes.CartaBudget;
import mtgmesaocommandzone.visoes.DeckBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartaRepository extends JpaRepository<Carta, Integer> {

    @Query("select new mtgmesaocommandzone.visoes.CartaBudget(cb.idCarta, cb.nomeCarta, cb.corTribo, cb.habilidade, cb.precoCarta)from Carta cb where cb.precoCarta <= 20.00")
    List<CartaBudget> findAllBaratos();

}
