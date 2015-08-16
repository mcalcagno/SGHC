package uy.com.sghc.gui.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

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
import uy.com.sghc.gui.listeners.BuscarPacienteIFrameListener;

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
			JMenuItem menuFichas = new JMenuItem(PropController.getPropInterfaz(PropController.DESKTOP_MENU_INICIAL_FICHAS));
			
			menuPaciente.add(menuNuevoPaciente);
			menuPaciente.add(menuBuscarPaciente);
			menuPaciente.add(menuFichas);

			barra.add(menuPaciente);
			setJMenuBar(barra);
			  
			add(this.desktop);		
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        this.setVisible(false);
	        this.setLocationRelativeTo(null);

	        menuNuevoPaciente.addActionListener(new ActionListener() {
	            @Override
	            // display new internal window
	            public void actionPerformed(final ActionEvent e) {
	            	PacienteFrame pacienteFrame = new PacienteFrame(PacienteFrame.Operacion.NUEVO);	            	
	            	abrirVentana(pacienteFrame);	                
	            }
	        });
	        menuBuscarPaciente.addActionListener(new BuscarPacienteIFrameListener(this));
	        	        
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
	
	public <T extends JInternalFrame> boolean abrirVentana(final T frame) {
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

    private boolean estaCerrado(final String tituloIntFrame){
        final JInternalFrame[] activos = this.desktop.getAllFrames();
        boolean cerrado = true;
        int i=0;
        while (i<activos.length && cerrado){
           cerrado=!activos[i].getTitle().equals(tituloIntFrame.trim());
           i++;
        }
        return cerrado;
    }
    
    public void mandarAlFondoInternalFrame(final String tituloIntFrame){
        final JInternalFrame[] activos = this.desktop.getAllFrames();
        boolean encontre = false;
        int i=0;
        JInternalFrame jif = null;
        while (!encontre && i<activos.length){
        	encontre = activos[i].getTitle().equals(tituloIntFrame.trim());
        	if (encontre) {
        		jif = activos[i];
        	}
        	i++;
        }
        if (jif!=null) {
//        	jif.setSize(getWidth()/2, getHeight()/2);
        	jif.moveToBack();
        }
    }    
}
