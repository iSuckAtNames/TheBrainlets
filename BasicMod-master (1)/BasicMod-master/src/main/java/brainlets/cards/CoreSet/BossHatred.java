package brainlets.cards.CoreSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;


public class BossHatred extends BaseCard {
    public static final String ID = makeID("BossHatred");
    private static final int DAMAGE = 13;

    private static final int MAGIC = 1;

    private static final int UPG_MAGIC = 1;

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );

    public BossHatred() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(MAGIC, UPG_MAGIC);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false), magicNumber));
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
        AbstractPower focus = m.getPower(FocusPower.POWER_ID);
        if (focus != null && focus.amount < 0) {
            addToBot(new GainEnergyAction(2));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BossHatred();
    }
}
