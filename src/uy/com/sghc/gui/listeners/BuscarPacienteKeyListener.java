package uy.com.sghc.gui.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import uy.com.sghc.gui.frames.BuscarPacienteFrame;

public class BuscarPacienteKeyListener implements KeyListener{

	public final BuscarPacienteFrame buscarPacienteFrame;
	//private static IFachadaPaciente fachadaPaciente = new ControlPaciente();
	
	public BuscarPacienteKeyListener(final BuscarPacienteFrame buscarPacienteFrame) {
		this.buscarPacienteFrame = buscarPacienteFrame;
	}

	@Override
	public void keyTyped(final KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void keyReleased(final KeyEvent e) {
//		try {
//			List<PacienteDto> lista = fachadaPaciente.buscarPacientes(buscarPacienteFrame.getBuscarTextField().getText());
//			int filas = this.buscarPacienteFrame.getModel().getRowCount();
//			for (int i=0;i<filas;i++) {
//				this.buscarPacienteFrame.getModel().removeRow(i);
//			}
//			
//			for (PacienteDto paciente : lista) {	
//				this.buscarPacienteFrame.getModel().addRow(new Object[]{paciente.getCi(), 
//						paciente.getNombreYApellido(),
//						"Ver Paciente"});
//			}
//			this.buscarPacienteFrame.setListaPacientes(lista);
//		} catch (final SGHCExcepcion e1) {
//			JOptionPane.showMessageDialog(this.buscarPacienteFrame, "Error al buscar los pacientes",  "Error", JOptionPane.ERROR_MESSAGE);	
//		}
	}

}
