/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import generated.ObjectFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    public JAXBElement unmarshalizar() throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        JAXBElement jaxbElement = (JAXBElement) unmarshaller.unmarshal(new File("albaran.xml"));
        return jaxbElement;
    }
    
    public void marshalizar(JAXBElement jaxbelement){
        Marshaller m = .createMarshaller();
    }
}
