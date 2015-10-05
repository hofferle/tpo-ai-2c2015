package controlador;

import modelo.Cliente;
import vista.MainView;

import javax.swing.*;
import java.util.List;

/**
 * Created by mpliego on 10/5/15.
 */
public class Controlador {
    private static Controlador instance = null;

    private Controlador() {
        super();
    }

    public static Controlador getInstance() {
        if (instance == null) {
            instance = new Controlador();
        }
        return instance;
    }

    public String[][] obtenerTablaDeClientes() {
        String[][] result;
        List<Cliente> clienteList = Cliente.buscarTodos(new Cliente());
        if (clienteList.size() > 0) {
            result = new String[clienteList.size()][3];
            int index = 0;
            for (Cliente cliente : clienteList) {
                result[index] = new String[]{
                        Integer.toString(cliente.getDni()),
                        (cliente.getNombre() != null) ? cliente.getNombre() : "",
                        (cliente.getMedioDePago() != null) ? cliente.getMedioDePago().getNombre() : ""};
                index += 1;
            }
        } else {
            result = new String[0][0];
        }

        return result;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new MainView());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public String[] buscarCliente(int dni) {
        Cliente cliente = new Cliente();
        cliente.setDni(dni);
        cliente = Cliente.buscar(cliente);
        return cliente.toStringArray();
    }

    public void guardarCliente(Cliente cliente) {
        cliente.guardar();
    }

    public void eliminarCliente(int dniCliente) {
        Cliente cliente = new Cliente();
        cliente.setDni(dniCliente);
        cliente = Cliente.buscar(cliente);
        cliente.remove();
    }
}
