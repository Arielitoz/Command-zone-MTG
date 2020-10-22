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
        return ResponseEntity.status(200).body(repositoryCarta.findAll());
    }

    @GetMapping("/budget")
    public ResponseEntity getCartasBaratas(){
        return ResponseEntity.status(200).body(repositoryCarta.findAllBaratos());
    }

    @GetMapping("/{idCarta}")
    public ResponseEntity getCartaUnidade(@PathVariable Integer idCarta){
        return ResponseEntity.status(200).body(repositoryCarta.findById(idCarta).get());
    }

    @PostMapping
    public ResponseEntity criarDeck(@RequestBody Carta carta){
        repositoryCarta.save(carta);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{idCarta}")
    public ResponseEntity deletarDeck(@PathVariable Integer idCarta){
        repositoryCarta.delete(repositoryCarta.findById(idCarta).get());
        return ResponseEntity.status(204).build();
    }
}


//  if (estabelecimentos.isEmpty()){
//          return ResponseEntity.status(204).build();
//          }else{
//          return ResponseEntity.status(200).body(estabelecimentos);
//          }
//
// if (repositoryCarta.size() >= idCarta){
//         repositoryCarta.remove(id-1);
//         return ResponseEntity.status(200).build();
//         }else{
//         return ResponseEntity.status(404).build();
//         }