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
import uy.com.sghc.gui.frames.components.RoundBorder;
import uy.com.sghc.gui.frames.components.RoundJTextField;
import uy.com.sghc.gui.listeners.NuevaFichaListener;

public class NuevaFichaFrame extends JInternalFrame {

	private static final long serialVersionUID = -5495433183198932221L;
	
	public static enum Operacion {NUEVO, EDITAR}
	private RoundJTextField numeroTextField = new RoundJTextField(40);
	private RoundJTextField diagnosticoTextField = new RoundJTextField(40);;
	private RoundJTextField observacionesTextField = new RoundJTextField(40);;
	private RoundJTextField motivoTextField = new RoundJTextField(40);;
	private RoundJTextField fecha = new RoundJTextField(40); 
	private RoundJTextField cedulaTextField = new RoundJTextField(40);
	private JButton ingresar = new JButton(PropController.getPropInterfaz(PropController.INT_PACIENTE_INGRESAR));
	private JButton editar = new JButton(PropController.getPropInterfaz(PropController.INT_PACIENTE_EDITAR));
	
    private static RoundBorder border = new RoundBorder(3);
    private static Font fuente = new Font("SansSerif", Font.BOLD, 14);
    private JPanel container = new JPanel();	
	
	public NuevaFichaFrame(final Operacion op, RoundJTextField cedula) {
		super(op.equals(Operacion.NUEVO) ? PropController.getPropInterfaz(PropController.INT_NUEVA_FICHA_TITULO) : 
			PropController.getPropInterfaz(PropController.INT_EDITAR_FICHA_TITULO), true, true, true, true);
		inicializarComponentes(getContentPane(), op, cedula);	
	} 

	private void inicializarComponentes(final Container cp, final Operacion op, RoundJTextField cedula) {
		NuevaFichaListener nuevaFichaListener = new NuevaFichaListener(this);
		
		cp.setLayout(new FlowLayout());

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cp.add(container);
		
        GridLayout layout = new GridLayout(10, 2);
        container.setLayout(layout);

        JLabel cedulaLabel = new JLabel("Cédula Paciente", JLabel.LEFT);
        cedulaLabel.setFont(fuente);
        cedulaLabel.setBorder(border);
        container.add(cedulaLabel);
        cedulaTextField.setFont(fuente);
        cedulaTextField.setText(cedula.getText());
        cedulaTextField.setEditable(false);
        container.add(cedulaTextField);
        
        JLabel numeroLabel = new JLabel("Número", JLabel.LEFT);
        numeroLabel.setFont(fuente);
        numeroLabel.setBorder(border);
        container.add(numeroLabel);
        numeroTextField.setFont(fuente);
        container.add(numeroTextField);
        
        JLabel fechaLabel = new JLabel("Fecha", JLabel.LEFT);
		fechaLabel.setFont(fuente);
		fechaLabel.setBorder(border);
		container.add(fechaLabel);
		fecha.setFont(fuente);
		container.add(fecha);
		
		JLabel motivoLabel = new JLabel("Motivo de consulta", JLabel.LEFT);
		motivoLabel.setFont(fuente);
		motivoLabel.setBorder(border);
		container.add(motivoLabel);
		motivoTextField.setFont(fuente);
		container.add(motivoTextField);		
        
		JLabel diagnosticoLabel = new JLabel("Diagnóstico", JLabel.LEFT);
		diagnosticoLabel.setFont(fuente);
		diagnosticoLabel.setBorder(border);
		container.add(diagnosticoLabel);
		diagnosticoTextField.setFont(fuente);
		container.add(diagnosticoTextField);
		
		JLabel observacionesLabel = new JLabel("Observaciones", JLabel.LEFT);
		observacionesLabel.setFont(fuente);
		observacionesLabel.setBorder(border);
		container.add(observacionesLabel);
		observacionesTextField.setFont(fuente);
		container.add(observacionesTextField);
		
		agregarBotones(op, nuevaFichaListener);
		
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
	}
	
	private void agregarBotones(final Operacion op, final NuevaFichaListener nuevaFichaListener) {
		if (op.equals(Operacion.NUEVO)) {
			ingresar.setFont(fuente);
	        ingresar.setForeground(Color.BLACK);
	        container.add(new JLabel(""));
	        container.add(ingresar);	  
	        ingresar.addActionListener(nuevaFichaListener);
		} else {
			editar.setFont(fuente);
	        editar.setForeground(Color.BLACK);        
	        container.add(new JLabel(""));
	        container.add(editar);	        
	        editar.addActionListener(nuevaFichaListener);
		}
	}

	public RoundJTextField getNumeroTextField() {
		return numeroTextField;
	}

	public RoundJTextField getDiagnosticoTextField() {
		return diagnosticoTextField;
	}

	public RoundJTextField getObservacionesTextField() {
		return observacionesTextField;
	}

	public RoundJTextField getMotivoTextField() {
		return motivoTextField;
	}

	public RoundJTextField getFecha() {
		return fecha;
	}

	public JButton getIngresar() {
		return ingresar;
	}

	public JButton getEditar() {
		return editar;
	}

	public RoundJTextField getCedulaTextField() {
		return cedulaTextField;
	}	
}
