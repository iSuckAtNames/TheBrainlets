package brainlets.cards.LXIXSet;

import brainlets.cards.BaseCard;
import brainlets.cards.Token.MistakesOfThePast;
import brainlets.character.theBrainlets;
import brainlets.powers.InfectionPower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SeepingInfection extends BaseCard {
    public static final String ID = makeID("SeepingInfection");

    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    private static final CardStrings cardStrings;
    private static final int MAGIC = 2;

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

    public SeepingInfection() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(MAGIC);
        cardsToPreview = new MistakesOfThePast();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(InfectionPower.POWER_ID)) {
            for (int i = 0; i < magicNumber; i++) {
                AbstractCard c = new MistakesOfThePast();
                if (upgraded) {
                    c.upgrade();
                }
                addToBot(new MakeTempCardInHandAction(c));
            }
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            cardsToPreview.upgrade();
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SeepingInfection();
    }
}
