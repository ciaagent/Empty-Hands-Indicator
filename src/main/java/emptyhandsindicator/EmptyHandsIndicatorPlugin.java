package emptyhandsindicator;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.Renderable;
import net.runelite.api.events.GameTick;
import net.runelite.client.callback.Hooks;
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

	@Inject
	private IndicationSelfOverlay indicationSelfOverlay;

	@Inject
	private Hooks hooks;

	private final Hooks.RenderableDrawListener renderableDrawListener = this::shouldDrawPlayer;

	@Provides
	EmptyHandsIndicatorConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(EmptyHandsIndicatorConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(emptyHandsIndicatorOverlay);
		hooks.registerRenderableDrawListener(renderableDrawListener);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(emptyHandsIndicatorOverlay);
		overlayManager.remove(indicationSelfOverlay);
		hooks.unregisterRenderableDrawListener(renderableDrawListener);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (event.getGroup().equals(EmptyHandsIndicatorConfig.GROUP) && event.getKey().equals("excludedPlayers")) {
			emptyHandsIndicatorService.updateExcludedPlayers();
		}
	}

	@Subscribe
	public void onGameTick(GameTick event) {
		if(emptyHandsIndicatorService.shouldNotify()) {
			overlayManager.add(indicationSelfOverlay);
		}
		else
			overlayManager.remove(indicationSelfOverlay);

	}

	boolean shouldDrawPlayer(Renderable renderable, boolean drawingUI) {
		if(!config.hideFullHandedPlayers()){
			return true;
		}
		if (renderable instanceof Player) {
			Player player = (Player) renderable;
			Player local = client.getLocalPlayer();

			if (player.getName() == null) {
				return true;
			}

            return player.getId() == local.getId() || emptyHandsIndicatorService.areHandsEmpty(player);
		}
		return true;
	}
}
