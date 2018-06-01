package com.monochrome.tmall.util;

import java.awt.*;
import java.awt.image.*;
import java.io.File;

public class ImageUtil {
    public static BufferedImage change2jsp(File file) {
        try {
            Image image = Toolkit.getDefaultToolkit().createImage(file.getAbsolutePath());
            PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, -1, -1, true);
            pixelGrabber.grabPixels();
            int width = pixelGrabber.getWidth(), height = pixelGrabber.getHeight();
            final int[] RGB_MASKS = { 0xFF0000, 0xFF00, 0xFF };
            final ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);
            DataBuffer buffer = new DataBufferInt((int[]) pixelGrabber.getPixels(), pixelGrabber.getWidth() * pixelGrabber.getHeight());
            WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
            BufferedImage bufferedImage = new BufferedImage(RGB_OPAQUE, raster, false, null);
            return bufferedImage;
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
