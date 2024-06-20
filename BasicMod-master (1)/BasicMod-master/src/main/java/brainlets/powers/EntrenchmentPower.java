package brainlets.powers;

import basemod.interfaces.CloneablePowerInterface;
import brainlets.orbs.StasisOrb;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static brainlets.BasicMod.makeID;

public class EntrenchmentPower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = makeID("EntrenchmentPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;



    public EntrenchmentPower(final AbstractCreature owner, final int amount) {
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
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void onEvokeOrb(AbstractOrb o) {
        if (o instanceof StasisOrb) {
            addToBot(new ApplyPowerAction(owner, owner, new PlatedArmorPower(owner, amount), amount));
        }
    }


    @Override
    public AbstractPower makeCopy() {
        return new EntrenchmentPower(owner, amount);
    }
}
