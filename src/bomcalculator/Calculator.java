package bomcalculator;

public class Calculator {
    
        public static double[] calcularTolerancia(double value, String prefixo, double tolerancia, String classe) {
        double[] resultado = new double[2];
        double valorConvertido;
        double ptol = 0, ntol = 0;

        //Se for resistor:
        if (classe.equals("resistor")) {
            // Converte o valor para decimal de acordo com o prefixo:
            if (prefixo.toLowerCase().equals("k")) {
                valorConvertido = value * Math.pow(10, 3);
            } else {
                if (prefixo.equals("M")) {
                    valorConvertido = value * Math.pow(10, 6);
                } else {
                    valorConvertido = value;
                }
            }

            //Faz o calculo das  tolerâncias positiva e negativa dos componentes:
            if (valorConvertido <= 300) {
                //Calcula tolerancia máxima:
                ptol = (tolerancia) + (5) + ((5 / valorConvertido) * 100);

                //Calcula tolerancia minima:
                ntol = (tolerancia) + (5);
            } else {
                if (valorConvertido < (100 * Math.pow(10, 3))) {
                    //Calcula tolerancia máxima:
                    ptol = (tolerancia) + (5);

                    //Calcula tolerancia minima:
                    ntol = (tolerancia) + (5);
                } else {
                    if (valorConvertido <= (999.9 * Math.pow(10, 3))) {
                        //Calcula tolerancia máxima:
                        ptol = (tolerancia) + (5);

                        //Calcula tolerancia minima:
                        ntol = (tolerancia) + (5);
                    } else {
			if(valorConvertido < (10 * Math.pow(10, 6))){
                        //Calcula tolerancia máxima:
                        ptol = (tolerancia) + (10);

                        //Calcula tolerancia minima:
                        ntol = (tolerancia) + (10);
			} else{
			//Calcula tolerancia máxima:
                        ptol = (tolerancia) + (20);

                        //Calcula tolerancia minima:
                        ntol = (tolerancia) + (20);
			}
                    }
                }
            }

        } //Se for capacitor:
        else if (classe.equals("capacitor")) {

            //Converte o valor para decimal de acordo com o prefixo:
            if (prefixo.toLowerCase().equals("m")) {
                valorConvertido = value * Math.pow(10, -3);
            } else {
                if (prefixo.toLowerCase().equals("u")) {
                    valorConvertido = value * Math.pow(10, -6);
                } else {
                    if (prefixo.toLowerCase().equals("n")) {
                        valorConvertido = value * Math.pow(10, -9);
                    } else {
                        if (prefixo.toLowerCase().equals("p")) {
                            valorConvertido = value * Math.pow(10, -12);
                        } else {
                            valorConvertido = value;
                        }
                    }
                }
            }

            //Faz o calculo das  tolerâncias positiva e negativa dos componentes:
            if ((valorConvertido <= (2 * Math.pow(10, -9))) && (valorConvertido > (100 * Math.pow(10, -12)))) {
                //Calcula tolerancia máxima:
                ptol = (tolerancia) + (10) + (((27 * Math.pow(10, -12)) / valorConvertido) * 100);

                //Calcula tolerancia minima:
                ntol = (tolerancia) + (10);
            } else {
                if (valorConvertido <= (99 * Math.pow(10, -6))) {
                    //Calcula tolerancia máxima:
                    ptol = (tolerancia) + (10);

                    //Calcula tolerancia minima:
                    ntol = (tolerancia) + (10);
                } else {
                    if (valorConvertido > (99 * Math.pow(10, -6)) ) {
                        //Calcula tolerancia máxima:
                        ptol = (tolerancia) + (10);

                        //Calcula tolerancia minima:
                        ntol = (tolerancia) + (10) + ((valorConvertido / 0.159) * 100);
                    } else {
                        ptol = tolerancia;

                        //Calcula tolerancia minima:
                        ntol = tolerancia;
                    }
                }
            }
        }   
    resultado[0] = ptol ;
    resultado[1] = ntol ;
    return resultado ;
}

}
