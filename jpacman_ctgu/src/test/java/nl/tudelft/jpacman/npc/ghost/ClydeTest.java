package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

/*
    @author Lv Chong
    @version jdk1.8.0_333
    @Date 2022-05-10
 */
public class ClydeTest {
    /*
        @param ghostMapParser: parse graph into the game and add player and ghosts
     */
    static private GhostMapParser ghostMapParser;

    /*
        @BeforeAll annotation must attach a static function
        Only run this function once when test whe whole class
        function initialize() is aim to initialize the ghostMapParser
     */
    @BeforeAll
    static void initialize() {
        // Initialize
        PacManSprites pacManSprites = new PacManSprites();
        LevelFactory levelFactory = new LevelFactory(pacManSprites, new GhostFactory(pacManSprites), mock(PointCalculator.class));
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
    }

    /*
        When the distance between clyde and player less than eight
        clyde will move to the opposite direction to player
        function testDistanceLessThanEight() is aim to test the clyde's behavior is correct or not
     */
    @Test
    @DisplayName("Distance less than 8")
    void testDistanceLessThanEight() {
        // Create graph:3*10
        List<String> graph = Lists.newArrayList("##########", "###C...P##", "##########");

        Level level = ghostMapParser.parseMap(graph);

        // Create clyde
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        // Ensure clyde exist
        assertThat(clyde).isNotNull();
        // Distance < 8: Clyde should stay away from player
        assertThat(clyde.getDirection().equals(Direction.WEST));

        Player player = new PlayerFactory(new PacManSprites()).createPacMan();
        player.setDirection(Direction.WEST);
        level.registerPlayer(player);
        // Ensure player exist
        assertThat(player).isNotNull();
        // Test player's function setDirection() did work or not
        assertThat(player.getDirection()).isEqualTo(Direction.WEST);

        // Record clyde's next movement
        Optional<Direction> optionalDirection = clyde.nextAiMove();

        // Test clyde move to west or not
        assertThat(optionalDirection.get()).isEqualTo(Direction.WEST);
    }

    /*
        When the distance between clyde and player greater than eight
        clyde will move to the direction to player
        function testDistanceGreaterThanEight() is aim to test the clyde's behavior is correct or not
    */
    @Test
    @DisplayName("Distance greater than 8")
    void testDistanceGreaterThanEight() {
        // Create graph:3*11
        List<String> graph = Lists.newArrayList("###########", "C.........P", "###########");

        Level level = ghostMapParser.parseMap(graph);

        // Create clyde
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        // Ensure clyde exist
        assertThat(clyde).isNotNull();
        // Distance > 8: Clyde should approach to player
        assertThat(clyde.getDirection().equals(Direction.EAST));

        Player player = new PlayerFactory(new PacManSprites()).createPacMan();
        player.setDirection(Direction.WEST);
        level.registerPlayer(player);
        // Ensure player exist
        assertThat(player).isNotNull();
        // Test player's function setDirection() did work or not
        assertThat(player.getDirection()).isEqualTo(Direction.WEST);

        // Record clyde's next movement
        Optional<Direction> optionalDirection = clyde.nextAiMove();

        // Test clyde move to east or not
        assertThat(optionalDirection.get()).isEqualTo(Direction.EAST);
    }

    /*
        When the graph have no player, we have to test this case may cause what kind of situation
        function testNoPlayerExist() is aim to test the case that no player exist in the graph
     */
    @Test
    @DisplayName("No player exist")
    void testNoPlayerExist() {
        // Create graph:3*12
        List<String> graph = Lists.newArrayList("############", "......C.....", "############");

        Level level = ghostMapParser.parseMap(graph);

        // Create clyde
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        // Ensure clyde exist
        assertThat(clyde).isNotNull();
        assertThat(clyde.getDirection().equals(Direction.EAST));

        // Test is there any player exist
        assertThat(level.isAnyPlayerAlive()).isFalse();

        // Record clyde's next movement
        Optional<Direction> optionalDirection = clyde.nextAiMove();

        // Test clyde move to east or not
        assertThat(optionalDirection.isPresent()).isFalse();
    }

    /*
        When there is no reachable path between clyde and player
        function testCanNotReach() is aim to test the case that no valid path between clyde and player
     */
    @Test
    @DisplayName("Clyde can not reach player")
    void testCanNotReach() {
        // Create graph:3*8
        List<String> graph = Lists.newArrayList("C#######", "########", "#######P");

        Level level = ghostMapParser.parseMap(graph);

        // Create clyde
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        // Ensure clyde exist
        assertThat(clyde).isNotNull();
        assertThat(clyde.getDirection().equals(Direction.EAST));

        // Player got no way to go
        assertThat(level.isAnyPlayerAlive()).isFalse();

        // Record clyde's next movement
        Optional<Direction> optionalDirection = clyde.nextAiMove();

        assertThat(optionalDirection.isPresent()).isFalse();
    }
}
