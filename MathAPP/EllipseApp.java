import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.math.*;

/*--
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import java.awt.Font;
*/
import static java. lang.Math.PI;

class DrawingPanel extends JPanel {
  public int x,y;
  public DrawingPanel(){
   x = 0;
   y = 0;
}  

  public void paint(Graphics g) {
    super.paintComponent(g);
    Dimension dim = getSize();
    g.setColor(getBackground());
    g.fillRect(0, 0, x, y);
    g.setColor(getForeground());
    g.drawOval(0, 0, x - 1, y - 1);
  }
  public Dimension getPreferredSize() {

    return (new Dimension(200,200));

  }
}

public class EllipseApp extends JFrame implements ActionListener{

	private JButton Button1 = null;
	private JLabel label_set = null;
	private JTextField text1 = null;
	private JLabel label_1 = null;
	private JLabel label_2 = null;
	private JLabel label_3 = null;
	private JLabel label_4 = null;
	private JLabel label_5 = null;

	private JLabel label_left = null;
	private JLabel label_right = null;
	private JPanel panel_T = null;
	private JPanel panel_V = null;
	private JPanel panel_F = null;
	private JPanel panel_A = null;

	private SpinnerNumberModel model_x;
	private SpinnerNumberModel model_y;

	private DrawingPanel draw = null;
/*-
	private int value_x;
	private int value_y;
*/
   public EllipseApp(){
	  super("EllipseApp");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	Container container = getContentPane();

	panel_T = new JPanel();
	panel_T.setLayout(new BorderLayout());
	container.add(panel_T, BorderLayout.NORTH);

	panel_F = new JPanel();
	panel_T.setLayout(new BorderLayout());
	container.add(panel_F,BorderLayout.CENTER);//cen
	draw = new DrawingPanel();

	panel_F.add(draw,BorderLayout.SOUTH);//Ce
//--=
	label_left = new JLabel("");
        panel_F.add(label_left,BorderLayout.WEST);
	label_right = new JLabel("");
        panel_F.add(label_right,BorderLayout.EAST);

//--=
	label_set = new JLabel("");
	panel_T.add(label_set,BorderLayout.WEST);
//--	
	panel_V = new JPanel();
	panel_V.setLayout(new BorderLayout());	
	panel_T.add(panel_V, BorderLayout.CENTER);
	FlowLayout layout = new FlowLayout();
	panel_V.setLayout(layout);
	
	label_1 = new JLabel("(x,yが)=");
	panel_V.add(label_1);
	
	label_2 = new JLabel("(");
        panel_V.add(label_2);

	model_x = new SpinnerNumberModel(0, 0, 200, 1);
	JSpinner spinner_x = new JSpinner(model_x);
	panel_V.add(spinner_x);
	/*--*/	
	label_3 = new JLabel(",");
        panel_V.add(label_3);
	/*--*/
	model_y = new SpinnerNumberModel(0, 0, 200, 1);
        JSpinner spinner_y = new JSpinner(model_y);
        panel_V.add(spinner_y);
	/*--*/
	label_4 = new JLabel(")");
        panel_V.add(label_4);
/*---*/
	label_5 = new JLabel("S=");
	panel_V.add(label_5);

	text1  = new JTextField(8);
	panel_V.add(text1);
/*---*/
	Button1 = new JButton("Run");
	Button1.setBackground(Color.yellow);
	Button1.addActionListener(this);
	panel_V.add(Button1);
/*---*
	label_5 = new JLabel("\nS=");
        panel_V.add(label_5);

        text1  = new JTextField(15);
        panel_V.add(text1);


/*-*
	panel_F = new JPanel();
        panel_F.setLayout(new BorderLayout());
        container.add(panel_F, BorderLayout.CENTER);
--*/
	pack();
   }
	
   public void actionPerformed(ActionEvent event){
	int value_x = model_x.getNumber().intValue();
	int value_y = model_y.getNumber().intValue();
	double S = 0;
	
	if(event.getSource()==Button1){
	     S = Math.round(value_x * value_y * PI*100.0)/100.0;
	     System.out.println("値は" + S);
	     String s = String.valueOf(S);
	     text1.setText(s);
	     draw.x = value_x;
	     draw.y = value_y;
	     draw.repaint();
	}
	
   }
   public static void main(String[] args) {
	EllipseApp frame = new EllipseApp();
	frame.setSize(400,300);
	frame.setResizable(false);
	frame.setVisible(true);
  }
}
