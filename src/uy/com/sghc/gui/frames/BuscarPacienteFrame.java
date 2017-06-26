package uy.com.sghc.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.lang.StringUtils;

import uy.com.sghc.config.PropController;
import uy.com.sghc.dtos.FichaDto;
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
    private RoundJTextField buscarTextField = new RoundJTextField(30);
    final JButton buscarButton = new JButton("Buscar");
	
	public BuscarPacienteFrame(final PrincipalFrame principalFrame) {
		super(PropController.getPropInterfaz(PropController.INT_BUSCAR_PACIENTE_TITULO), true, true, true, true);
		inicializarComponentes(getContentPane());
		this.principalFrame = principalFrame;
	}

	private void inicializarComponentes(final Container contentPane) {
		final BorderLayout layoutPrincipal = new BorderLayout();
		contentPane.setLayout(layoutPrincipal);
		
		contentPane.add(containerBuscar, BorderLayout.NORTH);		
		
        final JLabel buscarLabel = new JLabel(PropController.getPropInterfaz(PropController.INT_BUSCAR_PACIENTE_LABEL), JLabel.LEFT);
        buscarLabel.setFont(fuente);
        buscarLabel.setBorder(border);
        containerBuscar.add(buscarLabel);        
        buscarTextField.setFont(fuente);
        containerBuscar.add(buscarTextField);
        buscarButton.setFont(fuente);
        buscarButton.setForeground(Color.BLACK);
        containerBuscar.add(buscarButton);
        
        final BuscarPacienteListener buscarPacienteListener = new BuscarPacienteListener(this);
        final BuscarPacienteKeyListener buscarPacienteKeyListener = new BuscarPacienteKeyListener(this);
        buscarTextField.addActionListener(buscarPacienteListener);
        buscarTextField.addKeyListener(buscarPacienteKeyListener);
        
        buscarButton.addActionListener(buscarPacienteListener);
        
        // *** PANEL TABLA (lista pacientes) ****
        final Object[] columnNames = {
                PropController.getPropInterfaz(PropController.INT_BUSCAR_PACIENTE_TABLA_COL1),
                PropController.getPropInterfaz(PropController.INT_BUSCAR_PACIENTE_TABLA_COL2), 
                StringUtils.EMPTY,	
                StringUtils.EMPTY
        };
        final Object[][] data = {};
        model = new DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = -966601699676101102L;
			@Override
        	public boolean isCellEditable(final int fila, final int columna) {
				if (2==columna) {
					return true;
				}
				return false;
        	}
        };
        
        tabla = new JTable(model) {
			private static final long serialVersionUID = 1L;
			
			@Override
            public Class<?> getColumnClass(final int columna) {
                if (columna == 0) {
                    return Long.class;
                }
                else if (columna == 1) {
                    return String.class;
                }
                else {
                	return JButton.class;
                }
            }
			
			@Override
	        public Component prepareRenderer(final TableCellRenderer renderer, final int row, final int column) {
	           final Component componente = super.prepareRenderer(renderer,row, column);
	           final int modeloFila = convertRowIndexToModel(row);
	           if (!isRowSelected(modeloFila)) {
	               componente.setBackground(Color.WHITE);
	           }
	           else {
	               componente.setBackground(Color.LIGHT_GRAY);
	           }
	           return componente;
	        }
        };
        
        tabla.setSelectionBackground(Color.blue);
//        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tabla.getTableHeader().setFont(fuente);
        tabla.getTableHeader().setBorder(border);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(10);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(10);
        tabla.setFont(fuente);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabla.setAutoscrolls(true);
        tabla.setSelectionBackground(new Color( 231, 247 , 252));
        tabla.setSelectionForeground(new Color( 0,0,0));        
        tabla.setGridColor(new Color(221, 221, 221));
        tabla.setBorder(border);
        
        final DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.LEFT);
        
        tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
        
        final Action verPacienteAction = new AbstractAction()
        {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(final ActionEvent evento) {
				final PacienteFrame pacienteFrame = new PacienteFrame(PacienteFrame.Operacion.EDITAR, principalFrame);
				pacienteFrame.setPacienteDtoEditar(buscarPaciente(listaPacientes, (Long)model.getValueAt(tabla.getSelectedRow(), 0)));
            	principalFrame.abrirVentana(pacienteFrame);	
        		principalFrame.mandarAlFondoInternalFrame(PropController.getPropInterfaz(PropController.INT_BUSCAR_PACIENTE_TITULO));
            }

			private PacienteDto buscarPaciente(final List<PacienteDto> listaPacientes, final Long cedula) {
				final Iterator<PacienteDto> iteradorPacientes = listaPacientes.iterator();
				boolean encontre = false;
				PacienteDto pacienteDto = null;
				while (!encontre && iteradorPacientes.hasNext()) {
					pacienteDto = iteradorPacientes.next();
					encontre = pacienteDto.getCi().equals(cedula);
				}
				return pacienteDto;
			}
        };
        
        final Action verFichaAction = new AbstractAction()
        {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(final ActionEvent evento) {
				final ListadoFichasFrame fichasFrame = new ListadoFichasFrame(principalFrame, (Long)model.getValueAt(tabla.getSelectedRow(), 0));
				fichasFrame.cargarFichas(buscarFichasPaciente((Long)model.getValueAt(tabla.getSelectedRow(), 0)));
				principalFrame.abrirVentana(fichasFrame);	
        		principalFrame.mandarAlFondoInternalFrame(PropController.getPropInterfaz(PropController.INT_BUSCAR_PACIENTE_TITULO));
            }

			private List<FichaDto> buscarFichasPaciente(final Long cedula) {
				List<FichaDto> listaFichasPaciente = new ArrayList<FichaDto>();
				return listaFichasPaciente;
			}
        };
		
        final ButtonColumn butVerPaciente = new ButtonColumn(tabla, verPacienteAction, 2);
        butVerPaciente.setMnemonic(KeyEvent.KEY_PRESSED);
        final ButtonColumn butVerFicha = new ButtonColumn(tabla, verFichaAction, 3);
        butVerFicha.setMnemonic(KeyEvent.KEY_PRESSED);        
        containerLista.add(tabla);	
        containerLista.setPreferredSize(new Dimension(600, 400));
                
        containerLista = new JScrollPane(tabla);
        
		contentPane.add(containerLista, BorderLayout.SOUTH);        		
        		
		final InternalFrameAdapter internalFrameAdapter = new InternalFrameAdapter() {
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
	
	public JButton getBuscarButton() {
		return this.buscarButton;
	}
}