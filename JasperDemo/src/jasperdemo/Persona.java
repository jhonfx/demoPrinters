/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jasperdemo;

/**
 *
 * @author jhonfx
 */
public class Persona {
  
  String nombre;
  private String concepto;
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public String getNombre() {
    return nombre;
  }

  /**
   * @return the concepto
   */
  public String getConcepto() {
    return concepto;
  }

  /**
   * @param concepto the concepto to set
   */
  public void setConcepto(String concepto) {
    this.concepto = concepto;
  }
  
}
