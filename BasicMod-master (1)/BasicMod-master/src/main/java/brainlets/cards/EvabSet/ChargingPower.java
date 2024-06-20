package brainlets.cards.EvabSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.orbs.StasisOrb;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static brainlets.BasicMod.*;

public class ChargingPower extends BaseCard {
    public static final String ID = makeID("ChargingPower");

    private static final int MAGIC = 1;

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    public ChargingPower() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setBlock(6);
        setMagic(MAGIC);
        tags.add(SELFSTASIS);
        tags.add(TICK);
        tags.add(VOLATILE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new TalkAction(p, "DESTROY! DESTROY! DESTROY!"));
    }

    public void onStartOfTurn(StasisOrb orb) {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new GainBlockAction(p,p, block));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ChargingPower();
    }
}
