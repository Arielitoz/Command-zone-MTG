package mtgmesaocommandzone.controladores;

import com.sun.deploy.nativesandbox.comm.Response;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/decks")
public class DeckController {

    @Autowired
    private DeckRepository repositoryDeck;

    @GetMapping
    public ResponseEntity getDecks(){
        return ResponseEntity.status(200).body(repositoryDeck.findAll());
    }

    @GetMapping("/ramp")
    public ResponseEntity getCartasCmcBaixo(){
        return ResponseEntity.status(200).body(repositoryDeck.findAllBaixos());
    }

    @GetMapping("/{idDeck}")
    public ResponseEntity getDeckEspecifico(@PathVariable Integer idDeck){
        return ResponseEntity.status(200).body(repositoryDeck.findById(idDeck).get());
    }

    @GetMapping(value = "/download", produces = MediaType.ALL_VALUE)
    public ResponseEntity gravarDeck(@PathParam("ext")String ext){ //Pathparam , ulr /download?ext= csv || txt

        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "ddMMyyyy-HHmmss");

        List<Deck> decks = repositoryDeck.findAll();
        ListaObj<Deck> deck = new ListaObj<Deck>(repositoryDeck.contarReg());
        for (int i = 0; i < decks.size(); i++) {
            deck.adiciona(decks.get(i));
        }

        String directory = "src\\main\\resources\\static\\";
      String  archive = String.format("docfinal-%s."+ext, (formatter.format(dataDeHoje)));

            GravarArquivo ga = new GravarArquivo();
            ga.docArchive(archive , deck,ext);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment, filename = " +archive);
        headers.add("Content-Type", "text/csv");

        return new ResponseEntity(new FileSystemResource(directory + archive), headers, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity criarDeck(@RequestBody Deck deck){
        repositoryDeck.save(deck);
//        return ResponseEntity.created(null).build();
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{idDeck}")
    public ResponseEntity deletarDeck(@PathVariable Integer idDeck){
        repositoryDeck.delete(repositoryDeck.findById(idDeck).get());
//        return ResponseEntity.ok().build();
        return ResponseEntity.status(204).build();
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


//    @PutMapping("/atualizar/{idDeck}")
//    public ResponseEntity atualizarDeckComandante(@RequestBody Deck deck, @PathVariable Integer idDeck){
//        Deck deckAtt = repositoryDeck.findById(idDeck);
//
//        if(deckAtt != null){
//
//            deckAtt.setIdDeck(deckAtt.getIdDeck());
//            deckAtt.setTriboCommandante(deckAtt.getTriboCommandante());
//            deckAtt.setCartas(deckAtt.getCartas());
//            deckAtt.setPrecoTotalDeck(deckAtt.getPrecoTotalDeck());
//
//            repositoryDeck.save(deckAtt);
//
//            return ResponseEntity.status(200).body(idDeck);
//
//        }else{
//            return ResponseEntity.status(404).body(idDeck);
//        }
//
//    }
