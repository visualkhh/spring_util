package com.omnicns.medicine.test.java;

import com.omnicns.java.image.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTest {
    public static void main(String[] args) throws IOException {
//        File img = new File("/Users/visualkhh/Downloads/photo_2019-10-19 20.41.28 (4).jpeg");
        File img = new File("/Users/visualkhh/Downloads/heath-joker-7591 (1).jpg");
        BufferedImage b = ImageUtil.getImage(img);
        for (int i = 0; i < b.getHeight(); i+=1) {
            for (int y = 0; y < b.getWidth(); y+=1) {
                Color g = new Color(b.getRGB(y, i));

                Color ll = new Color(20, 20, 20);
                Color l = new Color(50, 50, 50);
                Color m = new Color(100, 100, 100);
                Color mm = new Color(150, 150, 150);
                Color h = new Color(200, 200, 200);
                if (g.getRed() <= ll.getRed() && g.getGreen() <= ll.getGreen() && g.getBlue() <= ll.getBlue()) {
                    System.out.print(String.format("%s", "■"));
                } else if (g.getRed() <= l.getRed() && g.getGreen() <= l.getGreen() && g.getBlue() <= l.getBlue()) {
                    System.out.print(String.format("%s", "◆"));
                } else if (g.getRed() <= m.getRed() && g.getGreen() <= m.getGreen() && g.getBlue() <= m.getBlue()) {
                    System.out.print(String.format("%s", "◎"));
                } else if (g.getRed() <= mm.getRed() && g.getGreen() <= mm.getGreen() && g.getBlue() <= mm.getBlue()) {
                    System.out.print(String.format("%s", "◦"));
                } else if (g.getRed() <= h.getRed() && g.getGreen() <= h.getGreen() && g.getBlue() <= h.getBlue()) {
                    System.out.print(String.format("%s", "．"));
                } else {
                    System.out.print(String.format("%s", "　"));
                }
            }
            System.out.println("<br/>");
        }

    }
}
