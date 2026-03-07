package utilities;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.Dimension;
import java.awt.Toolkit;

import enums.Measurements;

public class WindowUtils {
    /**
     * <p>It's just a function that returns the screen size Dimensions. A shortcut for me.</p>
     *
     * @return the screen size as a {@link Dimension}
     * @since 1.0
     */
    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    /**
     * <p>A function used to keep the aspect ratio based on context height or width.</p>
     *
     * @param desired_dimension height or width enum value
     * @param expected_dimension_value  context dimension e.g., the display width or height
     * @param aspect_ratio      provided aspect ratio for context dimension e.g., - [16, 9]
     * @return the desired scaled dimensions e.g., for provided context size of 1920 for WIDTH and aspect ratio of [16, 9] -&gt; [1920, 1080]
     * @since 1.0
     */
    @Contract("!null, _, _ -> new")
    public static @NotNull Dimension scaleDimensions(Measurements desired_dimension, int expected_dimension_value, Dimension aspect_ratio) {
        float aspectRatio = (float) aspect_ratio.getWidth() / (float) aspect_ratio.getHeight();
        if ( aspectRatio < 0 || expected_dimension_value < 0 )
            throw new IllegalArgumentException("The aspect_ratio / screen desired_dimension cannot be negative!");
        return desired_dimension == Measurements.WIDTH ? new Dimension(expected_dimension_value, (int) (expected_dimension_value / aspectRatio)) : new Dimension((int) (expected_dimension_value * aspectRatio), expected_dimension_value); //? we are using an integer because I haven't found any monitors with fractions of pixels yet r/whoosh
    }
}
