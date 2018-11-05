/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import logica.OperacionesFicheros.NoEsUnDirectorioNoSePuedeListar;
import logica.OperacionesFicheros;

/**
 *
 * @author dbarriof
 */
public class MainFicheros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OperacionesFicheros of = new OperacionesFicheros();

        
            String[] listado = of.listarFicheros("/prueba", true, true);
            if (listado != null) {
                for (String str : listado) {
                    System.out.println(str);
                }
            }
        

    }

}
