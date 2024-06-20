package brainlets.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

import static brainlets.BasicMod.makeID;

public class TemperingProtocolPower extends BasePower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = makeID("TemperingProtocolPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;



    public TemperingProtocolPower(final AbstractCreature owner, final int amount) {
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
        if (power.ID == DexterityPower.POWER_ID && target == this.owner && power.amount < 0 && !target.hasPower("Artifact")) {
            flash();
            addToBot((AbstractGameAction)new GainBlockAction(this.owner, this.amount, Settings.FAST_MODE));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new TemperingProtocolPower(owner, amount);
    }
}
