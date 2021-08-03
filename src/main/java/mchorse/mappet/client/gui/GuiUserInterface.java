package mchorse.mappet.client.gui;

import mchorse.mappet.api.ui.UI;
import mchorse.mappet.api.ui.UIContext;
import mchorse.mappet.network.Dispatcher;
import mchorse.mappet.network.common.ui.PacketUI;
import mchorse.mclib.client.gui.framework.GuiBase;
import mchorse.mclib.client.gui.framework.elements.GuiElement;
import net.minecraft.client.Minecraft;

public class GuiUserInterface extends GuiBase
{
    private UIContext context = new UIContext();

    public GuiUserInterface(Minecraft mc, UI ui)
    {
        GuiElement element = ui.root.create(mc, this.context);

        element.flex().relative(this.root).wh(1F, 1F);
        this.root.add(element);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @Override
    protected void closeScreen()
    {
        super.closeScreen();

        Dispatcher.sendToServer(new PacketUI());
    }
}