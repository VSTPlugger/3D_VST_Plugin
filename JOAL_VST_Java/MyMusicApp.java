import org.jfugue.*;
public class MyMusicApp
{
 public static void main(String[] arg) 
 {
 Player player = new Player();
	Pattern pattern = new Pattern("T160 I[PIANO] "+
		 "G3q G3q G3q Eb3q Bb3i G3q Eb3q Bb3i G3h");
	player.play(pattern);
 }
} 