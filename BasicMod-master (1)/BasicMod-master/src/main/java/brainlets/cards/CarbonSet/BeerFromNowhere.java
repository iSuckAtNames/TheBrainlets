package brainlets.cards.CarbonSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.SwiftPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

import static brainlets.BasicMod.modID;

public class BeerFromNowhere extends BaseCard {
    public static final String ID = makeID("BeerFromNowhere");

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public BeerFromNowhere() {
        super(ID, info);
        this.exhaust = true;
        cardsToPreview = new Wound();
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //Drink a random potion
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractPotion potion = null;
                ArrayList<AbstractPotion> potions = PotionHelper.getPotionsByRarity(AbstractPotion.PotionRarity.COMMON);
                ArrayList<AbstractPotion> drinkable = new ArrayList<>();

                for (AbstractPotion pot: potions) {
                    if(pot.canUse() && !pot.isThrown)
                        drinkable.add(pot);
                }

                if(drinkable.size() > 0)
                    potion = drinkable.get(AbstractDungeon.cardRandomRng.random(drinkable.size()-1)).makeCopy();

                if(potion == null)
                    potion = new SwiftPotion();

                potion.flash();
                potion.use(AbstractDungeon.player);
                for (AbstractRelic relic: AbstractDungeon.player.relics)
                    relic.onUsePotion();
                UIStrings potionStrings = CardCrawlGame.languagePack.getUIString(modID + ":Potions");
                addToBot(new TalkAction(true, "Here's a " + potion.name, 2.5f, 2.5f));
                isDone = true;
            }
        });
        addToBot(new MakeTempCardInDrawPileAction(new Wound(),1, true, true));
        if (isDeadOn()) {
            addToBot(new MakeTempCardInDrawPileAction(new Wound(),2, true, true));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (isDeadOnPos()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BeerFromNowhere();
    }
}
