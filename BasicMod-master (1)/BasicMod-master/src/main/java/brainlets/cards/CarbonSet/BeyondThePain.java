package brainlets.cards.CarbonSet;

import brainlets.actions.FastLoseHPAction;
import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BeyondThePain extends BaseCard {
    public static final String ID = makeID("BeyondThePain");
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final CardStrings cardStrings;

    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 2;
    //END TUNING CONSTANTS

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }

    public BeyondThePain() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int dmg = this.magicNumber;
        for (int i = 0; i < p.exhaustPile.size(); i++) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                    if (this.target != null && !isDeadOrEscaped(this.target)) {
                        this.addToTop(new FastLoseHPAction(this.target, AbstractDungeon.player, dmg, AttackEffect.FIRE));
                    }
                    isDone = true;
                }
            });
        }
    }

    public AbstractCard makeCopy() {
        return new BeyondThePain();
    }
}
