package brainlets.potions;

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

public class TamePotion extends BasePotion {
    public static final String ID = makeID("TamePotion");

    private static final Color LIQUID_COLOR = CardHelper.getColor(52, 216, 235);
    private static final Color HYBRID_COLOR = CardHelper.getColor(232, 169, 9);
    private static final Color SPOTS_COLOR = null;

    public TamePotion() {
        super(ID, 18, PotionRarity.COMMON, PotionSize.ANVIL, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
    }

    @Override
    public String getDescription() {
        return potionStrings.DESCRIPTIONS[0] + potency + potionStrings.DESCRIPTIONS[1] + potionStrings.DESCRIPTIONS[2];
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, potency));
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new WeakPower(AbstractDungeon.player, 2, false), 2));
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FrailPower(AbstractDungeon.player, 2, false), 2));
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new InfectionPower(AbstractDungeon.player, 2), 2));
        }
    }
}
