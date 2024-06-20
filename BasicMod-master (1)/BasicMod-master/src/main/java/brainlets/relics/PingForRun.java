package brainlets.relics;

import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static brainlets.BasicMod.makeID;

public class PingForRun extends BaseRelic {
    private static final String NAME = "pingForRun"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public PingForRun() {
        super(ID, NAME, theBrainlets.Enums.CARD_COLOR, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        flash();
        addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
        addToBot((AbstractGameAction)new DrawPileToHandAction(1, AbstractCard.CardType.ATTACK));
        addToBot((AbstractGameAction)new DrawPileToHandAction(1, AbstractCard.CardType.SKILL));
        this.grayscale = true;
    }

    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    public AbstractRelic makeCopy() {
        return new PingForRun();
    }
}
