package toto_alkalmazas;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Fordulo {
    private int ev;
    private int het;
    private int forduloAHeten;
    private LocalDate datum;
    private List<Talalat>talalatokLista;
    private List<Eredmeny>eredmenyekLista;
    public Fordulo(String sor)
    {
        String[] adatok=sor.split(";");
        this.ev=Integer.parseInt(adatok[0]);
        this.het=Integer.parseInt(adatok[1]);
        if (adatok[2].equals("")) {
            this.forduloAHeten=1;
        }
        else
        {
            this.forduloAHeten=Integer.parseInt(adatok[2]);
            
        }
        if(adatok[3].equals(""))
        {
            int napok= this.het*7;
            if (this.ev%4==0&&napok>366) {
                napok-=366;
                this.ev++;
            }
            else if(napok>365)
            {
                napok-=365;
                        this.ev++;
            }
            LocalDate segedDatum= LocalDate.ofYearDay(this.ev,napok);
             switch(this.forduloAHeten) 
             {
                case 1: this.datum = segedDatum.with(DayOfWeek.MONDAY); break;
                case 2: this.datum = segedDatum.with(DayOfWeek.TUESDAY); break;
                case 3: this.datum = segedDatum.with(DayOfWeek.WEDNESDAY); break;
                case 4: this.datum = segedDatum.with(DayOfWeek.THURSDAY); break;
                case 5: this.datum = segedDatum.with(DayOfWeek.FRIDAY); break;
                case 6: this.datum = segedDatum.with(DayOfWeek.SATURDAY); break;
                case 7: this.datum = segedDatum.with(DayOfWeek.SUNDAY); break;
             }
        }
        else
        {
            adatok[3]=adatok[3].replace(".", ";");
            String[] datum= adatok[3].split(";");
            this.datum=LocalDate.of(Integer.parseInt(datum[0]),Integer.parseInt(datum[1]),Integer.parseInt(datum[2]));
            
            
        } 
        this.talalatokLista = new ArrayList<>();
        int index = 4;
        int talalatokSzama = 14;
        do
        {
            String str = adatok[index + 1];
            str = str.replace(" ", "");
            int nyeremeny = Integer.parseInt(str.substring(0, str.length() - 2));
            this.talalatokLista.add(new Talalat(talalatokSzama, Integer.parseInt(adatok[index]), nyeremeny));
            index += 2;
            talalatokSzama--;
        }
        while (index < 14);
        
        this.eredmenyekLista = new ArrayList<>();
        for (int i = 14; i < adatok.length; i++)
        {
            switch (adatok[i])
            {   
                case "1": this.eredmenyekLista.add(Eredmeny._1); break;
                case "+1": this.eredmenyekLista.add(Eredmeny._1); break;
                case "2": this.eredmenyekLista.add(Eredmeny._2); break;
                case "+2": this.eredmenyekLista.add(Eredmeny._2); break;
                case "X": this.eredmenyekLista.add(Eredmeny.X); break;
                case "+X": this.eredmenyekLista.add(Eredmeny.X); break;
            }
        }
    }

    public int getEv() {
        return this.ev;
    }
    public void setEv(int ev) {
        this.ev = ev;
    }
    public int getHet() {
        return this.het;
    }
    public void setHet(int het) {
        this.het = het;
    }
    public int getForduloAHeten() {
        return this.forduloAHeten;
    }
    public void setForduloAHeten(int forduloAHeten) {
        this.forduloAHeten = forduloAHeten;
    }
    public LocalDate getDatum() {
        return this.datum;
    }
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
    public List<Talalat> getTalalatok()
    {
        return this.talalatokLista;
    }
    public void setTalalatok(List<Talalat> talalatLista)
    {
        this.talalatokLista = talalatLista;
    }
    public List<Eredmeny> getEredmenyek()
    {
        return this.eredmenyekLista;
    }
    public void setEredmenyek(List<Eredmeny> eredmenyekLista)
    {
        this.eredmenyekLista = eredmenyekLista;
    }

    public int getMaxNyeremeny()
    {
        int max = 0;
        for (int i = 0; i < this.talalatokLista.size(); i++)
        {
            if (max < this.talalatokLista.get(i).getNyeremeny())
            {
                max = this.talalatokLista.get(i).getNyeremeny();
            }
        }
        return max;
    }
}
