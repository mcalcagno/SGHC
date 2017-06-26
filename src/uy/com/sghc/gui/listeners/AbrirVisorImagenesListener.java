package uy.com.sghc.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uy.com.sghc.gui.frames.PrincipalFrame;
import uy.com.sghc.gui.frames.VisorImagenesFrame;

public class AbrirVisorImagenesListener implements ActionListener {

	private PrincipalFrame principalFrame;
	
	public AbrirVisorImagenesListener(final PrincipalFrame principalFrame) {
		this.principalFrame = principalFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		principalFrame.abrirVentana(new VisorImagenesFrame());
	}

}
