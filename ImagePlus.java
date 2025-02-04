import org.code.theater.*;
import org.code.media.*;

/*
 * Represents an image that can be modified with filters and effects
 */
public class ImagePlus extends Image {

  private Pixel[][] pixels;    // The 2D array of pixels

  /*
   * Sets the superclass filename to the specified filename
   * and calls the getPixelsFromImage() method to initialize
   * the 2D array of Pixel objects that make up the image
   */
  public ImagePlus(String filename) {
    super(filename);   // Calls the Image class constructor

    // Initialize the pixels array by getting the pixels from the image
    pixels = getPixelsFromImage();
  }

  /*
   * Returns the 2D array of pixels
   */
  public Pixel[][] getImagePixels() {
    return pixels;
  }

  /*
   * Returns the pixels in the image as a 2D array of Pixel objects
   */
  public Pixel[][] getPixelsFromImage() {
    Pixel[][] tempPixels = new Pixel[getHeight()][getWidth()];
   
    for (int row = 0; row < tempPixels.length; row++) {
      for (int col = 0; col < tempPixels[0].length; col++) {
        tempPixels[row][col] = getPixel(col, row);
      }
    }

    return tempPixels;
  }

 public void emboss() {
   

    Pixel[][] pixels = getImagePixels();

 
    for (int row = 1; row < pixels.length; row++) {
      for (int col = 1; col < pixels[0].length; col++) {
        Pixel currentPixel = pixels[row][col];
        Pixel topLeftPixel = pixels[row - 1][col - 1];

     
        int diffRed = currentPixel.getRed() - topLeftPixel.getRed();
        int diffGreen = currentPixel.getGreen() - topLeftPixel.getGreen();
        int diffBlue = currentPixel.getBlue() - topLeftPixel.getBlue();

 
        int maxDiff = 0;

        if (Math.abs(diffRed) > Math.abs(diffGreen) && Math.abs(diffRed) > Math.abs(diffBlue)) {
          maxDiff = Math.abs(diffRed);
        }
        else if (Math.abs(diffGreen) > Math.abs(diffRed) && Math.abs(diffGreen) > Math.abs(diffBlue)) {
          maxDiff = Math.abs(diffGreen);
        }
        else {
          maxDiff = Math.abs(diffBlue);
        }

   
        int newColor = 128 + maxDiff;
        currentPixel.setRed(newColor);
        currentPixel.setGreen(newColor);
        currentPixel.setBlue(newColor);
      }
    }
   
  }





 
}

