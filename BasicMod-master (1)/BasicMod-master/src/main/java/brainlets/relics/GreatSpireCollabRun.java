package brainlets.relics;

import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static brainlets.BasicMod.makeID;

public class GreatSpireCollabRun extends BaseRelic {
    private static final String NAME = "GreatSpireCollabRun"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.

    public GreatSpireCollabRun() {
        super(ID, NAME, theBrainlets.Enums.CARD_COLOR, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(PingForRun.ID);
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(PingForRun.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(PingForRun.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    public void atTurnStartPostDraw() {
        addToBot(new DrawCardAction(2));
        addToBot(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player,1, false));
    }

    public AbstractRelic makeCopy() {
        return new GreatSpireCollabRun();
    }
}
