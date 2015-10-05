package modelo;

import persistencia.APMedioDePago;

/**
 * Created by mpliego on 10/4/15.
 */
public class CBU extends MedioDePago{
    private String entidad;
    private String numero;


    //~ MedioDePago Overrides --------------------------------------------------

    @Override
    public void guardar(Cliente cliente) {
        APMedioDePago.create(this, cliente);
    }

    //~ MedioDePago Overrides --------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CBU cbu = (CBU) o;

        if (entidad != null ? !entidad.equals(cbu.entidad) : cbu.entidad != null) return false;
        if (numero != null ? !numero.equals(cbu.numero) : cbu.numero != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = entidad != null ? entidad.hashCode() : 0;
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        return result;
    }


    //~ Getters & Setters ------------------------------------------------------

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
