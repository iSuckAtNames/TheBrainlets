package brainlets.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import static brainlets.BasicMod.makeID;

public class GhostlyFormalityPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("GhostlyFormalityPower");
    public static PowerType POWER_TYPE = PowerType.DEBUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractCreature source;
    private static final boolean TURN_BASED = true;

    public GhostlyFormalityPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, POWER_TYPE, TURN_BASED, owner, amount);

        this.owner = owner;
        this.amount = amount;

        if (this.amount <= 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.POWER_ID));
        }
        updateDescription();

    }

    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(powerStrings.DESCRIPTIONS[0]);
        for (int i = 0; i < this.amount; i++)
            sb.append("[E] ");
        sb.append(LocalizedStrings.PERIOD);
        this.description = sb.toString();
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        if (this.owner.hasPower(IntangiblePlayerPower.POWER_ID)) {
            addToBot((AbstractGameAction)new LoseEnergyAction(this.amount));
            flash();
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new GhostlyFormalityPower(owner, source, amount);
    }
}
