package brainlets.cards.EvabSet;

import brainlets.cards.BaseCard;
import brainlets.cards.InStasisCard;
import brainlets.character.theBrainlets;
import brainlets.orbs.StasisOrb;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static brainlets.BasicMod.TICK;

public class Pushback extends BaseCard implements InStasisCard {
    public static final String ID = makeID("Pushback");
    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 3;
    private static final int MAGIC = 1;

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Pushback() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC);
        tags.add(TICK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }

    public void onStartOfTurn(StasisOrb orb) {
        addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Pushback();
    }
}
