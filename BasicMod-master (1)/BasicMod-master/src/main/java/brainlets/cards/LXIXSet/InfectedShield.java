package brainlets.cards.LXIXSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.powers.InfectionPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class InfectedShield extends BaseCard {
    public static final String ID = makeID("InfectedShield");
    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 4;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;
    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );

    public InfectedShield() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        this.addToBot(new ApplyPowerAction(p, p, new InfectionPower(p, magicNumber), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new InfectedShield();
    }
}
