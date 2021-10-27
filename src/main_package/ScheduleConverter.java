package main_package;

import java.awt.Color;
import java.awt.image.BufferedImage;

// class for converting schedule from the picture form(buffered image) into array form
public class ScheduleConverter {
    public BufferedImage screen, bw; // the part of screenshot, user wants to analize
    int[] schedule; // array with the schedule values: schedule[x] = y;
    int width, height;
    // width - width of the screen;
    // height - height of the screen;

    boolean[][] bwScreen; // black and white screen, put into boolean matrix
    // true - black, false - white

    final int maxGray = 164; // maximum value of gray, up to which we count our color black
    // if color is more than maxGray, than it's white

    //final Color black = new Color(0, 0, 0);
    //final Color white = new Color(255, 255, 255);

    ScheduleConverter() {
        ResizableRectangle myResRect = new ResizableRectangle();
        screen = myResRect.getScreenShot();

        setWidthHeight();

        getBlackWhiteImage();

        getSchedule();
    }

    private void setWidthHeight() { // setter of width and height
        width = screen.getWidth();
        height = screen.getHeight();
    }

    private boolean checkClosestColor(Color color) { // here we get which color is closer to the  given color - black or white
        double Y = 0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue();
        if (Y<=maxGray) return true;
        else return false;
    }

    private void getBlackWhiteImage() {
        bwScreen = new boolean[width][height];

        //bw = new BufferedImage(screen.getWidth(), screen.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int x=0; x<screen.getWidth(); x++) {
            for (int y=0; y<screen.getHeight(); y++) {
                int pixel = screen.getRGB(x, y);
                Color pixelColor = new Color(pixel);
                boolean isBlack = checkClosestColor(pixelColor);
                bwScreen[x][y] = isBlack;

                /*if (isBlack) bw.setRGB(x, y, black.getRGB());
                else bw.setRGB(x, y, white.getRGB());*/
            }
        }

        /*try {
            ImageIO.write(bw, "PNG",
                    new FileOutputStream("C:\\Компьютер\\Anna\\Java\\MyProjects\\ScreenShotAnalizeProject\\MyBlackWhite.png"));
        } catch (IOException e) {
            return;
        }*/
    }

    void getSchedule() {
        schedule = new int[width];
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                if (bwScreen[x][y]) {
                    schedule[x] = y; break;
                }
            }
        }
    }

}
