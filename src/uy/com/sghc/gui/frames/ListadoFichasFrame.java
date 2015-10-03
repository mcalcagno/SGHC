package uy.com.sghc.gui.frames;

import java.awt.Container;
import java.util.List;

import javax.swing.JInternalFrame;

import uy.com.sghc.config.PropController;
import uy.com.sghc.dtos.FichaDto;

public class ListadoFichasFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private PrincipalFrame principalFrame;
	
	public ListadoFichasFrame(final PrincipalFrame principalFrame, final Long cedulaPaciente) {
		super(PropController.getPropInterfaz(PropController.INT_LISTA_FICHAS_TITULO), true, true, true, true);
		inicializarComponentes(getContentPane());
		this.principalFrame = principalFrame;
	}

	private void inicializarComponentes(Container contentPane) {
		// TODO Auto-generated method stub		
	}

	public void cargarFichas(final List<FichaDto> buscarFichasPaciente) {
		// TODO Auto-generated method stub
		
	}

}
