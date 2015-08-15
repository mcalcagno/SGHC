package uy.com.sghc.gui.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.gui.frames.BuscarPacienteFrame;
import uy.com.sghc.logica.contoladores.ControlPaciente;
import uy.com.sghc.logica.interfaces.IFachadaPaciente;

public class BuscarPacienteKeyListener implements KeyListener{

	public final BuscarPacienteFrame buscarPacienteFrame;
	private static IFachadaPaciente fachadaPaciente = new ControlPaciente();
	private static Logger logger = Logger.getLogger(LoginListener.class);
	
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
		try {
			List<PacienteDto> lista = fachadaPaciente.buscarPacientes(buscarPacienteFrame.getBuscarTextField().getText());
			for (PacienteDto paciente : lista) {
				this.buscarPacienteFrame.getModel().addRow(new Object[]{paciente.getCi(), 
						paciente.getNombreYApellido(),
						new JButton("Ver")});
			}
		} catch (final SGHCExcepcion e1) {
			JOptionPane.showMessageDialog(this.buscarPacienteFrame, "Error al buscar los pacientes",  "Error", JOptionPane.ERROR_MESSAGE);	
		}
	}

}
