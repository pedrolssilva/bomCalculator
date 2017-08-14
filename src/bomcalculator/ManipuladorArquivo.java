package bomcalculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ManipuladorArquivo {

    public static String[][] leitorBoard(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));

        String linha = "";
        String convertSpaceLine = "";
        int contadorBoard = 0;
        String board[][] = new String[1000][20]; //Guarda as informações do board

        while (true) {
            if (linha != null) {
                System.out.println(linha + " , Linha sem espaço:" + convertSpaceLine);

            } else {
                break;
            }
            linha = buffRead.readLine();

            // Pega a linha para remover os espaços em branco e deixar
            // apenas um espaço.
            convertSpaceLine = linha;
            if (linha != null) {
                while (convertSpaceLine.contains("  ")) {
                    convertSpaceLine = convertSpaceLine.replace("  ", " ");
                }
                board[contadorBoard] = convertSpaceLine.split(" ");
                contadorBoard++;
            }
        }
        buffRead.close();
        return board;
    }

    public static String[][] leitorTestOrder(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        BufferedReader buffReadCount = new BufferedReader(new FileReader(path));
        String linha = "";
        String convertSpaceLine = "";
        int ContadorTestOrder = 0;
        String testOrder[][] = new String[(int) buffReadCount.lines().count()][30]; //Guarda as informações do board

        while (true) {
            if (linha != null) {
                System.out.println(convertSpaceLine);

            } else {
                break;
            }
            linha = buffRead.readLine();

            // Pega a linha para remover os espaços em branco e deixar
            // apenas um espaço.
            convertSpaceLine = linha;
            //Character caracterEsepcial = '"';
            if ((linha != null) && (!linha.equals(""))/*&& (!linha.contains("!"))*/) {
                convertSpaceLine = convertSpaceLine.replace(";", "");
                while ((convertSpaceLine.contains("  ")) || (convertSpaceLine.contains("\""))) {
                    convertSpaceLine = convertSpaceLine.replace("  ", " ");
                    convertSpaceLine = convertSpaceLine.replace("\"", "");
                }
                testOrder[ContadorTestOrder] = convertSpaceLine.split(" ");
                ContadorTestOrder++;
            }
        }
        buffRead.close();
        return testOrder;
    }

    public static String[][] leitorTestOrderOriginal(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        BufferedReader buffReadCount = new BufferedReader(new FileReader(path));
        String linha = "";
        String convertSpaceLine = "";
        int ContadorTestOrder = 0;
        String testOrder[][] = new String[(int) buffReadCount.lines().count()][30]; //Guarda as informações do board

        while (true) {
            if (linha != null) {
                System.out.println(convertSpaceLine);

            } else {
                break;
            }
            linha = buffRead.readLine();

            // Pega a linha para remover os espaços em branco e deixar
            // apenas um espaço.
            convertSpaceLine = linha;
            //Character caracterEsepcial = '"';
            if ((linha != null) /*&& (!linha.contains("!"))*/) {
                convertSpaceLine = convertSpaceLine.replace(";", "");
                while ((convertSpaceLine.contains("  ")) || (convertSpaceLine.contains("\""))) {
                    convertSpaceLine = convertSpaceLine.replace("  ", " ");
                    convertSpaceLine = convertSpaceLine.replace("\"", "");
                }
                testOrder[ContadorTestOrder] = convertSpaceLine.split(" ");
                ContadorTestOrder++;
            }
        }
        buffRead.close();
        return testOrder;
    }

    public static ArrayList<String> leitorComponente(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        ArrayList<String> componente = new ArrayList<>(); //Guarda as informações do componente
        String linha = "";
        //int contadorComponente = 0;
        //String componentes[] = new String[1000]; //Guarda as informações do componente
        while (true) {
            if (linha != null) {
                System.out.println(linha);

            } else {
                break;
            }
            linha = buffRead.readLine();
            if (linha != null) {
                if (linha.contains("!!!!")) {
                    componente.add(linha);
                    //contadorComponente++;
                }
                if (linha.contains("IPG ") && !linha.contains("!gg!")) {
                    componente.add(linha);
                    //contadorComponente++;
                }
                if (!linha.contains("!")) {
                    componente.add(linha);
                    //contadorComponente++;
                }
                if (linha.contains("Tolerance Multiplier")) {
                    componente.add(linha);
                    //contadorComponente++;
                }
                if (linha.contains("! DUT: nominal")) {
                    componente.add(linha);
                    //contadorComponente++;
                }

            }
        }
        buffRead.close();
        return componente;
    }

    public static ArrayList<String> leitorComponenteOriginal(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        ArrayList<String> componente = new ArrayList<>(); //Guarda as informações do componente
        String linha = "";
        //int contadorComponente = 0;
        //String componentes[] = new String[1000]; //Guarda as informações do componente
        while (true) {
            if (linha != null) {
                System.out.println(linha);

            } else {
                break;
            }
            linha = buffRead.readLine();
            if (linha != null) {
                if (linha.contains("IPG ") && !linha.contains("!gg!")) {
                    componente.add(linha);
                    //contadorComponente++;
                }
                if (!linha.contains("!")) {
                    componente.add(linha);
                    //contadorComponente++;
                }
                if (linha.contains("Tolerance Multiplier")) {
                    componente.add(linha);
                    //contadorComponente++;
                }
                if (linha.contains("! DUT: nominal")) {
                    componente.add(linha);
                    //contadorComponente++;
                }

            }
        }
        buffRead.close();
        return componente;
    }

    public static void escritorComponente(String path, ArrayList<String> componente, String nomeComponente, String classe) throws IOException {
        String linhaDeTeste = "", linhaDut = "";
        double[] tolerancias = new double[2];
        Locale l = new Locale("en", "US");
        Locale.setDefault(new Locale("en", "US"));
        //DecimalFormat df = new DecimalFormat().getInstance(DecimalFormat("0.##")); //formata o numero decimal
        DecimalFormat df = (DecimalFormat) new DecimalFormat("0.##"); //formata o numero decimal
        df.getInstance(l);
        String linha = "";
        String value = "", prefixo = ""; //Guarda valor e prefixo do componente

        // laço para capturar as infos principais do componente:
        for (int i = 0; i < componente.size(); i++) {
            linha = componente.get(i);
            if (linha.contains("resistor") || linha.contains("capacitor")) {
                linhaDeTeste = linha;
            } else if (linha.contains("DUT: nominal")) {
                linhaDut = linha;
            }
        }

        //limpa a variavel linha:
        linha = "";        
        linhaDeTeste = linhaDeTeste.replace(" ,", ",");
        

        String[] vetorLinhaDeTeste = linhaDeTeste.split(" "); //Quebra a linha de teste em um vetor

        String[] vetorLinhaDut = linhaDut.replace(",", "").split(" "); //Reorganiza e o joga as infos no vetor

        //Separa o valor do prefixo, em relação ao valor do componentes:
        for (char ch : vetorLinhaDut[3].toCharArray()) {
            if (Character.isLetter(ch)) {
                prefixo = prefixo + ch;
            } else if (Character.isDigit(ch)) {
                value = value + ch;
            } else {
                value = value + ch;
            }
        }

        //Chama o método que calcula as tolerancias:
        tolerancias = Calculator.calcularTolerancia(Double.valueOf(value), prefixo, Double.valueOf(vetorLinhaDut[6]), classe);

        //Apagar e reescrever o teste do componente:
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path, false));
        //Cabeçalho ETS:
        buffWrite.append(componente.get(0) + "\n"); //Linha necessaria para o teste ser executado como analog e nao como texto.
        buffWrite.append("!==================================================\n");
        buffWrite.append("!                     - ETS -                      \n");
        buffWrite.append("!    - tolerance calculator and test rewrite -     \n");
        buffWrite.append("!==================================================\n");

        buffWrite.append("!==================================================\n");
        buffWrite.append("! Device: " + nomeComponente + "\n"); //Escreve o nome do componente
        buffWrite.append("! BOM Tol % : " + vetorLinhaDut[6] + "\n"); // Escreve a tolerancia antiga do componente
        buffWrite.append("! New Tol% + : " + df.format(tolerancias[0]) + "\n"); // Escreve a nova tolerancia positiva
        buffWrite.append("! New Tol% - : " + df.format(tolerancias[1]) + "\n"); // Escreve a nova tolerancia negativa
        buffWrite.append("!==================================================\n");
        buffWrite.append("!==================================================\n");

        //Escreve no arquivo de acordo com a condição:
        for (int i = 1; i < componente.size(); i++) { //Começa a partir do indice 1, pq o indice 0 é informação para executar como analog
            linha = componente.get(i);
            if (linha.contains("resistor") || linha.contains("capacitor")) {
                
                for (int j = 0; j < vetorLinhaDeTeste.length; j++) {
                    if (j == 2) {
                        buffWrite.append(df.format(tolerancias[0]) + ", ");
                    } else if (j == 3) {
                        buffWrite.append(df.format(tolerancias[1]) + ", ");
                    } else {
                        buffWrite.append(vetorLinhaDeTeste[j] + " ");
                    }
                }

                buffWrite.append("\n!==================================================\n");
                buffWrite.append("!# ");

                for (int j = 0; j < vetorLinhaDeTeste.length; j++) {
                    buffWrite.append(vetorLinhaDeTeste[j] + " ");
                }
                buffWrite.append("\n");
                buffWrite.append("!==================================================\n");

            } else if (!linha.contains("DUT: nominal")) {
                buffWrite.append(linha + "\n");
            }
        }
        buffWrite.append("!==================================================\n");

        buffWrite.close();
    }

    public static void escritorComponenteOriginal(String path, ArrayList<String> componente, String nomeComponente, String classe) throws IOException {
        String linhaDeTeste = "", linhaDut = "";
        double[] tolerancias = new double[2];
        Locale l = new Locale("en", "US");
        Locale.setDefault(new Locale("en", "US"));
        //DecimalFormat df = new DecimalFormat().getInstance(DecimalFormat("0.##")); //formata o numero decimal
        DecimalFormat df = (DecimalFormat) new DecimalFormat("0.##"); //formata o numero decimal
        df.getInstance(l);
        String linha = "";
        String value = "", prefixo = ""; //Guarda valor e prefixo do componente

        // laço para capturar as infos principais do componente:
        for (int i = 0; i < componente.size(); i++) {
            linha = componente.get(i);
            if (linha.contains("resistor") || linha.contains("capacitor")) {
                linhaDeTeste = linha;
            } else if (linha.contains("DUT: nominal")) {
                linhaDut = linha;
            }
        }

        //limpa a variavel linha:
        linha = "";

        String[] vetorLinhaDeTeste = linhaDeTeste.split(" "); //Quebra a linha de teste em um vetor

        String[] vetorLinhaDut = linhaDut.replace(",", "").split(" "); //Reorganiza e o joga as infos no vetor

        //Separa o valor do prefixo, em relação ao valor do componentes:
        for (char ch : vetorLinhaDut[3].toCharArray()) {
            if (Character.isLetter(ch)) {
                prefixo = prefixo + ch;
            } else if (Character.isDigit(ch)) {
                value = value + ch;
            } else {
                value = value + ch;
            }
        }

        //Chama o método que calcula as tolerancias:
        tolerancias = Calculator.calcularTolerancia(Double.valueOf(value), prefixo, Double.valueOf(vetorLinhaDut[6]), classe);

        //Apagar e reescrever o teste do componente:
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path, false));
        //Cabeçalho ETS:
        buffWrite.append(componente.get(0) + "\n"); //Linha necessaria para o teste ser executado como analog e nao como texto.
        buffWrite.append("!==================================================\n");
        buffWrite.append("!                     - ETS -                      \n");
        buffWrite.append("!    - tolerance calculator and test rewrite -     \n");
        buffWrite.append("!==================================================\n");

        buffWrite.append("!==================================================\n");
        buffWrite.append("! Device: " + nomeComponente + "\n"); //Escreve o nome do componente
        buffWrite.append("! BOM Tol % : " + vetorLinhaDut[6] + "\n"); // Escreve a tolerancia antiga do componente
        buffWrite.append("! New Tol% + : " + df.format(tolerancias[0]) + "\n"); // Escreve a nova tolerancia positiva
        buffWrite.append("! New Tol% - : " + df.format(tolerancias[1]) + "\n"); // Escreve a nova tolerancia negativa
        buffWrite.append("!==================================================\n");
        buffWrite.append("!==================================================\n");

        //Escreve no arquivo de acordo com a condição:
        for (int i = 1; i < componente.size(); i++) { //Começa a partir do indice 1, pq o indice 0 é informação para executar como analog
            linha = componente.get(i);
            if (linha.contains("resistor") || linha.contains("capacitor")) {
                for (int j = 0; j < vetorLinhaDeTeste.length; j++) {
                    if (j == 2) {
                        buffWrite.append(df.format(tolerancias[0]) + ", ");
                    } else if (j == 3) {
                        buffWrite.append(df.format(tolerancias[1]) + ", ");
                    } else {
                        buffWrite.append(vetorLinhaDeTeste[j] + " ");
                    }
                }

                buffWrite.append("\n!==================================================\n");
                buffWrite.append("!# ");

                for (int j = 0; j < vetorLinhaDeTeste.length; j++) {
                    buffWrite.append(vetorLinhaDeTeste[j] + " ");
                }
                buffWrite.append("\n");
                buffWrite.append("!==================================================\n");

            } else if (!linha.contains("DUT: nominal")) {
                buffWrite.append(linha + "\n");
            }
        }
        buffWrite.append("!==================================================\n");

        buffWrite.close();
    }
    
    public static void escritorArquivoModififyCompile(String path, String[][] testOrder, int quantidadePlacas) throws IOException {
        //Escreve o arquivo CompModifyCompile de acordo com a versão a partir do testOrder:   

        //Sem MULTIBOARD:
        if (quantidadePlacas == 1) {
            for (int linha = 0; linha < testOrder.length; linha++) {

                //Faz a escrita dos componentes "analog powered" e "scan" no CompModifyCompile de acordo com a versao:
                /* Para os componetes "analog powered", 
                    deslocar +1 posição do vetor no TestOrder,
                    devido a este tipo de componente conter nome com espaço em sua classe  */
                if ((testOrder[linha][0].equals("test") && testOrder[linha].length > 4)) {
                    //Se for "analog powered" e tem versao vai para o caminho da versão:
                    if ((testOrder[linha][1].equals("analog")) && (testOrder[linha][2].equals("powered")) && (testOrder[linha][4].equals("version"))) {
                        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                        buffWrite.append("compile \"" + testOrder[linha][5] + "/analog/" + testOrder[linha][3] + "\"; version\"\"\n");
                        buffWrite.close();
                    }
                    //Se for "analog powered" e NÃO tem versao vai para o caminho padrão:
                    if ((testOrder[linha][1].equals("analog")) && (testOrder[linha][2].equals("powered")) && (!testOrder[linha][4].equals("version"))) {
                        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                        buffWrite.append("compile \"analog/" + testOrder[linha][3] + "\"; version\"\"\n");
                        buffWrite.close();
                    }

                    // Componentes Scan:
                    //Se for "scan" e tem versao vai para o caminho da versão (pasta digital):
                    if ((testOrder[linha][1].equals("scan")) && (testOrder[linha][4].equals("version"))) {
                        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                        buffWrite.append("compile \"" + testOrder[linha][5] + "/digital/" + testOrder[linha][3] + "\"; version\"\"\n");
                        buffWrite.close();
                    }
                    //Se for "scan" e NÃO tem versao vai para o caminho padrão:
                    if ((testOrder[linha][1].equals("scan")) && (!testOrder[linha][4].equals("version"))) {
                        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                        buffWrite.append("compile \"digital/" + testOrder[linha][3] + "\"; version\"\"\n");
                        buffWrite.close();
                    }
                } //Se for "analog powered" ou "scan" e NÃO tem coluna de info "version" vai para o caminho padrão:
                else if ((testOrder[linha][0].equals("test") && testOrder[linha].length <= 4)) {
                    //Se for "analog powered" e NÃO tem coluna de info "version" vai para o caminho padrão:
                    if ((testOrder[linha][1].equals("analog")) && (testOrder[linha][2].equals("powered"))) {
                        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                        buffWrite.append("compile \"analog/" + testOrder[linha][3] + "\"; version\"\"\n");
                        buffWrite.close();
                    }

                    //Se for "scan" e NÃO tem coluna de info "version" vai para o caminho padrão:
                    if ((testOrder[linha][1].equals("scan"))) {
                        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                        buffWrite.append("compile \"digital/" + testOrder[linha][3] + "\"; version\"\"\n");
                        buffWrite.close();
                    }
                }

                //Agr falta fazer para os componentes que nao são Analag:
                //Faz a escrita dos componentes diferentes de "analog powered" e "scan" no CompModifyCompile de acordo com a versao:
                if ((testOrder[linha][0].equals("test") && testOrder[linha].length > 3) && !testOrder[linha][1].equals("analog") && !testOrder[linha][1].equals("scan")) {

                    //Se tem versao vai para o caminho da versão:
                    if (testOrder[linha][3].equals("version")) {
                        if (testOrder[linha][1].equals("digital")) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                            buffWrite.append("compile \"" + testOrder[linha][4] + "/digital/" + testOrder[linha][2] + "\"; version\"\"\n");
                            buffWrite.close();
                        } else if (testOrder[linha][1].equals("testjet") || testOrder[linha][1].equals("shorts") || testOrder[linha][1].equals("pins")) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                            buffWrite.append("compile \"" + testOrder[linha][4] + "/" + testOrder[linha][2] + "\"; version\"\"\n");
                            buffWrite.close();
                        } else {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                            buffWrite.append("compile \"" + testOrder[linha][4] + "/analog/" + testOrder[linha][2] + "\"; version\"\"\n");
                            buffWrite.close();
                        }
                    }

                    //Se NÃO tem versao vai para o caminho padrão:
                    if (!testOrder[linha][3].equals("version")) {
                        if (testOrder[linha][1].equals("digital")) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                            buffWrite.append("compile \"digital/" + testOrder[linha][2] + "\"; version\"\"\n");
                            buffWrite.close();
                        } else if (testOrder[linha][1].equals("testjet") || testOrder[linha][1].equals("shorts") || testOrder[linha][1].equals("pins")) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                            buffWrite.append("compile \"" + testOrder[linha][2] + "\"; version\"\"\n");
                            buffWrite.close();
                        } else {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                            buffWrite.append("compile \"analog/" + testOrder[linha][2] + "\"; version\"\"\n");
                            buffWrite.close();
                        }
                    }

                } //Faz a escrita dos componentes diferentes de "analog powered" e "scan" Se NÃO tem coluna de info "version" vai para o caminho padrão:
                else if ((testOrder[linha][0].equals("test") && testOrder[linha].length <= 3) && !testOrder[linha][1].equals("analog") && !testOrder[linha][1].equals("scan")) {
                    if (testOrder[linha][1].equals("digital")) {
                        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                        buffWrite.append("compile \"digital/" + testOrder[linha][2] + "\"; version\"\"\n");
                        buffWrite.close();
                    } else if (testOrder[linha][1].equals("testjet") || testOrder[linha][1].equals("shorts") || testOrder[linha][1].equals("pins")) {
                        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                        buffWrite.append("compile \"" + testOrder[linha][2] + "\"; version\"\"\n");
                        buffWrite.close();
                    } else {
                        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                        buffWrite.append("compile \"analog/" + testOrder[linha][2] + "\"; version\"\"\n");
                        buffWrite.close();
                    }
                }
            }
        } // Com MULTIBOARD:
        else if (quantidadePlacas > 1) {
            for (int i = 1; i <= quantidadePlacas; i++) {
                for (int linha = 0; linha < testOrder.length; linha++) {

                    //Faz a escrita dos componentes "analog powered" e "scan" no CompModifyCompile de acordo com a versao:
                    /* Para os componetes "analog powered", 
                    deslocar +1 posição do vetor no TestOrder,
                    devido a este tipo de componente conter nome com espaço em sua classe  */
                    if ((testOrder[linha][0].equals("test") && testOrder[linha].length > 4)) {
                        //Se for "analog powered" e tem versao vai para o caminho da versão:
                        if ((testOrder[linha][1].equals("analog")) && (testOrder[linha][2].equals("powered")) && (testOrder[linha][4].equals("version"))) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                            buffWrite.append("compile \"" + testOrder[linha][5] + "/analog/" + i + "%" + testOrder[linha][3] + "\"; version\"\"\n");
                            buffWrite.close();
                        }
                        //Se for "analog powered" e NÃO tem versao vai para o caminho padrão:
                        if ((testOrder[linha][1].equals("analog")) && (testOrder[linha][2].equals("powered")) && (!testOrder[linha][4].equals("version"))) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                            buffWrite.append("compile \"analog/" + i + "%" + testOrder[linha][3] + "\"; version\"\"\n");
                            buffWrite.close();
                        }

                        // Componentes Scan:
                        //Se for "scan" e tem versao vai para o caminho da versão (pasta digital):
                        if ((testOrder[linha][1].equals("scan")) && (testOrder[linha][4].equals("version"))) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                            buffWrite.append("compile \"" + testOrder[linha][5] + "/digital/" + i + "%" + testOrder[linha][3] + "\"; version\"\"\n");
                            buffWrite.close();
                        }
                        //Se for "scan" e NÃO tem versao vai para o caminho padrão:
                        if ((testOrder[linha][1].equals("scan")) && (!testOrder[linha][4].equals("version"))) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                            buffWrite.append("compile \"digital/" + i + "%" + testOrder[linha][3] + "\"; version\"\"\n");
                            buffWrite.close();
                        }
                    } //Se for "analog powered" ou "scan" e NÃO tem coluna de info "version" vai para o caminho padrão:
                    else if ((testOrder[linha][0].equals("test") && testOrder[linha].length <= 4)) {
                        //Se for "analog powered" e NÃO tem coluna de info "version" vai para o caminho padrão:
                        if ((testOrder[linha][1].equals("analog")) && (testOrder[linha][2].equals("powered"))) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                            buffWrite.append("compile \"analog/" + i + "%" + testOrder[linha][3] + "\"; version\"\"\n");
                            buffWrite.close();
                        }

                        //Se for "scan" e NÃO tem coluna de info "version" vai para o caminho padrão:
                        if ((testOrder[linha][1].equals("scan"))) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                            buffWrite.append("compile \"digital/" + i + "%" + testOrder[linha][3] + "\"; version\"\"\n");
                            buffWrite.close();
                        }
                    }

                    //Agr falta fazer para os componentes que nao são Analag:
                    //Faz a escrita dos componentes diferentes de "analog powered" e "scan" no CompModifyCompile de acordo com a versao:
                    if ((testOrder[linha][0].equals("test") && testOrder[linha].length > 3) && !testOrder[linha][1].equals("analog") && !testOrder[linha][1].equals("scan")) {

                        //Se tem versao vai para o caminho da versão:
                        if (testOrder[linha][3].equals("version")) {
                            if (testOrder[linha][1].equals("digital")) {
                                BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                                buffWrite.append("compile \"" + testOrder[linha][4] + "/digital/" + i + "%" + testOrder[linha][2] + "\"; version\"\"\n");
                                buffWrite.close();
                            } else if (testOrder[linha][1].equals("testjet") || testOrder[linha][1].equals("shorts") || testOrder[linha][1].equals("pins")) {
                                BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                                buffWrite.append("compile \"" + testOrder[linha][4] + "/" + i + "%" + testOrder[linha][2] + "\"; version\"\"\n");
                                buffWrite.close();
                            } else {
                                BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                                buffWrite.append("compile \"" + testOrder[linha][4] + "/analog/" + i + "%" + testOrder[linha][2] + "\"; version\"\"\n");
                                buffWrite.close();
                            }
                        }

                        //Se NÃO tem versao vai para o caminho padrão:
                        if (!testOrder[linha][3].equals("version")) {
                            if (testOrder[linha][1].equals("digital")) {
                                BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                                buffWrite.append("compile \"digital/" + i + "%" + testOrder[linha][2] + "\"; version\"\"\n");
                                buffWrite.close();
                            } else if (testOrder[linha][1].equals("testjet") || testOrder[linha][1].equals("shorts") || testOrder[linha][1].equals("pins")) {
                                BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                                buffWrite.append("compile \"" + i + "%" + testOrder[linha][2] + "\"; version\"\"\n");
                                buffWrite.close();
                            } else {
                                BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                                buffWrite.append("compile \"analog/" + i + "%" + testOrder[linha][2] + "\"; version\"\"\n");
                                buffWrite.close();
                            }
                        }

                    } //Faz a escrita dos componentes diferentes de "analog powered" e "scan" Se NÃO tem coluna de info "version" vai para o caminho padrão:
                    else if ((testOrder[linha][0].equals("test") && testOrder[linha].length <= 3) && !testOrder[linha][1].equals("analog") && !testOrder[linha][1].equals("scan")) {
                        if (testOrder[linha][1].equals("digital")) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                            buffWrite.append("compile \"digital/" + i + "%" + testOrder[linha][2] + "\"; version\"\"\n");
                            buffWrite.close();
                        } else if (testOrder[linha][1].equals("testjet") || testOrder[linha][1].equals("shorts") || testOrder[linha][1].equals("pins")) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                            buffWrite.append("compile \"" + i + "%" + testOrder[linha][2] + "\"; version\"\"\n");
                            buffWrite.close();
                        } else {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyCompile", true));
                            buffWrite.append("compile \"analog/" + i + "%" + testOrder[linha][2] + "\"; version\"\"\n");
                            buffWrite.close();
                        }
                    }
                }
            }
        }
    }

    public static void escritorArquivoCompModifyLink(String path, String[][] testOrder, int quantidadePlacas) throws IOException {
        // Com MULTIBOARD:
        if (quantidadePlacas > 1) {
            for (int i = 2; i <= quantidadePlacas; i++) {
                for (int linha = 0; linha < testOrder.length; linha++) {

                    //Faz a escrita dos componentes "analog powered" e "scan" no arquivo CompModifyLink de acordo com a versao:
                    /* Para os componetes "analog powered", 
                    deslocar +1 posição do vetor no TestOrder,
                    devido a este tipo de componente conter nome com espaço em sua classe  */
                    if ((testOrder[linha][0].equals("test") && testOrder[linha].length > 4)) {
                        //Se for "analog powered" e tem versao vai para o caminho da versão:
                        if ((testOrder[linha][1].equals("analog")) && (testOrder[linha][2].equals("powered")) && (testOrder[linha][4].equals("version"))) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyLink", true));
                            buffWrite.append("link \"" + testOrder[linha][5] + "/analog/" + i + "%" + testOrder[linha][3] + "\" to \""
                                    + testOrder[linha][5] + "/analog/1%" + testOrder[linha][3] + "\" \n");
                            buffWrite.close();
                        }
                        //Se for "analog powered" e NÃO tem versao vai para o caminho padrão:
                        if ((testOrder[linha][1].equals("analog")) && (testOrder[linha][2].equals("powered")) && (!testOrder[linha][4].equals("version"))) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyLink", true));
                            buffWrite.append("link \"analog/" + i + "%" + testOrder[linha][3] + "\" to \""
                                    + "analog/1%" + testOrder[linha][3] + "\" \n");
                            buffWrite.close();
                        }

                        // Componentes Scan:
                        //Se for "scan" e tem versao vai para o caminho da versão (pasta digital):
                        if ((testOrder[linha][1].equals("scan")) && (testOrder[linha][4].equals("version"))) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyLink", true));
                            buffWrite.append("link \"" + testOrder[linha][5] + "/digital/" + i + "%" + testOrder[linha][3] + "\" to \""
                                    + testOrder[linha][5] + "/digital/1%" + testOrder[linha][3] + "\" \n");
                            buffWrite.close();
                        }
                        //Se for "scan" e NÃO tem versao vai para o caminho padrão:
                        if ((testOrder[linha][1].equals("scan")) && (!testOrder[linha][4].equals("version"))) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyLink", true));
                            buffWrite.append("link \"digital/" + i + "%" + testOrder[linha][3] + "\" to \""
                                    + "digital/1%" + testOrder[linha][3] + "\" \n");
                            buffWrite.close();
                        }
                    } //Se for "analog powered" ou "scan" e NÃO tem coluna de info "version" vai para o caminho padrão:
                    else if ((testOrder[linha][0].equals("test") && testOrder[linha].length <= 4)) {
                        //Se for "analog powered" e NÃO tem coluna de info "version" vai para o caminho padrão:
                        if ((testOrder[linha][1].equals("analog")) && (testOrder[linha][2].equals("powered"))) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyLink", true));
                            buffWrite.append("link \"analog/" + i + "%" + testOrder[linha][3] + "\" to \""
                                    + "analog/1%" + testOrder[linha][3] + "\" \n");
                            buffWrite.close();
                        }

                        //Se for "scan" e NÃO tem coluna de info "version" vai para o caminho padrão:
                        if ((testOrder[linha][1].equals("scan"))) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyLink", true));
                            buffWrite.append("link \"digital/" + i + "%" + testOrder[linha][3] + "\" to \""
                                    + "digital/1%" + testOrder[linha][3] + "\" \n");
                            buffWrite.close();
                        }
                    }

                    //Agr falta fazer para os componentes que nao são Analag e Scan:
                    //Faz a escrita dos componentes diferentes de "analog powered" e "scan" no CompModifyCompile de acordo com a versao:
                    if ((testOrder[linha][0].equals("test") && testOrder[linha].length > 3) && !testOrder[linha][1].equals("analog") && !testOrder[linha][1].equals("scan")) {

                        //Se tem versao vai para o caminho da versão:
                        if (testOrder[linha][3].equals("version")) {
                            if (testOrder[linha][1].equals("digital")) {
                                BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyLink", true));
                                buffWrite.append("link \"" + testOrder[linha][4] + "/digital/" + i + "%" + testOrder[linha][2] + "\" to \""
                                        + testOrder[linha][4] + "/digital/1%" + testOrder[linha][2] + "\" \n");
                                buffWrite.close();
                            } else if (testOrder[linha][1].equals("testjet") || testOrder[linha][1].equals("shorts") || testOrder[linha][1].equals("pins")) {
                                BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyLink", true));
                                buffWrite.append("link \"" + testOrder[linha][4] + "/" + i + "%" + testOrder[linha][2] + "\" to \""
                                        + testOrder[linha][4] + "/1%" + testOrder[linha][2] + "\" \n");
                                buffWrite.close();
                            } else {
                                BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyLink", true));
                                buffWrite.append("link \"" + testOrder[linha][4] + "/analog/" + i + "%" + testOrder[linha][2] + "\" to \""
                                        + testOrder[linha][4] + "/analog/1%" + testOrder[linha][2] + "\" \n");
                                buffWrite.close();
                            }
                        }

                        //Se NÃO tem versao vai para o caminho padrão:
                        if (!testOrder[linha][3].equals("version")) {
                            if (testOrder[linha][1].equals("digital")) {
                                BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyLink", true));
                                buffWrite.append("link \"digital/" + i + "%" + testOrder[linha][2] + "\" to \""
                                        + "digital/1%" + testOrder[linha][2] + "\" \n");
                                buffWrite.close();
                            } else if (testOrder[linha][1].equals("testjet") || testOrder[linha][1].equals("shorts") || testOrder[linha][1].equals("pins")) {
                                BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyLink", true));
                                buffWrite.append("link \"" + i + "%" + testOrder[linha][2] + "\" to \""
                                        + "1%" + testOrder[linha][2] + "\" \n");
                                buffWrite.close();
                            } else {
                                BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyLink", true));
                                buffWrite.append("link \"analog/" + i + "%" + testOrder[linha][2] + "\" to \""
                                        + "analog/1%" + testOrder[linha][2] + "\" \n");
                                buffWrite.close();
                            }
                        }

                    } //Faz a escrita dos componentes diferentes de "analog powered" e "scan" Se NÃO tem coluna de info "version" vai para o caminho padrão:
                    else if ((testOrder[linha][0].equals("test") && testOrder[linha].length <= 3) && !testOrder[linha][1].equals("analog") && !testOrder[linha][1].equals("scan")) {
                        if (testOrder[linha][1].equals("digital")) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyLink", true));
                            buffWrite.append("link \"digital/" + i + "%" + testOrder[linha][2] + "\" to \""
                                    + "digital/1%" + testOrder[linha][2] + "\" \n");
                            buffWrite.close();
                        } else if (testOrder[linha][1].equals("testjet") || testOrder[linha][1].equals("shorts") || testOrder[linha][1].equals("pins")) {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyLink", true));
                            buffWrite.append("link \"" + i + "%" + testOrder[linha][2] + "\" to \""
                                    + "1%" + testOrder[linha][2] + "\" \n");
                            buffWrite.close();
                        } else {
                            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + "CompModifyLink", true));
                            buffWrite.append("link \"analog/" + i + "%" + testOrder[linha][2] + "\" to \""
                                    + "analog/1%" + testOrder[linha][2] + "\" \n");
                            buffWrite.close();
                        }
                    }
                }
            }
        }
    }
}
