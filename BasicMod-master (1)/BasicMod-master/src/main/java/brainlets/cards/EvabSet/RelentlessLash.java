package brainlets.cards.EvabSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.orbs.StasisOrb;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static brainlets.BasicMod.SELFSTASIS;

public class RelentlessLash extends BaseCard {
    public static final String ID = makeID("RelentlessLash");
    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;
    private static final int MAGIC = 2;
    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    public RelentlessLash() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(MAGIC);
        tags.add(SELFSTASIS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
    }

    public void whenEnteredStasis(StasisOrb orb) {
        orb.passiveAmount = 2;
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new RelentlessLash();
    }
}
