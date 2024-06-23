package brainlets.powers;

import basemod.interfaces.CloneablePowerInterface;
import brainlets.util.TextureLoader;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;

import static brainlets.BasicMod.makeID;

public class StunnedPower extends BasePower implements CloneablePowerInterface{
    public static final String POWER_ID = makeID("StunnedPower");
    public static PowerType POWER_TYPE = PowerType.DEBUFF;
    public int storedHandSize;
    private AbstractCreature source;

    private static final boolean TURN_BASED = true;


    public StunnedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, POWER_TYPE, TURN_BASED, owner, amount);
        this.owner = owner;
        this.img = TextureLoader.getTexture("images/stslib/powers/32/stun.png");
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        updateDescription();

    }

    public void atEndOfRound() {
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, StunnedPower.POWER_ID));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, StunnedPower.POWER_ID, 1));
        }

    }

    public void atStartOfTurn() {
        this.storedHandSize = AbstractDungeon.player.gameHandSize;
        AbstractDungeon.player.gameHandSize = 0;
        AbstractDungeon.actionManager.cardQueue.clear();

        for (AbstractCard c : AbstractDungeon.player.limbo.group) {
            AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
        }

        AbstractDungeon.player.limbo.group.clear();
        AbstractDungeon.player.releaseCard();
        AbstractDungeon.overlayMenu.endTurnButton.disable(true);
    }

    public void atStartOfTurnPostDraw() {
        AbstractDungeon.player.gameHandSize = this.storedHandSize;
    }


    @Override
    public void updateDescription() {
        if (this.amount <= 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new StunnedPower(owner, amount);
    }
}
