package Maquina_Snacks_Archivos.dominio;

import java.io.Serializable;
import java.util.Objects;

public class Snack implements Serializable {

    private static int contador = 0;
    private int idSnack;
    private String nombre;
    private double precio;

    public Snack(String nombre, double precio){
        this.idSnack = ++Snack.contador;
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String toString(){
        return "Id Snack: " + this.getIdSnack() + ", Nombre: " + this.getNombre() + ", Precio: " + this.getPrecio();
    }

    public String escribirSnack(){
        return this.idSnack + "," + this.nombre + "," + this.precio;
    }

    //Encapsulamiento
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public double getPrecio() {
        return this.precio;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }

    public int getIdSnack() {
        return idSnack;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return this.idSnack == snack.idSnack && Double.compare(precio, snack.precio) == 0 && Objects.equals(nombre, snack.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSnack, nombre, precio);
    }
}
