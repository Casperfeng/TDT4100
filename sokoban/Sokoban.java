package sokoban;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * created 1/3/18
 * @author casperfeng
 * used to illustrate "SokobanGame" in pictures
 */
public class Sokoban extends JPanel{
	SokobanGame game = null; //sets game empty
	static String direction = ""; //sets direction empty
	static JFrame frame;
	
	String stage = ""; 
	
	
	public Sokoban() {
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
		
		game = new SokobanGame("#######\n" + 
				"#.@ # #\n" + 
				"#$* $ #\n" + 
				"#   $ #\n" + 
				"# ..  #\n" + 
				"#  *  #\n" + 
				"#######");
		stage = game.toString();
		frame.update(frame.getGraphics());
	}
	public void paint(Graphics g){
		Map<String, String> symbolToImage = new HashMap<String,String>();
		symbolToImage.put(" ", "Tile");
		symbolToImage.put(".", "Hole");
		symbolToImage.put("@", "Player");
		symbolToImage.put("+", "Player");
		symbolToImage.put("$", "Uncorrect");
		symbolToImage.put("*", "Correct");
		symbolToImage.put("#", "Wall");
		
		int x = 0;
		int y = 0;
		for(int n = 0; n < stage.length(); n++) {
			if (stage.charAt(n) == '\n') {
				x = 0;
				y++;
				continue;
			}
			LoadImageAtXY(symbolToImage.get(stage.charAt(n) + ""),32 + x*16,y*16, g);
			x++;
		}
    }
    public static void main(String[] args){
        frame = new JFrame();
        frame.setSize(180,180);
        frame.getContentPane().add(new Sokoban());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }  
    
    public void LoadImageAtXY(String ImageName, int x, int y, Graphics g) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("/"+getClass().getResource("/sokoban/" + ImageName + ".png").toString().replace("file:/", "")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, x, y, x + 16, y + 16, 0, 0, 16, 16, (ImageObserver)this);
    }
	public class MyKeyListener implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
			if (!"WASDRZ".contains(KeyEvent.getKeyText(e.getKeyCode())))
				return;
			direction = KeyEvent.getKeyText(e.getKeyCode());
			if (direction.equals("Z")) {
				game.undo();
			} 
			else if (direction.equals("R")) {
				game.redo();
			}
			
			else {
				game.move(direction.toLowerCase());
			}
			stage = game.toString();
			frame.update(frame.getGraphics());
		}

		@Override
		public void keyReleased(KeyEvent arg0) {}

		@Override
		public void keyTyped(KeyEvent arg0) {}
	}
}
