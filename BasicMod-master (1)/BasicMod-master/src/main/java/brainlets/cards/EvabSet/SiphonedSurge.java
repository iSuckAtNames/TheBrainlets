package brainlets.cards.EvabSet;

import brainlets.actions.DecelerateAllCardsInStasisAction;
import brainlets.actions.DecelerateRightMostCardAction;
import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SiphonedSurge extends BaseCard {
    public static final String ID = makeID("SiphonedSurge");
    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    private static final CardStrings cardStrings;

    private static final int MAGIC = 2;
    //END TUNING CONSTANTS

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            0
    );

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

    public SiphonedSurge() {
        super(ID, info);
        setMagic(MAGIC);
        setCustomVar("M2", 3);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(MAGIC));
        addToBot(new DrawCardAction(customVar("M2")));
        if (!upgraded) {
            addToBot(new DecelerateAllCardsInStasisAction());
        } else {
            addToBot(new DecelerateRightMostCardAction());
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            rawDescription = UPGRADED_DESCRIPTION;
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new SiphonedSurge();
    }
}
