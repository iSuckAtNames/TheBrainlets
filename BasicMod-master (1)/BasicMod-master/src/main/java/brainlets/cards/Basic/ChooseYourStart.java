package brainlets.cards.Basic;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.powers.InfectionPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

public class ChooseYourStart extends BaseCard {
    public static final String ID = makeID("ChooseYourStart");
    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            0
    );

    public ChooseYourStart() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(3,1);
        setCustomVar("M2",3,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, magicNumber), magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new InfectionPower(p, customVar("M2")), customVar("M2")));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ChooseYourStart();
    }
}




