package Game;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioStream;

public class Music {
    public AudioStream startMusic() {

        AudioStream BGM = null;

        try {
            InputStream test = new FileInputStream("Game-Pikachu/pikachu/src/Game/music/musicpokemon.wav");
            BGM = new AudioStream(test);

            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);
        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
        return BGM;
    }

    public AudioStream winningMusic() {
        AudioStream BGM = null;

        try {
            InputStream test = new FileInputStream("Game-Pikachu/pikachu/src/Game/music/winning.wav");
            BGM = new AudioStream(test);

            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);
        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
        return BGM;
    }

    public AudioStream loseMusic() {
        AudioStream BGM = null;

        try {
            InputStream test = new FileInputStream("Game-Pikachu/pikachu/src/Game/music/lose.wav");
            BGM = new AudioStream(test);

            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);
        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
        return BGM;
    }

    public AudioStream warningMusic() {
        AudioStream BGM = null;

        try {
            InputStream test = new FileInputStream("Game-Pikachu/pikachu/src/Game/music/warning.wav");
            BGM = new AudioStream(test);

            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);
        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
        return BGM;
    }
}
