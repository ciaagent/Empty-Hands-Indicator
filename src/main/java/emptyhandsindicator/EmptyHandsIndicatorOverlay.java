package emptyhandsindicator;

import java.awt.*;
import javax.inject.Inject;

import net.runelite.api.Player;
import net.runelite.api.Point;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;
import net.runelite.client.util.Text;

public class EmptyHandsIndicatorOverlay extends Overlay {

    private static final int TEXT_MARGIN = 50;

    private final EmptyHandsIndicatorService indicatorService;
    private final EmptyHandsIndicatorConfig config;
    private final ModelOutlineRenderer outlineRenderer;

    @Inject
    private EmptyHandsIndicatorOverlay(
            EmptyHandsIndicatorService indicatorService,
            EmptyHandsIndicatorConfig config,
            ModelOutlineRenderer outlineRenderer)
    {
        this.indicatorService = indicatorService;
        this.config = config;
        this.outlineRenderer = outlineRenderer;
        setPosition(OverlayPosition.DYNAMIC);
        setPriority(PRIORITY_MED);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        indicatorService.forEachPlayer((player, color) -> {

            if (config.indicationStyle() == IndicationStyle.OVERHEADTEXT || config.indicationStyle() == IndicationStyle.BOTH) {
                renderPlayerOverlay(graphics, player, color);
            }

            if (config.indicationStyle() == IndicationStyle.OUTLINE || config.indicationStyle() == IndicationStyle.BOTH) {
                renderPlayerOutline(player, color);
            }

        });
        return null;
    }

    private void renderPlayerOverlay(Graphics2D graphics, Player player, Color color)
    {
        final int zOffset = player.getLogicalHeight() + TEXT_MARGIN;

        final String name = Text.sanitize(player.getName() + "'s hands are empty. Egg em!");
        Point textLocation = player.getCanvasTextLocation(graphics, name, zOffset);

        if (textLocation == null)
        {
            return;
        }

        OverlayUtil.renderTextLocation(graphics, textLocation, name, color);
    }

    private void renderPlayerOutline(Player player, Color color) {
        outlineRenderer.drawOutline(player, config.getBorderWidth(), color, config.getBorderFeather());
    }
}