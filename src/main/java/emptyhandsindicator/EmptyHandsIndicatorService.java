package emptyhandsindicator;

import java.awt.Color;
import java.util.function.BiConsumer;
import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.kit.KitType;
import net.runelite.api.Player;
import net.runelite.client.party.PartyService;

@Slf4j
@Singleton
public class EmptyHandsIndicatorService {
    private final Client client;
    private final EmptyHandsIndicatorConfig config;
    private final PartyService partyService;

    @Inject
    private EmptyHandsIndicatorService(Client client, EmptyHandsIndicatorConfig config, PartyService partyService)
    {
        this.config = config;
        this.client = client;
        this.partyService = partyService;
    }

    void forEachPlayer(final BiConsumer<Player, Color> consumer)
    {
        for (Player player : client.getTopLevelWorldView().players())
        {
            if (player == null)
            {
                continue;
            }

            Color color = getColor(player);
            if (color != null)
            {
                consumer.accept(player, color);
            }
        }
    }

    Color getColor(Player player)
    {
        Color color = null;
        boolean handsEmpty = false;

        boolean isPlayer = player == client.getLocalPlayer();
        boolean isFriendChat = player.isFriendsChatMember();
        boolean isFriend = player.isFriend();
        boolean isInParty = partyService.isInParty() && partyService.getMemberByDisplayName(player.getName()) != null;
        boolean isClanMember = player.isClanMember();



        if (isFriendChat && config.indicateFriendChat()) {
            handsEmpty = areHandsEmpty(player);
            color = config.getFriendChatColor();
        }
        else if (isFriend && config.indicateFriends()) {
            handsEmpty = areHandsEmpty(player);
            color = config.getFriendColor();
        }
        else if (isInParty && config.indicatePartyMembers()) {
            handsEmpty = areHandsEmpty(player);
            color = config.getPartyMemberColor();
        }
        else if (isClanMember && config.indicateClanMembers()) {
            handsEmpty = areHandsEmpty(player);
            color = config.getClanMemberColor();
        }
        else if (!(isPlayer || isFriendChat || isFriend || isInParty || isClanMember) && config.indicateEveryoneElse()) {
            handsEmpty = areHandsEmpty(player);
            color = config.getEveryoneColor();
        }

        if (color == null || !handsEmpty)
        {
            return null;
        }

        return color;
    }

    private boolean areHandsEmpty(Player player) {
        int itemId = player.getPlayerComposition().getEquipmentId(KitType.WEAPON);
        return itemId == -1;
    }

}
