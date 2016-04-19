import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;


public class VST_GUI{

	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;

	public static void main( String[] args){
		SwingUtilities.invokeLater( new Runnable() {
			public void run()
			{
		 		createAndShowGUI();
			}
		} );
 
	}
	public static void createAndShowGUI(){
		JFrame frame = new ImageFrame(WIDTH,HEIGHT);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    frame.validate();
	    frame.setVisible(true);
	}
}

//####################################################################

class ImageFrame extends JFrame{
	private final JFileChooser chooser;
	private int width = WIDTH;
	private int height = HEIGHT;

	private BufferedImage img=null;
	private String audioFileName;


	private double x;
	private double y;
	private double z;

	private JSlider zSlider;
	private MousePanel panel;

	private boolean mouseClicked;

	//=========================
	public ImageFrame(int width, int height){
		//setup the frame's attributes

		this.setTitle("VST GUI");
		this.setSize( width, height );
		mouseClicked = false;

		addMenu();////add a menu to the frame
		//setup the file chooser dialog
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));

		zSlider = new JSlider(JSlider.VERTICAL, 0, 100, 50);
		zSlider.addChangeListener(new ChangeListener(){
	      	public void stateChanged(ChangeEvent e){
	        	int tempZ = ((JSlider)e.getSource()).getValue();
	        	z = tempZ/100.0;
				System.out.println("zSlider double val: " + z);
      		}
    	});

		img = simulatedImage(width,height);
		panel = new MousePanel(img);
		openBGImg();

		this.getContentPane().add( panel, BorderLayout.CENTER );
    	this.getContentPane().add(zSlider,BorderLayout.EAST);
    	this.pack();

	}
	private void addMenu(){
		//setup the frame's munu bar
		//File menu
		JMenu fileMenu = new JMenu("File");

		JMenuItem openItem = new JMenuItem("Load source audio");
		openItem.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent event){
				openNConfigureAudio();
			}
		}   );
		fileMenu.add( openItem);
		//Exit
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.exit( 0 );
			}
		}	);
		fileMenu.add( exitItem);

		//attach menu to a menu bar

		JMenuBar menuBar = new JMenuBar();
		menuBar.add( fileMenu);
		this.setJMenuBar( menuBar);

		
	}
	private void openBGImg(){
		File file = new File("bg.jpg");
		if(file != null){
			BufferedImage bImage = panel.validateBimg_File(file);
			panel.setImage(bImage);		
		}
	}
	private void openNConfigureAudio(){
		File file = getFile();
		if(file != null){
			audioFileName = getAudioFileString(file);
			
		}
	}
	private void playAudio(double x_, double y_, double z_){
		//call JOAL/OpenAL Functions with audioFileName
		while(mouseClicked){ //while the audio file is not done playing
				System.out.println("(X,Y,Z) from Config Audio: " + x + "," + y + "," + z);
				mouseClicked = false;
		}
		System.out.println("Outside of the while loop in openNConfigureAudio method");
	}
	private String getAudioFileString(File src){
		System.out.println("AudioFile name: " + src.getName());
		return src.getName();
	}
	
	private File getFile(){
		File file = null;
		if(chooser.showOpenDialog(this) ==JFileChooser.APPROVE_OPTION){
			file = chooser.getSelectedFile();
		}
		return file;
	}
	//open a file selected by the user.
	private int prompt(){ //helper method to bufferedIMage methods
		//try catch statement for non int inputs.
		int size = 0; 
		try{
			String inputVaule = JOptionPane.showInputDialog("Please enter the input size");	
			size = Integer.parseInt(inputVaule);
			if(size<=0){
				JOptionPane.showMessageDialog(null, "Invalid Input, please re-enter", "alert", JOptionPane.ERROR_MESSAGE);
				return 0;
			}
			return size;
		}
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Invalid Input, please re-enter", "alert", JOptionPane.ERROR_MESSAGE);
			return prompt();
		}
		//return size; //return 0 for error occured;		
	}
/*
	private void displayFile(BufferedImage image_){
		try{
			displayBufferedImage(image_);
		}
		catch(Exception exception){
			JOptionPane.showMessageDialog( this, exception);
		}
	}

	public void displayBufferedImage( BufferedImage image){
		this.setContentPane( new JScrollPane(new JLabel(new ImageIcon(image))));
		this.validate();
	}
*/
	protected BufferedImage simulatedImage(int width_,int height_){
		while (true) {
				if (width_ < 0 || height_ < 0)
					return null;
				try {
					BufferedImage img = new BufferedImage(width_,height_,BufferedImage.TYPE_INT_RGB);
					return img;
				} catch (OutOfMemoryError err) {
					JOptionPane.showMessageDialog(this, "Ran out of memory! Try using a smaller image size.");
				}
			}
	}

	//nested MousePanel class
class MousePanel extends JPanel{
	 // panel size
	 private final int WIDTH, MAX_X;
	 private final int HEIGHT, MAX_Y;
	 // image displayed on panel
	 private BufferedImage image;
	 private Graphics2D g2d;
	 //the point in which the mouse clicked
	 private Point mouseLoc;
 //------------------------------------------------------------------------
 // constructor
 public MousePanel( BufferedImage image ){
	 this.image = image;
	 g2d = image.createGraphics();

	 // define panel characteristics
	 WIDTH = image.getWidth();
	 HEIGHT = image.getHeight();
	 Dimension size = new Dimension( WIDTH, HEIGHT );
	 setMinimumSize( size );
	 setMaximumSize( size );
	 setPreferredSize( size );
	 MAX_X = WIDTH - 1;
	 MAX_Y = HEIGHT - 1;

	//initialize Timer

	this.addMouseListener( new MouseAdapter(){
		public void mousePressed( MouseEvent event ){
			mouseClicked = true;
		 	if(event.getButton() == MouseEvent.BUTTON1){
		 		mouseLoc = event.getPoint();
				x = mouseLoc.getX();
				y = mouseLoc.getY();

		 		mouseClicked = true;
		 		playAudio(x,y,z);
				//System.out.println("(X,Y): " + x + "," + y);
		 		//timer.start();

		 	}
		 	else if(event.getButton() == MouseEvent.BUTTON3){
		 		mouseClicked = false;
		 		playAudio(x,y,z);
				//System.out.println("(X,Y): " + x + "," + y);
		 	}

		}
		public void mouseReleased(MouseEvent event){
			mouseClicked = false; 
			playAudio(x,y,z);
		}
	});

	mouseClicked = false; 

/*
	this.addMouseMotionListener( new MouseMotionAdapter(){
		public void mouseDragged(MouseEvent event){
			mouseLoc = event.getPoint();
			//claming mouse points within the border of display
			int x = (int)mouseLoc.getX();
			int y = (int)mouseLoc.getY();
				if (x < 0)
					x = 0;
				else if (x > width-1)
					x = width-1;
				if (y < 0)
					y = 0;
				else if (y > height-1)
					y = height-1;
			mouseLoc.setLocation(x, y);
		}
	});
*/
 }
 //------------------------------------------------------------------------
 	public BufferedImage validateBimg_File(File file){
		//BufferedImage bImage = new BufferedImage(0,0,0xFF000000);
		try{
				BufferedImage bImage_ = ImageIO.read(file);
				return bImage_;
			}
				catch(IOException exception){
				JOptionPane.showMessageDialog( this, exception);
			}
			return null;
	}
	 public void setImage(BufferedImage src ){
		g2d.drawImage( src,
			 0, 0, MAX_X, MAX_Y,
			 0, 0, (src.getWidth() - 1), (src.getHeight() - 1),
			 null
		 );
		repaint();
	 }
 //------------------------------------------------------------------------

	 public void paintComponent( Graphics g ){
		 super.paintComponent( g );
		 g.drawImage( image, 0, 0, null );
	 }

	}//Panel
	//Nested class looping 
/*
	private class ImageZoomer implements ActionListener{
	 	public void actionPerformed(ActionEvent evt){
	 			new Thread(new Runnable() {
					public void run() {
					// If the panel currently has a Mandelbrot set, produce and draw a Mandelbrot set based on the new complex bounds
					if (mandelbrot) {
						//call mandelbrot
						mandelbrot(r0,i0,r1,i1);
					}	

					// If the panel currently has a Julia set, redraw a Julia set
					else{
						julia(r0,i0,r1,i1,constant[0],constant[1]);
					}
					// Update the image
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							panel.setImage(image);
						}
					});
				}
			}).start();
	 	}//action performed
  }//looper
*/

}//image frame