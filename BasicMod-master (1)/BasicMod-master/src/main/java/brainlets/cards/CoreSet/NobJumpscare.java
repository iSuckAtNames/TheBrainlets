package brainlets.cards.CoreSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;

public class NobJumpscare extends BaseCard {
    public static final String ID = makeID("NobJumpscare");

    public static final String NAME;
    public static final String DESCRIPTION;
    private static final CardStrings cardStrings;
    private static final int DAMAGE = 18;
    private static final int UPG_DAMAGE = 8;

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
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );

    public NobJumpscare() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED)));
            for (int i = 0; i < 5; i++) {
                addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new StarBounceEffect(m.hb.cX, m.hb.cY)));
            }
        } else {
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED), 0.4F));
            for (int i = 0; i < 5; i++) {
                addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new StarBounceEffect(m.hb.cX, m.hb.cY)));
            }
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if (p.hasPower(VulnerablePower.POWER_ID)) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
    }

    public void triggerOnGlowCheck() {
        AbstractPlayer p = AbstractDungeon.player;
        //the above line of code is very jank but I had to reference hasPower in a non-static context
        if (p.hasPower(VulnerablePower.POWER_ID)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new NobJumpscare();
    }
}
