package modelo;

import persistencia.APMedioDePago;

import java.util.Calendar;
import java.util.Date;


public class TarjetaDeCredito extends MedioDePago {
	private String entidad;
	private String numero;
	private Date fechaVencimiento;


    //~ MedioDePago Overrides --------------------------------------------------
    @Override
    public void guardar(Cliente cliente) {
        APMedioDePago.create(this, cliente);
    }

    @Override
    public String[] toStringArray() {

        String date = "";
        if(fechaVencimiento != null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaVencimiento);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            date = day + "-" + month + "-"+ year;
        }

        return new String[]{
                getClass().getSimpleName(),
                getEntidad(),
                getNumero(),
                date};
    }

    //~ Overrides --------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TarjetaDeCredito that = (TarjetaDeCredito) o;

        if (entidad != null ? !entidad.equals(that.entidad) : that.entidad != null) return false;
        if (numero != null ? !numero.equals(that.numero) : that.numero != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = entidad != null ? entidad.hashCode() : 0;
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (fechaVencimiento != null ? fechaVencimiento.hashCode() : 0);
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

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
