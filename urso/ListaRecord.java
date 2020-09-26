package urso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListaRecord {                                                                             
    
    public final String path = "C:\\Users\\Pedro\\Desktop\\teste\\teste.txt";
    private String vetRecord[];
    
    public ListaRecord(){
        try {
            this.vetRecord = this.carregarRecords();
        } catch (IOException ioe) {
            Logger.getLogger(ListaRecord.class.getName()).log(Level.SEVERE, null, ioe);
        }
    }
    
    private String[] carregarRecords() throws IOException{
        String retorno[] = new String[10];
        this.preencerVetor(retorno);
        int i = 0;
        
        File save = new File(this.path);
        
        if(!save.exists()){
            save.createNewFile();
            return retorno;
        }
        
        FileReader fr = new FileReader(save);
        BufferedReader br = new BufferedReader(fr);
        
        while(br.ready()){
            retorno[i] = br.readLine();
            i++;
        }
        fr.close();
        br.close();
        
        return retorno;
    }
    
    public void Save() throws IOException{
        File save = new File(this.path);

        FileWriter fw = new FileWriter(save);
        BufferedWriter bw = new BufferedWriter(fw);
        
        for(int i = 0; i < this.vetRecord.length; i++){
            bw.write(vetRecord[i]);
            bw.newLine();
        }
        
        bw.close();
        fw.close();
        
    }    
    
    public String getRecord(int i){
        return vetRecord[i];
    }
    
    public void setRecord(int i, String record){
        this.vetRecord[i] = record;
    }
    
    public int getRecordInt(int i){
        return Integer.parseInt(vetRecord[i].split("-")[1]);
    }
    
    
 @Override
    public String toString(){
        int cont = 0;
        int i;
        for(i = 0; i < vetRecord.length; i++){
            if(!vetRecord[i].equals("")){
                cont++;
            }
        }
        String retorno = "";
        for(i = 0; i < cont; i++){
            retorno = retorno + vetRecord[i] + "00\n";
        }
        return retorno;
    }
    
    private void preencerVetor(String[] vetor){
        for(int i = 0; i < vetor.length; i++){
            vetor[i] = "";
        }
    }
}

