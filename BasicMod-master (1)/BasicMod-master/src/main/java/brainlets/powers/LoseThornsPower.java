package brainlets.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static brainlets.BasicMod.makeID;

public class LoseThornsPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("LoseThornsPower");
    public static PowerType POWER_TYPE = PowerType.DEBUFF;

    private static final boolean TURN_BASED = true;

    public LoseThornsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, POWER_TYPE, TURN_BASED, owner, amount);
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = POWER_TYPE;
        this.amount = amount;
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        updateDescription();

    }


    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, LoseThornsPower.POWER_ID));
        if (this.owner.hasPower("Thorns")) {
            if (!this.owner.hasPower(ArtifactPower.POWER_ID)) {
                if (this.owner.getPower("Thorns").amount <= this.amount) {
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Thorns"));
                } else {
                    AbstractDungeon.player.getPower(ThornsPower.POWER_ID).stackPower(this.amount * -1);

                }
            } else {
                this.owner.getPower(ArtifactPower.POWER_ID).onSpecificTrigger();
            }
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new LoseThornsPower(owner, amount);
    }
}
