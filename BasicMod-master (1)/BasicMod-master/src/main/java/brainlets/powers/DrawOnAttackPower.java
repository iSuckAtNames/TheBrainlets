package brainlets.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static brainlets.BasicMod.makeID;

public class DrawOnAttackPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("DrawOnAttackPower");

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final boolean TURN_BASED = true;

    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;

    public DrawOnAttackPower(final int amount) {
        super(POWER_ID, TYPE, TURN_BASED, AbstractDungeon.player, amount);
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            addToBot(new DrawCardAction(this.owner, 1));
            reducePower(1);
            if (this.amount < 1) {
                addToBot(new RemoveSpecificPowerAction(owner, owner, this));
            }
            updateDescription();
        }
    }


    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }


    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new DrawOnAttackPower(amount);
    }
}
