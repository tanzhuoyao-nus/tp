package seedu.flashcard.model.flashcard;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Flashcard}'s {@code Note} matches any of the keywords given.
 */
public class NoteContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final List<String> keywords;

    public NoteContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return keywords.stream()
                .anyMatch(keyword ->
                        flashcard.getNote().toString().toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoteContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NoteContainsKeywordsPredicate) other).keywords)); // state check
    }
}
