package brainlets.actions;

import brainlets.cards.InStasisCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ReturnStasisCardToHandAction  extends AbstractGameAction {
    private final AbstractCard card;

    public ReturnStasisCardToHandAction(AbstractCard card) {
        this.card = card;
        this.actionType = ActionType.DAMAGE;
    }

    public void update() {
        if (!AbstractDungeon.player.hand.contains(this.card)) {
            AbstractDungeon.player.hand.addToHand(this.card);
            if (this.card instanceof InStasisCard) {
                ((InStasisCard) this.card).whenReturnedFromStasis();
            }
            this.card.update();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        this.isDone = true;
    }
}
