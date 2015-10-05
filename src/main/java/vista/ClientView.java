package vista;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mpliego on 10/5/15.
 */
public class ClientView extends JPanel implements ActionListener {

    JPanel right = new JPanel();
    CardLayout rightCards = new CardLayout();

    final static String MENU_PANEL = "Menu";
    final static String EDIT_CLIENT_PANEL = "Edit Client";

    JPanel leftPanel = new JPanel();
    JPanel clientView = new JPanel();
    JPanel editClientView = new JPanel();
    CardLayout leftCards = new CardLayout();

    JButton create = new JButton("Crear Cliente");
    private String CREATE_AC = "CREATE_AC";
    JButton edit = new JButton("Editar Cliente");
    private String EDIT_AC = "EDIT_AC";
    JButton delete = new JButton("Eliminar Cliente");
    private String DELETE_AC = "DELETE_AC";
    JButton save = new JButton("Guardar");
    private String SAVE_AC = "SAVE_AC";
    JButton cancel = new JButton("Cancelar");
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
        right.add(initEditClientControlPanel(), EDIT_CLIENT_PANEL);
        rightCards.show(right, MENU_PANEL);

        add(right, gbc);

        leftPanel.setLayout(leftCards);
        leftPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        leftPanel.setBorder(BorderFactory.createTitledBorder(border, "Clientes"));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = gbc.weighty = 80;

        leftPanel.add(initClientViewPanel(clientView), MENU_PANEL);
        leftCards.show(leftPanel, MENU_PANEL);

        add(leftPanel, gbc);
    }

    private JPanel initClientViewPanel(JPanel clientView) {
        clientView.removeAll();
        clientView.setLayout(new BorderLayout());



        return clientView;
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
        controlPanel.add(edit, gbc);
        gbc.gridy++;
        delete = new JButton("Eliminar Cliente");
        delete.setActionCommand(DELETE_AC);
        delete.addActionListener(this);
        controlPanel.add(delete, gbc);
        return controlPanel;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().equals(CREATE_AC)){

            rightCards.show(right, EDIT_CLIENT_PANEL);
        }else if(actionEvent.getActionCommand().equals(CANCEL_AC)){
            rightCards.show(right, MENU_PANEL);
        }
    }
}
