package brainlets.cards.LXIXSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

public class ProbingJab extends BaseCard {
    public static final String ID = makeID("ProbingJab");
    private static final int DAMAGE = 14;
    private static final int UPG_DAMAGE = 4;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;
    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2
    );

    public ProbingJab() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber), magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, magicNumber), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ProbingJab();
    }
}
