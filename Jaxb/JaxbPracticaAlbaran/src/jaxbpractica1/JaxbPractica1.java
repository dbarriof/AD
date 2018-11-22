/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxbpractica1;

import ExcepcionesAlbaran.ErrorFecha;
import ExcepcionesAlbaran.campoVacio;
import ExcepcionesAlbaran.listaArticulosVacia;
import Logica.Logica;
import generated.Articulos;
import generated.Direccion;
import generated.PedidoType;
import java.math.BigDecimal;
import java.math.BigInteger;
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
            
            /**
             * Creamos un artículo que será a?adido posteriormente
             * para comprobar el correcto funcionamiento del método específico
             */
            Articulos.Articulo articulo = new Articulos.Articulo();
            articulo.setCantidad(1);
            articulo.setCodigo("Codigo-invented");
            articulo.setComentario("Muy rico y bonito");
            articulo.setNombreProducto("Patito de Goma");
            articulo.setPrecio(new BigDecimal(1.5));

                XMLGregorianCalendar fec = DatatypeFactory.newInstance().newXMLGregorianCalendar();
                fec.setYear(2018);
                fec.setMonth(11);
                fec.setDay(22);
            articulo.setFechaEnvio(fec);
            
            /**
             * A?adimos el artículo al pedido
             */
            Logica.aniadirArticulo(pedidoType, articulo);
            
            /**
             * Creamos una nueva dirección que reemplazara a la existente en el pedido
             * para comprobar el funcionamiento del método específico
             */
            Direccion direccion = new Direccion();
            direccion.setCalle("Calle 1");
            direccion.setCiudad("A Coru?a");
            direccion.setCodigoPostal(new BigDecimal(15670));
            direccion.setNombre("Mi Tienda");
            direccion.setPais("Brasil");
            direccion.setProvincia("Curitiba");
            /**
             * Modificamos la dirección por la nueva
             */
            Logica.modificaDireccion(pedidoType, direccion);
            
            /**
             * Solicitamos el total del pedido que será mostrado por consola
             * para comprobar el correcto funcionamiento del método específico
             */
            BigDecimal total = Logica.calcularImporte(pedidoType);
            System.out.println("El total del pedido es: " + String.valueOf(total) + "€");
            
            
            
            
            Logica.marshalizar(jabxelement);
        } catch (JAXBException ex) {
            Logger.getLogger(JaxbPractica1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(JaxbPractica1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErrorFecha ex) {
            Logger.getLogger(JaxbPractica1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (campoVacio ex) {
            Logger.getLogger(JaxbPractica1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(JaxbPractica1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (listaArticulosVacia ex) {
            Logger.getLogger(JaxbPractica1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
