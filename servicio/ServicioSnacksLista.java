package Maquina_Snacks_Archivos.servicio;

import Maquina_Snacks_Archivos.dominio.Snack;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServicioSnacksLista implements IServicioSnacks {
    Scanner consola = new Scanner(System.in);
    static List<Snack> snacks = new ArrayList<>();

    @Override
    public void agregarSnack(){
        System.out.print("Ingresa el nombre del snack: ");
        String nombre = consola.nextLine();
        System.out.print("Ingresa el precio del snack: ");
        double precio = Double.parseDouble(consola.nextLine());
        snacks.add(new Snack(nombre,precio));
    }

    @Override
    public void mostrarSnacks(){
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

    public boolean isEmpty(){
        if(snacks.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public List<Snack> getSnacks() {
        return snacks;
    }
}
