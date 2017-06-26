package uy.com.sghc.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import uy.com.sghc.dtos.FichaDto;
import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.gui.frames.PacienteFrame;
import uy.com.sghc.gui.frames.PacienteFrame.Operacion;
import uy.com.sghc.logica.contoladores.ControlPaciente;
import uy.com.sghc.logica.contoladores.ControlReportes;
import uy.com.sghc.logica.interfaces.IFachadaPaciente;
import uy.com.sghc.logica.interfaces.IFachadaReportes;

public class ImprimirFichasListener implements ActionListener {

	private PacienteFrame pacienteFrame;
	private Operacion op;
	
	private static IFachadaReportes fachadaReporte = new ControlReportes();
	private static IFachadaPaciente fachadaPaciente = new ControlPaciente();
	
	public ImprimirFichasListener(final Operacion op, final PacienteFrame pacienteFrame) {
		this.op = op;
		this.pacienteFrame = pacienteFrame;
	} 
	
	@Override
	public void actionPerformed(final ActionEvent e) {
		if (op==Operacion.EDITAR) {
			PacienteDto pacienteDto = new PacienteDto();
			List<FichaDto> listaFichasDto = new ArrayList<FichaDto>();
			
			pacienteDto.setCi(Long.valueOf(this.pacienteFrame.getCedulaTextField().getText()));
			pacienteDto.setPrimerNombre(pacienteFrame.getPrimerNombreTextField().getText());
			pacienteDto.setSegundoNombre(pacienteFrame.getSegundoNombreTextField().getText());
			pacienteDto.setPrimerApellido(pacienteFrame.getPrimerApellidoTextField().getText());
			pacienteDto.setSegundoApellido(pacienteFrame.getSegundoApellidoTextField().getText());
			
			try {
				listaFichasDto = fachadaPaciente.obtenerFichasPaciente(Long.valueOf(pacienteFrame.getCedulaTextField().getText()));
				fachadaReporte.imprimirFichasPaciente(pacienteDto, listaFichasDto);
			} catch (final NumberFormatException e1) {
				JOptionPane.showMessageDialog(this.pacienteFrame, "Error al Imprimir la Historia Clínica. ",  "Error", JOptionPane.ERROR_MESSAGE);
			} catch (final SGHCExcepcion e1) {
				JOptionPane.showMessageDialog(this.pacienteFrame, "Error al Imprimir la Historia Clínica. ",  "Error", JOptionPane.ERROR_MESSAGE);
			}		
		}
	}
}
