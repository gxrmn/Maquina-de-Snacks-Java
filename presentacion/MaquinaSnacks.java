package Maquina_Snacks_Archivos.presentacion;

import Maquina_Snacks_Archivos.dominio.Snack;
import Maquina_Snacks_Archivos.servicio.IServicioSnacks;
import Maquina_Snacks_Archivos.servicio.ServicioSnacksArchivos;
import Maquina_Snacks_Archivos.servicio.ServicioSnacksLista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {
    Scanner consola = new Scanner(System.in);
    //IServicioSnacks snacks = new ServicioSnacksLista();
    IServicioSnacks snacks = new ServicioSnacksArchivos();
    List<Snack> productos = new ArrayList<>();

    private void menu(){
        boolean salir = false;
        while(!salir){
            try{
                snacks.mostrarSnacks();
                System.out.print("""
                Menu:
                1.- Comprar snack
                2.- Mostrar ticket
                3.- Agregar nuevo snack
                4.- Salir
                Elige una opcion:\s""");
                int opcion = Integer.parseInt(consola.nextLine());
                switch (opcion){
                    case 1:
                        comprarSnack();
                        break;
                    case 2:
                        mostrarTicket();
                        break;
                    case 3:
                        snacks.agregarSnack();
                        break;
                    case 4:
                        System.out.println("Adios");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            }
            catch (Exception e){
                System.out.println("Ocurrio un error:" + e.getMessage());
            }
            finally {
                System.out.println();//Imprime un salto de linea con cada iteracion
            }
        }
    }

    private void comprarSnack(){
        if(snacks.isEmpty()){
            System.out.println("La maquina no tiene productos aun!");
        }
        else{
            System.out.print("Ingresa el snack que deseas comprar(id): ");
            var idSnack = Integer.parseInt(consola.nextLine());
            boolean snackEncontrado = false;
            for(Snack snack: snacks.getSnacks()){
                if(idSnack == snack.getIdSnack()){
                    productos.add(snack);
                    System.out.println("OK, Snack agregado: " + snack);
                    snackEncontrado = true;
                    break;
                }
            }
            if(!snackEncontrado){
                System.out.println("Id de snack no encontrado");
            }
        }
    }

    private void mostrarTicket(){
        double total = 0;
        String ticket = "";
        for(Snack snack : productos){
            total += snack.getPrecio();
            ticket += "- " + snack.getNombre() + " - $" + snack.getPrecio() + "\n";
        }
        ticket += "Total -$" + total;
        if(productos.isEmpty()){
            System.out.println("Aun no ha comprado productos");
        }
        else {
            System.out.println("-------Ticket-------\n" + ticket);
        }
    }

    public static void main(String[] args) {

        MaquinaSnacks objeto = new MaquinaSnacks();
        objeto.menu();

    }
}
