package uy.com.sghc.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uy.com.sghc.gui.frames.BuscarPacienteFrame;
import uy.com.sghc.gui.frames.PrincipalFrame;

public class BuscarPacienteIFrameListener implements ActionListener {

	private PrincipalFrame principalFrame;
	
	public BuscarPacienteIFrameListener(PrincipalFrame principalFrame) {
		this.principalFrame = principalFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		principalFrame.abrirVentana(new BuscarPacienteFrame(this.principalFrame));
	}

}
