package uy.com.sghc.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.gui.frames.BuscarPacienteFrame;
import uy.com.sghc.logica.contoladores.ControlPaciente;
import uy.com.sghc.logica.interfaces.IFachadaPaciente;

public class BuscarPacienteListener implements ActionListener {

	public final BuscarPacienteFrame buscarPacienteFrame;
	private static IFachadaPaciente fachadaPaciente = new ControlPaciente();
	
	public BuscarPacienteListener(final BuscarPacienteFrame buscarPacienteFrame) {
		this.buscarPacienteFrame = buscarPacienteFrame;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource().equals(this.buscarPacienteFrame.getBuscarTextField())) {
			try {
				List<PacienteDto> lista = fachadaPaciente.buscarPacientes(buscarPacienteFrame.getBuscarTextField().getText());
				// limpio la tabla antes de volver a cargarla
				int filas = this.buscarPacienteFrame.getModel().getRowCount();
				for (int i=0;i<filas;i++) {
					this.buscarPacienteFrame.getModel().removeRow(0);
				}
				
				for (PacienteDto paciente : lista) {	
					this.buscarPacienteFrame.getModel().addRow(new Object[]{paciente.getCi(), 
							paciente.getNombreYApellido(),
							"Ver Paciente"});
				}
				this.buscarPacienteFrame.setListaPacientes(lista);
			} catch (final SGHCExcepcion e1) {
				JOptionPane.showMessageDialog(this.buscarPacienteFrame, "Error al buscar los pacientes",  "Error", JOptionPane.ERROR_MESSAGE);	
			}	
		}		
	}

}
