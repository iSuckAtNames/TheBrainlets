package brainlets.cards.LXIXSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.powers.AncientConstructPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class DubiousConstruct extends BaseCard {
    public static final String ID = makeID("DubiousConstruct");

    public static final String NAME;
    public static final String DESCRIPTION;
    private static final CardStrings cardStrings;

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            AbstractCard.CardType.POWER,
            AbstractCard.CardRarity.RARE,
            AbstractCard.CardTarget.SELF,
            3
    );

    public DubiousConstruct() {
        super(ID,info);
        setMagic(1);
        setCustomVar("M2", 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p, new ArtifactPower(p, customVar("M2"))));
        addToBot(new ApplyPowerAction(p, p, new AncientConstructPower(p,p, magicNumber), magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.name = this.cardStrings.EXTENDED_DESCRIPTION[0];
            upgradeBaseCost(2);
            this.initializeDescription();
            this.upgraded = true;
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DubiousConstruct();
    }
}
