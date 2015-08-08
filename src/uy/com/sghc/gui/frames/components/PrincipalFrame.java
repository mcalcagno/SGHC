package uy.com.sghc.gui.frames.components;

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
			// TODO: traer de una property la clase del lookandfeel
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			
			this.desktop = new JDesktopPane();
			
			JMenuBar barra = new JMenuBar(); // create menu bar
			
	      	JMenu menuInicial = new JMenu(PropController.getPropInterfaz(PropController.DESKTOP_MENU_INICIAL));
			
			JMenuItem menuPacientes = new JMenuItem(PropController.getPropInterfaz(PropController.DESKTOP_MENU_INICIAL_PACIENTE)); 

			JMenuItem menuFichas = new JMenuItem(PropController.getPropInterfaz(PropController.DESKTOP_MENU_INICIAL_FICHAS));
			
			menuInicial.add(menuPacientes);
			menuInicial.add(menuFichas);

			barra.add(menuInicial);
			setJMenuBar(barra);
			  
			add(this.desktop);		
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        this.setVisible(false);

	        menuPacientes.addActionListener(new ActionListener() {
	            @Override
	            // display new internal window
	            public void actionPerformed(final ActionEvent e) {
	                boolean abrir = abrirVentana(new PacienteFrame());
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
            desktop.add(frame); 
            if (frame.isShowing()) {
                int x = (desktop.getWidth() / 2) - (frame.getWidth() / 2);
                int y = (desktop.getHeight() / 2) - (frame.getHeight() / 2);
                frame.setLocation(x, y);
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
