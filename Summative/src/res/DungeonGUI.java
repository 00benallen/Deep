package res;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Main;

public class DungeonGUI extends GUI implements KeyListener, MouseListener {

	public DungeonGUI(int x, int y, String name) {
		super(x, y, name);
		try {
			genGUI();
		} catch(IOException e) {
			
		}
		this.setCurPane("roomPane");
	}
	
	public void genGUI() throws IOException{
		BufferedImage dungeonBackground = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/dungeonBackground.png"));
		Pane pane = new Pane("roomPane", dungeonBackground);
		BufferedImage roomImageI = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/roomImage.png"));
		ImageBox roomImage = new ImageBox((width/16)*2, (height/16)*2, roomImageI, "roomImage");
		BufferedImage textBoxI = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/textBox.png"));
		TextBox textBox = new TextBox((width/16)*2, (height/2) + height/16,textBoxI, "textBox");
		textBox.setText(Main.update.curDungeon.getRoom(Main.update.curDungeon.getCurRoom()).getDesc());
		BufferedImage toolBarI = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/toolBar.png"));
		ToolBar toolBar = new ToolBar(width/16, height - (height/16)*2, toolBarI, "toolBar");
		pane.add(toolBar);
		pane.add(roomImage);
		pane.add(textBox);
		addPane(pane);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < getCurPane().getElements().size(); i++) {
			if(getCurPane().getElement(i).getBound().contains(e.getPoint())) {
				if(getCurPane().getName().equals("roomPane")) {
					
				}		
			}
		}	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
