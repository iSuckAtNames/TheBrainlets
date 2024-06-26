package brainlets.relics;

import brainlets.actions.AccelerateRightMostCardAction;
import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static brainlets.BasicMod.makeID;

public class Flywheel extends BaseRelic{
    private static final String NAME = "Flywheel"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.RARE; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.HEAVY; //The sound played when the relic is clicked.

    public Flywheel() {
        super(ID, NAME, theBrainlets.Enums.CARD_COLOR, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atTurnStartPostDraw() {
        if (AbstractDungeon.player.orbs.size() > 0) {
            flash();
            addToBot(new RelicAboveCreatureAction( AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new AccelerateRightMostCardAction());
        }
    }

    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    public AbstractRelic makeCopy() {
        return new Flywheel();
    }
}
