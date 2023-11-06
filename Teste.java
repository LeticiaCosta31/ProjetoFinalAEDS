import java.util.Scanner;

public class Teste {
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
    
        Vestibular vestibular = new Vestibular();

        //Passa o caminho do arquivo que fará a leitura
        vestibular.lerEntrada("C:\\tmp\\entradaAED.txt");
        
        //Insere cada candidato em sua respectiva lista ou fila
        vestibular.calcularClassificacao();
        
        //Passa o caminho do arquivo que irá escrever como saída
        vestibular.escreverSaida("C:\\tmp\\saidaAED.txt");
        
    
        teclado.close();
        
       
    }
}