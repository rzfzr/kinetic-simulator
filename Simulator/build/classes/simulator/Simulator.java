/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import javafx.util.Pair;
import javax.swing.DefaultListModel;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author rzfzr
 */
public class Simulator {

//    public enum SimulationType{
//        DICE,CHEM
//    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public class Data {

        XYSeries s1;
        DefaultListModel lm;

        Data(XYSeries s1, DefaultListModel lm) {
            this.s1 = s1;
            this.lm = lm;//actual pair of values "roll=remaining"
        }

    }

    public class DataReadWriteCapsule {
//        SimulationType type;

        Data data;
        int dice;
        int sides;
        double theoric;

        DataReadWriteCapsule(int dice, int sides, double theoric, Data data) {
            //constructor for type dice
            this.dice = dice;
            this.sides = sides;
            this.theoric = theoric;
            this.data = data;
        }
//
//        public DataReadWriteCapsule() {
//            //constructor for chem
//
//        }

    }

    public String[] getDataCSV(int col, int lines, String filePath) {

        //File directory = new File("./");//System.out.println(directory.getAbsolutePath());
        File csv = new File(filePath);

        int i = 0;
        String[] arr = new String[lines];//TODO: make dinamic

        try {
            Scanner scan = new Scanner(csv);
            String line = new String();
            scan.nextLine();//ignores first line

            while (scan.hasNext()) {
                line = scan.nextLine();
                String[] item = line.split(",");
                arr[i] = item[col];
                i++;//item address is the column
//                System.out.println(item[1]);
            }
            return arr;
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found");
            return null;
        }

    }

    public Data CalculateDice(int dice, float sides) {

        final XYSeries s1 = new XYSeries("Decaimento"); //line to plot
        int roll = 0;   //current roll
        int rolled; //selected dice per roll

        DefaultListModel lm;  //needed for list
        lm = new DefaultListModel();

        //TODO: use hashMap as int is unique, or substitute '=' with correct number of spaces
        lm.addElement(new Pair(0, dice)); //inicial point
        s1.add(0, dice);
        while (dice > 0) {// if there are still dice
            roll++;
            rolled = 0;
            while (rolled < dice) {//roll all the remaining dice
                rolled++;//minus one to roll
                int rand = ThreadLocalRandom.current().nextInt(1, (int) (sides + 1));//rand number between 1 and the number of sides of the dice

                if (rand == 1) {//if random number == 1 then
                    dice--;//take one die away
                }

            }

            lm.addElement(new Pair(roll, rolled));//add to the list
            s1.add(roll, rolled);//add to the chart (x,y)

        }

        Data data = new Data(s1, lm);

        return data;
    }

    public Double CalculateSelected(int initialRoll, int finalRoll,
            int initialDice, int finalDice) {
        return (double) ((double) (finalRoll - initialRoll) * (double) Math.log10(2))
                / (double) (Math.log10((double) initialDice / finalDice));
    }

}
