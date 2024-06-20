package brainlets.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static brainlets.BasicMod.makeID;

public class BronzeReinforcementPower extends BasePower implements NonStackablePower {
    public static final String POWER_ID = makeID("BronzeReinforcementPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;
    private int lastTurnPlatedArmor;

    public BronzeReinforcementPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, AbstractDungeon.player, null, -1);
        canGoNegative = false;
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.type = PowerType.BUFF;
        updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(PlatedArmorPower.POWER_ID)) {
            lastTurnPlatedArmor = p.getPower(PlatedArmorPower.POWER_ID).amount;
        } else {
            lastTurnPlatedArmor = 0;
        }
    }

    public void atStartOfTurn() {
        int thisTurnPlatedArmor = 0;
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(PlatedArmorPower.POWER_ID)) {
            thisTurnPlatedArmor = p.getPower(PlatedArmorPower.POWER_ID).amount;
        }
        if (thisTurnPlatedArmor < lastTurnPlatedArmor - 3) {
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PlatedArmorPower(p, (lastTurnPlatedArmor - 3) - thisTurnPlatedArmor), (lastTurnPlatedArmor - 3) - thisTurnPlatedArmor));
        }
    }

    @Override
    public void updateDescription() {
            description = DESCRIPTIONS[0];
    }
}
