/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcepcionesAlbaran;

/**
 *
 * @author dbarriof
 */
public class NoExisteArticulo extends Exception{

    public NoExisteArticulo(String mensaje) {
        super(mensaje);
    }
   
}
