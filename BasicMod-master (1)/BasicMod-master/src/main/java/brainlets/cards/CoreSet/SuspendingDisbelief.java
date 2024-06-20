package brainlets.cards.CoreSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.orbs.StasisOrb;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;


public class SuspendingDisbelief extends BaseCard {
    public static final String ID = makeID("SuspendingDisbelief");

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC = 1;

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public SuspendingDisbelief() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        boolean allStasisOrbs = true;
        for (AbstractOrb o : p.orbs) {
            if (!(o instanceof StasisOrb)) {
                allStasisOrbs = false;
            }
        }
        if (!p.hasEmptyOrb() && allStasisOrbs) {
            addToBot(new GainEnergyAction(this.magicNumber));
            addToBot(new DrawCardAction(p, this.magicNumber));
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        boolean allStasisOrbs = true;
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (!(o instanceof StasisOrb)) {
                allStasisOrbs = false;
            }
        }
        if (!AbstractDungeon.player.hasEmptyOrb() && allStasisOrbs) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SuspendingDisbelief();
    }
}
