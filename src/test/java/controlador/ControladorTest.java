package controlador;

import org.junit.Before;
import org.junit.Test;
import persistencia.PoolConnection;
import util.Constants;
import util.ScriptRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;

import static org.junit.Assert.*;

public class ControladorTest {

    @Before
    public void setUp() throws Exception {
        Connection con = PoolConnection.getPoolConnection().getConnection();
        ScriptRunner runner = new ScriptRunner(con, false, true);
        runner.runScript(new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(Constants.DB_INIT_SCRIPT))));
        runner.runScript(new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(Constants.DB_INIT_CLIENTE_SCRIPT))));
        PoolConnection.getPoolConnection().realeaseConnection(con);
    }

    @Test
    public void testObtenerTablaDeClientes() throws Exception {

        String[][] tablaClientes = Controlador.getInstance().obtenerTablaDeClientes();
        assertTrue(tablaClientes.length == 3);
    }
}