package uy.com.sghc.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.commons.lang.StringUtils;

import uy.com.sghc.dtos.FichaDto;
import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.gui.frames.NuevaFichaFrame;
import uy.com.sghc.logica.contoladores.ControlPaciente;
import uy.com.sghc.logica.interfaces.IFachadaPaciente;

public class NuevaFichaListener implements ActionListener {

	private NuevaFichaFrame fichaFrame;
	private static IFachadaPaciente fachadaPaciente = new ControlPaciente();
	
	public NuevaFichaListener(final NuevaFichaFrame fichaFrame) {
		this.fichaFrame = fichaFrame;
	}

	@Override
	public void actionPerformed(final ActionEvent actionEvent) {
		// TODO: cambiar los textos por propiedades
		// se apretó el botón de ingresar o se ingreso un enter en algun campo 
		if (ingresarNuevaFicha(actionEvent)) {
			if (!seCumplenCamposNoNull()) {
				JOptionPane.showMessageDialog(this.fichaFrame, "Se deben ingresar todos los datos ",  "Error", JOptionPane.ERROR_MESSAGE);
			}					
			else {
				FichaDto fichaDto = new FichaDto();
				fichaDto.setDiagnostico(this.fichaFrame.getDiagnosticoTextField().getText());
				fichaDto.setMotivoConsulta(this.fichaFrame.getMotivoTextField().getText());
				fichaDto.setNumero(Long.valueOf(this.fichaFrame.getNumeroTextField().getText()));
				fichaDto.setObservaciones(this.fichaFrame.getObservacionesTextField().getText());
				final Long cedula = Long.valueOf(this.fichaFrame.getCedulaTextField().getText());
				try {
					fachadaPaciente.agregarFichaPaciente(cedula, fichaDto);
					this.fichaFrame.dispose();
				} catch (final SGHCExcepcion ex) {
					JOptionPane.showMessageDialog(this.fichaFrame, "Error al crear la ficha. ",  "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}		
	}

	private boolean seCumplenCamposNoNull() {
		return StringUtils.isNotEmpty(this.fichaFrame.getFecha().getText()) && 
				StringUtils.isNotEmpty(this.fichaFrame.getNumeroTextField().getText()) && 
				StringUtils.isNotEmpty(this.fichaFrame.getDiagnosticoTextField().getText()) &&
				StringUtils.isNotEmpty(this.fichaFrame.getMotivoTextField().getText()) && 
				StringUtils.isNotEmpty(this.fichaFrame.getCedulaTextField().getText());
	}

	private boolean ingresarNuevaFicha(final ActionEvent e) {
		return e.getSource().equals(this.fichaFrame.getIngresar());
	}


}
