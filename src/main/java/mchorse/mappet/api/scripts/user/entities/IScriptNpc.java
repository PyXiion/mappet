package mchorse.mappet.api.scripts.user.entities;

import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.values.reference.V8ValueObject;
import mchorse.mappet.entities.EntityNpc;

/**
 * Mappet's NPC entity interface.
 *
 * <pre>{@code
 *    function main(c)
 *    {
 *        if (c.getSubject().isNpc())
 *        {
 *            // Do something with the NPC...
 *        }
 *    }
 * }</pre>
 */
public interface IScriptNpc extends IScriptEntity
{
    /**
     * Get Mappet entity NPC instance. <b>BEWARE:</b> you need to know the
     * MCP mappings in order to directly call methods on this instance!
     *
     * <p>But some methods might have human readable method names. Please
     * check <a href="https://github.com/mchorse/mappet/blob/master/src/main/java/mchorse/mappet/entities/EntityNpc.java">EntityNpc</a> class for methods that
     * don't have {@link Override} annotation!</p>
     */
    public EntityNpc getMappetNpc();

    /**
     * Get NPC's NPC ID.
     *
     * <pre>{@code
     *    var pos = c.getSubject().getPosition();
     *    var npc = c.getWorld().spawnNpc("test", pos.x, pos.y + 2, pos.z);
     *
     *    // This will output "true" as long as you have an NPC configured
     *    // in Mappet's NPC dashboard panel
     *    c.send(npc.getNpcId() === "test");
     * }</pre>
     */
    public String getNpcId();

    public String getNpcState();
    public void setNpcState(String id);

    /**
     * Adds task to NPC. Need knowledge of Forge
     *
     * <pre>{@code
     *    class GoToTask {
     *        constructor(x, y, z, speed) {
     *            const minDistance = 2;
     *            this.minDistanceSq = minDistance * minDistance;
     *
     *            this.x = x;
     *            this.y = y;
     *            this.z = z;
     *            this.speed = speed;
     *        }
     *        shouldExecute() {
     *            // getAttackTarget
     *            if (this.target.func_70638_az() != null) {
     *               return false;
     *            }
     *
     *            // getDistanceSq
     *            return this.target.func_70092_e(this.x, this.y, this.z) > this.minDistanceSq;
     *        }
     *        shouldContinueExecuting() {
     *            // getAttackTarget
     *            if (this.target.func_70638_az() != null) {
     *               return false;
     *            }
     *
     *            // return (!this.target.getNavigator().noPath()) && this.target.getDistanceSq(this.x, this.y, this.z) > this.minDistanceSq;
     *            return (!this.target.func_70661_as().func_75500_f()) && this.target.func_70092_e(this.x, this.y, this.z) > this.minDistanceSq;
     *        }
     *        startExecuting() {
     *            this.timer = 0;
     *        }
     *        resetTask() {
     *            this.target.func_70661_as().func_75499_g(); // getNavigator clearPath
     *        }
     *        updateTask() {
     *            // this.target.getLookHelper().setLookPosition(this.x, this.y + this.target.height, this.z, 10, this.target.getVerticalFaceSpeed());
     *            this.target.func_70671_ap().func_75650_a(this.x, this.y + this.target.field_70131_O, this.z, 10, this.target.func_70646_bf());
     *
     *            if (this.timer > 0) {
     *                this.timer -= 1;
     *                return;
     *            }
     *            this.timer = 10;
     *
     *            let navigator = this.target.func_70661_as(); // getNavigator
     *            let path = navigator.func_75488_a(this.x, this.y, this.z); // getPathToXYZ
     *
     *            navigator.func_75484_a(path, this.speed); // setPath
     *        }
     *
     *        setPos(x, y, z) {
     *            this.x = x;
     *            this.y = y;
     *            this.z = z;
     *        }
     *    }
     *
     *    let task = null;
     *
     *    function main(c) {
     *        let npcs = c.getServer().getEntities("@e[mpid=someNpc]");
     *        task = new GoToTask(0, 4, 9, 2);
     *
     *        for (let npc of npcs) {
     *            npc.clearTasks(); // clear old tasks, also because old GoToTask not exists (script rerunned)
     *            npc.addTask(3, task);
     *        }
     *    }
     *
     *    // calls every second
     *    function update(c) {
     *        if (task === null) return;
     *        let anyplayer = c.server.getEntities("@r")[0];
     *        let pos = anyplayer.position;
     *
     *        task.setPos(pos.x, pos.y, pos.z);
     *    }
     * }</pre>
     */
    public void addTask(int priority, V8ValueObject object) throws JavetException;

    /**
     * Delete all JS tasks.
     */
    public void clearTasks();
}