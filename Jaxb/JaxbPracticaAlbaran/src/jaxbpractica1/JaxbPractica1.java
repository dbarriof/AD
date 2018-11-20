/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxbpractica1;

import generated.PedidoType;
import java.io.File;
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
     */
    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(PedidoType.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.unmarshal(new File("albaran.xml"));
    }
    
}
