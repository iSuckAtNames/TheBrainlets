package brainlets.potions;

import brainlets.actions.ReduceDebuffsAction;
import brainlets.character.theBrainlets;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static brainlets.BasicMod.makeID;

public class ClassicalElixir extends BasePotion{
    public static final String ID = makeID("ClassicalElixir");

    private static final Color LIQUID_COLOR = CardHelper.getColor(235, 158, 52);
    private static final Color HYBRID_COLOR = CardHelper.getColor(138, 97, 3);
    private static final Color SPOTS_COLOR = CardHelper.getColor(204, 52, 235);

    public ClassicalElixir() {
        super(ID, 3, PotionRarity.RARE, PotionSize.SPHERE, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
        playerClass = theBrainlets.Enums.TheBrainlets;
    }

    @Override
    public String getDescription() {
        return potionStrings.DESCRIPTIONS[0] + potency + potionStrings.DESCRIPTIONS[1];
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            addToBot(new IncreaseMaxOrbAction(potency));
        }
    }
}
