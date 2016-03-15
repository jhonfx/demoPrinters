
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
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
 * @author jpurata
 */
public class ChequeReport {

    public static JasperDesign jasperDesign;
    public static JasperPrint jasperPrint;
    public static JasperReport jasperReport;
    public static JasperPrintManager jasperPrintManager;
    public static String reportTemplateUrl = "/home/jpurata/printCheque.jrxml";
    Cheque cheque = new Cheque();

    public static String getReportFile() {
        return reportTemplateUrl;
    }

    public static Map getReportParameter() {
        Map parameters = new HashMap();
        parameters.put("ReporteCheque", 1);
        return parameters;
    }

    public static List<ChequeReportDTO> dataChequeReport() {
        double monto = 10.16;
        String paridad = "USD";
        String textMonto = CantidadesEnLetraUtils.convertNumberToLetter(monto, paridad, true);
        List<ChequeReportDTO> dato = new ArrayList<ChequeReportDTO>();

        ChequeReportDTO chequeReportDTO = new ChequeReportDTO();
        chequeReportDTO.setFolio(1);
        chequeReportDTO.setFecha(FechaHoraUtils.obtenerFechaActual());
        chequeReportDTO.setMonto(monto);
        chequeReportDTO.setRazonSocial("BenQ México S. de R.L de C.V");
        chequeReportDTO.setTextoMonto(textMonto);
        chequeReportDTO.setConcepto("Pago de Facturas de Proveedores de Bienes".toUpperCase());
        dato.add(chequeReportDTO);
        return dato;
    }

    public static void generateReportPDF() {
        try {
            //obtenemos el reporte y lo cargamos al diseño
            jasperDesign = JRXmlLoader.load(getReportFile());
            //System.out.print(jasperDesign.getFields());
            //compila el jasper
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            //prepara el reporte con los datos y parametros
            jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(dataChequeReport()));

            //mostramos el reporte generado usando la clase JasperViewer            
            //JasperViewer.viewReport(jasperPrint);
            //Muestra la ventana de las impresoras, lista para imprimir
            //el segundo parametro, es para decir si se quiere o no mostrar la ventana
            //o mandar a imprimir directo
            jasperPrintManager.printReport(jasperPrint, true);
        } catch (JRException e) {
            e.printStackTrace();
        }
        //return jasperPrintManager;
    }

    public static void main(String[] args) {
        ChequeReport.generateReportPDF();
    }
}
