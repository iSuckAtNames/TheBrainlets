package brainlets.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static brainlets.BasicMod.makeID;

public class AncientConstructPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("AncientConstructPower");
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractCreature source;
    private static final boolean TURN_BASED = true;

    public AncientConstructPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, POWER_TYPE, TURN_BASED, owner, amount);

        this.owner = owner;
        this.amount = amount;

        if (this.amount <= 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.POWER_ID));
        }
        updateDescription();

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[2];

    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        if (!this.owner.hasPower(ArtifactPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new ArtifactPower(this.owner, this.amount), this.amount));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new AncientConstructPower(owner, source, amount);
    }
}
