/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

/**
 *
 * @author rzfzr
 */
import java.awt.Font;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class CombinedXYPlotDemo1 extends ApplicationFrame {

    public CombinedXYPlotDemo1(final String title) {

        super(title);
        final JFreeChart chart = createCombinedChart();
        final ChartPanel panel = new ChartPanel(chart, true, true, true, false, true);
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(panel);

    }

    private JFreeChart createCombinedChart() {


        // create subplot 2...
        final XYDataset data2 = createDataset2();
        final XYItemRenderer renderer2 = new StandardXYItemRenderer();
        final NumberAxis rangeAxis2 = new NumberAxis("Range 2");
        rangeAxis2.setAutoRangeIncludesZero(false);
        final XYPlot subplot2 = new XYPlot(data2, null, rangeAxis2, renderer2);
        subplot2.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);

        // parent plot...
        final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new NumberAxis("Domain"));
        plot.setGap(10.0);
        
        // add the subplots...
        plot.add(subplot2, 1);
        plot.setOrientation(PlotOrientation.VERTICAL);

        // return a new chart containing the overlaid plot...
        return new JFreeChart("CombinedDomainXYPlot Demo",
                              JFreeChart.DEFAULT_TITLE_FONT, plot, true);

    }


    /**
     * Creates a sample dataset.
     *
     * @return Series 2.
     */
    private XYDataset createDataset2() {

        // create dataset 2...
        final XYSeries series2 = new XYSeries("Dados");

//        series2.add(10.0, 16853.2);
//        series2.add(20.0, 19642.3);
//        series2.add(30.0, 18253.5);
//        series2.add(40.0, 15352.3);
//        series2.add(50.0, 13532.0);
//        series2.add(100.0, 12635.3);
//        series2.add(110.0, 13998.2);
//        series2.add(120.0, 11943.2);
//        series2.add(130.0, 16943.9);
//        series2.add(140.0, 17843.2);
//        series2.add(150.0, 16495.3);
//        series2.add(160.0, 17943.6);
//        series2.add(170.0, 18500.7);
//        series2.add(180.0, 19595.9);
//        
        
                         for (int i = 1; i <= 50; i++) {
            series2.add(i, 10 * Math.exp(i / 5.0));
        }

        return new XYSeriesCollection(series2);

    }

    public static void main(final String[] args) {

        final CombinedXYPlotDemo1 demo = new CombinedXYPlotDemo1("Dadoss");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
