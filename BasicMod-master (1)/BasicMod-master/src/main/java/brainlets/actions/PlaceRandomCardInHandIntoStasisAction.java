package brainlets.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PlaceRandomCardInHandIntoStasisAction extends AbstractGameAction {
    public static final String[] TEXT;

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("Brainlets:UIOptions").TEXT;
    }

    public PlaceRandomCardInHandIntoStasisAction(AbstractCreature source) {
        this.setValues(AbstractDungeon.player, source);
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.duration == 0.5F) {
            if (AbstractDungeon.player.hand.isEmpty())
            {
                this.isDone = true;
                return;
            }
            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
        } else {
            AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));
            AbstractDungeon.actionManager.addToTop(new PlaceActualCardIntoStasis(AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng), AbstractDungeon.player.hand));
            this.isDone = true;
            return;
        }
        this.tickDuration();
    }
}
