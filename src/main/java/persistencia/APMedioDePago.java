package persistencia;

import modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Administrador de persistencia Cliente{@link modelo.Cliente}
 */
public class APMedioDePago {

    public static void create(TarjetaDeCredito tarjetaDeCredito, Cliente cliente){
        create(tarjetaDeCredito.getEntidad(),
                tarjetaDeCredito.getNumero(),
                tarjetaDeCredito.getFechaVencimiento(),
                cliente);
    }

    public static void create(CBU cbu, Cliente cliente){
        create(cbu.getEntidad(),
                cbu.getNumero(),
                null,
                cliente);
    }

    public static void create(String entidad,
                              String numero,
                              Date fechaVencimiento,
                              Cliente cliente){

        Connection con = PoolConnection.getPoolConnection().getConnection();
        try
        {
            String query = "INSERT INTO DBAI.dbo.MEDIOPAGO (entidad, numero, fechaVencimiento) VALUES ( ?, ?, ?);";
            PreparedStatement ps;
            ps = con.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,entidad);
            ps.setString(2,numero);
            ps.setDate(3, (fechaVencimiento!=null)?new java.sql.Date(fechaVencimiento.getTime()):null);
            int affectedRows = ps.executeUpdate();


            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                APCliente.updateMedioDePago(cliente, generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
        catch( SQLException e )
        {
            System.out.println("Mensaje Error al Borrar Producto: " + e.getMessage());
            System.out.println("Stack Trace al Borrar Producto: " + e.getStackTrace());
        }
        PoolConnection.getPoolConnection().realeaseConnection(con);
    }

    /**
     * Busca un metodo de pago en base a un ID dado.
     * @param medioPagoId Id de medio de pago.
     * @return MedioDePago Buscado.
     */
    public static MedioDePago retrieve(int medioPagoId) {
        MedioDePago result = null;
        String query = "SELECT * FROM DBAI.dbo.MEDIOPAGO WHERE medioPagoId = ?;";
        Connection con = PoolConnection.getPoolConnection().getConnection();
        try {
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ps.setInt(1, medioPagoId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                String entidad = rs.getString("entidad");
                String numero = rs.getString("numero");
                java.sql.Date fechaVencimiento = rs.getDate("fechaVencimiento");

                if(fechaVencimiento != null){
                    TarjetaDeCredito resultAux = new TarjetaDeCredito();
                    resultAux.setEntidad(entidad);
                    resultAux.setNumero(numero);
                    resultAux.setFechaVencimiento(new Date(fechaVencimiento.getTime()));
                    result = resultAux;
                }else{
                    CBU resultAux = new CBU();
                    resultAux.setEntidad(entidad);
                    resultAux.setNumero(numero);
                    result = resultAux;
                }
            }
            PoolConnection.getPoolConnection().realeaseConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}
