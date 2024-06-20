package brainlets.cards.Basic;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Defend extends BaseCard {
    public static final String ID = makeID("Defend");
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;
    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );

    public Defend() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setBlock(BLOCK, UPG_BLOCK);
        tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Defend();
    }
}



