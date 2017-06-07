package trash;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class csv {    
    
    
    public static void main(String[] args) {        
        
        File directory = new File("./");
        //System.out.println(directory.getAbsolutePath());
            
       File arquivoCSV = new File("../acervo.csv");
        
        try{
            Scanner scanner = new Scanner(arquivoCSV);
            String line = new String();            
            scanner.nextLine();//ignores first line
            
            while(scanner.hasNext()){
                line = scanner.nextLine();
                String[] item = line.split(",");
                System.out.println(item[1]);
            }
        
        }catch(FileNotFoundException e){            
                System.out.println("CSV file not found");
        }
        
    }
    
}
