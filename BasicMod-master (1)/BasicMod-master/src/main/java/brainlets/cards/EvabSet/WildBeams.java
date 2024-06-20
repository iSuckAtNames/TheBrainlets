package brainlets.cards.EvabSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.orbs.StasisOrb;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WildBeams extends BaseCard {
    public static final String ID = makeID("WildBeams");
    private static final int DAMAGE = 8;

    private static final int UPG_DAMAGE = 2;

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            2
    );

    public WildBeams() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        for (int i = 0; i < p.orbs.size(); i++) {
            if (p.orbs.get(i) instanceof StasisOrb) {
                addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new WildBeams();
    }
}
