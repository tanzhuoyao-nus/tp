package seedu.flashcard.model.flashcard;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;


/**
 * Represents the Diagram the Flashcard contains.
 */
public class Diagram {

    public static final String MESSAGE_CONSTRAINTS = "Diagram can be defined by a valid relative or absolute path";
    public static final String MESSAGE_INVALID_FILE_TYPE = "Invalid file type. Diagram only supports the following "
            + "image file types: jpg, png, jpeg, bmp, gif ";
    public static final String MESSAGE_NON_EXISTENT_DIAGRAM_FILE_TYPE = "Please ensure diagram file exists";
    public static final List<String> SUPPORTED_IMAGE_FILE_TYPE_LIST =
            Arrays.asList(new String[]{"jpg", "png", "jpeg", "bmp", "gif"});

    private String diagramFilePath;

    /**
     * Constructs a {@code Diagram}.
     *
     * @param diagramFilePath A valid file path.
     */
    public Diagram(String diagramFilePath) {
        requireNonNull(diagramFilePath);
        this.diagramFilePath = diagramFilePath;
    }

    /**
     * Returns true if a given string is a valid image file type.
     */
    public static boolean isValidImageFileType(String path) {
        File file = new File(path);
        try {
            if (ImageIO.read(file) != null) {
                return isAcceptedImageFileType(file);
            }
        } catch (IOException exception) {
            return false;
        }
        return false;
    }

    /**
     * Returns true if a given string is a valid file.
     */
    public static boolean isValidFile(String path) {
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if the given file extension is a supported file extension.
     */
    public static boolean isAcceptedImageFileType(File file) {
        String fileName = file.toString();
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            String extension = fileName.substring(index + 1);
            return SUPPORTED_IMAGE_FILE_TYPE_LIST.contains(extension);
        }
        return false;
    }

    @Override
    public String toString() {
        return diagramFilePath;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Diagram // instanceof handles nulls
                && diagramFilePath.equals(((Diagram) other).diagramFilePath)); // state check
    }

    @Override
    public int hashCode() {
        return diagramFilePath.hashCode();
    }
}
