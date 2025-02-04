import org.code.theater.*;
import org.code.media.*;

public class ImageFilter extends ImagePlus {
/*
   * Calls the superclass constructor to initialize pixels
   */
  public ImageFilter(String filename) {
    super(filename);
  }
 /*
   * Applies a pixelate filter to each Pixel object by dividing the image into a grid
   * of equal-sized rectangular regions and setting the color of each Pixel object in
   * a region to the color of the first Pixel object in the region
   */
    public void pixelate(int gridSize) {
      Pixel[][] pixels = getImagePixels();

    for (int row = 0; row < pixels.length; row += gridSize) {
      for (int col = 0; col < pixels[0].length; col += gridSize) {
        int endRow = Math.min(row + gridSize, pixels.length);
        int endCol = Math.min(col + gridSize, pixels[0].length);
        int avgRed = 0;
        int avgGreen = 0;
        int avgBlue = 0;

        for (int regionRow = row; regionRow < endRow; regionRow++) {
          for (int regionCol = col; regionCol < endCol; regionCol++) {
            avgRed += pixels[regionRow][regionCol].getRed();
            avgGreen += pixels[regionRow][regionCol].getGreen();
            avgBlue += pixels[regionRow][regionCol].getBlue();
          }
        }

        int totalPixelsInRegion = (endRow - row) * (endCol - col);
        avgRed /= totalPixelsInRegion;
        avgGreen /= totalPixelsInRegion;
        avgBlue /= totalPixelsInRegion;

        for (int regionRow = row; regionRow < endRow; regionRow++) {
          for (int regionCol = col; regionCol < endCol; regionCol++) {
            pixels[regionRow][regionCol].setRed(avgRed);
            pixels[regionRow][regionCol].setGreen(avgGreen);
            pixels[regionRow][regionCol].setBlue(avgBlue);
          }
        }
      }
    }
  }
   /*
   * Applies a Gaussian blur by finding the average of the red,
   * green, and blue color values of the current Pixel object and
   * its top-left neighboring Pixel object using a weighted average
   */
    public void applyBlur() {
      Pixel[][] pixels = getImagePixels();
    for (int row=0; row<pixels.length; row++) {
      int red = 0;
      int green = 0;
      int blue = 0;
      for (int col=0; col<pixels[0].length; col++) {
        if ((row > 0) && (col < pixels[0].length-1) && (row < pixels.length-1) && (col > 0)) {
         
          Pixel currentPixel = pixels[row][col];
          red = calcWeightedRed(pixels, row, col);
          green = calcWeightedGreen(pixels, row, col);
          blue = calcWeightedBlue(pixels, row, col);
          currentPixel.setRed(red);
          currentPixel.setGreen(green);
          currentPixel.setBlue(blue);
        }
      }
    }
    }
      
  public int calcWeightedRed(Pixel[][] pixels, int row, int col) {
    int avgRed = (pixels[row-1][col-1].getRed() + pixels[row-1][col].getRed() + pixels[row-1][col+1].getRed() +
                  pixels[row][col-1].getRed() + pixels[row][col].getRed() + pixels[row][col+1].getRed() +
                  pixels[row+1][col-1].getRed() + pixels[row+1][col].getRed() + pixels[row+1][col+1].getRed()) / 9;
    return avgRed;
  }

  public int calcWeightedGreen(Pixel[][] pixels, int row, int col) {
    int avgGreen = (pixels[row-1][col-1].getGreen() + pixels[row-1][col].getGreen() + pixels[row-1][col+1].getGreen() +
                    pixels[row][col-1].getGreen() + pixels[row][col].getGreen() + pixels[row][col+1].getGreen() +
                    pixels[row+1][col-1].getGreen() + pixels[row+1][col].getGreen() + pixels[row+1][col+1].getGreen()) / 9;
    return avgGreen;
  }

  public int calcWeightedBlue(Pixel[][] pixels, int row, int col) {
    int avgBlue = (pixels[row-1][col-1].getBlue() + pixels[row-1][col].getBlue() + pixels[row-1][col+1].getBlue() +
                   pixels[row][col-1].getBlue() + pixels[row][col].getBlue() + pixels[row][col+1].getBlue() +
                   pixels[row+1][col-1].getBlue() + pixels[row+1][col].getBlue() + pixels[row+1][col+1].getBlue()) / 9;
    return avgBlue;
   }

  public void colorize() {
     Pixel[][] temp = getImagePixels();
    
    for (int row = 0; row < temp.length; row++) {
      double average = 0;
      for (int col = 0; col < temp[0].length; col++) {
        Pixel currentPixel = temp[row][col];
        average = (currentPixel.getBlue() + currentPixel.getGreen() + currentPixel.getRed())/3;
        if (average < 85) {
          currentPixel.setRed(255);
          currentPixel.setGreen(0);
          currentPixel.setBlue(0);
        } else if (average < 170) {
          currentPixel.setGreen(255);
          currentPixel.setRed(0);
          currentPixel.setBlue(0);
        } else {
          currentPixel.setBlue(255);
          currentPixel.setGreen(0);
          currentPixel.setRed(0);
      }
    }
  }
}
 }