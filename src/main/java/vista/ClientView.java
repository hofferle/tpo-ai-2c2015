package vista;

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

/**
 * Created by mpliego on 10/5/15.
 */
public class ClientView extends JPanel implements ActionListener, ListSelectionListener {

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

    private JPanel initEditClientPanel(Cliente cliente){
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
            dniTextField.setText(Integer.toString(cliente.getDni()));
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
            nombreTextField.setText(cliente.getNombre());
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
            domicilioField.setText(cliente.getDomicilio());
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
            telefonoField.setText(Integer.toString(cliente.getTelefono()));
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
            mailField.setText(cliente.getMail());
        }
        editClienteView.add(mailField, gbc);

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

                Cliente cliente = Controlador.getInstance().buscarCliente(Integer.parseInt(
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
}
