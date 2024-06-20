package brainlets.actions;

import brainlets.BasicMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import java.util.Iterator;

public class DiscardPileToStasisAction extends AbstractGameAction{
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString("Brainlets:UIOptions").TEXT;
    private final AbstractPlayer p;
    private final int amount;

    public DiscardPileToStasisAction(int amount) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player);
        this.amount = amount;
        this.actionType = ActionType.CARD_MANIPULATION;
    }


    public void update() {
        if (this.duration == 0.5F) {
            if (!BasicMod.canSpawnStasisOrb()) {
                isDone = true;
                if (!AbstractDungeon.player.hasEmptyOrb())
                    AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, TEXT[5], true));
                return;
            }

            if (this.p.discardPile.size() == 0) {
                this.isDone = true;
            } else if (this.p.discardPile.size() == 1) {
                AbstractCard card = this.p.discardPile.group.get(0);
                card.lighten(false);
                AbstractDungeon.actionManager.addToBottom(new PlaceActualCardIntoStasis(card, AbstractDungeon.player.discardPile));
                this.isDone = true;
            } else {
                AbstractDungeon.gridSelectScreen.open(this.p.discardPile, this.amount, false, TEXT[0]);
            }
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
                BasicMod.logger.info("DiscardPileToStasisAction: card highlighted");
                Iterator<AbstractCard> cardIterator = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                AbstractCard c;
                while (cardIterator.hasNext()) {
                    c = cardIterator.next();
                    if (BasicMod.canSpawnStasisOrb()) {
                        AbstractDungeon.actionManager.addToBottom(new PlaceActualCardIntoStasis(c, AbstractDungeon.player.discardPile));
                    } else {
                        if (!AbstractDungeon.player.hasEmptyOrb()) {
                            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, TEXT[5], true));
                            this.isDone = true;
                        }
                    }
                    c.lighten(false);
                    c.unhover();
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();

                for (cardIterator = this.p.discardPile.group.iterator(); cardIterator.hasNext(); c.target_y = 0.0F) {
                    c = cardIterator.next();
                    c.unhover();
                    c.target_x = (float) CardGroup.DISCARD_PILE_X;
                }

                this.isDone = true;
            }
        }
        this.tickDuration();
    }
}
