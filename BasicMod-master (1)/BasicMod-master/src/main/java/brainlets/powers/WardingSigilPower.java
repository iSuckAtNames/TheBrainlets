package brainlets.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static brainlets.BasicMod.makeID;

public class WardingSigilPower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = makeID("WardingSigilPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;



    public WardingSigilPower(final AbstractCreature owner, final int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        if (this.amount <= 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.POWER_ID));
        }

        updateDescription();
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {

        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];

    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        AbstractPlayer p = AbstractDungeon.player;
        if (power.ID.equals(WeakPower.POWER_ID) && target == this.owner && !target.hasPower("Artifact")) {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                for (int i = 0; i < amount; i++) {
                    addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, power.amount, false), power.amount, true, AbstractGameAction.AttackEffect.POISON));
                }
            }
        }
        if (power.ID.equals(VulnerablePower.POWER_ID) && target == this.owner && !target.hasPower("Artifact")) {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                for (int i = 0; i < amount; i++) {
                    addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, power.amount, false), power.amount, true, AbstractGameAction.AttackEffect.POISON));
                }
            }
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new WardingSigilPower(owner, amount);
    }
}
