package toto_alkalmazas;
import java.io.IOException;
import java.util.Scanner;
import szolgaltatas.Toto_szoldaltatas;
public class Toto_alkalmazas {
    public static void main(String[] args) throws IOException {
       Toto_szoldaltatas t = new Toto_szoldaltatas("toto.csv");
       System.out.println(t.getmaximumNyeremeny() + " Ft");
        double sum = t.getEgyesGyozelem() + t.getKettesGyozelem() + t.getDontetlen();
        double avgElso = t.getEgyesGyozelem() / sum * 100;
        double avgMasik = t.getKettesGyozelem() / sum * 100;
        double avgDontetlen = t.getDontetlen() / sum * 100;
        System.out.println("Statisztika: #1 csapat nyert: " + Math.round(avgElso * 100.0) / 100.0 + " %, #2 csapat nyert: " 
                            + Math.round(avgMasik * 100.0) / 100.0 + " %, döntetlen: " 
                            + Math.round(avgDontetlen * 100.0) / 100.0 + " %");
        Scanner sc = new Scanner(System.in);
        System.out.print("Kérem adjon meg egy dátumot (YYYY.MM.DD.): ");
        String datum = sc.next();
        if (t.getvanEilyenDatum(datum) == false)
        {
            System.out.println("Ezen a napon nem volt játék!");
        }
        else
        {
            String tipp = "";
            do
            {
                System.out.print("Kérem adjon meg egy tippet (14 szám,amely 1, 2 vagy X): ");
                tipp = sc.next();
                if (tipp.length() != 14)
                {
                    System.out.println("14 tippnek kell lennie. Próbálkozzon újra!");
                }
            }
            while(tipp.length() != 14);
            t.geteredmeny(datum, tipp);
        } 
    }  
}
