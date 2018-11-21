/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import generated.Articulos;
import generated.ObjectFactory;
import generated.PedidoType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author dbarriof
 */
public class Logica {
    /**
     * Metodo que realiza unmarshal de un fichero xml dentro de un contexto dado 
     * @return JAXBElement
     * @throws JAXBException 
     */
    public static JAXBElement unmarshalizar() throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        JAXBElement jaxbElement = (JAXBElement) unmarshaller.unmarshal(new File("albaran.xml"));
        return jaxbElement;
    }
    
    /**
     * Metodo que permite marshalizar y pasar generar un documento xml con los datos contenidos en los objetos java
     * @param jaxbelement
     * @throws JAXBException 
     */
    public static void marshalizar(JAXBElement jaxbelement)throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
        Marshaller marshaller =  context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(jaxbelement, new File("pruebaAlbaran.xml"));
    }
    
    public static void aniadirArticulo(PedidoType pedidoType, Articulos.Articulo articulo)throws NullPointerException{
        Articulos articulos = pedidoType.getArticulos();
        articulos.getArticulo().add(articulo);
    }
    
}
