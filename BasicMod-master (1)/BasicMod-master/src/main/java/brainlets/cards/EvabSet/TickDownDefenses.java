package brainlets.cards.EvabSet;

import brainlets.cards.BaseCard;
import brainlets.character.theBrainlets;
import brainlets.orbs.StasisOrb;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static brainlets.BasicMod.TICK;

public class TickDownDefenses extends BaseCard {
    public static final String ID = makeID("TickDownDefenses");

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    private static final brainlets.util.CardStats info = new brainlets.util.CardStats(
            theBrainlets.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    public TickDownDefenses() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("M2", 1);
        tags.add(TICK);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, magicNumber), magicNumber));
    }

    public void onStartOfTurn(StasisOrb orb) {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, customVar("M2")), customVar("M2")));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new TickDownDefenses();
    }
}
