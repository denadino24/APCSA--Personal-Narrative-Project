 import org.code.theater.*;
import org.code.media.*;

public class MyStory extends Scene {

  // Instance Variables
  private String[][] makeup;
  private String[][] captions;

  
  // Constructor
  public MyStory(String[][] makeup, String[][] captions) {
   this.makeup = makeup;
   this.captions = captions;
 }

  // Calls all the parts of your animation, in order
  public void drawScene() {
    drawIntroScene();
    drawMakeupScene();
    drawEndScene();
  }

  public void drawIntroScene() {
    clear("pink");
    setTextHeight(15);
    setTextColor("black");
    drawText("Highest Reviewed Makeup Products at Sephora!", 1, 275);
             

    pause(2);
  }

  public void drawMakeupScene() {
    for (int row = 0; row < makeup.length; row++) {
      for (int col = 0; col < makeup[0].length; col++){
        
        // clear the scene and draw background color red
        clear("white");
        
        // set text to specific 
        setTextHeight(20);
        setTextColor("black");
        
        // draw text and images and play sound
        drawText("Makeup: " + makeup[row][col], 220, 195);
        setTextHeight(17);
        drawText(" " + captions[row][col], 10, 335);
        setTextHeight(30);
        drawText(makeup[row][col], 10, 40);

        // get the image from 2D array
        String[] words = makeup[row][col].split(" ");
        String fileName = "";
        if (words.length == 2) {
          fileName = words[0] + words[1] + ".jpg";
        } else {
          fileName = words[0] + ".jpg";
        }
        ImageFilter makeupImage = new ImageFilter(fileName);
        drawImage(makeupImage, 100, 100, 200);
        pause(1);
        playSound("blindinglights.wav");
        

        // apply filters to image
        if (row == 0 && col == 0) {
          makeupImage.pixelate(200);
        } else if (row == 0 && col == 1) {
          makeupImage.applyBlur();
        } else if ( row == 1 && col == 0) {
          makeupImage.colorize();
        } else if ( row == 1 && col == 1) {
          makeupImage.colorize();
        }
    // draw filtered image
        drawImage(makeupImage, 100, 100, 200);

    // pause 1 second before transitioning to filtered image
        pause (1);
    

      }
    }
  }

  public void drawEndScene() {
    
  }
}