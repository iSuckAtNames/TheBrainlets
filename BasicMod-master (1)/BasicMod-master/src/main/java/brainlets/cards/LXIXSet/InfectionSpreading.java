package brainlets.cards.LXIXSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.powers.InfectionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class InfectionSpreading extends BaseCard {
    public static final String ID = makeID("InfectionSpreading");
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 2;

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1
    );

    public InfectionSpreading() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if (p.hasPower(InfectionPower.POWER_ID)) {
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) m, (AbstractCreature) p, (AbstractPower) new InfectionPower((AbstractCreature) m, magicNumber + p.getPower(InfectionPower.POWER_ID).amount), this.magicNumber + p.getPower(InfectionPower.POWER_ID).amount, true, AbstractGameAction.AttackEffect.NONE));
        } else {
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) m, (AbstractCreature) p, (AbstractPower) new InfectionPower((AbstractCreature) m, magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(InfectionPower.POWER_ID)) {
            this.baseDamage += p.getPower(InfectionPower.POWER_ID).amount;
        }
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(InfectionPower.POWER_ID)) {
            this.baseDamage += p.getPower(InfectionPower.POWER_ID).amount;
        }
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new InfectionSpreading();
    }
}
