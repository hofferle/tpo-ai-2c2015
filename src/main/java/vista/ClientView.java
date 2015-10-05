package vista;

import com.sun.org.apache.bcel.internal.generic.Select;
import controlador.Controlador;
import modelo.Cliente;
import vista.table.UnmodifiableTableModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by mpliego on 10/5/15.
 */
public class ClientView extends JPanel implements ActionListener, ListSelectionListener, ItemListener {

    private JPanel right = new JPanel();
    private CardLayout rightCards = new CardLayout();

    final static String MENU_PANEL = "Menu";
    final static String EDIT_CLIENT_MENU_PANEL = "Edit Client Menu";
    final static String EDIT_CLIENT_PANEL = "Edit Client";
    final static String LIST_CLIENTS_PANEL = "List Clients";


    private JPanel leftPanel = new JPanel();
    private JPanel clientView = new JPanel();
    private JPanel clientEditView = new JPanel();
    private String[] clientColuntNames = {"DNI", "Nombre", "Medio De Pago"};
    private String[][] clientTableElements;


    private JPanel editClientView = new JPanel();
    private CardLayout leftCards = new CardLayout();

    private JTable tablaClientes;
    private JTextField dniTextField;
    private JTextField nombreTextField;
    private JTextField domicilioField;
    private JTextField telefonoField;
    private JTextField mailField;
    private JTextField entidadField;
    private JTextField numeroField;
    private JTextField fechaField;
    private JComboBox medioPagoComboBox;
    final static String MEDIO_PAGO_EFECTIVO = "Efectivo";
    final static String MEDIO_PAGO_TC = "TarjetaDeCredito";
    final static String MEDIO_PAGO_CBU = "CBU";
    private String medioDePagoCBItems[] = {
            MEDIO_PAGO_EFECTIVO,
            MEDIO_PAGO_CBU,
            MEDIO_PAGO_TC};

    private JButton create = new JButton("Crear Cliente");
    private String CREATE_AC = "CREATE_AC";
    private JButton edit = new JButton("Editar Cliente");
    private String EDIT_AC = "EDIT_AC";
    private JButton delete = new JButton("Eliminar Cliente");
    private String DELETE_AC = "DELETE_AC";
    private JButton save = new JButton("Guardar");
    private String SAVE_AC = "SAVE_AC";
    private JButton cancel = new JButton("Cancelar");
    private String CANCEL_AC = "CANCEL_AC";

    public ClientView() {
        initUI();
    }

    private void initUI() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Border border = BorderFactory.createEtchedBorder();

        right.setLayout(rightCards);
        right.setBorder(BorderFactory.createTitledBorder(border, "Controles"));
        gbc.gridx = gbc.gridy = 0;
        gbc.gridwidth = gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = gbc.weighty = 20;

        right.add(initRightPane(), MENU_PANEL);
        right.add(initEditClientControlPanel(), EDIT_CLIENT_MENU_PANEL);
        rightCards.show(right, MENU_PANEL);

        add(right, gbc);

        leftPanel.setLayout(leftCards);
        leftPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        leftPanel.setBorder(BorderFactory.createTitledBorder(border, "Clientes"));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = gbc.weighty = 80;

        leftPanel.add(initClientViewPanel(clientView), LIST_CLIENTS_PANEL);
        leftCards.show(leftPanel, LIST_CLIENTS_PANEL);

        add(leftPanel, gbc);
    }

    private JPanel initClientViewPanel(JPanel clientView) {
        clientView.removeAll();
        clientView.setLayout(new BorderLayout());

        clientTableElements = Controlador.getInstance().obtenerTablaDeClientes();
        tablaClientes = new JTable(new UnmodifiableTableModel(clientTableElements, clientColuntNames));
        tablaClientes.getSelectionModel().addListSelectionListener(this);
        clientView.add(tablaClientes);
        return clientView;
    }

    private JPanel initEditClientPanel(String[] cliente){
        JPanel editClienteView = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gbc.gridy = 0;
        gbc.gridwidth = gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        JLabel dni = new JLabel("DNI: ", JLabel.TRAILING);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 30;
        editClienteView.add(dni, gbc);

        dniTextField = new JTextField(8);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 70;
        if(cliente != null){
            dniTextField.setText(cliente[0]);
            dniTextField.setEnabled(false);
        }
        editClienteView.add(dniTextField, gbc);

        JLabel nombreLabel = new JLabel("Nombre: ", JLabel.TRAILING);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 30;
        editClienteView.add(nombreLabel, gbc);

        nombreTextField = new JTextField(40);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 70;
        if(cliente != null){
            nombreTextField.setText(cliente[1]);
        }
        editClienteView.add(nombreTextField, gbc);

        JLabel domicilioLabel = new JLabel("Domicilio: ", JLabel.TRAILING);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 30;
        editClienteView.add(domicilioLabel, gbc);

        domicilioField = new JTextField(40);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 70;
        if(cliente != null){
            domicilioField.setText(cliente[2]);
        }
        editClienteView.add(domicilioField, gbc);

        JLabel telefonoLabel = new JLabel("Telefono: ", JLabel.TRAILING);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 30;
        editClienteView.add(telefonoLabel, gbc);

        telefonoField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 70;
        if(cliente != null){
            telefonoField.setText(cliente[3]);
        }
        editClienteView.add(telefonoField, gbc);

        JLabel mailLabel = new JLabel("Mail: ", JLabel.TRAILING);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 30;
        editClienteView.add(mailLabel, gbc);

        mailField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 70;
        if(cliente != null){
            mailField.setText(cliente[4]);
        }
        editClienteView.add(mailField, gbc);

        JLabel modoLabel = new JLabel("Modo de Pago: ", JLabel.TRAILING);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 30;
        editClienteView.add(modoLabel, gbc);

        medioPagoComboBox = new JComboBox(medioDePagoCBItems);
        int index = -1;
        if(cliente != null){
            switch (cliente.length - 6){
                case 0:
                    index = 0;
                    break;
                case 2:
                    index = 1;
                    break;
                case 4:
                    index = 2;
                    break;
                default:
                    index = -1;
            }
        }
        medioPagoComboBox.setSelectedIndex(index);
        medioPagoComboBox.setEditable(false);
        medioPagoComboBox.addItemListener(this);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 70;
        editClienteView.add(medioPagoComboBox, gbc);

        JLabel entidadLabel = new JLabel("Entidad: ", JLabel.TRAILING);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 30;
        editClienteView.add(entidadLabel, gbc);

        entidadField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 70;
        if(cliente != null && cliente.length > 6){
            entidadField.setEnabled(true);
            entidadField.setText(cliente[6]);
        }else {
            entidadField.setEnabled(false);
        }
        editClienteView.add(entidadField, gbc);

        JLabel numeroLabel = new JLabel("Numero: ", JLabel.TRAILING);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 30;
        editClienteView.add(numeroLabel, gbc);

        numeroField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.weightx = 70;
        if(cliente != null && cliente.length > 6){
            numeroField.setEnabled(true);
            numeroField.setText(cliente[7]);
        }else {
            numeroField.setEnabled(false);
        }
        editClienteView.add(numeroField, gbc);

        JLabel fechaLabel = new JLabel("Fecha: ", JLabel.TRAILING);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.weightx = 30;
        editClienteView.add(fechaLabel, gbc);

        fechaField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.weightx = 70;
        if(cliente != null && cliente.length > 8){
            fechaField.setEnabled(true);
            fechaField.setText(cliente[8]);
        }else{
            fechaField.setEnabled(false);
        }
        editClienteView.add(fechaField, gbc);
        this.repaint();
        this.revalidate();

        return editClienteView;
    }

    private JPanel initEditClientControlPanel() {
        JPanel editClientControlPanel = new JPanel();
        editClientControlPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        gbc.ipady = 20;

        save = new JButton("Guardar");
        save.setActionCommand(SAVE_AC);
        save.addActionListener(this);
        editClientControlPanel.add(save, gbc);
        gbc.gridy++;
        cancel = new JButton("Cancelar");
        cancel.setActionCommand(CANCEL_AC);
        cancel.addActionListener(this);
        editClientControlPanel.add(cancel, gbc);
        gbc.gridy++;
        return editClientControlPanel;
    }

    private JPanel initRightPane() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        gbc.ipady = 20;

        create = new JButton("Crear Cliente");
        create.setActionCommand(CREATE_AC);
        create.addActionListener(this);
        controlPanel.add(create, gbc);
        gbc.gridy++;
        edit = new JButton("Editar Cliente");
        edit.setActionCommand(EDIT_AC);
        edit.addActionListener(this);
        edit.setEnabled(false);
        controlPanel.add(edit, gbc);
        gbc.gridy++;
        delete = new JButton("Eliminar Cliente");
        delete.setActionCommand(DELETE_AC);
        delete.setEnabled(false);
        delete.addActionListener(this);
        controlPanel.add(delete, gbc);
        return controlPanel;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals(CREATE_AC)) {
            leftPanel.remove(editClientView);
            editClientView = initEditClientPanel(null);
            leftPanel.add(EDIT_CLIENT_PANEL, editClientView);
            leftCards.show(leftPanel, EDIT_CLIENT_PANEL);
            rightCards.show(right, EDIT_CLIENT_MENU_PANEL);
        }else if (actionEvent.getActionCommand().equals(EDIT_AC)) {
            if(tablaClientes.getSelectedRow() != -1){

                String[] cliente = Controlador.getInstance().buscarCliente(Integer.parseInt(
                        clientTableElements[tablaClientes.getSelectedRow()][0]));
                leftPanel.remove(editClientView);
                editClientView = initEditClientPanel(cliente);
                leftPanel.add(EDIT_CLIENT_PANEL, editClientView);
                leftCards.show(leftPanel, EDIT_CLIENT_PANEL);
                rightCards.show(right, EDIT_CLIENT_MENU_PANEL);
            }
        }else if (actionEvent.getActionCommand().equals(CANCEL_AC)) {
            edit.setEnabled(false);
            delete.setEnabled(false);
            tablaClientes.clearSelection();
            rightCards.show(right, MENU_PANEL);
            leftCards.show(leftPanel, LIST_CLIENTS_PANEL);
        }
        this.repaint();
        this.revalidate();
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if(tablaClientes.getSelectedRow() != -1){
            edit.setEnabled(true);
            delete.setEnabled(true);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        switch (medioPagoComboBox.getSelectedIndex()){
            case 1:
                entidadField.setEnabled(true);
                numeroField.setEnabled(true);
                fechaField.setEnabled(false);
                break;
            case 2:
                entidadField.setEnabled(true);
                numeroField.setEnabled(true);
                fechaField.setEnabled(true);
                break;
            default:
                entidadField.setEnabled(false);
                numeroField.setEnabled(false);
                fechaField.setEnabled(false);
        }
    }
}
