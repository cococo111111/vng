package com.vng.zing.credits.report.mail.model;

import com.vng.jcore.common.Config;
import hapax.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.*;
import java.util.*;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.paymentreport.PaymentReportModel;
import vng.zingme.payment.thrift.T_ReportSummary;
import vng.zingme.payment.thrift.T_ReportTransaction;

/**
 *
 * @author quytp2@vng.com.vn
 */
public class AppReport {
    private String appId;
    private String appName;
    private String img;
    private String html;
    TemplateDataDictionary appreport;
    
    public String getData() throws TemplateException {
        return html;
    }

    public String getChart() {
        return img;
    }

    public String getChartUrl() {
        return CHART_DIR + img;
    }
    
    public String getAppName() {
        return appName;
    }
    
    public String getAppId() {
        return appId;
    }
    
    public AppReport(String appId, String appName) {
        this.appId = appId;
        this.appName = appName;
        
        try {
            this.appreport = TemplateDictionary.create();
            this.appreport.setVariable("app_id", appId);
            this.appreport.setVariable("app_name", appName);
            ArrayList<T_ReportSummary> summarys = new ArrayList<T_ReportSummary>();
            T_ReportSummary summary = new T_ReportSummary();
            getMonthlySummary(summarys, summary);
            renderReport(summarys, summary);
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
    }
    
    final static private Logger _logger = Logger.getLogger(AppReport.class);
    final static private PaymentReportModel _reportModel = new PaymentReportModel();
    final static String CHART_DIR = Config.getParam("REPORT", "chart_dir");
    
    private T_ReportSummary exportData(String appId, String startTime, String endTime) {
        T_ReportSummary res = new T_ReportSummary(appId, startTime, endTime, 0, 0, 0, 0, 0, 0, 0);
        List<T_ReportTransaction> transByApp = new LinkedList<T_ReportTransaction>();
        LinkedHashMap<Integer, Byte> setUsersOnApp = new LinkedHashMap<Integer, Byte>();
        int hope_count = 5;
        while (transByApp == null || (hope_count > 0 && transByApp.size() <= 0)) {
            try {
                boolean isPushMoneyApp = false;
                if (appId.equals("admin") || appId.equals(CommonDef.ZING_PAY_ID)) {
                    isPushMoneyApp = true;
                }
                transByApp = _reportModel.getListTransaction(appId, startTime, endTime, res, setUsersOnApp, isPushMoneyApp);
            } catch (Exception ex) {
                _logger.warn(ex.getMessage(), ex);
            }
            --hope_count;
        }
        res.totalDiffUser = setUsersOnApp.keySet().size();
        return res;
    }
    
    public static DateFormat ymdDf_start = new SimpleDateFormat("yyyy-MM-dd ' 00:00:00'");
    public static DateFormat ymdDf_end = new SimpleDateFormat("yyyy-MM-dd ' 23:59:59'");
    public static DateFormat imgFm = new SimpleDateFormat("'%s-'MM-yyyy'.png'");
    public static DateFormat dmyDf = new SimpleDateFormat("dd-MM-yyyy");
    
    int lastDOM;
    private void getMonthlySummary(ArrayList<T_ReportSummary> dailySummaries, T_ReportSummary monthlySummary) {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(System.currentTimeMillis());
        cl.add(Calendar.DAY_OF_MONTH, -1);
        String endTime = ymdDf_end.format(cl.getTime());
        cl.add(Calendar.DAY_OF_MONTH, +1);
        do {
            cl.add(Calendar.DAY_OF_MONTH, -1);
            dailySummaries.add(0, exportData(appId, ymdDf_start.format(cl.getTime()), ymdDf_end.format(cl.getTime())));            
        } while(cl.get(Calendar.DAY_OF_MONTH) > 1);
        lastDOM = cl.getActualMaximum(Calendar.DAY_OF_MONTH);     
        String startTime = ymdDf_start.format(cl.getTime());
        T_ReportSummary summary = exportData(appId, startTime, endTime);
        monthlySummary.totalTransaction = summary.totalTransaction;
        monthlySummary.totalTransactionFail = summary.totalTransactionFail;
        monthlySummary.totalTransactionNetError = summary.totalTransactionNetError;
        monthlySummary.totalTransactionSuccess = summary.totalTransactionSuccess;
        monthlySummary.totalTransactionTimeOut = summary.totalTransactionTimeOut;
        monthlySummary.totalMoney = summary.totalMoney;
        monthlySummary.totalDiffUser = summary.totalDiffUser;
        img = String.format(imgFm.format(cl.getTime()), appId);        
    }
    
    public static final Font DEFAULT_FONT = new Font("SansSerif", Font.BOLD, 12);    
    
    NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);     
    

    private void renderReport(ArrayList<T_ReportSummary> dailySummaries, T_ReportSummary monthlySummary) throws Exception {
        DefaultCategoryDataset plotData[] = {new DefaultCategoryDataset(), new DefaultCategoryDataset()};
        T_ReportSummary summary;
        String lable;
        TemplateDataDictionary tmpDic;
        for (int i = 1; i <= dailySummaries.size(); ++i) {    
            summary = dailySummaries.get(i - 1);
            lable = String.format("%2d", i);
            plotData[0].addValue(summary.totalMoney, "Zing Xu", lable);
            plotData[1].addValue(summary.totalDiffUser, "User", lable);            
            tmpDic = appreport.addSection("REPORT_ROW");
            tmpDic.setVariable("date", lable);
            tmpDic.setVariable("transaction", nf.format(summary.totalTransaction));
            tmpDic.setVariable("success", nf.format(summary.totalTransactionSuccess));
            tmpDic.setVariable("unsuccess", nf.format(summary.totalTransactionFail));
            tmpDic.setVariable("error", nf.format(summary.totalTransactionNetError + summary.totalTransactionTimeOut));
            tmpDic.setVariable("zingxu", nf.format(summary.totalMoney));
            tmpDic.setVariable("user", nf.format(summary.totalDiffUser));            
        }
        for (int i = dailySummaries.size() + 1; i <= lastDOM; ++i) {
            lable = String.format("%2d", i);
            plotData[0].addValue(null, "Zing Xu", lable);
            plotData[1].addValue(null, "User", lable);
        }        
        appreport.setVariable("total_transaction", nf.format(monthlySummary.totalTransaction));
        appreport.setVariable("total_success", nf.format(monthlySummary.totalTransactionSuccess));
        appreport.setVariable("total_unsuccess", nf.format(monthlySummary.totalTransactionFail));
        appreport.setVariable("total_error", nf.format(monthlySummary.totalTransactionNetError + monthlySummary.totalTransactionTimeOut));
        appreport.setVariable("total_zingxu", nf.format(monthlySummary.totalMoney));
        appreport.setVariable("total_user", nf.format(monthlySummary.totalDiffUser));
        createChart(plotData[0], plotData[1]);
        TemplateLoader templateLoader = TemplateResourceLoader.create("com/vng/zing/credits/report/mail/view/");
        Template template = templateLoader.getTemplate("appreport");
        html = template.renderToString(appreport);
    }
    
    private void createChart(CategoryDataset xuData, CategoryDataset userData) throws IOException {
        final CategoryAxis domainAxis = new CategoryAxis("Ngày");
        ValueAxis userAxis = new NumberAxis("User");
        userAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer userRender = new LineAndShapeRenderer();
        userRender.setDrawOutlines(true);
        userRender.setUseOutlinePaint(true);
        userRender.setSeriesPaint(0, Color.BLUE);
        userRender.setSeriesOutlinePaint(0, Color.YELLOW);
        
        
        final CategoryPlot plot = new CategoryPlot(userData, domainAxis, userAxis, userRender);
        
        plot.setRangeAxisLocation(AxisLocation.TOP_OR_RIGHT);
        plot.setDataset(1, xuData);
        plot.mapDatasetToRangeAxis(1, 1);
        
        final NumberAxis xuAxis = new NumberAxis("Zing Xu");
        xuAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        final BarRenderer xuRenderer = new BarRenderer();
        xuRenderer.setBarPainter(new StandardBarPainter());
        xuRenderer.setShadowVisible(false);
        xuRenderer.setDrawBarOutline(true);
        xuRenderer.setSeriesOutlinePaint(0, Color.ORANGE);
        
        plot.setRangeAxis(1, xuAxis);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_LEFT);               
      
        plot.setRenderer(1, xuRenderer); 
        plot.setDomainGridlinesVisible(true);
        
        JFreeChart chart = new JFreeChart("", DEFAULT_FONT, plot, true);
        chart.setBackgroundPaint(Color.white);
        BufferedImage bi = chart.createBufferedImage(750, 450);
        File outputfile = new File(getChartUrl());
        ImageIO.write(bi, "png", outputfile);
    }
    
//    private void createChart(CategoryDataset xuData, CategoryDataset userData) throws IOException {
//        final JFreeChart chart = ChartFactory.createBarChart(
//            "",
//            "Ngày",
//            "Zing Xu",
//            xuData,
//            PlotOrientation.VERTICAL,
//            true,
//            true,
//            false
//        );
//
//        chart.setBackgroundPaint(Color.white);
//
//        final CategoryPlot plot = chart.getCategoryPlot();
//        plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
//        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
//
//        plot.setDataset(1, userData);
//        plot.mapDatasetToRangeAxis(1, 1);
//        
//        final ValueAxis axis2 = new NumberAxis();
//        plot.setRangeAxis(1, axis2);
//
//        final LineAndShapeRenderer userRenderer = new LineAndShapeRenderer();
//        plot.setRenderer(1, userRenderer);
//        plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
//        chart.setBackgroundPaint(Color.white);
//        
//        BufferedImage bi = chart.createBufferedImage(750, 500);
//        File outputfile = new File(getChartUrl());
//        ImageIO.write(bi, "png", outputfile);
//    }
}
