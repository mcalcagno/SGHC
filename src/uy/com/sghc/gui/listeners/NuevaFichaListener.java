package uy.com.sghc.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uy.com.sghc.gui.frames.NuevaFichaFrame;
import uy.com.sghc.gui.frames.PrincipalFrame;

public class NuevaFichaListener implements ActionListener {

	PrincipalFrame principalFrame;
	
	public NuevaFichaListener(PrincipalFrame principalFrame) {
		this.principalFrame = principalFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.principalFrame.abrirVentana(new NuevaFichaFrame());
	}

}
