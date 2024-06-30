package brainlets.relics;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.PrismaticShard;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;

import java.util.ArrayList;

import static brainlets.BasicMod.makeID;

public class CarbonsSkillbook extends BaseRelic implements OnCreateCardInterface{
    private static final String NAME = "CarbonsSkillbook"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.FLAT; //The sound played when the relic is clicked.

    private static boolean receivedShard;

    public CarbonsSkillbook() {
        super(ID, NAME, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public int changeNumberOfCardsInReward(int numberOfCards) {
        return numberOfCards + 1;
    }

    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    public void onEquip() {
        receivedShard = false;
    }

    public void update() {
        super.update();
        if (AbstractDungeon.player.hasRelic(PrismaticShard.ID)) {
            receivedShard = true;
        }
        if (!AbstractDungeon.isScreenUp && !receivedShard) {
            AbstractDungeon.combatRewardScreen.open();
            AbstractDungeon.combatRewardScreen.rewards.clear();
            AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(RelicLibrary.getRelic(PrismaticShard.ID).makeCopy()));
            receivedShard = true;
            AbstractDungeon.combatRewardScreen.positionRewards();
            (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 0.25F;  
        }
    }

    public void onCardDraw(AbstractCard card) {
        if (card.cost >= 0) {
            int newCost = 1;
            if (card.cost != newCost) {
                card.cost = newCost;
                card.costForTurn = card.cost;
                card.isCostModified = true;
            }
            card.freeToPlayOnce = false;
        }
    }

    public void onCreateCard(AbstractCard card) {
        if (card.cost >= 0) {
            int newCost = 1;
            if (card.cost != newCost) {
                card.cost = newCost;
                card.costForTurn = card.cost;
                card.isCostModified = true;
            }
            card.freeToPlayOnce = false;
        }
    }


    public AbstractRelic makeCopy() {
        return new CarbonsSkillbook();
    }
}
