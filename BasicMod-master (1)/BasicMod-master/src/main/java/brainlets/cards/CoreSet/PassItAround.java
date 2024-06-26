package brainlets.cards.CoreSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Collections;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

public class PassItAround extends BaseCard {
    public static final String ID = makeID("PassItAround");
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final CardStrings cardStrings;

    //END TUNING CONSTANTS

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            4
    );

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }

    public PassItAround() {
        super(ID, info);
        tags.add(CardTags.HEALING);
        FleetingField.fleeting.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int randomNumber = cardRandomRng.random(100);
        if (randomNumber <= 50) {
            getCurrRoom().addRelicToRewards(new Pear());
        } else if (randomNumber <= 80) {
            int coinflip = cardRandomRng.random(10);
            if (coinflip % 2 == 0) {
                getCurrRoom().addRelicToRewards(new Whetstone());
            } else {
                getCurrRoom().addRelicToRewards(new WarPaint());
            }
        } else if (randomNumber <= 99) {
            int coinflip = cardRandomRng.random(10);
            if (coinflip % 2 == 0) {
                getCurrRoom().addRelicToRewards(new PotionBelt());
            } else {
                getCurrRoom().addRelicToRewards(new Waffle());
            }
        } else {
            getCurrRoom().addRelicToRewards(new TinyHouse());
        }
    }

    public AbstractCard makeCopy() {
        return new PassItAround();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
}
