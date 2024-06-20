package brainlets.cards.LXIXSet;

import brainlets.actions.EasyXCostAction;
import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.powers.InfectionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class SurvivalInstincts extends BaseCard {
    public static final String ID = makeID("SurvivalInstincts");

    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC = 1;

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            -1
    );

    public SurvivalInstincts() {
        super(ID,info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }
        addToBot((AbstractGameAction)new EasyXCostAction((AbstractCard)this, (effect, params) -> {
            for (int i = 0; i < effect.intValue(); i++) {
                addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)p, this.block));
                addToBot(new ApplyPowerAction(p, p, new WeakPower(p, magicNumber, false), magicNumber));
                addToBot(new ApplyPowerAction(p, p, new FrailPower(p, magicNumber, false), magicNumber));
                addToBot(new ApplyPowerAction(p, p, new InfectionPower(p, magicNumber), magicNumber));

            }
            return Boolean.valueOf(true);
        }));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SurvivalInstincts();
    }

}
