package emptyhandsindicator;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Empty Hands Indicator"
)
public class EmptyHandsIndicatorPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private EmptyHandsIndicatorConfig config;

	@Inject
	private EmptyHandsIndicatorService emptyHandsIndicatorService;

	@Inject
	private EmptyHandsIndicatorOverlay emptyHandsIndicatorOverlay;

	@Provides
	EmptyHandsIndicatorConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(EmptyHandsIndicatorConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(emptyHandsIndicatorOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(emptyHandsIndicatorOverlay);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (event.getGroup().equals(EmptyHandsIndicatorConfig.GROUP)) {
			emptyHandsIndicatorService.updateExcludedPlayers();
		}
	}
}
