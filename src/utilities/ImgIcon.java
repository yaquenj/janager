package utilities;

import javax.swing.ImageIcon;
import java.awt.Image;


public final class ImgIcon extends ImageIcon {

    public ImgIcon(String filename) {
        super(filename);
    }

    public ImgIcon(Image image) {
        super(image);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public ImageIcon resizeIcon(int desiredSize) {
        return resizeIcon(desiredSize, desiredSize);
    }

    public ImageIcon resizeIcon(int desiredWidth, int desiredHeight) {

        if (desiredWidth <= 0 || desiredHeight <= 0)
            throw new IllegalArgumentException("scale must be a positive double!");

        Image resizedImage = getImage().getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);

        return new ImageIcon(resizedImage);

    }

    public ImageIcon scaleIcon(double scale) {

        if (scale <= 0)
            throw new IllegalArgumentException("scale must be a positive float!");

        int desiredWidth = (int)(getIconWidth() * scale);
        int desiredHeight = (int)(getIconHeight() * scale);

        Image scaledImage = getImage().getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);

    }

}
