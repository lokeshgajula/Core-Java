package bounceThread;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class BounceThread {

	public static void main(String[] args)
	{
		EventQueue.invokeLater(()->{
			JFrame frame = new BounceFrame();
			frame.setTitle("BounceThread");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
class BounceFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BallComponent comp;
	public static final int STEPS = 1000;
	public static final int DELAY = 5;
	
	public BounceFrame()
	{
		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", event->addBall());
		addButton(buttonPanel, "Close", event->System.exit(0));
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}
	public void addButton(Container c, String title, ActionListener listener)
	{
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	public void addBall()
	{
		Ball ball = new Ball();
		comp.add(ball);
		Runnable r=()->{
			try
			{
				for(int i=1;i<=STEPS; i++)
				{
					ball.move(comp.getBounds());
					comp.repaint();
					Thread.sleep(DELAY);
				}
			}
			catch(InterruptedException e)
			{
				
			}
		};
		Thread t = new Thread(r);
		t.start();
	}
}
