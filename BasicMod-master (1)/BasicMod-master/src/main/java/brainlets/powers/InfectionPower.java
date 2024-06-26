package brainlets.powers;

import basemod.interfaces.CloneablePowerInterface;
import brainlets.relics.TheWhiteMothsEmbrace;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static brainlets.BasicMod.makeID;

public class InfectionPower extends BasePower implements CloneablePowerInterface, HealthBarRenderPower {
    public static final String POWER_ID = makeID("InfectionPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;
    public InfectionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void atEndOfTurn(boolean isPlayer) {
        flash();
        addToBot(new DamageAction(this.owner, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS)));
        if (this.owner != AbstractDungeon.player || !(AbstractDungeon.player.hasRelic(TheWhiteMothsEmbrace.ID))) {
            addToBot(new ReducePowerAction(this.owner, this.owner, this, 2));
        }
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public int getHealthBarAmount() {
        return this.amount;
    }

    @Override
    public Color getColor() {
        return CardHelper.getColor(227.0f, 123.0f, 18.0f);
    }

    @Override
    public AbstractPower makeCopy() {
        return new InfectionPower(owner, amount);
    }
}
