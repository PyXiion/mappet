package mchorse.mappet.commands.whitelist;

import mchorse.mappet.commands.MappetCommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.List;

public abstract class CommandWhitelistBase extends MappetCommandBase
{
    @Override
    public int getRequiredArgs()
    {
        return 0;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        if (args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, "save", "load");
        }

        return super.getTabCompletions(server, sender, args, targetPos);
    }
}