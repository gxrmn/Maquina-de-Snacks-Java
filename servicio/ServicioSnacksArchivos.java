/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maquina_Snacks_Archivos.servicio;

import Maquina_Snacks_Archivos.dominio.Snack;
import static Maquina_Snacks_Archivos.servicio.ServicioSnacksLista.snacks;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Administrador
 */
public class ServicioSnacksArchivos implements IServicioSnacks{
    
    Scanner consola = new Scanner(System.in);
    private final String NOMBRE_ARCHIVO = "listaSnacks.txt";
    List<Snack> snacks = new ArrayList<>();
    
    public ServicioSnacksArchivos(){
         var archivo = new File(NOMBRE_ARCHIVO);
         var existe = false;
         try{
             existe = archivo.exists();
             if(existe){
                 this.snacks = obtenerSnacks();
             }
             else{
                 var salida = new PrintWriter(new FileWriter(archivo));
                 salida.close();
                 System.out.println("Se ha creado el archivo!");
             }
         }
         catch(Exception e){
             System.out.println("Error al crear el archivo: " + e.getMessage());
         }
         
    }
    
    @Override
    public void agregarSnack() {
        //Agregar snack a la lista en memoria
        System.out.print("Ingresa el nombre del snack: ");
        String nombre = consola.nextLine();
        System.out.print("Ingresa el precio del snack: ");
        double precio = Double.parseDouble(consola.nextLine());
        Snack nuevoSnack = new Snack(nombre, precio);
        this.snacks.add(nuevoSnack);
        
        //Agregar snack al archivo
        this.agregarSnackArchivo(nuevoSnack);
    }
    
    public void agregarSnackArchivo(Snack snack){
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(snack.escribirSnack());
            salida.close();
            System.out.println("Snack agregado con exito al archivo!");
        }
        catch(Exception e){
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
        
    }

    @Override
    public void mostrarSnacks() {
        if(this.isEmpty()){
            System.out.println("MAQUINA SIN PRODUCTOS");
        }
        else {
            System.out.println("---- MENU DE SNACKS ----");
            snacks.forEach(snack -> {
                System.out.println(snack);
            });
        }
    }

    public boolean isEmpty() {
        return this.snacks.isEmpty();
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }
    
    private List<Snack> obtenerSnacks(){
        var snacks = new ArrayList<Snack>();
        try{
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            for(String linea: lineas){
                String[] lineaSnack = linea.split(",");//Se usa split para volver array los valores de tipo cadena que retorna
                var idSnack = lineaSnack[0];//No se usa
                var nombre = lineaSnack[1];
                var precio = Double.parseDouble(lineaSnack[2]);
                var snack = new Snack(nombre, precio);
                snacks.add(snack); //Aqui se agrega cada snack desde el archivo a la lista local de la funcion
            }
        }
        catch(Exception e){
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        return snacks;
    }
    
}
