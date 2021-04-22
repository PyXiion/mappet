package mchorse.mappet.client.gui.utils.overlays;

import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.function.Consumer;

public class GuiSoundOverlayPanel extends GuiResourceLocationOverlayPanel
{
    public GuiSoundOverlayPanel(Minecraft mc, Consumer<ResourceLocation> callback)
    {
        super(mc, IKey.str("Pick sound event..."), ForgeRegistries.SOUND_EVENTS.getKeys(), callback);
    }
}