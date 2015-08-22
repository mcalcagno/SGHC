package uy.com.sghc.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uy.com.sghc.gui.frames.PacienteFrame;
import uy.com.sghc.gui.frames.PacienteFrame.Operacion;
import uy.com.sghc.gui.frames.PrincipalFrame;

public class NuevoPacienteIFrameListener implements ActionListener {

	private PrincipalFrame principalFrame;
	private Operacion op;

	public NuevoPacienteIFrameListener(PrincipalFrame principalFrame, Operacion op) {
		this.principalFrame = principalFrame;
		this.op = op;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		principalFrame.abrirVentana(new PacienteFrame(op, principalFrame));
	}

}
