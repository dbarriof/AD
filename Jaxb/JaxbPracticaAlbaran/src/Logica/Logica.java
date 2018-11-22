/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import ExcepcionesAlbaran.ErrorFecha;
import ExcepcionesAlbaran.NoExisteArticulo;
import ExcepcionesAlbaran.campoVacio;
import ExcepcionesAlbaran.listaArticulosVacia;
import generated.Articulos;
import generated.Direccion;
import generated.ObjectFactory;
import generated.PedidoType;
import java.io.File;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author dbarriof
 */
public class Logica {

    /**
     * Metodo que realiza unmarshal de un fichero xml dentro de un contexto dado
     *
     * @return JAXBElement
     * @throws JAXBException
     */
    public static JAXBElement unmarshalizar() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        JAXBElement jaxbElement = (JAXBElement) unmarshaller.unmarshal(new File("albaran.xml"));
        return jaxbElement;
    }

    /**
     * Metodo que permite marshalizar y pasar generar un documento xml con los
     * datos contenidos en los objetos java
     *
     * @param jaxbelement
     * @throws JAXBException
     */
    public static void marshalizar(JAXBElement jaxbelement) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(jaxbelement, new File("pruebaAlbaran.xml"));
    }

    public static void aniadirArticulo(PedidoType pedidoType, Articulos.Articulo articulo) throws NullPointerException, ErrorFecha {

        XMLGregorianCalendar fecha = articulo.getFechaEnvio();
        int anio = fecha.getYear();
        int mes = fecha.getMonth();
        int dia = fecha.getDay();

        if (anio > 1988 && anio < 2031 && mes > 0 && mes < 13 && dia > 0 && dia < 32) {
            Articulos articulos = pedidoType.getArticulos();
            articulos.getArticulo().add(articulo);
        } else {
            throw new ErrorFecha("La fecha indicada no cumple con el formato establecido");
        }

    }

    /**
     * Metodo que permite modificar la direccion de facturacion y envio de un
     * pedido
     *
     * @param pedidotype
     * @param direccion
     * @throws campoVacio
     */
    public static void modificaDireccion(PedidoType pedidotype, Direccion direccion) throws campoVacio {
        if (direccion.getCalle() == null || direccion.getCalle() == null || direccion.getCodigoPostal() == null || direccion.getNombre() == null || direccion.getPais() == null || direccion.getProvincia() == null) {
            throw new ExcepcionesAlbaran.campoVacio("No puede haber campos vacios");
        } else {
            pedidotype.getFacturarA().setCalle(direccion.getCalle());
            pedidotype.getFacturarA().setCiudad(direccion.getCiudad());
            pedidotype.getFacturarA().setCodigoPostal(direccion.getCodigoPostal());
            pedidotype.getFacturarA().setNombre(direccion.getNombre());
            pedidotype.getFacturarA().setPais(direccion.getPais());
            pedidotype.getFacturarA().setProvincia(direccion.getProvincia());
        }
    }

    /**
     * Metodo que devuelve el importe del total de articulos
     *
     * @param pedidoType
     * @return BigDecimal
     * @throws listaArticulosVacia
     */
    public static BigDecimal calcularImporte(PedidoType pedidoType) throws listaArticulosVacia {
        BigDecimal total = new java.math.BigDecimal(0);

        Articulos articulos = pedidoType.getArticulos();
        List<Articulos.Articulo> listaArticulos = articulos.getArticulo();

        if (listaArticulos.isEmpty()) {
            throw new ExcepcionesAlbaran.listaArticulosVacia("La lista de artículos está vacía.");
        } else {
            for(Articulos.Articulo articulo : listaArticulos){
                total = total.add(articulo.getPrecio());             
            }
        }

        return total;
    }

    /**
     * Metodo que elimina un articulo del pedido en función de su nombre
     *
     * @param pedidoType
     * @param nombre
     * @throws NoExisteArticulo
     * @throws listaArticulosVacia
     */
    public static void eliminarArticulo(PedidoType pedidoType, String nombre) throws NoExisteArticulo, listaArticulosVacia {
        boolean eliminado = false;

        Articulos articulos = pedidoType.getArticulos();
        List<Articulos.Articulo> listaArticulos = articulos.getArticulo();

        if (listaArticulos.isEmpty()) {
            throw new ExcepcionesAlbaran.listaArticulosVacia("La lista de artículos está vacía.");
        } else {

            Iterator<Articulos.Articulo> it = listaArticulos.iterator();

            while (it.hasNext()) {

                if (it.next().getNombreProducto() == nombre) {

                    it.remove();
                    eliminado = true;
                }
            }

            if (!eliminado) {
                throw new NoExisteArticulo("El artículo indicado no está incluido en el pedido");
            }
        }
    }

}
