package brainlets.relics;

import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static brainlets.BasicMod.makeID;

public class StolenSpyglass extends BaseRelic{
    private static final String NAME = "StolenSpyglass"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SHOP; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public StolenSpyglass() {
        super(ID, NAME, theBrainlets.Enums.CARD_COLOR, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atTurnStartPostDraw() {
        this.counter = 1;
    }

    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    public AbstractRelic makeCopy() {
        return new StolenSpyglass();
    }
}
