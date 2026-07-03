/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Maquina_Snacks_Archivos.servicio;

import Maquina_Snacks_Archivos.dominio.Snack;
import java.util.List;

/**
 *
 * @author Administrador
 */
public interface IServicioSnacks {
    
    void agregarSnack();
    void mostrarSnacks();
    boolean isEmpty();
    List<Snack> getSnacks();
}
