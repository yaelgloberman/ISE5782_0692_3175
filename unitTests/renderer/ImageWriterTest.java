package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {
    /**
     * test method for {@link ImageWriter#writeToImage()}
     */
    @Test
    void testWriteToImage() {
        int nX = 800;
        int nY = 500;

        ImageWriter imageWriter = new ImageWriter("yellowred",nX,nY);

        Color yellowColor = new Color(255d,255d,0d);
        Color red = new Color(255d,0d,0d);

        for (int j = 0; j < nX; j++) {
            for (int i = 0; i < nY; i++) {
                //grid 16 X 10
                if( j % 50 == 0 || i % 50 == 0){
                    imageWriter.writePixel(j, i, red);         //color the "border"
                }
                else {
                    imageWriter.writePixel(j, i, yellowColor);  //color the "pixel"
                }
            }
        }
        imageWriter.writeToImage();  //create the image
    }
}