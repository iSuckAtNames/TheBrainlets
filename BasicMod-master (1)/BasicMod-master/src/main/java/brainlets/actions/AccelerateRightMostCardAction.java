package brainlets.actions;

import brainlets.BasicMod;
import brainlets.orbs.StasisOrb;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;

public class AccelerateRightMostCardAction extends AbstractGameAction{

    public AccelerateRightMostCardAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = 0.05F;
    }

    public void update() {
        if (this.duration == this.startDuration && AbstractDungeon.player.orbs.size() > 0) {
            BasicMod.logger.info("AccelerateRightMostCard firing.");
            AbstractOrb theOrb = null;
            for (AbstractOrb o : AbstractDungeon.player.orbs) {
                if (o instanceof StasisOrb) {
                    o.onStartOfTurn();
                    theOrb = o;
                    break;
                }
            }
            if (theOrb != null) {
                for (int i = 0; i < 20; i++) {
                    AbstractDungeon.effectsQueue.add(new LightFlareParticleEffect(theOrb.tX, theOrb.tY, Color.YELLOW));
                }
            }
        }
        this.tickDuration();
    }
}
