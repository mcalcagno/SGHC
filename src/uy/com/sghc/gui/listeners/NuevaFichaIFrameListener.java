package uy.com.sghc.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uy.com.sghc.gui.frames.NuevaFichaFrame;
import uy.com.sghc.gui.frames.NuevaFichaFrame.Operacion;
import uy.com.sghc.gui.frames.PrincipalFrame;

public class NuevaFichaIFrameListener implements ActionListener {

	PrincipalFrame principalFrame;
	Operacion op;
	
	public NuevaFichaIFrameListener(PrincipalFrame principalFrame, Operacion op) {
		this.principalFrame = principalFrame;
		this.op = op;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.principalFrame.abrirVentana(new NuevaFichaFrame(this.op, this.principalFrame));
	}

}
