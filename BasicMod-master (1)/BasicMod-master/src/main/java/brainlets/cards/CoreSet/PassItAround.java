package brainlets.cards.CoreSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Circlet;

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

    private static boolean equipCheck(AbstractRelic r) throws NoSuchMethodException {
        //Returns true if the relic does NOT override equip or unequip effects.
        //i.e. Returns true if this relic has an "upon pickup" effect
        return r.getClass().getMethod("onEquip").getDeclaringClass() == AbstractRelic.class  && r.getClass().getMethod("onUnequip").getDeclaringClass() == AbstractRelic.class;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //I have more faith in this code than the last iteration, but still not much.
        ArrayList<AbstractRelic> eligibleRelicsList = new ArrayList<>();
        for (String r : AbstractDungeon.commonRelicPool) {
            eligibleRelicsList.add(RelicLibrary.getRelic(r));
        }
        for (String r : AbstractDungeon.uncommonRelicPool) {
            eligibleRelicsList.add(RelicLibrary.getRelic(r));
        }
        for (String r : AbstractDungeon.rareRelicPool) {
            eligibleRelicsList.add(RelicLibrary.getRelic(r));
        }
        for (String r : AbstractDungeon.shopRelicPool) {
            eligibleRelicsList.add(RelicLibrary.getRelic(r));
        }
        eligibleRelicsList.removeIf(c -> {
            try {
                return equipCheck(c);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return false;
            }
        });
        if (!eligibleRelicsList.isEmpty()) {
            Collections.shuffle(eligibleRelicsList, cardRandomRng.random);
            AbstractRelic q = eligibleRelicsList.get(0);
            addToBot(new TalkAction(true, "Thanks for the " + q.name, 2.5f, 2.5f));
            getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.valueOf(q.relicId));
        } else {
            addToBot(new TalkAction(true, "Erm what the sigma", 2.5f, 2.5f));
            getCurrRoom().addRelicToRewards(new Circlet());
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
