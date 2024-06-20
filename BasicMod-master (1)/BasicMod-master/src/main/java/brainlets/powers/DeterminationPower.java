package brainlets.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static brainlets.BasicMod.makeID;


public class DeterminationPower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = makeID("DeterminationPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;



    public DeterminationPower(final AbstractCreature owner, final int amount) {
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
        if (power.type == AbstractPower.PowerType.DEBUFF && target == this.owner && !target.hasPower("Artifact")) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, amount), amount));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new DeterminationPower(owner, amount);
    }
}
