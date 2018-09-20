import java.util.Optional;

public enum PlayerPosition {
    BUTTON,
    SMALL_BLIND,
    BIG_BLIND,
    UNDER_THE_GUN,
    CUT_OFF;

    public Optional<PlayerPosition> next() {
        switch(this) {
            case BUTTON: return Optional.of(SMALL_BLIND);
            case SMALL_BLIND: return Optional.of(BIG_BLIND);
            case BIG_BLIND: return Optional.of(UNDER_THE_GUN);
            case UNDER_THE_GUN: return Optional.of(CUT_OFF);
            case CUT_OFF: return Optional.of(BUTTON);
            default: return Optional.empty();
        }
    }
}
