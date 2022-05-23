package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class PlayerCollisionsTest {

    /**
     * PlayerCollisions为测试对象
     * PointCalculator为依赖，使用mock模拟
     * Player为依赖，使用mock模拟
     * Ghost为依赖，使用mock模拟
     * Pellet为依赖，使用mock模拟
     */
    private PlayerCollisions playerCollisions;
    private PointCalculator pointCalculator = mock(PointCalculator.class);
    private Player player = mock(Player.class);
    private Ghost ghost = mock(Ghost.class);
    private Pellet pellet = mock(Pellet.class);

    /**
     * BeforeEach 为每次测试前创建一个新的 PlayerCollisions 实例
     */
    @BeforeEach
    void createPlayerCollisions() {
        playerCollisions = new PlayerCollisions(pointCalculator);
    }

    /**
     * 测试1:
     * 碰撞者：Player
     * 被碰撞者：Ghost
     */
    @Test
    void playerCollidingGhost() {
        playerCollisions.collide(player, ghost);
        verify(pointCalculator).collidedWithAGhost(player, ghost);
        verify(player).setAlive(false);
        verify(player).setKiller(ghost);
    }

    /**
     * 测试2:
     * 碰撞者：Player
     * 被碰撞者：Pellet
     */
    @Test
    void playerCollidingPellet() {
        playerCollisions.collide(player, pellet);
        verify(pointCalculator).consumedAPellet(player, pellet);
        verify(pointCalculator).consumedAPellet(player, pellet);
        verify(pellet).leaveSquare();
    }

    /**
     * 测试3:
     * 碰撞者：Ghost
     * 被碰撞者：Player
     */
    @Test
    void ghostCollidingPlayer() {
        playerCollisions.collide(ghost, player);
        verify(pointCalculator).collidedWithAGhost(player, ghost);
        verify(player).setAlive(false);
        verify(player).setKiller(ghost);
    }

    /**
     * 测试4:
     * 碰撞者：Pellet
     * 被碰撞者：Player
     */
    @Test
    void pelletCollidingPlayer() {
        playerCollisions.collide(pellet, player);
        verify(pointCalculator).consumedAPellet(player, pellet);
        verify(pellet).leaveSquare();
    }

    /**
     * 测试5:
     * 碰撞者：Ghost
     * 被碰撞者：Pellet
     */
    @Test
    void ghostCollidingPellet() {
        playerCollisions.collide(ghost, pellet);
        verify(pellet, times(0)).leaveSquare();
    }

    /**
     * 测试6:
     * 碰撞者：Pellet
     * 被碰撞者：Ghost
     */
    @Test
    void pelletCollidingGhost() {
        playerCollisions.collide(pellet, ghost);
        verify(pellet, times(0)).leaveSquare();
    }
}
