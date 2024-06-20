package brainlets.cards.CoreSet;

import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vfx.SmallLaserEffectColored;

public class MinorBeams extends BaseCard{

    public static final String ID = makeID("MinorBeams");
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 2;

    private static final CardStrings cardStrings;

    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            0
    );

    public MinorBeams() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new SmallLaserEffectColored(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY, Color.WHITE), 0.2F));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        AbstractCard c = this.makeCopy();
        c.cost = 1;
        c.costForTurn = 1;
        if (this.upgraded) {
            c.upgrade();
        }
        CardModifierManager.addModifier(c, new ExhaustMod());
        CardModifierManager.addModifier(c, new EtherealMod());
        addToBot(new MakeTempCardInHandAction(c));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            this.rawDescription = UPGRADED_DESCRIPTION;
            initializeDescription();
        }   
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MinorBeams();
    }
}
