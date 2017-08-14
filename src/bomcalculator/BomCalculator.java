package bomcalculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BomCalculator {

    public static void main(String[] args) throws IOException {
        //Variaveis:        
        String testOrder[][] = new String[30000][30]; //Guarda as informações do testOrder
        //String componente[] = new String[100];  // Guarda as informações do componente
        ArrayList<String> componente = new ArrayList<>();
        String classe = "";

        //Criar arquivos 'CompModifyAnalog", "CompModifyCompile" e "CompModifyLink"
        /*File CompModifyAnalog = new File("c:\\Users\\pluis\\Desktop\\ArquivosHP\\virgo\\CompModifyAnalog");
        CompModifyAnalog.createNewFile(); */ // Não é mais necessário
        String path = "c:\\Users\\pluis\\Desktop\\ArquivosHP\\nextracker\\";
        
        File CompModifyCompile = new File(path+"CompModifyCompile");
        if (!CompModifyCompile.exists()) {
            CompModifyCompile.createNewFile();
        }
        
        File CompModifyLink = new File(path+"CompModifyLink");
        if (!CompModifyLink.exists()) {
            CompModifyLink.createNewFile();
        }
        
        //para cortar casas depois da virgula:
        double calculo = 2 * Math.pow(10, -12);
        DecimalFormat df = new DecimalFormat("0.############");
        String calc = df.format(calculo);
        System.out.println(calc);

        String teste = "100.1k";
        String value = "", prefixo = "";
        for (char ch : teste.toCharArray()) {
            if (Character.isLetter(ch)) {
                prefixo = prefixo+ch;
            } else if(Character.isDigit(ch)){
                value = value+ch;
            }else{
                value = value+ch;
            }
        }

        //Testa se esta calculando as tolerancias corretamente:
        double tolerancias[] = Calculator.calcularTolerancia(100, "u", 10, "capacitor");


        testOrder = ManipuladorArquivo.leitorTestOrder(path + "\\testorder");
        //String variavelTeste = "test";
        
        
        //ManipuladorArquivo.escritorArquivoModififyCompile(path, testOrder, 2);
        //ManipuladorArquivo.escritorArquivoCompModifyLink(path, testOrder, 3);

        // boolean teste = testOrder[3153][0].contains(null);
        // pegar informações do componete a partir do testOrder:
        int linha = 0;
        if ((testOrder[linha][0].equals("test") && testOrder[linha].length > 3) && (testOrder[linha][1].equals("resistor") || testOrder[linha][1].equals("capacitor"))) {
            if (!testOrder[linha][2].equals(".discharge")) {
                //Se tem versao vai para o caminho da versão:
                if (testOrder[linha][3].equals("version")) {
                    componente = ManipuladorArquivo.leitorComponente(path + "\\" + testOrder[linha][4] + "\\analog\\" + testOrder[linha][2]);
                    classe = testOrder[linha][1];
                    ManipuladorArquivo.escritorComponente(path + "\\" + testOrder[linha][4] + "\\analog\\" + testOrder[linha][2], componente, testOrder[linha][2], classe);
                }

                //Se NÃO tem versao vai para o caminho padrão:
                if (!testOrder[linha][3].equals("version")) {
                    componente = ManipuladorArquivo.leitorComponente(path + "\\analog\\1%" + testOrder[linha][2]);
                    classe = testOrder[linha][1];
                    ManipuladorArquivo.escritorComponente(path + "\\analog\\1%" + testOrder[linha][2], componente, "1%"+testOrder[linha][2], classe);
                }
            }
        } //Se NÃO tem coluna de info "version" vai para o caminho padrão:
        else if ((testOrder[linha][0].equals("test") && testOrder[linha].length <= 3) && (testOrder[linha][1].equals("resistor") || testOrder[linha][1].equals("capacitor"))) {
            if (!testOrder[linha][2].equals(".discharge")) {
                componente = ManipuladorArquivo.leitorComponente(path + "\\analog\\" + testOrder[linha][2]);
                classe = testOrder[linha][1];
            }
        }
        
        

        //Apagar e reescrever o teste do componente:
        // BufferedWriter buffWrite = new BufferedWriter(new FileWriter("c:\\Users\\pluis\\Desktop\\ArquivosHP\\pr4510", false));
    }
}
