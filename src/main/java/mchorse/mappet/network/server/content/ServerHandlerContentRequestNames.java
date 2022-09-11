package mchorse.mappet.network.server.content;

import mchorse.mappet.Mappet;
import mchorse.mappet.network.Dispatcher;
import mchorse.mappet.network.common.content.PacketContentNames;
import mchorse.mappet.network.common.content.PacketContentRequestNames;
import mchorse.mclib.network.ServerMessageHandler;
import mchorse.mclib.utils.OpHelper;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.ArrayList;
import java.util.List;

public class ServerHandlerContentRequestNames extends ServerMessageHandler<PacketContentRequestNames>
{
    @Override
    public void run(EntityPlayerMP player, PacketContentRequestNames message)
    {
        if (!Mappet.dashboardWhitelist.isWhitelisted(player))
        {
            return;
        }

        List<String> names = new ArrayList<String>(message.type.getManager().getKeys());

        Dispatcher.sendTo(new PacketContentNames(message.type, names, message.requestId), player);
    }
}