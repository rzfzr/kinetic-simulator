/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 *
 * @author a120127
 */
public class SaveFile {
    
    public static void main(String[]args) throws FileNotFoundException{
        WriteData();
    }
    public static void WriteData() throws FileNotFoundException{
        
        PrintWriter pw = new PrintWriter(new File("writeTeste.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("id");
        sb.append(',');
        sb.append("Name");
        sb.append('\n');

        sb.append("1");
        sb.append(',');
        sb.append(" Ghimire");
        sb.append('\n');

        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");
        
        
    }
    
    
    
}