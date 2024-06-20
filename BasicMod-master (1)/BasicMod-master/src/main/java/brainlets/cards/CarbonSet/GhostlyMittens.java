package brainlets.cards.CarbonSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class GhostlyMittens extends BaseCard {

    public static final String ID = makeID("GhostlyMittens");

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public GhostlyMittens() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        this.isEthereal = true;
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public void afterlife() {
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)mo, magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    public void triggerOnEndOfPlayerTurn() {
        addToTop((AbstractGameAction)new ExhaustAllEtherealAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new GhostlyMittens();
    }
}
