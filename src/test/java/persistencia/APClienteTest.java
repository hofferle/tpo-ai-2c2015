package persistencia;

import modelo.Cliente;
import modelo.Efectivo;
import modelo.TarjetaDeCredito;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.Constants;
import util.ScriptRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class APClienteTest {

    @Before
    public void setUp() throws Exception {
        Connection con = PoolConnection.getPoolConnection().getConnection();
        ScriptRunner runner = new ScriptRunner(con, false, true);
        runner.runScript(new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(Constants.DB_INIT_SCRIPT))));
        con.close();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setDni(12345678);
        cliente.setNombre("NombreValue");
        cliente.setDomicilio("DomicilioValue");
        cliente.setTelefono(12345678);
        cliente.setMedioDePago(new Efectivo());
        cliente.guardar();

        Cliente cliente1 = Cliente.search(cliente);
        assertNotNull(cliente1);
        assertTrue(cliente1.equals(cliente));

        cliente.setNombre("TestValue");
        cliente.guardar();
        cliente1 = Cliente.search(cliente);
        assertNotNull(cliente1);
        assertTrue(cliente1.equals(cliente));

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),0,0,0);

        TarjetaDeCredito tarjetaDeCredito = new TarjetaDeCredito();
        tarjetaDeCredito.setFechaVencimiento(cal.getTime());
        tarjetaDeCredito.setNumero("123456");
        tarjetaDeCredito.setEntidad("Entidad Value");
        cliente.setMedioDePago(tarjetaDeCredito);

        cliente.guardar();
        cliente1 = Cliente.search(cliente);
        assertNotNull(cliente1);
        assertTrue(cliente1.equals(cliente));

    }
}