package mtgmesaocommandzone.controladores;

import mtgmesaocommandzone.dominios.Carta;
import mtgmesaocommandzone.dominios.Deck;
import mtgmesaocommandzone.repository.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartas")
public class CartaController {

    @Autowired
    private CartaRepository repositoryCarta;

    @GetMapping
    public ResponseEntity getCartas(){
        return ResponseEntity.ok().body(repositoryCarta.findAll());
    }

    @GetMapping("/budget")
    public ResponseEntity getCartasBaratas(){
        return ResponseEntity.ok(repositoryCarta.findAllBaratos());
    }

    @GetMapping("/{idCarta}")
    public ResponseEntity getCartaUnidade(@PathVariable Integer idCarta){
        return ResponseEntity.ok(repositoryCarta.findById(idCarta).get());
    }

    @PostMapping
    public ResponseEntity criarDeck(@RequestBody Carta carta){
        repositoryCarta.save(carta);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{idCarta}")
    public ResponseEntity deletarDeck(@PathVariable Integer idCarta){
        repositoryCarta.delete(repositoryCarta.findById(idCarta).get());
        return ResponseEntity.ok().build();
    }
}
