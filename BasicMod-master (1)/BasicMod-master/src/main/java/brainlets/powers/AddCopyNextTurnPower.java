package brainlets.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static brainlets.BasicMod.makeID;

public class AddCopyNextTurnPower extends BasePower implements NonStackablePower {
    public static final String POWER_ID = makeID("AddCopyNextTurnPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    private AbstractCard tar;

    public AddCopyNextTurnPower(AbstractCard tar) {
        super(POWER_ID, TYPE, TURN_BASED, AbstractDungeon.player, null, -1);
        canGoNegative = false;
        this.tar = tar;
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.type = PowerType.BUFF;
        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        flash();
        addToBot(new MakeTempCardInHandAction(tar.makeStatEquivalentCopy()));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        if (tar != null)
            description = DESCRIPTIONS[0] + FontHelper.colorString(tar.name, "y") + DESCRIPTIONS[1];
    }

}
