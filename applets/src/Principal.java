/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jhonfx
 */
public class Principal {

    public static JasperDesign jasperDesign;
    public static JasperPrint jasperPrint;
    public static JasperReport jasperReport;
    public static JasperPrintManager jasperPrintManager;
    public static String reportTemplateUrl = "test.jrxml";    

//return the report filename
    public static String getReportFile() {
        return reportTemplateUrl;
    }

//get a parameter to pass into jrxml
    public static Map getReportParameter() {
        Map parameters = new HashMap();
        parameters.put("ReporteCheque", 1);
        return parameters;
    }

//get the data to pass into jrxml
    public static List<Object> findReportData() {
        //declare a list of object
        List<Object> reports = new LinkedList<Object>();

        //declare 2nd ReportObject and set the fields
        ReportObject ro2 = new ReportObject();
        ro2.setNombre("Juan");
        ro2.setEdad(27);        
        //add 2nd ReportObject into list reports
        reports.add((Object) ro2);

        //declare 3rd ReportObject and set the fields
        /*ReportObject ro3 = new ReportObject();
        ro3.setNombre("Picharras");
        ro3.setEdad(27);        
        //add 3rd ReportObject into list reports
        reports.add((Object) ro3);

        //declare 4th ReportObject and set the fields
        ReportObject ro4 = new ReportObject();
        ro4.setNombre("Julian");
        ro4.setEdad(24);        
        //add 4th ReportObject into list reports
        reports.add((Object) ro4);*/

        return reports;
    }
    
    public static void generateReportPDF() {
        try {
            //get report file and then load into jasperDesign
            jasperDesign = JRXmlLoader.load(getReportFile());           
            //compile the jasperDesign
            //JasperCompileManager.verifyDesign(jasperDesign);
            //JasperCompileManager.writeReportToXmlFile(jasperDesign, "test.xml");
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            //fill the ready report with data and parameter
            jasperPrint = JasperFillManager.fillReport(jasperReport, getReportParameter(), new JRBeanCollectionDataSource(findReportData()));
            //view the report using JasperViewer
            //jasperPrintManager.printReport(jasperPrint, true);
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException e) {
            e.printStackTrace();
        }        
        //return jasperPrintManager;
    }

    public static void main(String[] args) throws IOException {
        Principal.generateReportPDF();
    }
}
