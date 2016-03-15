/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jasperdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.PrintOrderEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jhonfx
 */
public class JasperDemo {

  /**
   * @param args the command line arguments
   */
  
  ;
  
  public static JasperReport jasperReport;
  public static JasperPrint jasperPrint;
  public static JRPrintPage jreJRPrintPage;
  //public static JasperDesign jasperDesign;
  public static String reportTemplateUrl = "/home/jhonfx/demo.jrxml";
  
  public static void main(String[] args) {
    
    try {
            
      
      
      
      Persona persona = new Persona();
      persona.setNombre("Pacoto");
      persona.setConcepto("Un reporte");
      
      List<Persona> list = new ArrayList<Persona>();
      list.add(persona);
      
      JasperDesign jasperDesign = new JasperDesign();
      jasperDesign.setName("printCheque");
      jasperDesign.setPageWidth(595);
      jasperDesign.setPageHeight(842);            
      jasperDesign.setLeftMargin(20);
      jasperDesign.setRightMargin(20);
      jasperDesign.setTopMargin(20);
      jasperDesign.setBottomMargin(20);
      jasperDesign.setPageWidth(612);
      jasperDesign.setPageHeight(592);
      jasperDesign.setPrintOrder(PrintOrderEnum.HORIZONTAL);
      
      JRDesignField field = new JRDesignField();
      field.setName("concepto");
      field.setValueClass(String.class);
      jasperDesign.addField(field);
      
      JRDesignBand band = new JRDesignBand();
      band.setHeight(150);
      
      JRDesignTextField textField = new JRDesignTextField();
      textField.setX(60);
      textField.setY(0);
      textField.setWidth(200);
      textField.setHeight(20);      
      
      
      JRDesignExpression expression = new JRDesignExpression();
      expression.setValueClass(String.class);
      expression.setText("$F{concepto}");
      textField.setExpression(expression);
      //textField.getLineBox().getRightPen().setLineWidth(1);
      //textField.getLineBox().getBottomPen().setLineWidth(1);
      //textField.getLineBox().setLeftPadding(10);
      band.addElement(textField);
      ((JRDesignSection) jasperDesign.getDetailSection()).addBand(band);
      
      //jasperDesign = JRXmlLoader.load(reportTemplateUrl);
      jasperReport = JasperCompileManager.compileReport(jasperDesign);
      jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(list));
      JasperViewer.viewReport(jasperPrint);
    } catch (JRException ex) {
      System.out.println(ex);
    }
    
  }
}
