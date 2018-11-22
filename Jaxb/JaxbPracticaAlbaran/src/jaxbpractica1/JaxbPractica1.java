/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxbpractica1;

import Logica.Logica;
import generated.Articulos;
import generated.PedidoType;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author dbarriof
 */
public class JaxbPractica1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            JAXBElement jabxelement = Logica.unmarshalizar();
            PedidoType pedidoType = (PedidoType)jabxelement.getValue();
            
            Articulos.Articulo articulo = new Articulos.Articulo();
            articulo.setCantidad(1);
            articulo.setCodigo("Codigo-invented");
            articulo.setComentario("Muy rico y bonito");
            articulo.setNombreProducto("Patito de Goma");
            articulo.setPrecio(new BigDecimal(1.5));
            try {
                XMLGregorianCalendar fec = DatatypeFactory.newInstance().newXMLGregorianCalendar();
                fec.setYear(2018);
                fec.setMonth(11);
                fec.setDay(22);
                articulo.setFechaEnvio(fec);
            } catch (DatatypeConfigurationException ex) {
            }
            
            Logica.aniadirArticulo(pedidoType, articulo);
            
            Logica.marshalizar(jabxelement);
        } catch (JAXBException ex) {
            Logger.getLogger(JaxbPractica1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
