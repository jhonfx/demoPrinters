
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.PrintService;
import javax.swing.JApplet;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jpurata
 */
public class First extends JApplet implements ActionListener {

    Button one = new Button("Click");
    Canvas canvas = new Canvas();    

    public void init() {
        makeGui();
    }

    public void oneClick() {
        JOptionPane.showMessageDialog(rootPane, "Mensaje desde el botón");
        Principal.generateReportPDF();
        //printEvent();
    }

    public void drawFirst() {
        System.out.print("Metodo drawFirst");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == one) {
            oneClick();
        }
    }

    public void printEvent() throws JRException {
        
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        PageFormat pf = printerJob.defaultPage();
        Paper paper = new Paper();
        
        //printerJob.setPrintable(jasperPrint, pf);
        PrintService printer = printerJob.getPrintService();
        if (printer == null) {
            JOptionPane.showMessageDialog(rootPane, "Impresoras no disponibles", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //printerJob.setPrintable(null);
        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException pe) {
                System.out.print(pe);
                JOptionPane.showMessageDialog(rootPane, "Otro chingado error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void paint(Graphics g) {
        drawFirst();
    }

    private void makeGui() {
        setBackground(Color.black);
        setForeground(Color.white);
        setLayout(new BorderLayout());


        Panel p = new Panel();
        p.add(one);
        add(BorderLayout.NORTH, p);
        one.addActionListener(this);
    }
}
