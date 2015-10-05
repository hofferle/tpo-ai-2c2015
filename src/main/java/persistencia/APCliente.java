package persistencia;

import modelo.Cliente;
import modelo.Efectivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Administrador de persistencia Cliente{@link modelo.Cliente}
 */
public class APCliente {

    /**
     * Permite la persistencia de un objeto Cliente{@link modelo.Cliente}
     * @param cliente Cliente a persistir.
     */
    public static void create(Cliente cliente){

        Connection con = PoolConnection.getPoolConnection().getConnection();
        try
        {
            String query = "INSERT INTO DBAI.dbo.CLIENTE (DNI, nombre, domicilio, telefono, mail, medioPagoId) VALUES (?, ?, ?, ?, ?, null);";
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ps.setInt(1,cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDomicilio());
            ps.setInt(4,cliente.getTelefono());
            ps.setString(5, cliente.getMail());
            ps.execute();

            if(cliente.getMedioDePago() != null){
                cliente.getMedioDePago().guardar(cliente);
            }

        }
        catch( SQLException e )
        {
            System.out.println("Mensaje Error al Borrar Producto: " + e.getMessage());
            System.out.println("Stack Trace al Borrar Producto: " + e.getStackTrace());
        }
        try{
            PoolConnection.getPoolConnection().realeaseConnection(con);
        }catch (Exception e){
            System.out.println("Error al cerrar el pool de conecciones: " + e.getMessage());
        }
    }

    /**
     * Busca un cliente persistido en base a los atributos completados
     * del cliente pasado por parametro.
     * En caso de obtener multiples resultados se devolvera el primero.
     * En caso de no encontrar el cliente buscado devolvera null.
     *
     * @param cliente Cliente de busqueda.
     * @return Cliente completo
     */
    public static List<Cliente> searchAll(Cliente cliente, int max) {
        List<Cliente> result = new ArrayList<Cliente>();
        String query = "SELECT TOP " + max + " * FROM DBAI.dbo.CLIENTE WHERE 1=1" +
                ((cliente.getDni() != 0)?" AND DNI = ?":"")+
                ((cliente.getNombre() != null)?" AND nombre = ?":"")+
                ((cliente.getDomicilio() != null)?" AND domicilio = ?":"")+
                ((cliente.getTelefono() != 0)?" AND telefono = ?":"")+
                ((cliente.getMail() != null)?" AND mail = ?":"");
        Connection con = PoolConnection.getPoolConnection().getConnection();
        try {
            int index = 1;
            PreparedStatement ps;
            ps = con.prepareStatement(query);

            if(cliente.getDni() != 0){
                ps.setInt(index, cliente.getDni());
                index += 1;
            }
            if(cliente.getNombre() != null){
                ps.setString(index, cliente.getNombre());
                index += 1;
            }
            if(cliente.getDomicilio() != null){
                ps.setString(index, cliente.getDomicilio());
                index += 1;
            }
            if(cliente.getTelefono() != 0){
                ps.setInt(index, cliente.getTelefono());
                index += 1;
            }
            if(cliente.getMail() != null){
                ps.setString(index, cliente.getMail());
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente clienteAux = new Cliente();
                clienteAux.setDni(rs.getInt("DNI"));
                clienteAux.setNombre(rs.getString("nombre"));
                clienteAux.setDomicilio(rs.getString("domicilio"));
                clienteAux.setTelefono(rs.getInt("telefono"));
                clienteAux.setMail(rs.getString("mail"));

                // todo-mpliego Effectivo no debe ir aca
                int medioDePagoId = rs.getInt("medioPagoId");
                if(!rs.wasNull()){
                    clienteAux.setMedioDePago(APMedioDePago.retrieve(medioDePagoId));
                }else{
                    clienteAux.setMedioDePago(new Efectivo());
                }
                result.add(clienteAux);
            }
            PoolConnection.getPoolConnection().realeaseConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void updateMedioDePago(Cliente cliente, int medioDePagoId){
        Connection con = PoolConnection.getPoolConnection().getConnection();
        try
        {
            String query = "UPDATE DBAI.dbo.CLIENTE SET medioPagoId = ? WHERE DNI = ?;";
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ps.setInt(1, medioDePagoId);
            ps.setInt(2, cliente.getDni());
            ps.execute();

            PoolConnection.getPoolConnection().realeaseConnection(con);
        }
        catch( SQLException e )
        {
            System.out.println("Mensaje Error al Borrar Producto: " + e.getMessage());
            System.out.println("Stack Trace al Borrar Producto: " + e.getStackTrace());
            PoolConnection.getPoolConnection().closeConnections();
        }

    }

    public static boolean exists(Cliente cliente) {
        Cliente clienteAux = new Cliente();
        clienteAux.setDni(cliente.getDni());
        if(APCliente.searchAll(clienteAux,1).size()>0){
            return true;
        }else{
            return false;
        }
    }

    public static void update(Cliente cliente) {
        String query = "UPDATE DBAI.dbo.CLIENTE SET DNI = ?" +
                ((cliente.getNombre() != null)?", nombre = ?":"")+
                ((cliente.getDomicilio() != null)?", domicilio = ?":"")+
                ((cliente.getTelefono() != 0)?", telefono = ?":"")+
                ((cliente.getMail() != null)?", mail = ?":"")+
                " WHERE DNI = ?;";
        Connection con = PoolConnection.getPoolConnection().getConnection();
        try {
            int index = 1;
            PreparedStatement ps;
            ps = con.prepareStatement(query);

            ps.setInt(index, cliente.getDni());
            index += 1;
            if(cliente.getNombre() != null){
                ps.setString(index, cliente.getNombre());
                index += 1;
            }
            if(cliente.getDomicilio() != null){
                ps.setString(index, cliente.getDomicilio());
                index += 1;
            }
            if(cliente.getTelefono() != 0){
                ps.setInt(index, cliente.getTelefono());
                index += 1;
            }
            if(cliente.getMail() != null){
                ps.setString(index, cliente.getMail());
                index += 1;
            }
            ps.setInt(index, cliente.getDni());

            ps.execute();
            if(cliente.getMedioDePago() != null){
                cliente.getMedioDePago().guardar(cliente);
            }

            PoolConnection.getPoolConnection().realeaseConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
