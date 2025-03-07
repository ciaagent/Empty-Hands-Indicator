package emptyhandsindicator;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class EmptyHandsIndicatorTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(EmptyHandsIndicatorPlugin.class);
		RuneLite.main(args);
	}
}