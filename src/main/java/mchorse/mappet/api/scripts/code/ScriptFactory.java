package mchorse.mappet.api.scripts.code;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import mchorse.mappet.api.scripts.code.blocks.ScriptBlockState;
import mchorse.mappet.api.scripts.code.items.ScriptItemStack;
import mchorse.mappet.api.scripts.code.nbt.ScriptNBTCompound;
import mchorse.mappet.api.scripts.code.nbt.ScriptNBTList;
import mchorse.mappet.api.scripts.user.IScriptFactory;
import mchorse.mappet.api.scripts.user.blocks.IScriptBlockState;
import mchorse.mappet.api.scripts.user.items.IScriptItemStack;
import mchorse.mappet.api.scripts.user.nbt.INBTCompound;
import mchorse.mappet.api.scripts.user.nbt.INBTList;
import mchorse.metamorph.api.MorphManager;
import mchorse.metamorph.api.morphs.AbstractMorph;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ScriptFactory implements IScriptFactory
{
    @Override
    public IScriptBlockState createBlockState(String blockId, int meta)
    {
        ResourceLocation location = new ResourceLocation(blockId);
        Block block = ForgeRegistries.BLOCKS.getValue(location);

        if (block != null)
        {
            IBlockState state = block.getStateFromMeta(meta);

            return new ScriptBlockState(state);
        }

        return null;
    }

    @Override
    public INBTCompound createCompound(String nbt)
    {
        NBTTagCompound tag = new NBTTagCompound();

        if (nbt != null)
        {
            try
            {
                tag = JsonToNBT.getTagFromJson(nbt);
            }
            catch (Exception e)
            {}
        }

        return new ScriptNBTCompound(tag);
    }

    @Override
    public INBTCompound createCompoundFromJS(Object jsObject)
    {
        NBTBase base = this.convertToNBT(jsObject);

        return base instanceof NBTTagCompound ? new ScriptNBTCompound((NBTTagCompound) base) : null;
    }

    @Override
    public INBTList createList(String nbt)
    {
        NBTTagList list = new NBTTagList();

        if (nbt != null)
        {
            try
            {
                list = (NBTTagList) JsonToNBT.getTagFromJson("{List:" + nbt + "}").getTag("List");
            }
            catch (Exception e)
            {}
        }

        return new ScriptNBTList(list);
    }

    @Override
    public INBTList createListFromJS(Object jsObject)
    {
        NBTBase base = this.convertToNBT(jsObject);

        return base instanceof NBTTagList ? new ScriptNBTList((NBTTagList) base) : null;
    }

    private NBTBase convertToNBT(Object object)
    {
        if (object instanceof String)
        {
            return new NBTTagString((String) object);
        }
        else if (object instanceof Double)
        {
            return new NBTTagDouble((Double) object);
        }
        else if (object instanceof Integer)
        {
            return new NBTTagInt((Integer) object);
        }
        else if (object instanceof ScriptObjectMirror)
        {
            ScriptObjectMirror mirror = (ScriptObjectMirror) object;

            if (mirror.isArray())
            {
                NBTTagList list = new NBTTagList();

                for (int i = 0, c = mirror.size(); i < c; i++)
                {
                    NBTBase base = this.convertToNBT(mirror.getSlot(i));

                    if (base != null)
                    {
                        list.appendTag(base);
                    }
                }

                return list;
            }
            else
            {
                NBTTagCompound tag = new NBTTagCompound();

                for (String key : mirror.keySet())
                {
                    NBTBase base = this.convertToNBT(mirror.get(key));

                    if (base != null)
                    {
                        tag.setTag(key, base);
                    }
                }

                return tag;
            }
        }

        return null;
    }

    @Override
    public IScriptItemStack createItemStack(INBTCompound compound)
    {
        if (compound != null)
        {
            return new ScriptItemStack(new ItemStack(compound.getNBTTagComound()));
        }

        return ScriptItemStack.EMPTY;
    }

    @Override
    public EnumParticleTypes getParticleType(String type)
    {
        return EnumParticleTypes.getByName(type);
    }

    @Override
    public AbstractMorph createMorph(INBTCompound compound)
    {
        if (compound == null)
        {
            return null;
        }

        return MorphManager.INSTANCE.morphFromNBT(compound.getNBTTagComound());
    }
}