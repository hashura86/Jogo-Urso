package urso;

import objetos.Fruta;
import java.security.SecureRandom; 
import objetos.FrutaPodre;
        
public class SpawnFrutas {
    
    public static Fruta spawnFruta(){
        SecureRandom r = new SecureRandom();
        int n1 = r.nextInt(751);
        return new Fruta(n1,0);    
    }
    
    public static FrutaPodre spawnFrutaPodre(){
        SecureRandom r = new SecureRandom();
        int n1 = r.nextInt(751);
        return new FrutaPodre(n1,0);    
    }
}
