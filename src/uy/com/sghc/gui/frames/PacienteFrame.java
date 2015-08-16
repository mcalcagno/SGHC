package uy.com.sghc.gui.frames;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import uy.com.sghc.config.PropController;
import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.gui.frames.components.RoundBorder;
import uy.com.sghc.gui.frames.components.RoundJTextField;
import uy.com.sghc.gui.listeners.EditarPacienteListener;
import uy.com.sghc.gui.listeners.NuevoPacienteListener;

/**
 * Created by user on 24/12/2014.
 */
public class PacienteFrame extends JInternalFrame {
	
	public static enum Operacion {NUEVO, EDITAR}
	
	private static final long serialVersionUID = 1L; 
	// Ventana de ingreso y modificacion de los pacientes
	
    private static RoundBorder border = new RoundBorder(3);
    private static Font fuente = new Font("SansSerif", Font.BOLD, 14);
    private JPanel container = new JPanel();	
	private RoundJTextField cedulaTextField = new RoundJTextField(20);
	private RoundJTextField primerNombreTextField = new RoundJTextField(20);
	private RoundJTextField segundoNombreTextField = new RoundJTextField(20);
	private RoundJTextField primerApellidoTextField = new RoundJTextField(20);
	private RoundJTextField segundoApellidoTextField = new RoundJTextField(20);
	private RoundJTextField direccionTextField = new RoundJTextField(20);
	private RoundJTextField celularTextField = new RoundJTextField(20);
	private RoundJTextField telefonoTextField = new RoundJTextField(20);
	private RoundJTextField mailTextField = new RoundJTextField(20);
	private JButton ingresar = new JButton(PropController.getPropInterfaz(PropController.INT_PACIENTE_INGRESAR));
	private JButton editar = new JButton(PropController.getPropInterfaz(PropController.INT_PACIENTE_EDITAR));
	
	public PacienteFrame(final Operacion op) {
		super(op.equals(Operacion.NUEVO) ? PropController.getPropInterfaz(PropController.INT_NUEVO_PACIENTE_TITULO) : 
				PropController.getPropInterfaz(PropController.INT_EDITAR_PACIENTE_TITULO), true, true, true, true);								
		inicializarComponentes(getContentPane(), op);
	}
	
	private void inicializarComponentes(final Container cp, final Operacion op) {
		
		NuevoPacienteListener nuevoPacienteListener = new NuevoPacienteListener(this);
		EditarPacienteListener editarPacienteListener = new EditarPacienteListener(this);
		
        cp.setLayout(new FlowLayout());

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cp.add(container);
		
        GridLayout layout = new GridLayout(9, 2);
        container.setLayout(layout);
                
        JLabel ciLabel = new JLabel("Cédula de Identidad", JLabel.LEFT);
        ciLabel.setFont(fuente);
        ciLabel.setBorder(border);
        container.add(ciLabel);        
        cedulaTextField.setFont(fuente);
        container.add(cedulaTextField);
        
        JLabel nomLabel = new JLabel("Nombres", JLabel.LEFT);
        nomLabel.setFont(fuente);
        nomLabel.setBorder(border);      
        container.add(nomLabel);        
        JPanel grupoNombre = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        primerNombreTextField.setFont(fuente);
        segundoNombreTextField.setFont(fuente);
        grupoNombre.add(primerNombreTextField);
        grupoNombre.add(segundoNombreTextField);
        grupoNombre.setVisible(true);
        container.add(grupoNombre);
        
        JLabel apellidoLabel = new JLabel("Apellidos", JLabel.LEFT);
        apellidoLabel.setFont(fuente);
        apellidoLabel.setBorder(border);
        container.add(apellidoLabel);       
        JPanel grupoApellidos = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        primerApellidoTextField.setFont(fuente);
        segundoApellidoTextField.setFont(fuente);
        grupoApellidos.add(primerApellidoTextField);
        grupoApellidos.add(segundoApellidoTextField);
        grupoApellidos.setVisible(true);
        container.add(grupoApellidos);
        
        JLabel direccionLabel = new JLabel("Dirección", JLabel.LEFT);
        direccionLabel.setFont(fuente);
        direccionLabel.setBorder(border);
        container.add(direccionLabel);        
        direccionTextField.setFont(fuente);
        container.add(direccionTextField);

        JLabel telefonoLabel = new JLabel("Teléfono", JLabel.LEFT);
        telefonoLabel.setFont(fuente);
        telefonoLabel.setBorder(border);
        container.add(telefonoLabel);      
        telefonoTextField.setFont(fuente);
        container.add(telefonoTextField);

        JLabel celularLabel = new JLabel("Celular", JLabel.LEFT);
        celularLabel.setFont(fuente);
        celularLabel.setBorder(border);
        container.add(celularLabel);      
        celularTextField.setFont(fuente);
        container.add(celularTextField);
        
        JLabel mailLabel = new JLabel("Mail", JLabel.LEFT);
        mailLabel.setFont(fuente);
        mailLabel.setBorder(border);
        container.add(mailLabel);      
        mailTextField.setFont(fuente);
        container.add(mailTextField);
        
        agregarListeners(op, nuevoPacienteListener, editarPacienteListener);        

        InternalFrameAdapter internalFrameAdapter = new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent arg0) {
                int i = JOptionPane.showConfirmDialog(null, PropController.getPropMessage(PropController.MESS_CERRAR_VENTANA),
                        "Cerrar", JOptionPane.YES_NO_OPTION);
                if (i==0) {
                    dispose();
                }
            }
        };		
		this.pack();
		this.setVisible(true);
		this.setAutoscrolls(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addInternalFrameListener(internalFrameAdapter);        
        
        cedulaTextField.requestFocus();
	}

	private void agregarListeners(final Operacion op, final NuevoPacienteListener nuevoPacienteListener, final EditarPacienteListener editarPacienteListener) {
		if (op.equals(Operacion.NUEVO)) {
	        ingresar.setFont(fuente);
	        ingresar.setForeground(Color.BLACK);        
	        container.add(new JLabel(""));
	        container.add(ingresar);
	        ingresar.addActionListener(nuevoPacienteListener);
		    cedulaTextField.addActionListener(nuevoPacienteListener);
	        primerNombreTextField.addActionListener(nuevoPacienteListener);
	        segundoNombreTextField.addActionListener(nuevoPacienteListener);
	        primerApellidoTextField.addActionListener(nuevoPacienteListener);
	        segundoApellidoTextField.addActionListener(nuevoPacienteListener);
	        direccionTextField.addActionListener(nuevoPacienteListener);
	        telefonoTextField.addActionListener(nuevoPacienteListener);
	        mailTextField.addActionListener(nuevoPacienteListener);
	        celularTextField.addActionListener(nuevoPacienteListener);
        } else {
	        editar.setFont(fuente);
	        editar.setForeground(Color.BLACK);        
	        container.add(new JLabel(""));
	        container.add(editar);
	        editar.addActionListener(editarPacienteListener);       
		    cedulaTextField.addActionListener(editarPacienteListener);
	        primerNombreTextField.addActionListener(editarPacienteListener);
	        segundoNombreTextField.addActionListener(editarPacienteListener);
	        primerApellidoTextField.addActionListener(editarPacienteListener);
	        segundoApellidoTextField.addActionListener(editarPacienteListener);
	        direccionTextField.addActionListener(editarPacienteListener);
	        telefonoTextField.addActionListener(editarPacienteListener);
	        mailTextField.addActionListener(editarPacienteListener);
	        celularTextField.addActionListener(editarPacienteListener);
        }
	}
	
	public RoundJTextField getCedulaTextField() {
		return cedulaTextField;
	}
	public RoundJTextField getPrimerNombreTextField() {
		return primerNombreTextField;
	}
	public RoundJTextField getSegundoNombreTextField() {
		return segundoNombreTextField;
	}
	public RoundJTextField getPrimerApellidoTextField() {
		return primerApellidoTextField;
	}
	public RoundJTextField getSegundoApellidoTextField() {
		return segundoApellidoTextField;
	}
	public RoundJTextField getDireccionTextField() {
		return direccionTextField;
	}
	public RoundJTextField getCelularTextField() {
		return celularTextField;
	}
	public RoundJTextField getTelefonoTextField() {
		return telefonoTextField;
	}
	public JButton getIngresar() {
		return ingresar;
	}
	public JButton getEditar() {
		return editar;
	}
	public RoundJTextField getMailTextField() {
		return mailTextField;
	}

	public void setPacienteDtoEditar(final PacienteDto pacienteDtoEditar) {
		cedulaTextField.setText(String.valueOf(pacienteDtoEditar.getCi()));
		cedulaTextField.setEditable(false);
		primerNombreTextField.setText(pacienteDtoEditar.getPrimerNombre());
		segundoNombreTextField.setText(pacienteDtoEditar.getSegundoNombre());
		primerApellidoTextField.setText(pacienteDtoEditar.getPrimerApellido());
		segundoApellidoTextField.setText(pacienteDtoEditar.getSegundoApellido());
		direccionTextField.setText(pacienteDtoEditar.getDireccion());
		celularTextField.setText(pacienteDtoEditar.getCelular());
		telefonoTextField.setText(pacienteDtoEditar.getTelefono());
		mailTextField.setText(pacienteDtoEditar.getMail());		
	}	
}
