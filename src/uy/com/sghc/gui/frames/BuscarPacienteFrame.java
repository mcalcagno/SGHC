package uy.com.sghc.gui.frames;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang.StringUtils;

import uy.com.sghc.config.PropController;
import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.gui.frames.components.ButtonColumn;
import uy.com.sghc.gui.frames.components.RoundBorder;
import uy.com.sghc.gui.frames.components.RoundJTextField;
import uy.com.sghc.gui.listeners.BuscarPacienteKeyListener;
import uy.com.sghc.gui.listeners.BuscarPacienteListener;

public class BuscarPacienteFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;	
    private static RoundBorder border = new RoundBorder(3);
    private static Font fuente = new Font("SansSerif", Font.BOLD, 14);
    private JPanel containerBuscar = new JPanel();
    private JScrollPane containerLista = new JScrollPane();
    private DefaultTableModel model;
    private JTable tabla;
    private List<PacienteDto> listaPacientes;
    private PrincipalFrame principalFrame;    
    private RoundJTextField buscarTextField = new RoundJTextField(20);
	
	public BuscarPacienteFrame(final PrincipalFrame principalFrame) {
		super(PropController.getPropInterfaz(PropController.INT_BUSCAR_PACIENTE_TITULO), true, true, true, true);
		inicializarComponentes(getContentPane());
		this.principalFrame = principalFrame;
	}

	private void inicializarComponentes(final Container contentPane) {
		BorderLayout layoutPrincipal = new BorderLayout();
		contentPane.setLayout(layoutPrincipal);
		
		contentPane.add(containerBuscar, BorderLayout.NORTH);		
		
        JLabel buscarLabel = new JLabel("BUSCAR PACIENTES ", JLabel.LEFT);
        buscarLabel.setFont(fuente);
        buscarLabel.setBorder(border);
        containerBuscar.add(buscarLabel);        
        buscarTextField.setFont(fuente);
        containerBuscar.add(buscarTextField);
        
        BuscarPacienteListener buscarPacienteListener = new BuscarPacienteListener(this);
        BuscarPacienteKeyListener buscarPacienteKeyListener = new BuscarPacienteKeyListener(this);
        buscarTextField.addActionListener(buscarPacienteListener);
        buscarTextField.addKeyListener(buscarPacienteKeyListener);
        
        // *** PANEL TABLA (lista pacientes) ****
        Object[] columnNames = {
                PropController.getPropInterfaz(PropController.INT_BUSCAR_PACIENTE_TABLA_COL1),
                PropController.getPropInterfaz(PropController.INT_BUSCAR_PACIENTE_TABLA_COL2), 
                StringUtils.EMPTY	
        };
        Object[][] data = {};
        model = new DefaultTableModel(data, columnNames);
        
        tabla = new JTable(model) {
			private static final long serialVersionUID = 1L;
			
			@Override
            public Class<?> getColumnClass(int columna) {
                if (columna == 1) {
                    return Long.class;
                }
                else if (columna == 2) {
                    return String.class;
                }
                else {
                	return JButton.class;
                }
            }
        };
        tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(10);

        Action verPacienteAction = new AbstractAction()
        {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				final PacienteFrame pacienteFrame = new PacienteFrame(PacienteFrame.Operacion.EDITAR);
				pacienteFrame.setPacienteDtoEditar(buscarPaciente(listaPacientes, (Long)model.getValueAt(tabla.getSelectedRow(), 0)));
            	principalFrame.abrirVentana(pacienteFrame);	
        		principalFrame.mandarAlFondoInternalFrame(PropController.getPropInterfaz(PropController.INT_BUSCAR_PACIENTE_TITULO));
            }

			private PacienteDto buscarPaciente(final List<PacienteDto> listaPacientes, final Long cedula) {
				final Iterator<PacienteDto> it = listaPacientes.iterator();
				boolean encontre = false;
				PacienteDto pacienteDto = null;
				while (!encontre && it.hasNext()) {
					pacienteDto = it.next();
					encontre = pacienteDto.getCi().equals(cedula);
				}
				return pacienteDto;
			}
        };
		
        ButtonColumn but = new ButtonColumn(tabla, verPacienteAction, 2);
        but.setMnemonic(KeyEvent.KEY_PRESSED);        
        
        containerLista.add(tabla);	
        containerLista.setPreferredSize(new Dimension(600, 400));
                
        containerLista = new JScrollPane(tabla);
        
		contentPane.add(containerLista, BorderLayout.SOUTH);        		
        		
		InternalFrameAdapter internalFrameAdapter = new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(final InternalFrameEvent arg0) {
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

	public RoundJTextField getBuscarTextField() {
		return buscarTextField;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(final DefaultTableModel model) {
		this.model = model;
	}

	public List<PacienteDto> getListaPacientes() {
		return listaPacientes;
	}

	public void setListaPacientes(final List<PacienteDto> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}	
}