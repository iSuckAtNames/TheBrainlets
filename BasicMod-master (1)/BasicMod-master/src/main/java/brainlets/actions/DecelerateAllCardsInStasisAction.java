package brainlets.actions;

import brainlets.BasicMod;
import brainlets.orbs.StasisOrb;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class DecelerateAllCardsInStasisAction extends AbstractGameAction {
    public DecelerateAllCardsInStasisAction() {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = 0.05F;
    }

    public void update() {
        if (this.duration == this.startDuration && AbstractDungeon.player.orbs.size() > 0) {
            BasicMod.logger.info("Decelerating all cards in Stasis.");

            for (AbstractOrb o : AbstractDungeon.player.orbs) {
                if (o instanceof StasisOrb) {
                    o.passiveAmount++;
                    o.evokeAmount++;
                    ((StasisOrb) o).stasisCard.superFlash(Color.GOLDENROD);
                }
            }
        }
        this.tickDuration();
    }
}
