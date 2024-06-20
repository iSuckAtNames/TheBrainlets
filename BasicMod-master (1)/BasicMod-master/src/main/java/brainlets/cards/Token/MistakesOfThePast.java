package brainlets.cards.Token;

import brainlets.cards.BaseCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;

public class MistakesOfThePast extends BaseCard {
    public static final String ID = makeID("MistakesOfThePast");

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;
    private static final int MAGIC = 1;

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            1
    );

    public MistakesOfThePast() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        this.isEthereal = true;
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public void afterlife() {
        AbstractPlayer p = AbstractDungeon.player;
        use(p, null);
        addToBot(new ApplyPowerAction(p, p, new BlurPower(p, magicNumber), magicNumber));
    }

    public void triggerOnEndOfPlayerTurn() {
        addToTop((AbstractGameAction)new ExhaustAllEtherealAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MistakesOfThePast();
    }
}
