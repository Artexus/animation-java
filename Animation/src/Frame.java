import javax.swing.JFrame;

public class Frame{
	public Frame()
	{
		JFrame frame = new JFrame("Animation");
		frame.add(new TextureBoard());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750,500);	
		frame.setVisible(true);
		System.out.println("udah");
	}
	public static void main(String[] args)
	{
		new Frame();
	}

}
