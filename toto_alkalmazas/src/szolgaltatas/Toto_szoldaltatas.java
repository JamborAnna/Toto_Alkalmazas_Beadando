package szolgaltatas;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import toto_alkalmazas.Eredmeny;
import toto_alkalmazas.Fordulo;

public class Toto_szoldaltatas {
    private List<Fordulo>fordulokListaja;
    public Toto_szoldaltatas(String file) throws IOException
        {
        this.fordulokListaja = new ArrayList<>();
        try
        {
            FileReader beolvas=new FileReader("fajl");
            BufferedReader buffer= new BufferedReader(beolvas);
            String sor= buffer.readLine();
            while(sor!=null)
            {
                this.fordulokListaja.add(new Fordulo(sor));
                sor=buffer.readLine();
            }
            beolvas.close();
            buffer.close();
        } 
        catch(FileNotFoundException e)
        {
            System.out.println("A fájl nem található.");
        }
    }
    public int getmaximumNyeremeny()
    {
    int maximum=0;
        for (int i = 0; i < this.fordulokListaja.size(); i++) {
            if (maximum<this.fordulokListaja.get(i).getMaxNyeremeny()) {
                maximum=this.fordulokListaja.get(i).getMaxNyeremeny(); 
            } 
        }
        return maximum;
    }
    public int getEgyesGyozelem()
    {
        int sum=0;
        for (int i = 0; i < this.fordulokListaja.size(); i++) {
                for (int j = 0; j < this.fordulokListaja.get(i).getEredmenyek().size(); j++) {
                    if(this.fordulokListaja.get(i).getEredmenyek().get(j).equals(Eredmeny._1)) {
                        sum++;
                    }
                }
            }
        return sum;
    }
    public int getKettesGyozelem()
    {
        int sum = 0;
        for (int i = 0; i < this.fordulokListaja.size(); i++)
        {
            for (int j = 0; j < this.fordulokListaja.get(i).getEredmenyek().size(); j++)
            {
                if (this.fordulokListaja.get(i).getEredmenyek().get(j).equals(Eredmeny._2))
                {
                    sum++;
                }
            }
        }
        return sum;
    }
    public int getDontetlen()
    {
        int sum = 0;
        for (int i = 0; i < this.fordulokListaja.size(); i++)
        {
            for (int j = 0; j < this.fordulokListaja.get(i).getEredmenyek().size(); j++)
            {
                if (this.fordulokListaja.get(i).getEredmenyek().get(j).equals(Eredmeny.X))
                {
                    sum++;
                }
            }
        }
        return sum;
    }
     public boolean getvanEilyenDatum(String segedDatum)
    {
        segedDatum = segedDatum.replace(".", "-");
        String[] datumok = segedDatum.split("-");
        LocalDate datum = LocalDate.of(Integer.parseInt(datumok[0]), Integer.parseInt(datumok[1]), Integer.parseInt(datumok[2]));
        boolean van = false;
        for (int i = 0; i < this.fordulokListaja.size(); i++)
        {
            if (this.fordulokListaja.get(i).getDatum().equals(datum))
            {
                van = true;
            }
        }
        return van;
    }
     public void geteredmeny(String segedDatum,String tipp){
     segedDatum = segedDatum.replace(".", "-");
        String[] datumok = segedDatum.split("-");
        LocalDate datum = LocalDate.of(Integer.parseInt(datumok[0]), Integer.parseInt(datumok[1]), Integer.parseInt(datumok[2]));
        tipp = tipp.toUpperCase();
        String[] tippek = tipp.split("");
        List<Eredmeny> tipLista = new ArrayList<>();
        for (int i = 0; i < tippek.length; i++)
        {
            switch (tippek[i])
            {
                case "1": tipLista.add(Eredmeny._1); break;
                case "2": tipLista.add(Eredmeny._2); break;
                case "X": tipLista.add(Eredmeny.X); break;
            }
        }
        int talalat = 0;
        int nyeremeny = 0;
        boolean voltJatek = false;
        for (int i = 0; i < this.fordulokListaja.size(); i++)
        {
            if (this.fordulokListaja.get(i).getDatum().equals(datum))
            {
                voltJatek = true;
                for (int j = 0; j < this.fordulokListaja.get(i).getEredmenyek().size(); j++)
                {
                    if (this.fordulokListaja.get(i).getEredmenyek().get(j) == tipLista.get(j))
                    {
                        talalat++;
                    }
                }
                if (talalat >= 10)
                {
                    for (int j = 0; j < this.fordulokListaja.get(i).getTalalatok().size(); j++)
                    {
                        if (this.fordulokListaja.get(i).getTalalatok().get(j).getTalalatokSzama() == talalat)
                        {
                            nyeremeny = this.fordulokListaja.get(i).getTalalatok().get(j).getNyeremeny();
                        }
                    }
                }
                else
                {
                    nyeremeny = 0;
                }
            }
        }
        if (voltJatek == false)
        {
            System.out.println("Nem volt ezen a napon totó.");
        }
        else
        {
            System.out.printf("Eredmény: találat: %d, nyeremeny: %d Ft", talalat, nyeremeny);
        }
     }
    
}
