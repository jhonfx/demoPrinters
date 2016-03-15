/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.util.JRLoader;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: PrinterApplet.java 4595 2011-09-08 15:55:10Z teodord $
 */
public class PrinterApplet extends javax.swing.JApplet
{


	/**
	 *
	 */
	private URL url;
	private JasperPrint jasperPrint;


	/** Creates new form AppletViewer */
	public PrinterApplet()
	{
		initComponents();
	}


	/**
	*
	*/
	public void init()
	{
		String strUrl = "/home/jpurata/webTest.jasper";
		if (strUrl != null)
		{
			try
			{
				url = new URL(getCodeBase(), strUrl);
			}
			catch (Exception e)
			{
				StringWriter swriter = new StringWriter();
				PrintWriter pwriter = new PrintWriter(swriter);
				e.printStackTrace(pwriter);
				JOptionPane.showMessageDialog(this, swriter.toString());
			}
		}
		else
		{
		JOptionPane.showMessageDialog(this, "Source URL not specified");
		}
	}


	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents() {//GEN-BEGIN:initComponents
		pnlMain = new javax.swing.JPanel();
		btnPrint = new javax.swing.JButton();
		btnView = new javax.swing.JButton();

		btnPrint.setText("Print the report");
		btnPrint.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPrintActionPerformed(evt);
			}
		});

		pnlMain.add(btnPrint);

		btnView.setText("View the report");
		btnView.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnViewActionPerformed(evt);
			}
		});

		pnlMain.add(btnView);

		getContentPane().add(pnlMain, java.awt.BorderLayout.WEST);

	}//GEN-END:initComponents

	protected void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
		// Add your handling code here:
		JOptionPane.showMessageDialog(this, "Generando vista de jasper");
		if (url != null)
		{
			try
			{
				if (jasperPrint == null)
				{
					jasperPrint = (JasperPrint)JRLoader.loadObject(url);
				}
				if (jasperPrint != null)
				{
					ViewerFrame viewerFrame = new ViewerFrame(this.getAppletContext(), jasperPrint);
					viewerFrame.show();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Empty report.");
				}
			}
			catch (Exception e)
			{
				StringWriter swriter = new StringWriter();
				PrintWriter pwriter = new PrintWriter(swriter);
				e.printStackTrace(pwriter);
				JOptionPane.showMessageDialog(this, swriter.toString());
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Source URL not specified");
		}
	}//GEN-LAST:event_btnViewActionPerformed

	protected void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
		// Add your handling code here:
		if (url != null)
		{
			if (jasperPrint == null)
			{
				try
				{
					jasperPrint = (JasperPrint)JRLoader.loadObject(url);
				}
				catch (Exception e)
				{
					StringWriter swriter = new StringWriter();
					PrintWriter pwriter = new PrintWriter(swriter);
					e.printStackTrace(pwriter);
					JOptionPane.showMessageDialog(this, swriter.toString());
				}
			}
			
			if (jasperPrint != null)
			{				
				final JasperPrint print = jasperPrint;
				
				Thread thread = new Thread
					(
						new Runnable()
						{
							public void run()
							{
								try 
								{
									JasperPrintManager.printReport(print, true);
								}
								catch (Exception e) 
								{
									StringWriter swriter = new StringWriter();
									PrintWriter pwriter = new PrintWriter(swriter);
									e.printStackTrace(pwriter);
									JOptionPane.showMessageDialog(null, swriter.toString());
								}
							}
						}
					);
				
				thread.start();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Empty report.");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Source URL not specified");
		}
	}//GEN-LAST:event_btnPrintActionPerformed
	
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel pnlMain;
	private javax.swing.JButton btnView;
	private javax.swing.JButton btnPrint;
	// End of variables declaration//GEN-END:variables
	
}
