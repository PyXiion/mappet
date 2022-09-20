package mchorse.mappet.api.scripts.code.entities;

import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.values.reference.V8ValueObject;
import mchorse.mappet.Mappet;
import mchorse.mappet.api.npcs.Npc;
import mchorse.mappet.api.npcs.NpcState;
import mchorse.mappet.api.scripts.user.entities.IScriptNpc;
import mchorse.mappet.entities.EntityNpc;
import mchorse.mappet.entities.ai.EntityAIBaseJS;
import mchorse.metamorph.api.MorphUtils;
import mchorse.metamorph.api.morphs.AbstractMorph;

public class ScriptNpc extends ScriptEntity<EntityNpc> implements IScriptNpc
{
    public ScriptNpc(EntityNpc entity)
    {
        super(entity);
    }

    @Override
    public EntityNpc getMappetNpc()
    {
        return this.entity;
    }

    @Override
    public String getNpcId()
    {
        return this.entity.getId();
    }

    @Override
    public boolean setMorph(AbstractMorph morph)
    {
        this.entity.getState().morph = MorphUtils.copy(morph);
        this.entity.setMorph(this.entity.getState().morph);
        this.entity.sendMorph();

        return true;
    }

    @Override
    public String getNpcState()
    {
        return this.entity.getState().id;
    }

    @Override
    public void setNpcState(String id)
    {
        Npc npc = Mappet.npcs.load(this.entity.getId());
        NpcState state = npc.states.get(id);
        this.entity.setState(state, true);
    }

    @Override
    public void addTask(int priority, V8ValueObject object) throws JavetException {
        this.entity.addJSTask(priority, new EntityAIBaseJS(entity, object));
    }

    @Override
    public void clearTasks() {
        this.entity.clearJSTasks();
    }
}