package uy.com.sghc.gui.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import uy.com.sghc.config.PropController;
import uy.com.sghc.gui.frames.PacienteFrame;

public class PrincipalFrame extends JFrame {

	private static final long serialVersionUID = -688266337205684858L;
	
	JDesktopPane desktop;
	
	public PrincipalFrame() {
		super(PropController.getPropInterfaz(PropController.DESKTOP_TITULO));
		try {
			UIManager.setLookAndFeel(PropController.getPropInterfaz(PropController.DESKTOP_LOOKANDFEEL_WIN));
			
			this.desktop = new JDesktopPane();
			
			JMenuBar barra = new JMenuBar(); // create menu bar
	      	JMenu menuPaciente = new JMenu(PropController.getPropInterfaz(PropController.DESKTOP_MENU_PACIENTE));			
			JMenuItem menuNuevoPaciente = new JMenuItem(PropController.getPropInterfaz(PropController.DESKTOP_MENU_NUEVO_PACIENTE));
			JMenuItem menuBuscarPaciente = new JMenuItem(PropController.getPropInterfaz(PropController.DESKTOP_MENU_BUSCAR_PACIENTE));
			JMenuItem menuEditarPaciente = new JMenuItem(PropController.getPropInterfaz(PropController.DESKTOP_MENU_EDITAR_PACIENTE));			
			JMenuItem menuFichas = new JMenuItem(PropController.getPropInterfaz(PropController.DESKTOP_MENU_INICIAL_FICHAS));
			
			menuPaciente.add(menuNuevoPaciente);
			menuPaciente.add(menuBuscarPaciente);
			menuPaciente.add(menuEditarPaciente);
			menuPaciente.add(menuFichas);

			barra.add(menuPaciente);
			setJMenuBar(barra);
			  
			add(this.desktop);		
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        this.setVisible(false);

	        menuNuevoPaciente.addActionListener(new ActionListener() {
	            @Override
	            // display new internal window
	            public void actionPerformed(final ActionEvent e) {
	                abrirVentana(new PacienteFrame(PacienteFrame.Operacion.NUEVO));
	            }
	        });
	        menuBuscarPaciente.addActionListener(new ActionListener() {
	            @Override
	            // display new internal window
	            public void actionPerformed(final ActionEvent e) {
	                abrirVentana(new BuscarPacienteFrame());
	            }
	        });	        
	        menuEditarPaciente.addActionListener(new ActionListener() {
	            @Override
	            // display new internal window
	            public void actionPerformed(final ActionEvent e) {
	                abrirVentana(new PacienteFrame(PacienteFrame.Operacion.EDITAR));
	            }
	        });
	        
		} catch (final ClassNotFoundException e) {
			// TODO Auto-generated catch block
		} catch (final InstantiationException e) {
			// TODO Auto-generated catch block
		} catch (final IllegalAccessException e) {
			// TODO Auto-generated catch block
		} catch (final UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
		}		
	}
	
	private <T extends JInternalFrame> boolean abrirVentana(final T frame) {
        if (estaCerrado(frame.getTitle())) {
            this.desktop.add(frame); 
            if (frame.isVisible()) {
                int x = 0;//(this.desktop.getWidth() / 2) - (frame.getWidth() / 2);
                int y = 0;//(this.desktop.getHeight() / 2) - (frame.getHeight() / 2);
                frame.setLocation(x, y);
                frame.setSize(this.desktop.getSize().width, this.desktop.getSize().height);
            }
            return true;
        }
        return false;
    }

    public boolean estaCerrado(final String tituloIntFrame){
        final JInternalFrame[] activos = this.desktop.getAllFrames();
        boolean cerrado = true;
        int i=0;
        while (i<activos.length && cerrado){
           cerrado=!activos[i].getTitle().equals(tituloIntFrame.trim());
           i++;
        }
        return cerrado;
    }
	
}
