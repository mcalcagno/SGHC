package uy.com.sghc.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uy.com.sghc.gui.frames.NuevaFichaFrame;
import uy.com.sghc.gui.frames.NuevaFichaFrame.Operacion;
import uy.com.sghc.gui.frames.PrincipalFrame;
import uy.com.sghc.gui.frames.components.RoundJTextField;

public class NuevaFichaIFrameListener implements ActionListener {

	PrincipalFrame principalFrame;
	RoundJTextField cedula;
	Operacion op;
	
	public NuevaFichaIFrameListener(final PrincipalFrame principalFrame, final Operacion op, final RoundJTextField cedula) {
		this.principalFrame = principalFrame;
		this.cedula = cedula;
		this.op = op;
	}

	@Override
	public void actionPerformed(final ActionEvent actionEvent) {
		this.principalFrame.abrirVentana(new NuevaFichaFrame(this.op, this.cedula));
	}

}
