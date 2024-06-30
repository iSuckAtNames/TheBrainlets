package brainlets.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.GamblingChipAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static brainlets.BasicMod.makeID;

public class DeveloperFormPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("DeveloperFormPower");
    public static PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractCreature source;


    public DeveloperFormPower(AbstractCreature owner, AbstractCreature source, int amount) {
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
        for (int i = 0; i < this.amount; i++) {
            addToBot((AbstractGameAction)new GamblingChipAction(AbstractDungeon.player));
        }
    }
    @Override
    public AbstractPower makeCopy() {
        return new DeveloperFormPower(owner, source, amount);
    }
}