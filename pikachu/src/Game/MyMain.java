package Game;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class MyMain {
	MyFrame frame;
	Music m = new Music();
	AudioStream as = null;
	AudioPlayer ap = AudioPlayer.player;

	public MyMain() {
		frame = new MyFrame();
		MyTimeCount timeCount = new MyTimeCount();
		timeCount.start();
		new Thread(frame).start();
	}

	public static void main(String[] args) {
		new MyMain();
	}

	class MyTimeCount extends Thread {

		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				frame.setTime(frame.getTime() - 1);

				if (frame.time == 100) {
					as = m.warningMusic();
					ap.start(as);
				}
				if (frame.time == 0) {

					as = m.loseMusic();
					ap.start(as);
					if (frame.showDialogNewGame(
							"Full time\nDo you want to play again?", "Lose", 1)) {
						ap.stop(as);
					};
				}
			}
		}
	}
}
