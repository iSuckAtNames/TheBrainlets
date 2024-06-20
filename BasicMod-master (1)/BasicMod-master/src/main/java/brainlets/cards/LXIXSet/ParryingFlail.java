package brainlets.cards.LXIXSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.powers.LoseThornsPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

public class ParryingFlail extends BaseCard {
    public static final String ID = makeID("ParryingFlail");
    private static final int DAMAGE = 6;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;
    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );

    public ParryingFlail() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("M2",6);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
        this.addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, customVar("M2")), customVar("M2")));
        this.addToBot(new ApplyPowerAction(p, p, new LoseThornsPower(p, customVar("M2")), customVar("M2")));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ParryingFlail();
    }
}
