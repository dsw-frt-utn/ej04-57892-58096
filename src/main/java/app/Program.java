package app;

import data.Persistencia;
import java.util.InvalidPropertiesFormatException;
import views.ListarVehiculosView;
import views.MenuPrincipal;

public class Program {
     public static void main(String[] args) throws IllegalArgumentException, InvalidPropertiesFormatException {
        Persistencia.inicializar();
      

    java.awt.EventQueue.invokeLater(() -> {
        new MenuPrincipal().setVisible(true);
    });
    }
}
