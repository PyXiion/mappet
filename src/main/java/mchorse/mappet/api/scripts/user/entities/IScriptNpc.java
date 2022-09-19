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
     * Adds task to NPC
     *
     * Simple moving to point task
     * <pre>{@code
     *    var print;
     *
     * class GoToTask {
     *     constructor(x, y, z, speed) {
     *         this.x = x;
     *         this.y = y;
     *         this.z = z;
     *         this.speed = speed;
     *     }
     *     shouldExecute() {
     *         return true;
     *     }
     *     shouldContinueExecuting() {
     *         return this.stopTimer > 0;
     *     }
     *     startExecuting() {
     *         this.stopTimer = 120;
     *         this.timer = 0;
     *         print("Started")
     *     }
     *     resetTask() {
     *         this.target.func_70661_as().func_75499_g(); // getNavigator().clearPath()
     *     }
     *     updateTask() {
     *         // getLookHelper() setLookPosition(x, y, z) height getVerticalFaceSpeed
     *         this.target.func_70671_ap().func_75650_a(this.x, this.y + this.target.field_70131_O, this.z, 10, this.target.func_70646_bf());
     *
     *         this.stopTimer -= 1;
     *         if (this.timer > 0) {
     *             this.timer -= 1;
     *             return;
     *         }
     *         this.timer = 10;
     *
     *         let navigator = this.target.func_70661_as(); // getNavigator()
     *         let path = navigator.func_75488_a(this.x, this.y, this.z); // getPathToXYZ(x, y, z)
     *
     *         navigator.func_75484_a(path, this.speed); // setPath(path, speed)
     *     }
     * }
     *
     * class A {
     *     constructor(a) {
     *         this.a = a;
     *     }
     * }
     *
     * function main(c) {
     *     let npcs = c.getServer().getEntities("@e[mpid=someNpc]");
     *
     *     print = c.send;
     *
     *     for (let npc of npcs) {
     *         npc.addTask(1, new GoToTask(0, 4, 9, 5));
     *     }
     * }
     * }</pre>
     */
    public void addTask(int priority, V8ValueObject object) throws JavetException;
}