/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.util.*;

/**
 *
 * @author dbarriof
 */
public class OperacionesFicheros {

    private class CarpetaVacia extends Exception {
    };

    public class NoEsUnDirectorioNoSePuedeListar extends Exception {
    };

    public OperacionesFicheros() {
    }

    /**
     * Método que permite listar el contenido de un directorio pasado como parámetro, si el directorio no existe se lanza excepción,
     * si el directorio está vacio se lanza excepción y se lista la raiz del sistema.
     * @param ruta Se debe indicar una ruta donde se realizara el listado.
     * @param ordenadosPorTamanio Permite devolver el resultado ordenado por tamaño de los ficheros o directorios.
     * @param soloDirectorios Prmite devolver unicamente los directorios y obviar los ficheros .
     * @return String[] Devuelve un Array de Strings con los nombres de cada fichero.
     */
    public String[] listarFicheros(String ruta, boolean ordenadosPorTamanio, boolean soloDirectorios) {
        String[] archivos = null;
        File[] listado = null;
        String sp = File.separator;
        File rutaParaListar = new File(ruta);

        try {
            //Comprobamos si es un directorio y se puede listar si no lo es lanzamos excepcion y finaliza método:   
            if (!rutaParaListar.isDirectory()) {
                throw (new NoEsUnDirectorioNoSePuedeListar());

            } else {
                //Listamos el directorio: 
                listado = rutaParaListar.listFiles();
            }

            try {
                //Comprobamos que no está vacio el directorio y en ese caso lanzamos excepcion y listamos la raiz:       
                if (listado.length == 0) {
                    throw (new CarpetaVacia());
                }
            } catch (CarpetaVacia cv) {
                //Comprobamos si el SO es Windows y listamos la raiz
                System.out.println("La carpeta " + ruta + " está vacía, listando la raiz:\n");

                if (listado.length == 0 && sp.equals("\\")) {
                    rutaParaListar = new File("C:\\");
                    listado = rutaParaListar.listFiles();
                } //Comprobamos si el SO es Unix y listamos la raiz
                else if (listado.length == 0 && sp.equals("/")) {
                    rutaParaListar = new File("/");
                    listado = rutaParaListar.listFiles();
                }
            }
            //Convertimos el array en listado para trabajar con él
            List<File> listadoDeFicheros = new ArrayList(Arrays.asList(listado));

            //creamos comparador para operar con tamaños de ficheros:
            Comparator<File> comparaTamanio = new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    if (f1.length() < f2.length()) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            };

            //Ordenamos por tamaño si es solicitado:
            if (ordenadosPorTamanio) {
                //Ordenamos por tamanio:
                Collections.sort(listadoDeFicheros, comparaTamanio);
            }

            //Excluimos ficheros si es solicitado:
            if (soloDirectorios) {
                //Eliminamos los ficheros que no son directorios con iterador:
                Iterator<File> it = listadoDeFicheros.iterator();

                while (it.hasNext()) {
                    if (!it.next().isDirectory()) {
                        it.remove();
                    }
                }
            }

            //Retornamos un array de Strings con los nombres de los Ficheros o Directorios listados:
            archivos = new String[listadoDeFicheros.size()];
            int i = 0;
            for (File f : listadoDeFicheros) {
                if (f.isDirectory()) {
                    archivos[i] = "\\" + f.getName();
                } else {
                    archivos[i] = f.getName();

                }
                i++;
            }
            
        //Catch de la excepcion lanzada en caso de no haber recibido una ruta válida
        } catch (NoEsUnDirectorioNoSePuedeListar nd) {
            System.out.println("No esta indicando una ruta válida");
        }

        return archivos;
    }
    
    public int crearDirectorios(File rutaOrigen, ArrayList<String> listaDirectorios){
        int contadorDirectorios = 0;
        
        for(String str : listaDirectorios){
            
        }
        
    }

}
