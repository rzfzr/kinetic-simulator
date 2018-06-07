package simulator;

import simulator.SaveFile;
import org.jfree.chart.axis.NumberAxis;
import java.awt.BorderLayout;
import org.jfree.chart.plot.PlotOrientation;

import java.lang.Math;//for logs
import javax.swing.DefaultListModel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;

//import simulator.demos.periodicTable.*;
import javafx.util.Pair;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author rzfzr
 */
public class Screen extends javax.swing.JFrame implements ChartMouseListener {
//    GraphJPanel graph;\
//    static NewJPanel ne = new NewJPanel();

//    public List<Double> scores;
    public Screen() {
        initComponents();

        myInitComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    private ChartPanel chartPanel;
    private Crosshair xCrosshair;
    private Crosshair yCrosshair;

    Simulator sim;
    Simulator.Data data;

    public String[] HLValues;//data
    public String[] HLTypes;

    public void myInitComponents() {
        sim = new Simulator();

        int lines = 11;
        String path = "../acervo.csv";

        ComboType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Gramas", "Mols", "Unidades"}));
        ComboTypeTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Anos", "Meses", "Dias", "Horas", "Minutos"}));

        ComboTypeElement.setModel(new javax.swing.DefaultComboBoxModel<>(sim.getDataCSV(0, lines, path)));
        HLValues = sim.getDataCSV(1, lines, path);
        HLTypes = sim.getDataCSV(2, lines, path);

        InputTimeTemp.setEnabled(false);
        ComboTypeTime.setEnabled(false);
        jLabel18.setEnabled(false);

        LabelDiceCalculated.setText("Selecione 2");
//        CalculateSelected.setEnabled(false);

//        jList1.getSelectionModel().setSelectionInterval(0, 3);
//jList1.addSelectionInterval(0,2);
        jList1.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                int pos[] = jList1.getSelectedIndices();
                System.out.println("Selected: " + index0 + " and " + index1);//why 2 ?? dk
                if (super.isSelectedIndex(index0)) {//is selected so deselect
                    super.removeSelectionInterval(index0, index1);
                    LabelDiceCalculated.setText("Selecione 2");
                } else {
                    if (jList1.getSelectedValuesList().size() < 2) {
                        super.addSelectionInterval(index0, index1);
                        if (jList1.getSelectedValuesList().size() == 2) {
                            UpdateCalculatedDice();
                        }
                    } else {
                        if (index0 > pos[1]) {
                            super.removeSelectionInterval(pos[1], pos[1]);
                        } else if (index0 < pos[0]) {
                            super.removeSelectionInterval(pos[0], pos[0]);
                        } else { 
                            if (index0 - pos[0] < pos[1] - index0) {
                                super.removeSelectionInterval(pos[0], pos[0]);
                            } else {
                                super.removeSelectionInterval(pos[1], pos[1]);
                            }

                        }
                        super.addSelectionInterval(index0, index1);//after deleting one, add new

                        UpdateCalculatedDice();

                    }
                }
            }
        });
        jList3.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                int pos[] = jList3.getSelectedIndices();
                System.out.println("Selected: " + index0 + " and " + index1);//why 2 ?? dk
                if (super.isSelectedIndex(index0)) {//is selected so deselect
                    super.removeSelectionInterval(index0, index1);
                    LabelSimulatorCalculated.setText("Selecione 2");
                } else {
                    if (jList3.getSelectedValuesList().size() < 2) {
                        super.addSelectionInterval(index0, index1);
                        if (jList3.getSelectedValuesList().size() == 2) {
                            UpdateCalculatedSimulator();
                        }
                    } else {
                        if (index0 > pos[1]) {
                            super.removeSelectionInterval(pos[1], pos[1]);
                        } else if (index0 < pos[0]) {
                            super.removeSelectionInterval(pos[0], pos[0]);
                        } else { 
                            if (index0 - pos[0] < pos[1] - index0) {
                                super.removeSelectionInterval(pos[0], pos[0]);
                            } else {
                                super.removeSelectionInterval(pos[1], pos[1]);
                            }

                        }
                        super.addSelectionInterval(index0, index1);//after deleting one, add new

                        UpdateCalculatedSimulator();

                    }
                }
            }
        });
        
        
        
    }

    
    
    private void UpdateCalculatedDice() {
        int pos2[] = jList1.getSelectedIndices();
        List<String> ss = jList1.getSelectedValuesList();
        String[] ini = (data.lm.elementAt(pos2[0]).toString()).split("=");
        String[] fin = (data.lm.elementAt(pos2[1]).toString()).split("=");

        System.out.println("Selected Initial = " + ini[0] + " " + ini[1]);
        System.out.println("Selected Final = " + fin[0] + " " + fin[1]);

        int initialRoll = Integer.parseInt(ini[0]);
        int initialDice = Integer.parseInt(ini[1]);
        int finalRoll = Integer.parseInt(fin[0]);
        int finalDice = Integer.parseInt(fin[1]);
        
        System.out.println("teste"+ (2.4+2));
        
        LabelDiceCalculated.setText(Double.toString(sim.CalculateDiceSelected(initialRoll, finalRoll, initialDice, finalDice)));
    }
    
    
    
    private void UpdateCalculatedSimulator() {
        int pos2[] = jList3.getSelectedIndices();
        List<String> ss = jList3.getSelectedValuesList();
        String[] ini = (data.lm.elementAt(pos2[0]).toString()).split("=");
        String[] fin = (data.lm.elementAt(pos2[1]).toString()).split("=");

        System.out.println("Selected Initial = " + ini[0] + " " + ini[1]);
        System.out.println("Selected Final = " + fin[0] + " " + fin[1]);

        double initialRoll = Integer.parseInt(ini[0]);
        double initialDice = Integer.parseInt(ini[1]);
        double finalRoll = Integer.parseInt(fin[0]);
        double finalDice = Integer.parseInt(fin[1]);
        
        
        LabelSimulatorCalculated.setText(Double.toString(sim.CalculateSimulatorSelected(initialRoll, finalRoll, initialDice, finalDice)));
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        ComboType = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        InputQuantity = new javax.swing.JTextField();
        JButtonSimulate = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        GraphPanelSimulator = new javax.swing.JPanel();
        BetterScrollSimulator = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        value_p1 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        ComboTypeElement = new javax.swing.JComboBox<>();
        InputTimeTemp = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        ComboTypeTime = new javax.swing.JComboBox<>();
        jCheckBoxCustomElement = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        LabelSimulatorCalculated = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        InputDices = new javax.swing.JTextField();
        InputSides = new javax.swing.JTextField();
        jButtonRollDice = new javax.swing.JButton();
        jButtonClearDice = new javax.swing.JButton();
        GraphPanel = new javax.swing.JPanel();
        BetterScrollDice = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        value_p = new javax.swing.JLabel();
        SaveDice = new javax.swing.JButton();
        LoadDice = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        LabelDiceCalculated = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        ComboType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboTypeActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Decaimento");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Quantidade de");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Elemento");

        InputQuantity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        InputQuantity.setText("100");
        InputQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputQuantityActionPerformed(evt);
            }
        });

        JButtonSimulate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JButtonSimulate.setText("Simular");
        JButtonSimulate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonSimulateActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(204, 0, 0));
        jButton5.setText("Limpar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        GraphPanelSimulator.setBackground(new java.awt.Color(255, 255, 204));
        GraphPanelSimulator.setLayout(new java.awt.BorderLayout());

        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        BetterScrollSimulator.setViewportView(jList3);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Meia Vida\n");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Teórica (P)");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("P = ");

        value_p1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton6.setText("Salvar Resultados");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        ComboTypeElement.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboTypeElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboTypeElementActionPerformed(evt);
            }
        });

        InputTimeTemp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        InputTimeTemp.setText("15");
        InputTimeTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputTimeTempActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Meia Vida");

        ComboTypeTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCheckBoxCustomElement.setText("Elemento Customizado");
        jCheckBoxCustomElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCustomElementActionPerformed(evt);
            }
        });

        jButton1.setText("Tabela Periódica");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Meia Vida Calculada:");

        LabelSimulatorCalculated.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(56, 56, 56)
                                .addComponent(ComboTypeElement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(value_p1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(JButtonSimulate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(InputQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(InputTimeTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboTypeTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCheckBoxCustomElement)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton1)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(GraphPanelSimulator, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(BetterScrollSimulator)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelSimulatorCalculated, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GraphPanelSimulator, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(ComboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(ComboTypeElement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxCustomElement)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(InputTimeTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboTypeTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(value_p1))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(JButtonSimulate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6))
                            .addComponent(jButton5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BetterScrollSimulator)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19)
                    .addComponent(LabelSimulatorCalculated, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Meia Vida Quimica", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setMaximumSize(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Decaimento");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Quantidade de Dados");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Lados");

        InputDices.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        InputDices.setText("100");
        InputDices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputDicesActionPerformed(evt);
            }
        });

        InputSides.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        InputSides.setText("6");

        jButtonRollDice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonRollDice.setText("Jogar");
        jButtonRollDice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRollDiceActionPerformed(evt);
            }
        });

        jButtonClearDice.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jButtonClearDice.setForeground(new java.awt.Color(204, 0, 0));
        jButtonClearDice.setText("Limpar");
        jButtonClearDice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearDiceActionPerformed(evt);
            }
        });

        GraphPanel.setBackground(new java.awt.Color(255, 255, 204));
        GraphPanel.setLayout(new java.awt.BorderLayout());

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        BetterScrollDice.setViewportView(jList1);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Meia Vida\n");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Teórica (P)");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("P = ");

        value_p.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        SaveDice.setText("Salvar Resultados");
        SaveDice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveDiceActionPerformed(evt);
            }
        });

        LoadDice.setText("Carregar  Resultados");
        LoadDice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadDiceActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Meia Vida Calculada:");

        LabelDiceCalculated.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(LoadDice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(InputSides, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(InputDices, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SaveDice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jButtonRollDice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonClearDice, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(value_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GraphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(BetterScrollDice, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(LabelDiceCalculated, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(GraphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputDices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputSides, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(value_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonRollDice)
                            .addComponent(jButtonClearDice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveDice))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BetterScrollDice)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LoadDice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11))
                    .addComponent(LabelDiceCalculated, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Meia Vida Química");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoadDiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadDiceActionPerformed

        Simulator.DataReadWriteCapsule cap = SaveFile.ReadData();
        data = cap.data;
        dice = cap.dice;
        sides = cap.sides;
        theoric = cap.theoric;

        System.out.println("Loaded " + cap.dice + " " + cap.sides + " sided dice");
        SimulateGlobalDice();
    }//GEN-LAST:event_LoadDiceActionPerformed

    private void SaveDiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveDiceActionPerformed

        try {

            SaveFile sv = new SaveFile();

            Simulator.DataReadWriteCapsule cap
                    = sim.new DataReadWriteCapsule(dice, sides, theoric, data);
            sv.WriteData(cap);

            // TODO add your handling code here:
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SaveDiceActionPerformed

    private void jButtonClearDiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearDiceActionPerformed
        ClearDiceScreen();
    }//GEN-LAST:event_jButtonClearDiceActionPerformed

    private void jButtonRollDiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRollDiceActionPerformed

        dice = Integer.valueOf(InputDices.getText()); //number of dice
        sides = Integer.valueOf(InputSides.getText()); //number of sides
        theoric = Math.log10(2) / Math.log10((double) sides / ((double) sides - 1)); //meia vida teorica
        data = sim.CalculateDice(dice, sides);

        System.out.println("Rolled " + dice + " " + sides + " sided dice");
        SimulateGlobalDice();
    }//GEN-LAST:event_jButtonRollDiceActionPerformed

    private void InputDicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputDicesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputDicesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFrame janela = new PeriodicTableFrame(this);

        janela.setVisible(true);
        //janela.setLocationRelativeTo(null);
        //janela.setExtendedState(janela.getExtendedState()|JFrame. );
        janela.setSize(1500, 700);
        janela.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBoxCustomElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCustomElementActionPerformed
        MyCustomElementCheckboxChecker();
    }//GEN-LAST:event_jCheckBoxCustomElementActionPerformed

    private void MyCustomElementCheckboxChecker() {
        if (jCheckBoxCustomElement.isSelected()) {
            System.out.println("Checked custom element");
            InputTimeTemp.setEnabled(true);
            ComboTypeTime.setEnabled(true);
            jLabel18.setEnabled(true);

            jLabel14.setEnabled(false);
            ComboTypeElement.setEnabled(false);

        } else {
            System.out.println("Unchecked custom element");
            InputTimeTemp.setEnabled(false);
            ComboTypeTime.setEnabled(false);
            jLabel18.setEnabled(false);

            jLabel14.setEnabled(true);
            ComboTypeElement.setEnabled(true);
        }
    }
    private void InputTimeTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputTimeTempActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputTimeTempActionPerformed

    private void ComboTypeElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTypeElementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboTypeElementActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        ClearSimulateScreen();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void JButtonSimulateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonSimulateActionPerformed
        //Simular

        int quantity = Integer.valueOf(InputQuantity.getText()); //quantity of grams/molecule etc
        float time;//half life time

        System.out.println("Simulated " + quantity + " " + ComboType.getItemAt(ComboType.getSelectedIndex()) + " of ");

        if (jCheckBoxCustomElement.isSelected()) {//get custom element from user
            time = Float.valueOf(InputTimeTemp.getText());
            System.out.println("Elemento Customizado" + " (MV de " + time + " " + ComboTypeTime.getItemAt(ComboTypeTime.getSelectedIndex()) + ")");
        } else {//get choosen element from csv file
            time = Float.valueOf(HLValues[ComboTypeElement.getSelectedIndex()]);

            System.out.println(ComboTypeElement.getItemAt(ComboTypeElement.getSelectedIndex()) + " (MV de "
                    + HLValues[ComboTypeElement.getSelectedIndex()] + " " + HLTypes[ComboTypeElement.getSelectedIndex()] + ")");

        }
        CalculatedHalfLife(time);
        boolean isUnd = false;

//        System.out.println(ComboType.getItemAt(ComboType.getSelectedIndex()));
        if (ComboType.getItemAt(ComboType.getSelectedIndex()) == "Unidades") {
            System.out.println("isUnd");
            isUnd = true;

        }

        data = sim.CalculateChemical(quantity, time, isUnd);
        GraphIt(GraphPanelSimulator, data.s1);//plot all the points
        jList3.setModel(data.lm);//after this the list is updated when we add or remove to the model
    }//GEN-LAST:event_JButtonSimulateActionPerformed

    private void InputQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputQuantityActionPerformed

    private void ComboTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboTypeActionPerformed

    void ClearDiceScreen() {
        // Clear everything
        value_p.setText(" ");

        GraphPanel.removeAll();
        GraphPanel.validate();

        DefaultListModel lm;  //empty model to the list
        lm = new DefaultListModel();
        jList1.setModel(lm);

        System.out.println("Cleared Dice Screen");
    }

    void ClearSimulateScreen() {
        // Clear everything
        value_p1.setText(" ");

        GraphPanelSimulator.removeAll();
        GraphPanelSimulator.validate();

        DefaultListModel lm;  //empty model to the list
        lm = new DefaultListModel();
        jList3.setModel(lm);

        System.out.println("Cleared Simulator Screen");
    }

    int dice;
    int sides;

    double theoric;

    public void SetPeriodicElement(String name, int isotope,
            int exponential, float seconds) {
        ClearSimulateScreen();

        InputTimeTemp.setText(String.valueOf(seconds));

        jCheckBoxCustomElement.setSelected(true);
        MyCustomElementCheckboxChecker();

    }

    private void SimulateGlobalDice() {

        InputDices.setText(Integer.toString(dice)); //number of dice
        InputSides.setText(Integer.toString(sides)); //number of sides
        value_p.setText(Double.toString(theoric));

        GraphIt(GraphPanel, data.s1);//plot all the points
        jList1.setModel(data.lm);//after this the list is updated when we add or remove to the model

    }

    private void GraphIt(JPanel panel, XYSeries s) {//settings to mess with the graph styling

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s);
        if (panel == GraphPanel) {
            final JFreeChart chart = ChartFactory.createXYLineChart(
                    "Dados", // chart title
                    "Category", // domain axis label
                    "Value", // range axis label
                    dataset, // data
                    PlotOrientation.VERTICAL,
                    true, // include legend
                    true,
                    false
            );

            final XYPlot plot = chart.getXYPlot();
            final NumberAxis domainAxis = new NumberAxis("Jogadas");
            final NumberAxis rangeAxis = new NumberAxis("Dados Restante");

            plot.setDomainAxis(domainAxis);
            plot.setRangeAxis(rangeAxis);
            chart.setBackgroundPaint(Color.white);
            plot.setOutlinePaint(Color.black);

            rangeAxis.setAutoRangeIncludesZero(false);
            plot.setOrientation(PlotOrientation.VERTICAL);
//        final ChartPanel
            chartPanel = new ChartPanel(chart);

        } else if (panel == GraphPanelSimulator) {

            final JFreeChart chart = ChartFactory.createXYLineChart(
                    "Quantidade", // chart title
                    "Category", // domain axis label
                    "Value", // range axis label
                    dataset, // data
                    PlotOrientation.VERTICAL,
                    true, // include legend
                    true,
                    false
            );

            final XYPlot plot = chart.getXYPlot();

            NumberAxis domainAxis;

            if (jCheckBoxCustomElement.isSelected()) {
                domainAxis = new NumberAxis(ComboTypeTime.getItemAt(ComboTypeTime.getSelectedIndex()));//TODO: make dinamic, years/hours/minutes
            } else {
                domainAxis = new NumberAxis(HLTypes[ComboTypeElement.getSelectedIndex()]);//TODO: make dinamic, years/hours/minutes

            }

            final NumberAxis rangeAxis = new NumberAxis("Quantidade Restante");

            plot.setDomainAxis(domainAxis);
            plot.setRangeAxis(rangeAxis);
            chart.setBackgroundPaint(Color.white);
            plot.setOutlinePaint(Color.black);

            rangeAxis.setAutoRangeIncludesZero(false);
            plot.setOrientation(PlotOrientation.VERTICAL);
//        final ChartPanel
            chartPanel = new ChartPanel(chart);

        }

        panel.removeAll();
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.validate();

        //TODO: clean this up, its just a copy from http://stackoverflow.com/questions/21172794/jfreechart-dispay-mouse-coordinates-near-to-mouse-as-hints-on-mouse-move
        //TODO: round rolls to next int (snap if possible)
        //TODO: select item in list by crosshair
        chartPanel.addChartMouseListener(this);
        CrosshairOverlay crosshairOverlay = new CrosshairOverlay();
        this.xCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0f));
        this.xCrosshair.setLabelVisible(true);
        this.yCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0f));
        this.yCrosshair.setLabelVisible(true);
        crosshairOverlay.addDomainCrosshair(xCrosshair);
        crosshairOverlay.addRangeCrosshair(yCrosshair);
        chartPanel.addOverlay(crosshairOverlay);
    }
    //CalculateDialog calcDialog;

    private void CalculatedHalfLife(float time) {

        double theoric = Math.log10(2) / Math.log10((double) time / ((double) time - 1));
        value_p1.setText(Double.toString(theoric));

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Screen().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane BetterScrollDice;
    private javax.swing.JScrollPane BetterScrollSimulator;
    private javax.swing.JComboBox<String> ComboType;
    private javax.swing.JComboBox<String> ComboTypeElement;
    private javax.swing.JComboBox<String> ComboTypeTime;
    private javax.swing.JPanel GraphPanel;
    private javax.swing.JPanel GraphPanelSimulator;
    private javax.swing.JTextField InputDices;
    private javax.swing.JTextField InputQuantity;
    private javax.swing.JTextField InputSides;
    private javax.swing.JTextField InputTimeTemp;
    private javax.swing.JButton JButtonSimulate;
    private javax.swing.JLabel LabelDiceCalculated;
    private javax.swing.JLabel LabelSimulatorCalculated;
    private javax.swing.JButton LoadDice;
    private javax.swing.JButton SaveDice;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButtonClearDice;
    private javax.swing.JButton jButtonRollDice;
    private javax.swing.JCheckBox jCheckBoxCustomElement;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel value_p;
    private javax.swing.JLabel value_p1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void chartMouseClicked(ChartMouseEvent cme) {
        System.out.println("Click not supported yet, should choose the data in the table instead");
    }

    @Override
    public void chartMouseMoved(ChartMouseEvent event) {//keep track of mouse for X and Y guides
        Rectangle2D dataArea = this.chartPanel.getScreenDataArea();
        JFreeChart chart = event.getChart();
        XYPlot plot = (XYPlot) chart.getPlot();
        ValueAxis xAxis = plot.getDomainAxis();
        double x = xAxis.java2DToValue(event.getTrigger().getX(), dataArea,
                RectangleEdge.BOTTOM);
        double y = DatasetUtilities.findYValue(plot.getDataset(), 0, x);
        this.xCrosshair.setValue(x);
        this.yCrosshair.setValue(y);
    }

}
