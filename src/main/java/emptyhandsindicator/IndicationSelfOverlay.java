package emptyhandsindicator;

import com.google.common.base.Strings;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayPanel;

import javax.inject.Inject;
import java.awt.*;

public class IndicationSelfOverlay extends OverlayPanel {
    private final EmptyHandsIndicatorConfig config;
    private final Client client;
    private final Color transparent = new Color(0, 0, 0, 0);

    @Inject
    private IndicationSelfOverlay(EmptyHandsIndicatorConfig config, Client client)
    {
        this.config = config;
        this.client = client;
        setPriority(PRIORITY_LOW);
        setMovable(false);
        setSnappable(false);
        setDragTargetable(false);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if ((client.getGameCycle() % 50) >= (50 / 2))
        {
            graphics.setColor(config.flashColor());
        } else
        {
            graphics.setColor(transparent);
        }
        int canvasWidth = client.getCanvasWidth();
        int canvasHeight = client.getCanvasHeight();

        graphics.fillRect(0, 0, canvasWidth, canvasHeight);
        renderTextCentered(graphics, canvasWidth, canvasHeight, "Your hands are empty!");

        return client.getCanvas().getSize();
    }

    public static void renderTextCentered(Graphics2D graphics, int canvasWidth, int canvasHeight, String text) {
        if (!Strings.isNullOrEmpty(text)) {
            int halfStringWidth = getStringWidth(graphics, text) / 2;
            int x = canvasWidth / 2 - halfStringWidth+ 1;
            int y = (canvasHeight / 5) * 3;
            graphics.setColor(Color.BLACK);
            graphics.drawString(text, x, y);
        }
    }

    private static int getStringWidth(Graphics graphics, String text) {
        FontMetrics metrics = graphics.getFontMetrics();
        return metrics.stringWidth(text);
    }

}
