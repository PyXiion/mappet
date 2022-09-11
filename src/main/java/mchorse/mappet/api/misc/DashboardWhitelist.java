package mchorse.mappet.api.misc;

import mchorse.mappet.utils.NBTToJsonLike;
import mchorse.mclib.utils.OpHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DashboardWhitelist implements INBTSerializable<NBTTagCompound>
{
    private File file;
    public final ArrayList<String> whitelistedPlayers = new ArrayList<>();
    public boolean inverse = false;

    // if disabled, operators is whitelisted
    public boolean enabled = false;

    public DashboardWhitelist(File file)
    {
        this.file = file;
    }

    private boolean isWhitelisted(String playerNameOrUuid)
    {
        return inverse != whitelistedPlayers.contains(playerNameOrUuid);
    }

    public boolean isWhitelisted(EntityPlayerMP player)
    {
        if (!enabled)
        {
            return OpHelper.isPlayerOp(player);
        }
        return isWhitelisted(player.getName()) || isWhitelisted(player.getUniqueID().toString());
    }

    /* Deserialization / Serialization */

    public void load()
    {
        if (file == null || !file.isFile())
            return;

        try {
            NBTTagCompound tag = NBTToJsonLike.read(file);

            deserializeNBT(tag);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void save() {
        try
        {
            NBTToJsonLike.write(file, serializeNBT());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /* NBT */

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound tag = new NBTTagCompound();
        NBTTagList players = new NBTTagList();

        tag.setBoolean("inverse", inverse);
        tag.setBoolean("enabled", enabled);

        for (String playerNameOrUuid : whitelistedPlayers)
        {
            players.appendTag(new NBTTagString(playerNameOrUuid));
        }

        tag.setTag("whitelist", players);

        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        if (nbt.hasKey("inverse"))
        {
            inverse = nbt.getBoolean("inverse");
        }
        if (nbt.hasKey("enabled"))
        {
            enabled = nbt.getBoolean("enabled");
        }
        if (nbt.hasKey("whitelist"))
        {
            NBTTagList players = nbt.getTagList("whitelist", Constants.NBT.TAG_STRING);
            whitelistedPlayers.clear();

            for (int i = 0; i < players.tagCount(); ++i)
            {
                whitelistedPlayers.add(players.getStringTagAt(i));
            }
        }
    }
}
