package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class MyGraphics extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private int row;
	private int col;
	private int bound = 2;
	private int size = 50;
	private int score = 0;
	private JButton[][] btn;
	private Point p1 = null;
	private Point p2 = null;
	private Algorithm algorithm;
	private MyLine line;
	private MyFrame frame;
	private Color backGroundColor = Color.lightGray;
	private int item;
	Music m = new Music();
	AudioStream as = null;
	AudioPlayer ap = AudioPlayer.player;
	private int BestTime;


	public MyGraphics(MyFrame frame, int row, int col) {
		this.frame = frame;
		this.row = row + 2;
		this.col = col + 2;
		item = row * col / 2;

		setLayout(new GridLayout(row, col, bound, bound));
		setBackground(backGroundColor);
		setPreferredSize(new Dimension((size + bound) * col, (size + bound)
				* row));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setAlignmentY(JPanel.CENTER_ALIGNMENT);

		newGame();

	}

	public void newGame() {
		algorithm = new Algorithm(this.row, this.col);
		addArrayButton();

	}

	private void addArrayButton() {
		btn = new JButton[row][col];
		for (int i = 1; i < row - 1; i++) {
			for (int j = 1; j < col - 1; j++) {
				btn[i][j] = createButton(i + "," + j);
				Icon icon = getIcon(algorithm.getMatrix()[i][j]);
				btn[i][j].setIcon(icon);
				add(btn[i][j]);
			}
		}
	}

	private Icon getIcon(int index) {
		int width = 48, height = 48;
		Image image = new ImageIcon(getClass().getResource(
				"/Game/icon/icon" + index + ".jpg")).getImage();
		Icon icon = new ImageIcon(image.getScaledInstance(width, height,
				image.SCALE_SMOOTH));
		return icon;
	}

	private JButton createButton(String action) {
		JButton btn = new JButton();
		btn.setActionCommand(action);
		btn.setBorder(null);
		btn.addActionListener(this);
		return btn;
	}

	public void execute(Point p1, Point p2) {
		System.out.println("delete");
		setDisable(btn[p1.x][p1.y]);
		setDisable(btn[p2.x][p2.y]);
	}

	private void setDisable(JButton btn) {
		btn.setIcon(null);
		btn.setBackground(backGroundColor);
		btn.setEnabled(false);
	}

	public static void pause(int seconds) {
		System.out.println("pause");
		Date start = new Date();
		Date end = new Date();
		while (end.getTime() - start.getTime() < seconds * 1000) {
			end = new Date();
		}
	}

	public void Best() {
		try {
			File f = new File("Game-Pikachu/pikachu/src/Game/best.txt");
			Scanner sc = new Scanner(f);

			while (sc.hasNext()) {
				BestTime = sc.nextInt();
				break;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (( (frame.getMaxTime()/100) - frame.getTime() ) < BestTime) {
			File f = new File("Game-Pikachu/pikachu/src/Game/best.txt");
			try (PrintWriter pw = new PrintWriter(f)){
				pw.print((frame.getMaxTime()/100) - frame.getTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnIndex = e.getActionCommand();
		int indexDot = btnIndex.lastIndexOf(",");
		int x = Integer.parseInt(btnIndex.substring(0, indexDot));
		int y = Integer.parseInt(btnIndex.substring(indexDot + 1,
				btnIndex.length()));
		if (p1 == null) {
			p1 = new Point(x, y);
			btn[p1.x][p1.y].setBorder(new LineBorder(Color.red));
		} else {
			p2 = new Point(x, y);
			System.out.println("(" + p1.x + "," + p1.y + ")" + " --> " + "("
					+ p2.x + "," + p2.y + ")");
			line = algorithm.checkTwoPoint(p1, p2);
			if (line != null) {
				System.out.println("line != null");

				algorithm.getMatrix()[p1.x][p1.y] = 0;
				algorithm.getMatrix()[p2.x][p2.y] = 0;
				algorithm.showMatrix();
				execute(p1, p2);
				line = null;
				score += 10;
				item--;
				frame.time++;
				frame.getLbScore().setText(score + "");
			}
			btn[p1.x][p1.y].setBorder(null);
			p1 = null;
			p2 = null;
			System.out.println("done");
			if (item == 0) {
				Best();
				as = m.winningMusic();
				ap.start(as);
				if (frame.showDialogNewGame(
						"You are winner!\nDo you want to play again?", "Win", 1)) {
					ap.stop(as);
				};
			}
		}
	}
}
