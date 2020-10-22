package mtgmesaocommandzone.controladores;

import mtgmesaocommandzone.ListaObj;
import mtgmesaocommandzone.dominios.Deck;
import mtgmesaocommandzone.repository.DeckRepository;
import mtgmesaocommandzone.visoes.GravarArquivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/decks")
public class DeckController {

    @Autowired
    private DeckRepository repositoryDeck;


    //ARRUMAR MÉTODOS HTTP - Response
    //FAZER DELEÇÃO E LIMPEZA DA LISTA E UM PUT
    @GetMapping
    public ResponseEntity getDecks(){
        return ResponseEntity.ok().body(repositoryDeck.findAll());
    }

    @GetMapping("/ramp")
    public ResponseEntity getCarrosSimples(){
        return ResponseEntity.ok(repositoryDeck.findAllBaixos());
    }

    @GetMapping("/{idDeck}")
    public ResponseEntity getDeckEspecifico(@PathVariable Integer idDeck){
        return ResponseEntity.ok(repositoryDeck.findById(idDeck).get());
    }

    @GetMapping(value = "/download", produces = MediaType.ALL_VALUE)
    public ResponseEntity gravarDeckCSV(@PathParam("ext")String ext){ //Pathparam , ulr /download?ext=
        List<Deck> decks = repositoryDeck.findAll();
        ListaObj<Deck> deck = new ListaObj<Deck>(repositoryDeck.contarReg());
        for (int i = 0; i < decks.size(); i++) {
            deck.adiciona(decks.get(i));
        }

        String dirPath = "src\\main\\resources\\static\\";


            GravarArquivo ga = new GravarArquivo();
            ga.docArquivo("docfinal."+ ext , deck,ext);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment, filename = " +"docfinal."+ ext);
        headers.add("Content-Type", "text/csv");

        return new ResponseEntity(new FileSystemResource(dirPath + "docfinal."+ ext), headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity criarDeck(@RequestBody Deck deck){
        repositoryDeck.save(deck);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{idDeck}")
    public ResponseEntity deletarDeck(@PathVariable Integer idDeck){
        repositoryDeck.delete(repositoryDeck.findById(idDeck).get());
        return ResponseEntity.ok().build();
    }

}

//    @GetMapping(value = "/download/txt", produces = MediaType.ALL_VALUE)
//    public ResponseEntity gravarDeckTXT(){
//        List<Deck> decks = repositoryDeck.findAll();
//        ListaObj<Deck> deck = new ListaObj<Deck>(repositoryDeck.contarReg());
//        for (int i = 0; i < decks.size(); i++) {
//            deck.adiciona(decks.get(i));
//        }
//
//        String dirPath = "src\\main\\resources\\static\\";
//        String arqName = "docFinal.txt";
//
//        GravarArquivo ga = new GravarArquivo();
//        ga.gravarRegistroCSV(arqName , deck);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment, filename = " +arqName );
//        headers.add("Content-Type", "text/csv");
//
//        return new ResponseEntity(new FileSystemResource(dirPath+arqName), headers, HttpStatus.OK);
//    }
