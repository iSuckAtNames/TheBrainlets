package brainlets.cards.EvabSet;

import brainlets.actions.DiscardPileToStasisAction;
import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DataRecovery extends BaseCard {
    public static final String ID = makeID("DataRecovery");

    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 3;

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public DataRecovery() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        this.isEthereal = true;
        setBlock(BLOCK,UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot((new DiscardPileToStasisAction(1)));
    }


    @Override
    public AbstractCard makeCopy() { //Optional
        return new DataRecovery();
    }
}
