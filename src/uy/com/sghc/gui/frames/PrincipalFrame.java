package uy.com.sghc.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import uy.com.sghc.config.PropController;
import uy.com.sghc.gui.frames.PacienteFrame.Operacion;
import uy.com.sghc.gui.listeners.BuscarPacienteIFrameListener;
import uy.com.sghc.gui.listeners.NuevaFichaListener;
import uy.com.sghc.gui.listeners.NuevoPacienteIFrameListener;

public class PrincipalFrame extends JFrame {

	private static final long serialVersionUID = -688266337205684858L;

	JDesktopPane desktop;

	private JPanel panelIzquierdo;
	
	public PrincipalFrame() {
		super(PropController.getPropInterfaz(PropController.DESKTOP_TITULO));
		try {
			Image icon = new ImageIcon(getClass().getResource(PropController.getPropInterfaz(PropController.DESKTOP_ICON))).getImage();
	        setIconImage(icon);
			
			construyePanelIzquierdo();
			getContentPane().setLayout(new BorderLayout());
			getContentPane().add(panelIzquierdo, BorderLayout.WEST);
						
			UIManager.setLookAndFeel(PropController.getPropInterfaz(PropController.DESKTOP_LOOKANDFEEL_WIN));
			this.desktop = new JDesktopPane();
			this.desktop.setBackground(Color.DARK_GRAY);
			
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

	        menuNuevoPaciente.addActionListener(new NuevoPacienteIFrameListener(this, Operacion.NUEVO));
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
	
	private void construyePanelIzquierdo() {
		panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.PAGE_AXIS));
        panelIzquierdo.setBackground(Color.LIGHT_GRAY);
        
        // TODO: ver la manera de que los botones queden de igual tamaño 
        JButton botonNuevoPaciente = addIcon(panelIzquierdo, new Point(10, 10),   "NUEVO PACIENTE   ", UIManager.getIcon("Tree.openIcon"));
		JButton botonBuscarPaciente = addIcon(panelIzquierdo, new Point(10, 100), "BUSCAR PACIENTE", UIManager.getIcon("FileChooser.listViewIcon"));
		JButton botonNuevaFicha = addIcon(panelIzquierdo, new Point(10, 100), "    BUSCAR FICHA   ", UIManager.getIcon("Tree.openIcon"));
		
		botonNuevoPaciente.addActionListener(new NuevoPacienteIFrameListener(this, PacienteFrame.Operacion.NUEVO));
		botonBuscarPaciente.addActionListener(new BuscarPacienteIFrameListener(this));	
		botonNuevaFicha.addActionListener(new NuevaFichaListener(this));
		try {
			botonNuevoPaciente.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(PropController.getPropInterfaz(PropController.DESKTOP_BTN_NUEVOPACIENTE_ICON)))));
			botonBuscarPaciente.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(PropController.getPropInterfaz(PropController.DESKTOP_BTN_BUSCARPACIENTE_ICON)))));
			botonNuevaFicha.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(PropController.getPropInterfaz(PropController.DESKTOP_BTN_FICHA_ICON)))));
			
		} catch (Exception e) {
			// TODO
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
    			Icon icon = new ImageIcon(getClass().getResource("/imagenes/IconoFrame.png"));
    	        frame.setFrameIcon(icon);
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
        	jif.moveToBack();
        }
    }
    
    private static JButton addIcon(final JComponent pane, final Point p, final String text, final Icon icon) {
        final JButton button = new JButton(text, icon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setBounds(new Rectangle(p, button.getPreferredSize()));
        pane.add(button);
        return button;
//        Image img = ImageIO.read(getClass().getResource("resources/water.bmp"));
//        button.setIcon(new ImageIcon(img));
    }
    
    /*
     *	UIManager.getIcon(	 
      	Tree.collapsedIcon
		FileChooser.directoryIcon
		FileChooser.detailsViewIcon
		OptionPane.questionIcon
		FileChooser.newFolderIcon
		FileView.floppyDriveIcon
		Tree.openIcon
		Tree.expandedIcon
		OptionPane.informationIcon
		Tree.closedIcon
		Tree.leafIcon
		FileChooser.upFolderIcon
		OptionPane.errorIcon
		ToolBar.handleIcon
		FileChooser.floppyDriveIcon
		FileChooser.fileIcon
		RadioButton.icon
		FileView.fileIcon
     */
}
