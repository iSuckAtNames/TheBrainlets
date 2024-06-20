package brainlets.cards;

import brainlets.orbs.StasisOrb;

public interface InStasisCard {
    default void onStartOfTurn(StasisOrb orb) {

    }

    default void onEvoke(StasisOrb orb) {

    }

    default void whenEnteredStasis(StasisOrb orb) {

    }

    default void whenReturnedFromStasis() {

    }
}
