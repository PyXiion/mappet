package mchorse.mappet.entities.ai;

import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.values.V8Value;
import com.caoccao.javet.values.primitive.V8ValueBoolean;
import com.caoccao.javet.values.primitive.V8ValueInteger;
import com.caoccao.javet.values.reference.V8ValueFunction;
import com.caoccao.javet.values.reference.V8ValueObject;
import mchorse.mappet.entities.EntityNpc;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAIBaseJS extends EntityAIBase
{
    public int priority = 0;

    private final EntityNpc target;
    private final V8ValueObject self;
    private final V8ValueFunction shouldExecute;
    private final V8ValueFunction shouldContinueExecuting;
    private final V8ValueFunction startExecuting;
    private final V8ValueFunction resetTask;
    private final V8ValueFunction updateTask;

    public EntityAIBaseJS(EntityNpc target, V8ValueObject object) throws JavetException
    {
        object.setWeak();
        self = object;
        this.target = target;
        object.set("target", this.target);
        shouldExecute = getFunction(object, "shouldExecute");
        shouldContinueExecuting = getFunction(object, "shouldContinueExecuting");
        startExecuting = getFunction(object, "startExecuting");
        resetTask = getFunction(object, "resetTask");
        updateTask = getFunction(object, "updateTask");

        System.out.println(object.toJsonString());
        System.out.println(object.toString());
        System.out.println(object.toProtoString());
    }


    @Override
    public boolean shouldExecute()
    {
        try {
            if (checkFunctionAlive(shouldExecute))
                return shouldExecute.callBoolean(self);
        } catch (JavetException je)
        {
            je.printStackTrace();
            // TODO: do something later
        }
        return false;
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        try {
            if (checkFunctionAlive(shouldContinueExecuting))
                return shouldContinueExecuting.callBoolean(self);
        } catch (JavetException je)
        {
            je.printStackTrace();
            // TODO: do something later
        }
        return false;
    }

    @Override
    public void startExecuting()
    {
        try {
            if (checkFunctionAlive(startExecuting))
                startExecuting.callVoid(self);
        } catch (JavetException je)
        {
            je.printStackTrace();
            // TODO: do something later
        }
    }

    @Override
    public void resetTask()
    {
        try {
            if (checkFunctionAlive(resetTask))
                resetTask.callVoid(self);
        } catch (JavetException je)
        {
            je.printStackTrace();
            // TODO: do something later
        }
    }

    @Override
    public void updateTask()
    {
        try {
            if (checkFunctionAlive(updateTask))
                updateTask.callVoid(self);
        } catch (JavetException je)
        {
            je.printStackTrace();
            // TODO: do something later
        }
    }

    public int getPriority() throws JavetException {
        if (self.has("priority")) {
            V8Value val = self.get("priority");
            if (val instanceof V8ValueInteger)
                return ((V8ValueInteger) val).getValue();
        }
        return 6;
    }

    public boolean isAlive() {
        return self != null && !self.isClosed();
    }

    private boolean checkFunctionAlive(V8ValueFunction func) {
        return isAlive() && func != null && !func.isClosed();
    }

    private V8ValueFunction getFunction(V8ValueObject object, String name) throws JavetException {
        if (!object.has(name)) return null;
        V8Value val = object.get(name);
        if (val instanceof V8ValueFunction)
            return (V8ValueFunction) val;
        return null;
    }
}
