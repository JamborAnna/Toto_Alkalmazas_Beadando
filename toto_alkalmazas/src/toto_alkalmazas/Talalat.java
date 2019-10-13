package toto_alkalmazas;
public class Talalat {
    private int nyertesekSzama;
    private int talalatok;
    private int nyeremeny;
    public Talalat(int nyertesekSzama,int talalatok,  int nyeremeny)
    {
        this.nyeremeny=nyeremeny;
        this.nyertesekSzama=nyertesekSzama;
        this.talalatok=talalatok;
    }
    public int getTalalatokSzama()
    {
    return this.talalatok;
    }
    public int gyetNyertesekSzama()
    {
        return this.nyertesekSzama;
    }
    public int getNyeremeny()
    {
        return this.nyeremeny;
    }
}
