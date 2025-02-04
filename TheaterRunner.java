import org.code.theater.*;
import org.code.media.*;

public class TheaterRunner {
  public static void main(String[] args) {

   String[] soundFiles = {"blindinglights.wav"};
 

  // 2D Array
    String[][] makeup = {
      {"lip balm", "blush"},
      {"concealer", "contour"}
    };

    String[][] captions = {
      { "Highest Reviewed Brand: Summer Fridays", "Highest Reviewed Brand: Rare Beauty" },
      { "Highest Reviewed Brand: Nars ", "Highest Reviewed Brand: Fenty Beauty" },
    };

    // MyStory object/ instantiates the object 
    MyStory scene = new MyStory(makeup, captions);
    
    // Call drawScene
    scene.drawScene();
    
    // Play scene in Theater
    Theater.playScenes(scene);
    
  }
}


    

    
