/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
/**
 *
 * @author a120127
 */
public class SaveFile {
    
    public static void main(String[]args) throws FileNotFoundException{
    }
    public static  void WriteData(int dice,int sides,double theoric, Simulator.Data data) throws FileNotFoundException {
        
        PrintWriter pw = new PrintWriter(new File("UltimoSimuladoDados.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("Simulado de "+dice +" dados de "+sides+ " lados (P = "+theoric+").");
        sb.append("\n");
        sb.append("Horario e data: "+LocalDateTime.now());
        
//        sb.append(data.lm);
//        sb.append("\n");
        for(int i=0;i<data.lm.getSize();i++){
        sb.append("\n");
            sb.append(data.lm.elementAt(i));
            
        }
//        sb.append(data.s1.toArray());

        pw.write(sb.toString());
        pw.close();
        System.out.println("Saved!");
        
      }
    
    
    
}