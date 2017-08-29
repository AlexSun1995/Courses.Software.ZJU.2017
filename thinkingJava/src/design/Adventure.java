package design;

/**
 * 会战斗
 */
interface CanFight {
    void fight();
}
/**
 * 会游泳
 */
interface CanSwim {
    void swim();
}
/**
 * 会飞
 */
interface CanFly {
    void fly();
}
/**
 * 动作特点
 */
class ActionCharacter {
    public void fight() {
        System.out.println("战斗");
    }
}
/**
 * 英雄，此英雄能战斗、游泳、会飞
 */

class Hero extends ActionCharacter
        implements CanFight, CanSwim, CanFly {
    public void swim() {
        System.out.println("游泳");
    }
    public void fly() {
        System.out.println("飞翔");
    }
}
/**
 *  冒险
 */
public class Adventure {
    public static void t(CanFight x) { x.fight(); }
    public static void u(CanSwim x) { x.swim(); }
    public static void v(CanFly x) { x.fly(); }
    public static void w(ActionCharacter x) { x.fight(); }
    public static void main(String[] args) {
        Hero h = new Hero();
        t(h); // 向上转型为 CanFight
        u(h); // 向上转型为 CanSwim
        v(h); // 向上转型为 CanFly
        w(h); // 向上转型为 ActionCharacter
    }
}
