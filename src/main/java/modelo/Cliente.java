package modelo;

import persistencia.APCliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Cliente {
	private int dni = 0;
	private String nombre = null;
	private String domicilio = null;
	private int telefono = 0;
	private String mail = null;
    private MedioDePago medioDePago = null;
	private Collection<Contrato> contratosCliente = new ArrayList<Contrato>();
	
	public Cliente(){
	}

	private boolean verificarCliente(int dni){
		return false;
	}
	
	public void modificarMP(){
	
	}
	
	public void verificarEstadoCtaCte(){
		
	}
    //~ DB Methods -------------------------------------------------------------
    public void guardar(){
        if(!APCliente.exists(this)){
            APCliente.create(this);
        }else{
            APCliente.update(this);
        }
    }

    public static Cliente search(Cliente cliente){
        List<Cliente> result = APCliente.searchAll(cliente, 1);
        if(result.size() > 0){
            return result.get(0);
        }else{
            return null;
        }
    }

    //~ Overrides --------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (dni != cliente.dni) return false;
        if (telefono != cliente.telefono) return false;
        if (domicilio != null ? !domicilio.equals(cliente.domicilio) : cliente.domicilio != null) return false;
        if (mail != null ? !mail.equals(cliente.mail) : cliente.mail != null) return false;
        if (medioDePago != null ? !medioDePago.equals(cliente.medioDePago) : cliente.medioDePago != null) return false;
        if (nombre != null ? !nombre.equals(cliente.nombre) : cliente.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dni;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (domicilio != null ? domicilio.hashCode() : 0);
        result = 31 * result + telefono;
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (medioDePago != null ? medioDePago.hashCode() : 0);
        result = 31 * result + (contratosCliente != null ? contratosCliente.hashCode() : 0);
        return result;
    }


    //~ Getters & Setters ------------------------------------------------------

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public MedioDePago getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(MedioDePago medioDePago) {
        this.medioDePago = medioDePago;
    }

    public Collection<Contrato> getContratosCliente() {
        return contratosCliente;
    }

    public void setContratosCliente(Collection<Contrato> contratosCliente) {
        this.contratosCliente = contratosCliente;
    }

}