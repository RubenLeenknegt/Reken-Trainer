package rekentrainer;

public class Moeilijkheidsgraad{
    public boolean optellen;
    public boolean aftrekken;
    public boolean delen;
    public boolean vermenigvuldigen;
    
    public int mingetalopaf;
    public int maxgetalopaf;
    
    public int mingetaldelver;
    public int maxgetaldelver;
    
    public Moeilijkheidsgraad(boolean optellen,boolean aftrekken,boolean delen,boolean vermenigvuldigen,int mingetalopaf,int maxgetalopaf,int mingetaldelver,int maxgetaldelver){
        this.optellen = optellen;
        this.aftrekken = aftrekken;
        this.delen = delen;
        this.vermenigvuldigen = vermenigvuldigen;
        
        this.mingetalopaf = mingetalopaf;
        this.maxgetalopaf = maxgetalopaf;
        
        this.mingetaldelver = mingetaldelver;
        this.maxgetaldelver = maxgetaldelver;
    }
}