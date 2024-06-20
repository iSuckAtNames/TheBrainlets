package brainlets.cards.LXIXSet;

import brainlets.actions.ReduceDebuffsAction;
import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class VirtuousRepentance extends BaseCard {
    public static final String ID = makeID("VirtuousRepentance");
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final CardStrings cardStrings;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;
    //END TUNING CONSTANTS

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }

    public VirtuousRepentance() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ReduceDebuffsAction(AbstractDungeon.player, magicNumber));
    }

    public AbstractCard makeCopy() {
        return new VirtuousRepentance();
    }
}
