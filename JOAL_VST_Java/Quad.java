// Quad.java
// - user moves control points to change the shape of a QuadCurve in real time
//
// v1.0 - original version taken from
// http://java.sun.com/docs/books/tutorial/2d/display/example-1dot2/Quad.java
// v1.1 - 200310.17 - Restructured by Dave Small
// v1.2 - 200611.03 - Fixed the "jumping" control box issue
// v1.3 - 200710.26 - GUI creation moved to EDT
// v1.4 - 201311.05 - Fixed loss of interactivity on selecting outside control box
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.Applet;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
public class Quad extends JApplet
{
 static protected JLabel label;
 private QuadPanel quadPanel;
 public void init()
 {
	 //Initialize the layout.
	 getContentPane().setLayout( new BorderLayout() );
	 quadPanel = new QuadPanel();
	 quadPanel.setBackground( Color.white );
	 //getContentPane().add( quadPanel );
	 getContentPane().add( quadPanel, BorderLayout.CENTER );
	 label = new JLabel( "Drag the points to adjust the curve." );
	 //getContentPane().add( "South", label );
	 getContentPane().add( label, BorderLayout.SOUTH );
	 }
 public static void main( String s[] )
 {
 	SwingUtilities.invokeLater( new Runnable() {
	 	public void run()
	 	{
	 	createAndShowGUI();
	 	}
 	} );
 }
 public static void createAndShowGUI()
 {
	 JFrame frame = new JFrame( "Quad" );
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 JApplet applet = new Quad();
	 frame.getContentPane().add( applet, BorderLayout.CENTER );
	 applet.init();
	 frame.pack(); // automatically calls validate()
	 frame.setSize( new Dimension( 450,350 ) );
	 frame.setVisible( true );
 }
}
//*************************************************************************
class QuadPanel extends JPanel implements MouseListener, MouseMotionListener
{
	 // The logical points which define the QuadCurve
	 private Point2D.Double start, end, control;
	 // The shapes we’ll being drawing -- a QuadCurve and point Markers
	 private QuadCurve2D.Double quad = new QuadCurve2D.Double();
	 private Rectangle startMarker, endMarker, controlMarker; 
	 // Note: unlike Rectangle2D.Double, java.awt.Rectangle uses ints &
	 // has setLocation( x, y )
	 private BufferedImage bi; // the image we’ll draw on
	 private Graphics2D big; // the Graphics for that image
	 private Rectangle biArea; // Rectangle the size of BufferedImage
	 private Point2D.Double selectedPoint;// QuadCurve logical point last selected
	 private Rectangle selectedMarker; // QuadCurve point Marker last selected
	 private boolean mousePressedInsideMarker = true;
	 private boolean firstTime = true; // flags that system not initialized
	 private boolean dragged; // mouse dragged after last button down
 //=======================================================================
 // Constructor
 public QuadPanel()
 {
	 setBackground( Color.white );
	 addMouseMotionListener( this );
	 addMouseListener( this );
 }
 //=======================================================================
 // Mouse handling operations
 //-----------------------------------------------------------------------
 // MouseListener operations
 // - handle mouse events (press, release, click, enter, and exit)
 public void mouseClicked( MouseEvent e ) // mouse pressed and released
 {}
 public void mouseEntered( MouseEvent e ) // moves over component
 {}
 public void mouseExited( MouseEvent e ) // leaves component
 {}
 public void mousePressed( MouseEvent e )
 {
	 int x = e.getX();
	 int y = e.getY();
	 if( startMarker.contains( x, y ) )
	 {
		 selectedMarker = startMarker;
		 selectedPoint = start;
		 mousePressedInsideMarker = true;
		 System.out.println( "Selected start" );
	 }
	 else if( endMarker.contains( x, y ) )
	 {
		 selectedMarker = endMarker;
		 selectedPoint = end;
		 mousePressedInsideMarker = true;
		 System.out.println( "Selected end" );
	 }
	 else if( controlMarker.contains( x, y ) )
	 {
		 selectedMarker = controlMarker;
		 selectedPoint = control;
		 mousePressedInsideMarker = true;
		 System.out.println( "Selected control" );
	 }
	 else
	 {
		 mousePressedInsideMarker = false;
		 System.out.println( "Selection outside markers" );
	 }
	 dragged = false;
	 }
	public void mouseReleased( MouseEvent e )
	 {
	 if ( dragged )
	 {
		 int x = e.getX();
		 int y = e.getY();

	 if( startMarker.contains( x, y ) )
	 {
		 selectedMarker = startMarker;
		 selectedPoint = start;
		 System.out.println( "Released start" );
		 updateLocation( e );
	 }
	 else if( endMarker.contains( x, y ) )
	 {
		 selectedMarker = endMarker;
		 selectedPoint = end;
		 System.out.println( "Released end" );
		 updateLocation( e );
	 }
	 else if( controlMarker.contains( x, y ) )
	 {
		 selectedMarker = controlMarker;
		 selectedPoint = control;
		 System.out.println( "Released control" );
		 updateLocation( e );
	 }
	 else
	 {
		 mousePressedInsideMarker = true;
		 System.out.println( "Released outside markers" );
	 }
	}
 }
 //-----------------------------------------------------------------------
 // MouseMotionListener operations
 // - handle mouse events: drag and move
 public void mouseDragged( MouseEvent e ) // mouse moved with button down
 {
 if( mousePressedInsideMarker )
 {
 updateLocation( e );
 dragged = true;
 }
 }
 public void mouseMoved( MouseEvent e ) // mouse moved without button down
 {}
 //-----------------------------------------------------------------------
 // helper operations for mouse handling
 public void updateLocation( MouseEvent e )
 {
 int x = e.getX();
 int y = e.getY();
 selectedMarker.setLocation( (x - 4), (y - 4) ); // center marker on point
 selectedPoint.setLocation( x, y );
 checkPoint();
 quad.setCurve( start, control, end );
 repaint();
 }
 void checkPoint()
 {

 /*
 * Checks if the rectangle is contained within the applet window.
 * If the rectangle is not contained withing the applet window, it
 * is redrawn so that it is adjacent to the edge of the window and
 * just inside the window.
 */
 if ( biArea == null )
 {
 return;
 }
 if( ( biArea.contains( selectedMarker ) ) &&
 ( biArea.contains( selectedPoint ) ) )
 {
 return;
 }
 // reset selectedPoint and selectedMarker so they are at
 // closest point on image edge
 int new_mx = selectedMarker.x;
 int new_my = selectedMarker.y;
 double new_px = selectedPoint.x;
 double new_py = selectedPoint.y;
 if( (selectedMarker.x + selectedMarker.width) > biArea.getWidth() )
 {
 new_mx = (int) biArea.getWidth() - (selectedMarker.width - 1);
 }
 if( selectedPoint.x > biArea.getWidth() )
 {
 new_px = (int) biArea.getWidth() - 1;
 }
 if( selectedMarker.x < 0 )
 {
 new_mx = -1;
 }
 if( selectedPoint.x < 0 )
 {
 new_px = -1;
 }
 if( ( selectedMarker.y + selectedMarker.width ) > biArea.getHeight() )
 {
 new_my = (int) biArea.getHeight() - (selectedMarker.height - 1);
 }
 if( selectedPoint.y > biArea.getHeight() )
 {
 new_py = (int) biArea.getHeight() - 1;
 }
 if( selectedMarker.y < 0 )
 {
 new_my = -1;
 }
 if( selectedPoint.y < 0 )
 {
 new_py = -1;
 }
 selectedMarker.setLocation( new_mx, new_my );
 selectedPoint.setLocation( new_px, new_py );
 }
 //=======================================================================
 // Component and BufferedImage painting
 public void paintComponent( Graphics g )
 {
 super.paintComponent( g );
 if ( firstTime )
 initialize( getSize() );
 // Clear the BufferedImage
 big.setColor( Color.white );
 big.clearRect( 0, 0, biArea.width, biArea.height );
 // Draw the newly defined QuadCurve and Markers to the BufferedImage
 big.setPaint( Color.black );
 big.draw( quad );
 big.setPaint( Color.red );
 big.fill( startMarker );
 big.setPaint( Color.magenta );
 big.fill( endMarker );
 big.setPaint( Color.blue );
 big.fill( controlMarker );
 // Draw the BufferedImage to the screen.
 ((Graphics2D) g).drawImage( bi, 0, 0, this );
 }
 //-----------------------------------------------------------------------
 public void initialize( Dimension dim )
 {
 int w = dim.width;
 int h = dim.height;
 // set up the logical points
 start = new Point2D.Double();
 start.setLocation( w/2-50, h/2 );
 end = new Point2D.Double();
 end.setLocation( w/2+50, h/2 );
 control = new Point2D.Double();
 control.setLocation( (int)(start.x + 50), (int)(start.y - 50) );
 // define the QuadCurve
 quad.setCurve( start, control, end );
 // set up visual markers for the logical points
 startMarker = new Rectangle( 0, 0, 8, 8 );
 startMarker.setLocation( (int)(start.x - 4), (int)(start.y - 4) );
 endMarker = new Rectangle( 0, 0, 8, 8 );
 endMarker.setLocation( (int)(end.x - 4), (int)(end.y - 4) );
 controlMarker = new Rectangle( 0, 0, 8, 8 );
 controlMarker.setLocation( (int)(control.x - 4), (int)(control.y - 4) );
 // create the BufferedImage
 bi = (BufferedImage) createImage( w, h );
 // setup the Graphics and its drawing characteristics
 big = bi.createGraphics();
 big.setStroke( new BasicStroke( 5.0f ) );
 big.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
 RenderingHints.VALUE_ANTIALIAS_ON );
 // create a rectangle the size of the BufferedImage
 biArea = new Rectangle( dim );
 // we never need to do this again...
 firstTime = false;
 }
}