package uy.com.sghc.gui.frames;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import net.java.dev.designgridlayout.DesignGridLayout;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import uy.com.sghc.config.PropController;
import uy.com.sghc.gui.frames.components.DateLabelFormatter;
import uy.com.sghc.gui.frames.components.RoundBorder;
import uy.com.sghc.gui.frames.components.RoundJScrollPane;
import uy.com.sghc.gui.frames.components.RoundJTextArea;
import uy.com.sghc.gui.frames.components.RoundJTextField;
import uy.com.sghc.gui.listeners.NuevaFichaListener;

public class NuevaFichaFrame extends JInternalFrame {

	private static final long serialVersionUID = -5495433183198932221L;
	
	public static enum Operacion {NUEVO, EDITAR}
	private RoundJTextField numeroTextField = new RoundJTextField(40);
	
	private RoundJTextArea diagnosticoAreaField = new RoundJTextArea(6, 80);
	private RoundJScrollPane diagnosticoScroll = new RoundJScrollPane(diagnosticoAreaField);
	
	private RoundJTextArea observacionesAreaField = new RoundJTextArea(6, 80);
	private RoundJScrollPane observacionesScroll = new RoundJScrollPane(observacionesAreaField);
	
	private RoundJTextArea motivoAreaField = new RoundJTextArea(6, 80);
	private RoundJScrollPane motivoScroll = new RoundJScrollPane(motivoAreaField);
	
	private JDatePickerImpl datePicker;
	
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
		
        DesignGridLayout layout = new DesignGridLayout(container);

        JLabel cedulaLabel = new JLabel("Cédula Paciente", JLabel.LEFT);
        cedulaLabel.setFont(fuente);
        cedulaLabel.setBorder(border);
        cedulaTextField.setFont(fuente);
        cedulaTextField.setText(cedula.getText());
        cedulaTextField.setEditable(false);
        
        JLabel numeroLabel = new JLabel("Número", JLabel.LEFT);
        numeroLabel.setFont(fuente);
        numeroLabel.setBorder(border);
        numeroTextField.setFont(fuente);
        
        JLabel fechaLabel = new JLabel("Fecha", JLabel.LEFT);
		fechaLabel.setFont(fuente);
		fechaLabel.setBorder(border);
		UtilDateModel model = new UtilDateModel();
		model.setSelected(true);
		Calendar calendario = Calendar.getInstance();
		model.setDate(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH));
		JDatePanelImpl datePanel = new JDatePanelImpl(model);		
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());		
		
		JLabel motivoLabel = new JLabel("Motivo de consulta", JLabel.LEFT);
		motivoLabel.setFont(fuente);
		motivoLabel.setBorder(border);
		motivoAreaField.setFont(fuente);
		motivoAreaField.setLineWrap(true);
        
		JLabel diagnosticoLabel = new JLabel("Diagnóstico", JLabel.LEFT);
		diagnosticoLabel.setFont(fuente);
		diagnosticoLabel.setBorder(border);
		diagnosticoAreaField.setFont(fuente);
		diagnosticoAreaField.setLineWrap(true);
		
		JLabel observacionesLabel = new JLabel("Observaciones", JLabel.LEFT);
		observacionesLabel.setFont(fuente);
		observacionesLabel.setBorder(border);
		observacionesAreaField.setFont(fuente);
		observacionesAreaField.setLineWrap(true);

		layout.row().grid(cedulaLabel)			.add(cedulaTextField);
		layout.row().grid(numeroLabel)			.add(numeroTextField);
		layout.row().grid(fechaLabel)			.add(datePicker);
		layout.row().grid(motivoLabel)			.add(motivoScroll);
		layout.row().grid(diagnosticoLabel)		.add(diagnosticoScroll);
		layout.row().grid(observacionesLabel)	.add(observacionesScroll);
		layout.emptyRow();
		
		agregarBotones(op, nuevaFichaListener, layout);
		
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
	
	private void agregarBotones(final Operacion op, final NuevaFichaListener nuevaFichaListener, final DesignGridLayout layout) {
		if (op.equals(Operacion.NUEVO)) {
			ingresar.setFont(fuente);
	        ingresar.setForeground(Color.BLACK);
	        layout.row().right().add(ingresar);
	        ingresar.addActionListener(nuevaFichaListener);
		} else {
			editar.setFont(fuente);
	        editar.setForeground(Color.BLACK);        
	        layout.row().right().add(editar);	        
	        editar.addActionListener(nuevaFichaListener);
		}
	}

	public RoundJTextField getNumeroTextField() {
		return numeroTextField;
	}

	public RoundJTextArea getDiagnosticoTextField() {
		return diagnosticoAreaField;
	}

	public RoundJTextArea getObservacionesTextField() {
		return observacionesAreaField;
	}

	public RoundJTextArea getMotivoTextField() {
		return motivoAreaField;
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

	public JDatePickerImpl getDatePicker() {
		return datePicker;
	}	
}
