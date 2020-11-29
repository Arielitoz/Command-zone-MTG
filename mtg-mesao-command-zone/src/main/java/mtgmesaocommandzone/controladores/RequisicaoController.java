package mtgmesaocommandzone.controladores;

import mtgmesaocommandzone.Fila;
import mtgmesaocommandzone.PilhaObj;
import mtgmesaocommandzone.dominios.Carta;
import mtgmesaocommandzone.dominios.Deck;
import mtgmesaocommandzone.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exe")
//@EnableAsync
public class RequisicaoController {

    @Autowired
    private DeckRepository repositoryDeck;

    //UUID e HashMap
//    private Map<UUID, Integer> requisicoes = new HashMap<>();
    private List<Integer> requisicoesProcessadas = new ArrayList<>();

    //Pilha Pop e Push
    PilhaObj<Carta> cartas = new PilhaObj<>(10);
    Fila<Integer> requisicoesQueue = new Fila<>(10);
    private Integer requisicaoIndice = 0;
    @PostMapping("/addStack")
    public ResponseEntity addPilha(@RequestBody Carta carta){

//        Integer ultimo =  requisicoes.values().stream().collect(Collectors.toList()).get(requisicoes.values().size()-1);
//        requisicoes.put(UUID.randomUUID(), ultimo++);
//        Integer ultimo = (requisicoes.size() -1);

        System.out.println("\nRequisição de Adicionar na Pilha: ");
        requisicoesQueue.insert(requisicaoIndice++);
        System.out.println(requisicaoIndice);
        cartas.push(carta);
        cartas.exibe();
        requisicoesQueue.exibe();
        return ResponseEntity.created(null).build();

    }

    @DeleteMapping("/popStack")
    public ResponseEntity popPilha(){
        System.out.println("\nRequisição de Deletar na Pilha: ");
        requisicoesQueue.insert(requisicaoIndice++);
        requisicoesQueue.exibe();
        cartas.pop();
        System.out.println(requisicaoIndice);
        cartas.exibe();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/addQueue")
    public ResponseEntity addRequisicoes(){
        System.out.println("\nRequisição de Enfileirar: ");
        requisicoesQueue.insert(requisicaoIndice++);
        System.out.println(requisicaoIndice);
        requisicoesQueue.exibe();
        return ResponseEntity.ok().body(requisicaoIndice);
    }

//    @Async
    @Scheduled(cron = "0/10 * * * * *")
    public void tratarRequisicoes(){
        System.out.println("Cron Job iniciado: -->:");
        if(requisicoesQueue.isEmpty()){
            System.out.println("Sem requisições no Momento");
        }else{
                requisicoesQueue.exibe();
                Integer filaEncontrada = requisicoesQueue.poll();
                requisicoesProcessadas.add(filaEncontrada);

                System.out.println("Enviando para a lista de processados");
                System.out.println(requisicoesProcessadas);
        }
    }

    @GetMapping("/{requisicaoIndice}")
    public ResponseEntity resultado(@PathVariable Integer requisicaoIndice){
        if(requisicoesProcessadas.contains(requisicaoIndice)){
            if(requisicoesProcessadas.get(requisicaoIndice) == 0){
                return ResponseEntity.noContent().build();
            }else{
                System.out.println(requisicoesProcessadas);
                requisicoesProcessadas.remove(requisicaoIndice);
                System.out.println("Removendo:");
                System.out.println(requisicoesProcessadas);
            }
        }else{
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    public Deck montarLayout(String linha){
        Deck deck = new Deck();
        String [] upladoArquivo = linha.split("\\|");
        deck.setIdDeck(Integer.parseInt(upladoArquivo[1]));
        deck.setNomeCommandante(upladoArquivo[2]);
        deck.setCmcCommander(Integer.parseInt(upladoArquivo[3]));
        deck.setTriboCommandante(null);
        deck.setPrecoTotalDeck(Double.parseDouble(upladoArquivo[4]));
        deck.setCartas(null);

        return deck;
    }

    @PostMapping("/upload")
    public ResponseEntity enviar(@RequestParam("arquivo") MultipartFile archive) throws IOException {


        if(archive.isEmpty()){
            return ResponseEntity.badRequest().body("Arquivo não enviado");
        }
        System.out.println("Recebendo um arquivo do tipo" + archive.getContentType());

//        byte[] conteudo = archive.getBytes();
        String conteudo = new String(archive.getBytes(), StandardCharsets.UTF_8);
//        System.out.println("OIIII " + conteudo);
        new BufferedReader(new StringReader(conteudo)).lines().forEach(
                (line) -> {
                    if(line.contains("99")){
                        Deck deck = montarLayout(line);
                        repositoryDeck.save(deck);
                    }
                    System.out.println(line);

                }
        );

        Path path = Paths.get(archive.getOriginalFilename());

//        Files.write(path, conteudo);

        requisicoesQueue.insert(requisicaoIndice++);
        System.out.println(requisicaoIndice);

        return ResponseEntity.created(null).build();
    }
}
