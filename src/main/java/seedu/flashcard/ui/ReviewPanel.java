package seedu.flashcard.ui;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import seedu.flashcard.logic.ReviewManager;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * An UI component that displays review function.
 */
public class ReviewPanel extends StudyPanel {

    public static final String EXIT_MESSAGE = "Exited review mode";
    private final ReviewManager reviewManager;

    /**
     * Creates a {@code ReviewPanel} that handles review mode.
     */
    public ReviewPanel(ObservableList<Flashcard> flashcardList, MainWindow parent) {
        super(parent);
        reviewManager = new ReviewManager(flashcardList);
        showFlashcard(flashcardList.get(0));
        handleStudy();
    }

    /**
     * Executes review function.
     */
    @Override
    protected void handleStudy() {
        keyDownEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!(event.getTarget() instanceof TextInputControl)) {
                    return;
                }
                switch (event.getCode().getCode()) {
                case 39: // right arrow key down
                    if (!reviewManager.hasNextFlashcard()) {
                        exitStudyMode(ReviewManager.NO_NEXT_FLASHCARD_MESSAGE + "\n" + EXIT_MESSAGE);
                    } else {
                        showFlashcard(reviewManager.getNextFlashcard());
                    }
                    break;
                case 37: // left arrow key down
                    if (!reviewManager.hasPreviousFlashcard()) {
                        exitStudyMode(ReviewManager.NO_PREVIOUS_FLASHCARD_MESSAGE + "\n" + EXIT_MESSAGE);
                    } else {
                        showFlashcard(reviewManager.getPrevFlashcard());
                    }
                    break;
                case 40: // up arrow key down
                    FlashcardAnswerCard flashcardAnswerCard = new FlashcardAnswerCard(
                            reviewManager.getCurrentFlashcard());
                    showAnswer(flashcardAnswerCard);
                    break;
                case 38: // down arrow key down
                    hideAnswer();
                    break;
                case 81: // 'q' key down
                    exitStudyMode(EXIT_MESSAGE);
                    break;
                default:
                    break;
                }
                event.consume();
            }
        };
        parent.getRoot().addEventFilter(KeyEvent.KEY_PRESSED, keyDownEventHandler);
    }

}