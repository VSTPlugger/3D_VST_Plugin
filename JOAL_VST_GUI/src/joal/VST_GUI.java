package joal;

import com.jogamp.openal.AL;
import com.jogamp.openal.UnsupportedAudioFileException;
import com.jogamp.openal.sound3d.AudioSystem3D;
import com.jogamp.openal.sound3d.Buffer;
import com.jogamp.openal.sound3d.Context;
import com.jogamp.openal.sound3d.Device;
import com.jogamp.openal.sound3d.Listener;
import com.jogamp.openal.sound3d.Source;
import static java.awt.JobAttributes.DestinationType.FILE;
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Xiaoxi
 */
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
import java.lang.Math;



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
        //my custom soundProcess class to call for hrtf and related functions 
        //public soundProcess soundProcess;
        
        public soundProcessAL soundAL;
        
        //GUI stuff
	private final JFileChooser chooser;
	private int width = WIDTH;
	private int height = HEIGHT;

	private BufferedImage img=null;
	private String audioFileName;

        //initialize initial user points to be (0,0,0)
	private float x = 0.0f;
	private float y = 0.0f;
	private float z = 0.0f;

	private JSlider zSlider;
	private MousePanel panel;
	private boolean mouseClicked;
        
        //top menu GUI components
        private JPanel topMenu; 
        private JLabel label;
        private JButton loadWav;
        private JButton exitButt;
        
        private JPanel bottomMenu;
        //button audio process sliders;
        private JSlider freqSlider;
        private JSlider pitchSlider;
        private JSlider gainSlider;
        
        private int freq;
        private float pitch = 1.0f;
        private float gain = 1.0f;
        
        private boolean sliderChanged = false; 
	//=========================
	public ImageFrame(int width, int height){
		//setup the frame's attributes
                //soundProcess = new soundProcess();
                
                soundAL = new soundProcessAL(0.0f,0.0f,0.0f);

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
	        	z = (float)(-1+ tempZ*(2/100.0f));
                        //(float)(-1 + (2.0/WIDTH)*(x_)
				System.out.println("zSlider float val: " + z);
      		}
                });
                
                	
                
		img = simulatedImage(width,height);
		panel = new MousePanel(img);
		openBGImg();
                
                
                topMenu = new JPanel ();
		loadWav = new JButton("Load Audio");
                label = new JLabel("Welcome!");
                exitButt = new JButton("Exit Program");

                topMenu.setLayout(new GridLayout(1, 5));
                topMenu.add(loadWav);
                topMenu.add(new Label(""));
                topMenu.add(label);
                topMenu.add(new Label(""));
                topMenu.add(exitButt);	
                
                bottomMenu = new JPanel ();
            /*    
                freqSlider = new JSlider(JSlider.HORIZONTAL, 200, 200000, 44100);
		freqSlider.addChangeListener(new ChangeListener(){
	      	public void stateChanged(ChangeEvent e){
	        	freq = ((JSlider)e.getSource()).getValue();
                        sliderChanged = true; 
                        System.out.println("freqSlider float val: " + freq);
      		}
                });
            */   
                pitchSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
		pitchSlider.addChangeListener(new ChangeListener(){
	      	public void stateChanged(ChangeEvent e){
	        	int tempPitch = ((JSlider)e.getSource()).getValue();
                        pitch = (float)(1.0f + tempPitch/10.0f);
                        sliderChanged = true; 
			System.out.println("pitchSlider float val: " + pitch);
      		}
                });
                
                gainSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, 50);
		gainSlider.addChangeListener(new ChangeListener(){
	      	public void stateChanged(ChangeEvent e){
                        int tempGain;
                        tempGain = ((JSlider)e.getSource()).getValue();
	        	gain = (float)(0.0f + tempGain/10.0f);
                        sliderChanged = true; 
	        	System.out.println("gainSlider float val: " + gain);
      		}
                });
                
                bottomMenu.setLayout(new GridLayout(3,2));
                //bottomMenu.add(new Label("Frequency Slider: "));
                //bottomMenu.add(freqSlider);
                bottomMenu.add(new Label("Pitch Slider"));
                bottomMenu.add(pitchSlider);
                bottomMenu.add(new Label("Gain Slider"));
                bottomMenu.add(gainSlider);
                
                loadWav.addActionListener( new ActionListener(){
                    public void actionPerformed( ActionEvent event ){
                        openNConfigureAudio();
                    }
                });
                exitButt.addActionListener( new ActionListener(){
                    public void actionPerformed( ActionEvent event ){
			System.exit( 0 );
                    }
                });
                
                this.getContentPane().add( topMenu, BorderLayout.NORTH );
		this.getContentPane().add( panel, BorderLayout.CENTER );
                this.getContentPane().add(zSlider,BorderLayout.EAST);
                this.getContentPane().add(bottomMenu,BorderLayout.SOUTH);
                //this.getContentPane().add(pitchSlider,BorderLayout.SOUTH);
                //this.getContentPane().add(gainSlider,BorderLayout.SOUTH);
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
		File file = new File("src/bg.jpg");
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
	private void playAudio(float x_, float y_, float z_){
		//call JOAL/OpenAL Functions with audioFileName
		while(mouseClicked){ //while the audio file is not done playing
                    //soundProcess.loadNPlay(x_,y_,z_,audioFileName);
                    
                   // soundAL = new soundProcessAL(x_,y_,z_,audioFileName);
                   if(sliderChanged){
                    //soundAL.playAL( x_, z_,y_,audioFileName,pitch,gain);
                    //gain = gain -(float)Math.log(Math.sqrt((x_*x_)+(y_*y_)+(z_*z_)));
                    soundAL.playAL( y_, z_,x_,audioFileName,pitch,gain);
                    System.out.println("(x,y,z) from playAudio: " + x +"," + y + "," + z);
                   }
                   else{
                    //soundAL.playAL(x_,z_,y_,audioFileName);
                    //soundAL.playAL( y_, z_,x_,audioFileName);
                    gain = gain *(float)Math.log(Math.sqrt((x_*x_)+(y_*y_)+(z_*z_)));
                    soundAL.playAL( y_, z_,x_,audioFileName,pitch,gain);
                    
                    System.out.println("(x,y,z) from playAudio: " + x +"," + y + "," + z);
                   }            
                    System.out.println("(X,Y,Z) from Config Audio: " + x_ + "," + y_ + "," + z_);
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
         
         private float centeredX;//the (0,0) on standard cartesian plane
         private float centeredY;
         
         private float localX;
         private float localY;
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
         
         centeredX = width/2;
         centeredY = height/2; 
         
         localX = 0;
         localY = 0;
	//initialize Timer

	this.addMouseListener( new MouseAdapter(){
		public void mousePressed( MouseEvent event ){
			mouseClicked = true;
		 	if(event.getButton() == MouseEvent.BUTTON1){
		 		mouseLoc = event.getPoint();
				localX = (float)mouseLoc.getX();
				localY = (float)mouseLoc.getY();

		 		mouseClicked = true;
                                
                                mapToOneToOne(localX,localY);
		 		playAudio(x,y,z);
				//System.out.println("(X,Y): " + x + "," + y);
		 		//timer.start();

		 	}
		 	else if(event.getButton() == MouseEvent.BUTTON3){
		 		mouseClicked = false;
                                System.out.println("Right mouse clicked");
		 		//playAudio(x,y,z);
				//System.out.println("(X,Y): " + x + "," + y);
		 	}

		}
		public void mouseReleased(MouseEvent event){
			mouseClicked = false; 
			//playAudio(x,y,z);
                        System.out.println("mouse Released");
		}
	});

	mouseClicked = false; 

 } 
 //float []
    public void mapToOneToOne(float x_, float y_){
        //float [] tempCoord = new float [2];
        x = (float)(-1 + (2.0/WIDTH)*(x_));
        y = (float)(1 - (2.0/HEIGHT)*(y_));
               // tempCoord[0] = x;
       // tempCoord[1] = y;
        //return tempCoord;
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