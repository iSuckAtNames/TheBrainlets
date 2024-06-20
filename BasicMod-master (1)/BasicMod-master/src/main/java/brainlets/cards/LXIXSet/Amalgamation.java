package brainlets.cards.LXIXSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.powers.CoalescencePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

public class Amalgamation extends BaseCard {
    public static final String ID = makeID("Amalgamation");
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final CardStrings cardStrings;

    private static final int MAGIC = 3;

    private static final int UPG_MAGIC = 1;


    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    // /STAT DECLARATION/

    public Amalgamation() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("M2",2,1);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber), magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, magicNumber), magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new CoalescencePower(p, customVar("M2")), customVar("M2")));
    }

    public AbstractCard makeCopy() {
        return new Amalgamation();
    }
}
