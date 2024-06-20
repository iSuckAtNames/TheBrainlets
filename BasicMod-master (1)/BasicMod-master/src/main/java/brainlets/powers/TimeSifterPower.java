package brainlets.powers;

import basemod.interfaces.CloneablePowerInterface;
import brainlets.actions.AccelerateRightMostCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static brainlets.BasicMod.makeID;

public class TimeSifterPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("TimeSifterPower");
    public static PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractCreature source;


    public TimeSifterPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        updateDescription();

    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];

        }

    }

    @Override
    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();

        if (AbstractDungeon.player.orbs.size() > 0) {

            for (int i = 0; i < this.amount; i++) {
                AbstractDungeon.actionManager.addToBottom(new AccelerateRightMostCardAction());

            }

        }

    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new TimeSifterPower(owner, source, amount);
    }
}
