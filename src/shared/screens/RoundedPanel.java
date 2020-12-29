package shared.screens;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RoundedPanel extends JPanel
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6862648596525083487L;
	/** Stroke size. it is recommended to set it to 1 for better view */
    protected int strokeSize = 1;
    /** Color of shadow */
    protected Color shadowColor = Color.black;
    /** Sets if it drops shadow */
    protected boolean shady = true;
    /** Sets if it has an High Quality view */
    protected boolean highQuality = true;
    /** Double values for Horizontal and Vertical radius of corner arcs */
    protected Dimension arcs = new Dimension(10, 10);
    /** Distance between shadow border and opaque panel border */
    protected int shadowGap = 5;
    /** The offset of shadow.  */
    protected int shadowOffset = 4;
    /** The transparency value of shadow. ( 0 - 255) */
    protected int shadowAlpha = 150;

    //Constructor
    /**
     * Create a RoundedPanel with a radius of 10 and drop shadow enabled
     */
    public RoundedPanel() 
    {
        super();
        setOpaque(false);
    }
    /**
     * Create a RoundedPanel with a radius of 10 and whether drop shadow should be enabled.
     */
    public RoundedPanel(boolean dropShadow)
    {
    	super();
    	setOpaque(false);
    	this.dropShadow(dropShadow);
    }
    
    //Public methods
    /**
     * Set the size of the stroke.
     * @param size the stroke size
     */
    public void setStrokeSize(int size)
    {
    	this.strokeSize = size;
    }
    
    /**
     * Set if there should be a drop shadow
     * @param b a boolean
     */
    public void dropShadow(boolean b)
    {
    	this.shady = b;
    }
    
    /**
     * Set the radius of the corner arcs.
     * @param radius
     */
    public void setRadius(int radius)
    {
    	this.arcs.setSize(radius, radius);
    }
    
    /**
     * Set the gap between the shadow border and the opaque panel border
     * @param gap
     */
    public void setShadowGap(int gap)
    {
    	this.shadowGap = gap;
    }
    
    /**
     * Set the offset of the shadow
     * @param offset
     */
    public void setShadowOffset(int offset)
    {
    	this.shadowOffset = offset;
    }
    
    /**
     * Set the alpha value of the shadow (0-255).
     * @param alpha an integer between the values of (0-255) inclusive.
     */
    public void setShadowAlpha(int alpha)
    {
    	this.shadowAlpha = alpha;
    }
    
    //Overridden Methods
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int shadowGap = this.shadowGap;
        Color shadowColorA = new Color(shadowColor.getRed(),
        								shadowColor.getGreen(), 
        								shadowColor.getBlue(), 
        								shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

        //Sets antialiasing if HQ.
        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        }

        //Draws shadow borders if any.
        if (shady) {
            graphics.setColor(shadowColorA);
            graphics.fillRoundRect(
                    shadowOffset,// X position
                    shadowOffset,// Y position
                    width - strokeSize - shadowOffset, // width
                    height - strokeSize - shadowOffset, // height
                    arcs.width, arcs.height);// arc Dimension
        } else {
            shadowGap = 1;
        }

        //Draws the rounded opaque panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - shadowGap,
        height - shadowGap, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke(strokeSize));
        if (this.strokeSize > 0)
        {
        	graphics.drawRoundRect(0, 0, width - shadowGap,
        	        height - shadowGap, arcs.width, arcs.height);
        }

        //Sets strokes to default, is better.
        graphics.setStroke(new BasicStroke());
    }
    
    public static void main(String args[])
    {
    	//Initialization
		JFrame frame = new JFrame();
		RoundedPanel panel = new RoundedPanel();
		
		//Properties
		panel.arcs.setSize(10, 10);
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(panel);
		
		frame.setVisible(true);
    }
} 

//Original code provided by: https://www.codeproject.com/Articles/114959/Rounded-Border-JPanel-JPanel-graphics-improvements