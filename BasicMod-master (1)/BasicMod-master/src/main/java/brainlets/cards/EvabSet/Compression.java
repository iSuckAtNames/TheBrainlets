package brainlets.cards.EvabSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

public class Compression extends BaseCard {
    public static final String ID = makeID("Compression");

    public static final String DESCRIPTION;
    private static final CardStrings cardStrings;
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
    }

    public Compression() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, (p.currentBlock)/2), (p.currentBlock)/2));
        addToBot((AbstractGameAction)new RemoveAllBlockAction((AbstractCreature)p, (AbstractCreature)p));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
            this.initializeDescription();
        }
    }



    @Override
    public AbstractCard makeCopy() { //Optional
        return new Compression();
    }
}
