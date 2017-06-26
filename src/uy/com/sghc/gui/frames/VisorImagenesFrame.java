package uy.com.sghc.gui.frames;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.filechooser.FileFilter;

import uy.com.sghc.config.PropController;

public class VisorImagenesFrame extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JLabel labelImagen;
	private JMenuItem openItem;

	public VisorImagenesFrame() {
		super("Visor de Imagen", true, true, true, true);
		inicializarComponentes();	
	}

	private void inicializarComponentes() {
		setSize(300, 400);
		
	    final JMenuBar barraMenu = new JMenuBar();
	    final JMenu menu = new JMenu("File");
	    this.openItem = new JMenuItem("Open");
	    this.openItem.addActionListener(this);
	    menu.add(openItem);
	    barraMenu.add(menu);
	    this.setJMenuBar(barraMenu);
	
	    labelImagen = new JLabel();
	    final Container contentPane = getContentPane();
	    contentPane.add(labelImagen, "Center");
	    
	    final InternalFrameAdapter internalFrameAdapter = new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(final InternalFrameEvent arg0) {
                int i = JOptionPane.showConfirmDialog(null, PropController.getPropMessage(PropController.MESS_CERRAR_VENTANA), 
                		"Cerrar", JOptionPane.YES_NO_OPTION);
                if (i==0) {
                    dispose();
                }
            }
        };		
		this.pack();
		this.setVisible(true);
		this.setAutoscrolls(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addInternalFrameListener(internalFrameAdapter);		
	}

	public void actionPerformed(final ActionEvent evt) {
		if (evt.getSource() == openItem) {
			final JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));

			chooser.setFileFilter(new FileFilter() {
				public boolean accept(final File f) {
					return (f.getName().toLowerCase().endsWith(".gif") || f.getName().toLowerCase().endsWith(".jpg") || 
						f.getName().toLowerCase().endsWith(".png")|| f.isDirectory());
				}

		        public String getDescription() {
		          return "GIF/JPG/PNG Images";
		        }		        
			});
			
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				labelImagen.setIcon(new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));
			}
		}
	}
}
