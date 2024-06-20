package brainlets.cards.CarbonSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.powers.NicoretteBarrelPower;
import brainlets.powers.NicotineAddictionPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NicoretteBarrel extends BaseCard {
    public static final String ID = makeID("NicoretteBarrel");

    public static final String NAME;
    public static final String DESCRIPTION;
    private static final CardStrings cardStrings;


    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            AbstractCard.CardType.POWER,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.SELF,
            1
    );

    public NicoretteBarrel() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(4,2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.effectsQueue.add(new com.megacrit.cardcrawl.vfx.BorderFlashEffect(Color.PURPLE, true));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NicoretteBarrelPower(p, magicNumber), magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NicotineAddictionPower(p, magicNumber), magicNumber));
    }


    public AbstractCard makeCopy() {
        return new NicoretteBarrel();
    }
}
