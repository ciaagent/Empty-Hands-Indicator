package emptyhandsindicator;

import net.runelite.client.config.Config;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;
import java.awt.*;

@ConfigGroup(EmptyHandsIndicatorConfig.GROUP)
public interface EmptyHandsIndicatorConfig extends Config
{
	String GROUP = "Indicate empty handed";

	@ConfigSection(
			name = "Indication Style",
			description = "Configure the type of indication.",
			position = 1
	)
	String indicationStyleSection = "indicationStyleSection";

	@ConfigSection(
			name = "Player types",
			description = "Toggle Indicating players by type and choose their color.",
			position = 2
	)
	String playersSection = "playersSection";

	@ConfigSection(
			name = "Check your hands",
			description = "Notifies you if you're empty handed",
			position = 3
	)
	String alarmSection = "alarmSection";

	@ConfigSection(
			name = "Player Exclusion",
			description = "Exclude player from indication.",
			position = 4
	)
	String playersExclusionSection = "playersExclusionSection";

	@ConfigItem(
			position = 1,
			keyName = "indicationStyle",
			name = "Indication Style",
			description = "How would you like it to show?",
			section = indicationStyleSection
	)
	default IndicationStyleOthers indicationStyle(){
		return IndicationStyleOthers.OVERHEADTEXT;
	}

	@ConfigItem(
			position = 2,
			keyName = "borderWidth",
			name = "Outline Border Width",
			description = "This is the width of the outline around a player",
			section = indicationStyleSection
	)
	@Range(
			min = 1,
			max = 50
	)
	default int getBorderWidth(){return 3;}

	@ConfigItem(
			position = 3,
			keyName = "borderFeather",
			name = "Outline Feather",
			description = "Amount to feather the outline around a player.",
			section = indicationStyleSection
	)
	@Range(
			max = 5
	)
	default int getBorderFeather(){return 5;}

	@ConfigItem(
			position = 4,
			keyName = "hideFullHandedPlayers",
			name = "Hide others",
			description = "Only show empty handed players.",
			section = indicationStyleSection
	)
	default boolean hideFullHandedPlayers()
	{
		return false;
	}

	@ConfigItem(
			position = 1,
			keyName = "indicatePartyMembers",
			name = "Party members",
			description = "Show indicator for party members.",
			section = playersSection
	)
	default boolean indicatePartyMembers()
	{
		return false;
	}

	@ConfigItem(
			position = 2,
			keyName = "partyMemberTextColor",
			name = "Party color",
			description = "Color for party members.",
			section = playersSection
	)
	default Color getPartyMemberColor()
	{
		return new Color(234, 123, 91);
	}

	@ConfigItem(
			position = 3,
			keyName = "indicateFriends",
			name = "Friends",
			description = "Show indicator for friends.",
			section = playersSection
	)
	default boolean indicateFriends()
	{
		return false;
	}

	@ConfigItem(
			position = 4,
			keyName = "friendTextColor",
			name = "Friends' color",
			description = "Color for friends.",
			section = playersSection
	)
	default Color getFriendColor()
	{
		return new Color(0, 200, 83);
	}

	@ConfigItem(
			position = 5,
			keyName = "indicateClan",
			name = "Clan members",
			description = "Show indicator for clan members.",
			section = playersSection
	)
	default boolean indicateClanMembers()
	{
		return false;
	}

	@ConfigItem(
			position = 6,
			keyName = "clanMemberColor",
			name = "Clan color",
			description = "Color for clan members.",
			section = playersSection
	)
	default Color getClanMemberColor()
	{
		return new Color(255, 0, 212);
	}

	@ConfigItem(
			position = 7,
			keyName = "indicateFriendChat",
			name = "Friend Chat",
			description = "Show indicator for friend chat members.",
			section = playersSection
	)
	default boolean indicateFriendChat()
	{
		return false;
	}

	@ConfigItem(
			position = 8,
			keyName = "friendChatColor",
			name = "Friend Chat color",
			description = "Color for friend chat member.",
			section = playersSection
	)
	default Color getFriendChatColor()
	{
		return new Color(170, 0, 255);
	}

	@ConfigItem(
			position = 9,
			keyName = "EveryoneElse",
			name = "Everyone Else",
			description = "Show indicator for Everyone else.",
			section = playersSection
	)
	default boolean indicateEveryoneElse()
	{
		return false;
	}

	@ConfigItem(
			position = 10,
			keyName = "EveryoneElseColor",
			name = "Everyone else's color",
			description = "Color of everyone's text.",
			section = playersSection
	)
	default Color getEveryoneColor()
	{
		return new Color(255, 0, 0);
	}

	@ConfigItem(
			position = 1,
			keyName = "playerAlarm",
			name = "Enable flash alarm",
			description = "Flashes screen when your hands are empty.",
			section = alarmSection
	)
	default boolean selfAlarmNotification()
	{
		return false;
	}

	@Alpha
	@ConfigItem(
			position = 2,
			keyName = "alarmColor",
			name = "Flash alarm color",
			description = "Sets the color of the alarm flashes",
			section = alarmSection
	)
	default Color flashColor()
	{
		return new Color(255, 0, 0, 70);
	}

	@ConfigItem(
			position = 0,
			keyName = "excludedPlayers",
			name = "Excluded Players",
			description = "Format: Woox, Zezima, Shiddy acc",
			section = playersExclusionSection
	)
	default String getExcludedPlayers()
	{
		return "";
	}

}
