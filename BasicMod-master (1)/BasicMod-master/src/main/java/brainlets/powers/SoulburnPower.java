package brainlets.powers;

import basemod.interfaces.CloneablePowerInterface;
import brainlets.util.GeneralUtils;
import brainlets.util.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import vfx.ExplosionSmallEffectGreen;

import static brainlets.BasicMod.makeID;
import static brainlets.BasicMod.modID;
import static brainlets.character.theBrainlets.Enums.TheBrainlets;

public class SoulburnPower extends TwoAmountPower implements CloneablePowerInterface, HealthBarRenderPower {
    public static final String POWER_ID = makeID("SoulburnPower");
    public static Color myColor = new Color(0.529F, 0.922F, 0, 1);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SoulburnPower(final AbstractCreature owner, final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        amount2 = 3;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        String unPrefixed = GeneralUtils.removePrefix(POWER_ID);
            Texture normalTexture = TextureLoader.getPowerTexture(unPrefixed);
            Texture hiDefImage = TextureLoader.getHiDefPowerTexture(unPrefixed);
            if (hiDefImage != null)
            {
                region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
                if (normalTexture != null)
                    region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
            }
            else if (normalTexture != null)
            {
                this.img = normalTexture;
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
            }
        this.updateDescription();
    }

    @Override
    public int getHealthBarAmount() {
        if (amount2 == 1)
            return amount;
        return 0;
    }

    @Override
    public Color getColor() {
        return myColor.cpy();
    }

    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead() && amount2 == 1) {// 65 66
            explode();
        } else {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    amount2--;
                    updateDescription();
                }
            });
        }
    }// 70

    public void explode(){
        this.flashWithoutSound();
        this.addToBot(new VFXAction(new ExplosionSmallEffectGreen(this.owner.hb.cX, this.owner.hb.cY), 0.1F));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        this.addToBot(new LoseHPAction(owner, owner, amount, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void updateDescription() {
        if (amount2 == 1)
            description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
        else
            description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1] + amount + DESCRIPTIONS[3];
    }

    @Override
    public AbstractPower makeCopy() {
        return new SoulburnPower(owner, amount);
    }
}
