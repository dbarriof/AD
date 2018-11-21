/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxbpractica1;

import generated.ObjectFactory;
import generated.PedidoType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author dbarriof
 */
public class JaxbPractica1 {

    /**
     * @param args the command line arguments
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.unmarshal(new FileInputStream("albaran.xml"));
    }
    
}
