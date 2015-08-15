package uy.com.sghc.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uy.com.sghc.gui.frames.BuscarPacienteFrame;

public class BuscarPacienteListener implements ActionListener {

	private BuscarPacienteFrame buscarPacienteFrame; 
	
	public BuscarPacienteListener(final BuscarPacienteFrame buscarPacienteFrame) {
		this.buscarPacienteFrame = buscarPacienteFrame;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource().equals(this.buscarPacienteFrame.getBuscarTextField())) {
			// 
		}
	}

}
