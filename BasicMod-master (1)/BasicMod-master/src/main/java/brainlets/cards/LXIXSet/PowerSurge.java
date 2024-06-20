package brainlets.cards.LXIXSet;

import brainlets.actions.ReduceDebuffsAction;
import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.powers.DrawLessNextTurnPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class PowerSurge extends BaseCard {
    public static final String ID = makeID("PowerSurge");
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final CardStrings cardStrings;

    //TUNING CONSTANTS
    public static String UPGRADED_DESCRIPTION;


    //END TUNING CONSTANTS

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

    public PowerSurge() {
        super(ID, info);
        setMagic(4);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new ApplyPowerAction(p, p, new DrawLessNextTurnPower(2), 2));
        if (upgraded){
            addToBot(new ReduceDebuffsAction(AbstractDungeon.player, 1));
        }
    }

    public AbstractCard makeCopy() {
        return new PowerSurge();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
