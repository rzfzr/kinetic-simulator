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
import java.util.Scanner;
import javafx.util.Pair;
import javax.swing.DefaultListModel;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author a120127
 */
public class SaveFile {

    public static void main(String[] args) throws FileNotFoundException {
    }

    static final int DICE = 1;
    static final int SIDES = 3;
    static final int THEORIC = 5;
    static final int FIRSTROLL = 9;
//    static final int DATA

    public static void WriteData(Simulator.DataReadWriteCapsule cap) throws FileNotFoundException {

        PrintWriter pw = new PrintWriter(new File("UltimoSimuladoDados.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("Dados:\n" + cap.dice + "\nlados:\n" + cap.sides + "\nP:\n" + cap.theoric + "\n");
        sb.append("Horario e data:\n" + LocalDateTime.now());
        sb.append("\n");

//        sb.append(data.lm);
//        sb.append("\n");
        for (int i = 0; i < cap.data.lm.getSize(); i++) {
            sb.append("\n");
            sb.append(cap.data.lm.elementAt(i));

        }
//        sb.append(data.s1.toArray());

        pw.write(sb.toString());
        pw.close();
        System.out.println("Saved!");

    }

    public static Simulator.DataReadWriteCapsule ReadData() {

        Simulator sim = new Simulator();
        //File directory = new File("./");//System.out.println(directory.getAbsolutePath());
        File csv = new File("UltimoSimuladoDados.csv");

        //String[] arr= new String[10];//TODO: make dinamic
        int i = 0;
        int dice = 0, sides = 0;
        double theoric = 0;

        final XYSeries s1 = new XYSeries("Decaimento");
        DefaultListModel lm;
        lm = new DefaultListModel();

        try {
            Scanner scan = new Scanner(csv);
            String line = new String();

            while (scan.hasNext()) {
                line = scan.nextLine();
                System.out.println("Line " + i + " : " + line);

                if (i == DICE) {
                    dice = Integer.parseInt(line);
                } else if (i == SIDES) {
                    sides = Integer.parseInt(line);
                } else if (i == THEORIC) {
                    theoric = Double.parseDouble(line);
                } else if (i >= FIRSTROLL) {
                    String[] temp = line.split("=");
                    s1.add(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));

                    lm.addElement(new Pair(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
                }

                i++;
            }
            System.out.println("read");

        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found");

        }

        Simulator.Data data = sim.new Data(s1, lm);

        return sim.new DataReadWriteCapsule(dice, sides, theoric, data);

    }

}
