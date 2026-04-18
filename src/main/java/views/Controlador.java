package views;

import data.Persistencia;
import domain.Marca;
import domain.Sucursal;
import domain.Vehiculo;
import domain.VehiculoCombustible;
import domain.VehiculoElectrico;
import domain.VehiculoTipo;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class Controlador {
    
    public static ArrayList<VehiculoViewModel> getVehiculos(){
        ArrayList<VehiculoViewModel> vehiculos = new ArrayList<>();
        for(Vehiculo vehiculo : Persistencia.getVehiculos()) {
            vehiculos.add(new VehiculoViewModel(vehiculo));
        }
        return vehiculos;
    }
    
    public static double[] calcularConsumos(Map<String, Double> vehiculos){
        double consumoElectricos = 0;
        double consumoCombustible= 0;
        for(Map.Entry<String, Double> entry : vehiculos.entrySet()){
           double consumo = 0;
           Optional<Vehiculo> vehiculo = Persistencia.getVehiculo(entry.getKey());
           if(vehiculo.isPresent()){
               consumo = vehiculo.get().calcularConsumo(entry.getValue());
               consumoElectricos += vehiculo.get().esDe(VehiculoTipo.ELECTRICO) ? consumo : 0;
               consumoCombustible += vehiculo.get().esDe(VehiculoTipo.COMBUSTIBLE) ? consumo : 0;
           }
        }
        return new double[] {consumoElectricos, consumoCombustible};
    }
    
     public static void agregarVehiculoElectrico(String patente, String marca, String modelo,
        int anio, double capacidad, String sucursal, double kwhBase){

    Marca m = Persistencia.getMarca(marca).get();
    Sucursal s = Persistencia.getSucursal(sucursal).get();

    Vehiculo v = new VehiculoElectrico(
        patente, m, modelo, anio, capacidad, s, kwhBase
    );

    Persistencia.agregarVehiculo(v);
}
    public static void agregarVehiculoCombustible(String patente, String marca, String modelo,
        int anio, double capacidad, String sucursal, double kmLitro, double litrosExtras){

    Marca m = Persistencia.getMarca(marca).get();
    Sucursal s = Persistencia.getSucursal(sucursal).get();

    Vehiculo v = new VehiculoCombustible(
        patente, m, modelo, anio, capacidad, s, kmLitro, litrosExtras
    );

    Persistencia.agregarVehiculo(v);
}
    
    
    
    
}
