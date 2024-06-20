package brainlets.cards.CoreSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.Collections;

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
        //I do not know if this code works, at all.
        ArrayList<AbstractRelic> eligibleRelicsList = new ArrayList<>(AbstractDungeon.player.relics);
        eligibleRelicsList.removeIf(c -> c.tier == AbstractRelic.RelicTier.STARTER || c.tier == AbstractRelic.RelicTier.BOSS || c.tier == AbstractRelic.RelicTier.SPECIAL);
        eligibleRelicsList.removeIf(c -> {
            try {
                return equipCheck(c);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return false;
            }
        });
        if (!eligibleRelicsList.isEmpty()) {
            Collections.shuffle(eligibleRelicsList, AbstractDungeon.cardRandomRng.random);
            AbstractRelic q = eligibleRelicsList.get(0);
            q.flash();
            AbstractDungeon.player.loseRelic(q.relicId);
            AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.valueOf(q.relicId));
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean newMusic = super.canUse(p, m);
        if (p.relics.stream().anyMatch(r -> {
            try {
                return (r.tier != AbstractRelic.RelicTier.STARTER && r.tier != AbstractRelic.RelicTier.SPECIAL  && r.tier != AbstractRelic.RelicTier.BOSS && equipCheck(r));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return false;
        })) {
            return newMusic;
        }
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
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
