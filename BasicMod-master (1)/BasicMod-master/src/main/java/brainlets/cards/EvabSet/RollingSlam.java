package brainlets.cards.EvabSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

public class RollingSlam extends BaseCard {
    public static final String ID = makeID("RollingSlam");

    private static final CardStrings cardStrings;

    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            2
    );

    public RollingSlam() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(2);
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(PlatedArmorPower.POWER_ID)) {
            addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractPlayer)p, magicNumber*(p.getPower(PlatedArmorPower.POWER_ID).amount), this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            if (!upgraded) {
                addToBot(new RemoveSpecificPowerAction(p,p,PlatedArmorPower.POWER_ID));
            } else {
                addToBot(new ReducePowerAction(p,p,PlatedArmorPower.POWER_ID, (p.getPower(PlatedArmorPower.POWER_ID).amount)/2));
            }
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            rawDescription = UPGRADED_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new RollingSlam();
    }
}
