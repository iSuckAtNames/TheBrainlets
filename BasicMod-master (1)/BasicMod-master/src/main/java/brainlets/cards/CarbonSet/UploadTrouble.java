package brainlets.cards.CarbonSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.powers.UploadTroublePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class UploadTrouble extends BaseCard {
    public static final String ID = makeID("UploadTrouble");

    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    private static final CardStrings cardStrings;

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            AbstractCard.CardType.POWER,
            AbstractCard.CardRarity.RARE,
            AbstractCard.CardTarget.SELF,
            2
    );

    public UploadTrouble() {
        super(ID,info);
        setMagic(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new UploadTroublePower(p, magicNumber), magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = UPGRADED_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new UploadTrouble();
    }
}
