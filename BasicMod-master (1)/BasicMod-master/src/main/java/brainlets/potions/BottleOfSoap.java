package brainlets.potions;

import brainlets.actions.ReduceDebuffsAction;
import brainlets.powers.InfectionPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static brainlets.BasicMod.makeID;

public class BottleOfSoap extends BasePotion {
    public static final String ID = makeID("BottleOfSoap");

    private static final Color LIQUID_COLOR = CardHelper.getColor(255, 255, 242);
    private static final Color HYBRID_COLOR = CardHelper.getColor(255, 255, 242);
    private static final Color SPOTS_COLOR = CardHelper.getColor(255, 255, 242);

    public BottleOfSoap() {
        super(ID, 1, PotionRarity.UNCOMMON, PotionSize.BOTTLE, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
    }

    @Override
    public String getDescription() {
        return potionStrings.DESCRIPTIONS[0] + potency + potionStrings.DESCRIPTIONS[1];
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            addToBot(new ReduceDebuffsAction(AbstractDungeon.player, potency));
        }
    }
}
