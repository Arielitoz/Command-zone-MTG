//package mtgmesaocommandzone.dominios;
//
//import mtgmesaocommandzone.ListaObj;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.Formatter;
//import java.util.FormatterClosedException;
//import java.util.List;
//
//public class LayoutDocumento<T> {
//
//    public static void gravaRegistro(String nomeArq, String registro) {
////
//        BufferedWriter saida = null;
//        try{
//            saida = new BufferedWriter(new FileWriter(nomeArq, true));
//        }catch (IOException e){
//            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
//        }
//        try{
//            saida.append(registro + "\n");
//            saida.close();
//        }catch (IOException e){
//            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
//        }
//
//    }
//
//    public void layoutDocumento(String nomeArq, ListaObj<Deck> decks) {
//
////        String nomeArq = "docFinal.txt";
//
//        String header ="";
//        String corpo = "";
//        String trailer = "";
//
//        //Pegar data -
//        LocalDateTime dataHoraAtual = LocalDateTime.now();
////        int contRegDados = 0;
//
//        //Monta o registro Header
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//
//        header += "01 DECK ";
//        header += formatter.format(dataHoraAtual);
//
//        //Grava o registro header
//        gravaRegistro(nomeArq, header);
//
//        //Monta o Corpo
//
//        //1°Registro de Dados
//        corpo += "02";
//        corpo += String.format("99 %3d %20s %10d %102.f  \n", "IdDeck", "Nome Comandante", "Custo Mana", "Preço Deck");
//
//        gravaRegistro("src\\main\\resources\\static\\" + nomeArq, corpo);
//
//        Integer contRegDados = gravarRegistroDtxt("src\\main\\resources\\static\\" + nomeArq, decks);
//
//        trailer += "935";
//        trailer += String.format("%010d", contRegDados);
//
//        //gravar trailer
//        gravaRegistro("src\\main\\resources\\static\\" + nomeArq, trailer);
//
//    }
//
//    public Integer gravarRegistroDtxt(String nomeArq, ListaObj<Deck> decks){
//
//        Integer contRegDados = 0;
//
//        Formatter saida = null;
//        boolean pane = false;
//        FileWriter nomeArquivo = null;
//
//        try{
//            nomeArquivo = new FileWriter("src\\main\\resources\\static\\" + nomeArq,true);
//            saida = new Formatter(nomeArquivo);
//        }
//        catch (IOException e){
//            System.err.println("Erro ao abrir o arquivo");
//            System.exit(1);
//        }
//
//        try{
//            for(Integer item = 0; item < decks.getTamanho(); item++){
//                Deck deck = decks.getElemento(item);
//                saida.format("99 %3d %20s %10d %102.f  \n", deck.getIdDeck(), deck.getNomeCommandante(), deck.getCmcCommander(), deck.getPrecoTotalDeck());
//                contRegDados++;
//            }
//        }catch (FormatterClosedException error){
//            System.err.printf("Erro ao gravar o arquivo");
//            pane = true;
//        }
//        finally {
//            saida.close();
//            try{
//                nomeArquivo.close();
//            }
//            catch (IOException erro){
//                System.err.printf("Erro ao fechar o arquivo");
//                pane = true;
//            }
//            if(pane){
//                System.exit(1);
//            }
//        }
//        return contRegDados;
//    }
//
//    public Integer gravarRegistroCSV(String nomeArq, ListaObj<Deck> decks){
//
//        Integer contRegDados = 0;
//
//        Formatter saida = null;
//        boolean pane = false;
//        FileWriter nomeArquivo = null;
//
//        try{
//            nomeArquivo = new FileWriter("src\\main\\resources\\static\\" + nomeArq,true);
//            saida = new Formatter(nomeArquivo);
//        }
//        catch (IOException e){
//            System.err.println("Erro ao abrir o arquivo");
//            System.exit(1);
//        }
//
//        try{
//            for(Integer item = 0; item < decks.getTamanho(); item++){
//                Deck deck = decks.getElemento(item);
//                saida.format("99;%3d;%20s;%10d;%102.f;  \n", deck.getIdDeck(), deck.getNomeCommandante(), deck.getCmcCommander(), deck.getPrecoTotalDeck());
//                contRegDados++;
//            }
//        }catch (FormatterClosedException error){
//            System.err.printf("Erro ao gravar o arquivo");
//            pane = true;
//        }
//        finally {
//            saida.close();
//            try{
//                nomeArquivo.close();
//            }
//            catch (IOException erro){
//                System.err.printf("Erro ao fechar o arquivo");
//                pane = true;
//            }
//            if(pane){
//                System.exit(1);
//            }
//        }
//        return decks;
//    }
//
//
//}
//
//
