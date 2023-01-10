import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CalcularValorEncherTanque {
    private static Double precoGasolina = Double.valueOf(fetchPrecoGasolina()).doubleValue();

    private static String formatarValorGasolina(String valorGasolina){
        char[] chars = valorGasolina.toCharArray();


        for(int i = 0; i < chars.length; i++){
            if(chars[i] == ','){
                chars[i] = '.';
            }
        }


        String comPonto = new String(chars);

        return comPonto;
    }

    
    private static String fetchPrecoGasolina(){
        try{
            Document doc = Jsoup.connect("https://precos.petrobras.com.br/sele%C3%A7%C3%A3o-de-estados-gasolina").get();
            Element newsHeadlines = doc.getElementById("telafinal-precofinal");
            String valorGasolina = newsHeadlines.html();
            String precoFinal = formatarValorGasolina(valorGasolina);
            
            return precoFinal;
        }
        catch(IOException e){
            e.printStackTrace();
            return "Não foi possível econtrar o preço da gasolina";
        }
        
    }

    public static void calcularValorTotalEncherTanqueGasolina(int capacidadeDoTanque){
        if(capacidadeDoTanque == 0){
            System.out.println("Você não informou a capacidade do seu tanque");
        }
        else{
            System.out.println("O Valor total para encher o tanque do seu carro é: R$" + (precoGasolina*capacidadeDoTanque));
            System.out.println("O preço da Gasolina é: R$" + precoGasolina);
            System.out.println("O tamanho do seu tanque é de: " + capacidadeDoTanque + " litros");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        System.out.println("Digite a capacidade do tanque de combustivel >>>");
        int capacidadeDoTanque = scanner.nextInt();
        scanner.close();

        
        calcularValorTotalEncherTanqueGasolina(capacidadeDoTanque);
        
    }
}
