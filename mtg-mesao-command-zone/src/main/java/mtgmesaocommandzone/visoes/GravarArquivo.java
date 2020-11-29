package mtgmesaocommandzone.visoes;

import mtgmesaocommandzone.ListaObj;
import mtgmesaocommandzone.dominios.Deck;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

public class GravarArquivo {

    public static void gravarRegistro(String nameArq, String registro) {
        //Gravar Registro - Gravar Nome do Arquivo e o tipo de registro
            //Header, corpo e trailer.
        BufferedWriter saida = null;
        try{
            saida = new BufferedWriter(new FileWriter(nameArq, true));
        }catch (IOException e){
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
        try{
            saida.append(registro + "\n");
            saida.close();
        }catch (IOException e){
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }

    }

    public void docArchive(String nameArq, ListaObj<Deck> decks, String tipo) {
        String  body = "", trailer = "";

        //Var Path diretorio
        String directory = "src\\main\\resources\\static\\";

        //Pegar data Atual
        LocalDateTime dataHoraAtual = LocalDateTime.now();

        //Monta Corpo e Executa o Método Referentes ao TXT
        if(tipo.equals("txt")){

            //Variaveis de Registro do Layout - Documento
            String header = "";

            //Monta o registro Header
            header += "01 DECKS ";
            header += dataHoraAtual;

            //Grava o registro header
            gravarRegistro(directory + nameArq, header);

            body += String.format("115 %s %s %s %s  \n", "IdDeck", "Nome Comandante", "Custo Mana", "Preço Total");
            gravarRegistro(directory + nameArq , body);

            Integer contadorRegistro = writeTXT(directory + nameArq, decks);
            trailer += "935";
            trailer += String.format("%03d", contadorRegistro);

            //gravar trailer
            gravarRegistro(directory + nameArq, trailer);


            //Monta Corpo e Executa o Método Referentes ao CSV
        }else if(tipo.equals("csv")){

            body += String.format("15 %s %s %s %s  \n", "IdDeck", "Nome Comandante", "Custo Mana", "Preço Total");
            gravarRegistro(directory + nameArq, body);
            writeCSV(directory + nameArq, decks);

        }
    }
    //Gravar corpo TXT
    public Integer writeTXT(String nameArq, ListaObj<Deck> decks){

        Integer contadorRegistro = 0;

        FileWriter nomeArquivo = null;
        Formatter saida = null;
        boolean pane = false;

        try{
            nomeArquivo = new FileWriter( nameArq,true);
            saida = new Formatter(nomeArquivo);
        }
        catch (IOException erro){
            System.err.println("Erro ao abrir o arquivo"  + erro.getMessage());
            System.exit(1);
        }

        try{
            for(Integer item = 0; item < decks.getTamanho(); item++){
                Deck deck = decks.getElemento(item);
                saida.format("99|%d|%s|%d|%.2f  \n", deck.getIdDeck(), deck.getNomeCommandante(), deck.getCmcCommander(), deck.getPrecoTotalDeck());
                contadorRegistro++;
            }
        }catch (FormatterClosedException error){
            System.err.printf("Erro ao gravar o arquivo");
            pane = true;
        }
        finally {
            saida.close();
            try{
                nomeArquivo.close();
            }
            catch (IOException erro){
                System.err.printf("Erro ao fechar o arquivo");
                pane = true;
            }
            if(pane){
                System.exit(1);
            }
        }
        return contadorRegistro;
    }

    //Gravar corpo CSV
    public Integer writeCSV(String nameArq, ListaObj<Deck> decks){

        Integer contadorRegistro = 0;

        Formatter saida = null;
        boolean pane = false;
        FileWriter nomeArquivo = null;

        try{
            nomeArquivo = new FileWriter( nameArq,true);
            saida = new Formatter(nomeArquivo);
        }
        catch (IOException erro){
            System.err.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try{
            for(Integer item = 0; item < decks.getTamanho(); item++){
                Deck deck = decks.getElemento(item);
                saida.format("99;%d;%s;%d;%.2f\n", deck.getIdDeck(), deck.getNomeCommandante(), deck.getCmcCommander(), deck.getPrecoTotalDeck());
                contadorRegistro++;
            }
        }catch (FormatterClosedException error){
            System.err.printf("Erro ao gravar o arquivo");
            pane = true;
        }
        finally {
            saida.close();
            try{
                nomeArquivo.close();
            }
            catch (IOException erro){
                System.err.printf("Erro ao fechar o arquivo");
                pane = true;
            }
            if(pane){
                System.exit(1);
            }
        }
        return contadorRegistro;
    }
}



