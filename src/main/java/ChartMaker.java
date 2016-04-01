import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by madavis on 3/25/16.
 */
public class ChartMaker extends ApplicationFrame {

    JSONArray myData;

    public ChartMaker(String title, JSONArray inboundData) {
        super(title);
        myData = inboundData;
        JFreeChart lineChart = ChartFactory.createLineChart(title, "Date", "Snow in mm", createDataset(), PlotOrientation.VERTICAL, true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        JSONObject data;
        for (int i = 0; i < myData.length(); i++) {
            data = myData.getJSONObject(i);
            String date = data.get("date").toString().substring(8,10);

            dataset.addValue(data.getInt("value"), "Snow in mm", date);

        }


        return dataset;
    }



}
