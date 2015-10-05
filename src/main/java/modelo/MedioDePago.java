package modelo;

public abstract class MedioDePago {

    public abstract void guardar(Cliente cliente);

    public String getNombre() {
        return getClass().getSimpleName();
    }
}
