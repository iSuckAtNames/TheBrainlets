package brainlets.cards.EvabSet;

import brainlets.actions.DecelerateRightMostCardAction;
import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.actions.watcher.WallopAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BrakingBlast extends BaseCard {
    public static final String ID = makeID("BrakingBlast");
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 2;

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public BrakingBlast() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new WallopAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new DecelerateRightMostCardAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BrakingBlast();
    }
}
