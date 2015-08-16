package uy.com.sghc.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.commons.lang.StringUtils;

import uy.com.sghc.config.PropController;
import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.gui.frames.PacienteFrame;
import uy.com.sghc.logica.contoladores.ControlPaciente;
import uy.com.sghc.logica.interfaces.IFachadaPaciente;

public class EditarPacienteListener implements ActionListener {

	private PacienteFrame pacienteFrame;
	private static IFachadaPaciente fachadaPaciente = new ControlPaciente();
	
	public EditarPacienteListener(final PacienteFrame pacienteFrame) {
		this.pacienteFrame = pacienteFrame;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		// se apretó el botón de ingresar o se ingreso un enter en algun campo 
		if (editarPaciente(e)) {
			if (!seCumplenCamposNoNull()) {
				JOptionPane.showMessageDialog(this.pacienteFrame, PropController.getPropInterfaz(PropController.MESS_PACIENTE_VALIDA_DATOS),  "Error", JOptionPane.ERROR_MESSAGE);
			}					
			else {				
				if (JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(this.pacienteFrame, PropController.getPropInterfaz(PropController.MESS_PACIENTE_EDITARPACIENTE), 
						"Confirmar", JOptionPane.YES_NO_OPTION)) {				
					PacienteDto pacienteDto = new PacienteDto();
					pacienteDto.setCi(Long.valueOf(this.pacienteFrame.getCedulaTextField().getText()));
					pacienteDto.setCelular(this.pacienteFrame.getCelularTextField().getText());
					pacienteDto.setDireccion(this.pacienteFrame.getDireccionTextField().getText());
					pacienteDto.setMail(this.pacienteFrame.getMailTextField().getText());
					pacienteDto.setPrimerApellido(this.pacienteFrame.getPrimerApellidoTextField().getText());
					pacienteDto.setSegundoApellido(this.pacienteFrame.getSegundoApellidoTextField().getText());
					pacienteDto.setPrimerNombre(this.pacienteFrame.getPrimerNombreTextField().getText());
					pacienteDto.setSegundoNombre(this.pacienteFrame.getSegundoNombreTextField().getText());
					pacienteDto.setTelefono(this.pacienteFrame.getTelefonoTextField().getText());
					try {
						fachadaPaciente.editarPaciente(pacienteDto);
						this.pacienteFrame.dispose();
					} catch (final SGHCExcepcion e1) {
						JOptionPane.showMessageDialog(this.pacienteFrame, PropController.getPropInterfaz(PropController.MESS_PACIENTE_ERROREDITAR),  
								"Error", JOptionPane.ERROR_MESSAGE);
					}					
				}
			}
		}			
	}
	
	private boolean editarPaciente(final ActionEvent e) {
		return e.getSource().equals(this.pacienteFrame.getEditar()) || e.getSource().equals(this.pacienteFrame.getCedulaTextField()) ||
		e.getSource().equals(this.pacienteFrame.getPrimerNombreTextField()) || 
		e.getSource().equals(this.pacienteFrame.getSegundoNombreTextField()) ||
		e.getSource().equals(this.pacienteFrame.getPrimerApellidoTextField()) ||
		e.getSource().equals(this.pacienteFrame.getSegundoApellidoTextField()) || 
		e.getSource().equals(this.pacienteFrame.getDireccionTextField()) ||
		e.getSource().equals(this.pacienteFrame.getTelefonoTextField()) ||
		e.getSource().equals(this.pacienteFrame.getCelularTextField()) ||
		e.getSource().equals(this.pacienteFrame.getMailTextField());
	}
	
	/**
	 * Retorna true si los campos tienen algun valor, todos menos el segundo nombre, el mail y segundo apellido 
	 * @return
	 */
	private boolean seCumplenCamposNoNull() {
		return StringUtils.isNotEmpty(this.pacienteFrame.getCedulaTextField().getText()) &&
		StringUtils.isNotEmpty(this.pacienteFrame.getPrimerNombreTextField().getText()) && 
		StringUtils.isNotEmpty(this.pacienteFrame.getPrimerApellidoTextField().getText()) &&		
		StringUtils.isNotEmpty(this.pacienteFrame.getDireccionTextField().getText()) &&
		StringUtils.isNotEmpty(this.pacienteFrame.getTelefonoTextField().getText()) &&
		StringUtils.isNotEmpty(this.pacienteFrame.getCelularTextField().getText());
	}	
}
