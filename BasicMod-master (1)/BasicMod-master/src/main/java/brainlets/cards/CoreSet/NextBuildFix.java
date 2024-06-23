package brainlets.cards.CoreSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.powers.AddCopyNextTurnPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class NextBuildFix extends BaseCard {
    public static final String ID = makeID("NextBuildFix");
    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public NextBuildFix() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(4,2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, magicNumber), magicNumber));
        addToBot(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> {
            addToTop(new ExhaustSpecificCardAction(cards.get(0), p.hand, true));
            addToTop(new ApplyPowerAction(p, p, new AddCopyNextTurnPower(cards.get(0))));
        }));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new NextBuildFix();
    }
}
