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

public class InkyTest {
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
        Inky is alongside Blinky when they are behind Pac-Man, Inky will usually follow Blinky the whole time
     */
    @Test
    @DisplayName("Inky is alongside Blinky and behind Pac-Man")
    void testInkyAlongsideBlinkyBehind() {
        // Create graph:3*10
        List<String> graph = Lists.newArrayList("##########", ".P....BI..", "#####.....");

        Level level = ghostMapParser.parseMap(graph);

        // Create Inky
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        // Create Blinky
        Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
        // Ensure inky exist
        assertThat(inky).isNotNull();
        // Ensure blinky exist
        assertThat(blinky).isNotNull();
        // Test inky initial direction
        assertThat(inky.getDirection().equals(Direction.WEST));
        // Test blinky initial direction
        assertThat(blinky.getDirection().equals(Direction.WEST));

        Player player = new PlayerFactory(new PacManSprites()).createPacMan();
        player.setDirection(Direction.WEST);
        level.registerPlayer(player);
        // Ensure player exist
        assertThat(player).isNotNull();
        // Test player's function setDirection() did work or not
        assertThat(player.getDirection()).isEqualTo(Direction.WEST);

        // Record clyde's next movement
        Optional<Direction> optionalDirection = inky.nextAiMove();

        // Test inky alongside blinky
        assertThat(optionalDirection.get()).isEqualTo(Direction.WEST);
    }

    /*
        Inky is in front of Pac-Man when Blinky is far behind him, Inky tends to want to move away from Pac-Man
     */
    @Test
    @DisplayName("Inky is in front of Pac-Man")
    void testInkyFrontPacMan() {
        // Create graph:3*10
        List<String> graph = Lists.newArrayList("B.....####", ".P......I.", "#####.....");

        Level level = ghostMapParser.parseMap(graph);

        // Create Inky
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        // Create Blinky
        Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
        // Ensure inky exist
        assertThat(inky).isNotNull();
        // Ensure blinky exist
        assertThat(blinky).isNotNull();
        // Test inky initial direction
        assertThat(inky.getDirection().equals(Direction.WEST));
        // Test blinky initial direction
        assertThat(blinky.getDirection().equals(Direction.WEST));

        Player player = new PlayerFactory(new PacManSprites()).createPacMan();
        player.setDirection(Direction.EAST);
        level.registerPlayer(player);
        // Ensure player exist
        assertThat(player).isNotNull();
        // Test player's function setDirection() did work or not
        assertThat(player.getDirection()).isEqualTo(Direction.EAST);

        // Record clyde's next movement
        Optional<Direction> optionalDirection = inky.nextAiMove();

        // Test inky is in front of Pac-Man
        assertThat(optionalDirection.get()).isEqualTo(Direction.SOUTH);
    }

    /*
        There is no player exists
     */
    @Test
    @DisplayName("No Player")
    void testNoInky() {
        // Create graph:3*10
        List<String> graph = Lists.newArrayList("B.....####", ".I........", "#####.....");

        Level level = ghostMapParser.parseMap(graph);

        // Create Inky
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        // Create Blinky
        Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
        // Ensure inky exist
        assertThat(inky).isNotNull();
        // Ensure blinky exist
        assertThat(blinky).isNotNull();
        // Test inky initial direction
        assertThat(inky.getDirection().equals(Direction.WEST));
        // Test blinky initial direction
        assertThat(blinky.getDirection().equals(Direction.WEST));

        // Test player do exist or not
        assertThat(level.isAnyPlayerAlive()).isFalse();

        // Record clyde's next movement
        Optional<Direction> optionalDirection = inky.nextAiMove();

        // Test inky is in front of Pac-Man
        assertThat(optionalDirection.isPresent()).isFalse();
    }

    /*
        There is no Blinky exists
     */
    @Test
    @DisplayName("No Blinky")
    void testNoBlinky() {
        // Create graph:3*10
        List<String> graph = Lists.newArrayList("P.....####", ".I........", "#####.....");

        Level level = ghostMapParser.parseMap(graph);

        // Create Inky
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        // Create Blinky
        Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
        // Ensure inky exist
        assertThat(inky).isNotNull();
        // Ensure blinky do not exist
        assertThat(blinky).isNull();
        // Test inky initial direction
        assertThat(inky.getDirection().equals(Direction.WEST));

        // Test player do exist or not
        assertThat(level.isAnyPlayerAlive()).isFalse();

        // Record clyde's next movement
        Optional<Direction> optionalDirection = inky.nextAiMove();

        // Test inky is in front of Pac-Man
        assertThat(optionalDirection.isPresent()).isFalse();
    }

    /*
        There is no valid path between inky and Pac-Man
     */
    @Test
    @DisplayName("Can not reach Pac-Man")
    void testCanNotReachPacMAn() {
        // Create graph:3*10
        List<String> graph = Lists.newArrayList("P.....####", "#IB.......", "#####.....");

        Level level = ghostMapParser.parseMap(graph);

        // Create Inky
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        // Create Blinky
        Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
        // Ensure inky exist
        assertThat(inky).isNotNull();
        // Ensure blinky do not exist
        assertThat(blinky).isNotNull();
        // Test inky initial direction
        assertThat(inky.getDirection().equals(Direction.WEST));
        // Test blinky initial direction
        assertThat(blinky.getDirection().equals(Direction.WEST));

        // Test player do exist or not
        assertThat(level.isAnyPlayerAlive()).isFalse();

        // Record clyde's next movement
        Optional<Direction> optionalDirection = inky.nextAiMove();

        // Test inky is in front of Pac-Man
        assertThat(optionalDirection.isPresent()).isFalse();
    }
}
