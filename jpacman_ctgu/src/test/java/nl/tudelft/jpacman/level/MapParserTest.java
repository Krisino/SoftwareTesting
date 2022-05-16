package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class MapParserTest {
    private MapParser mapParser;
    private LevelFactory levelFactory;
    private BoardFactory boardFactory;

    // Initialize the test environment
    @BeforeEach
    void build() {
        levelFactory = mock(LevelFactory.class);
        boardFactory = mock(BoardFactory.class);

        mapParser = new MapParser(levelFactory, boardFactory);

        when(levelFactory.createGhost()).thenReturn(mock(Ghost.class));
        when(levelFactory.createPellet()).thenReturn(mock(Pellet.class));
        when(boardFactory.createGround()).thenReturn(mock(Square.class));
        when(boardFactory.createWall()).thenReturn(mock(Square.class));
    }

    //    parseMap(char [][]) 正确执行
    @Test
    @DisplayName("parseMap(char [][])")
    void testParseMapCharCorrect() {
        char[][] graph = new char[][]{
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#'},
            {'#', ' ', '#', ' ', '#', ' ', 'G', ' ', '#', ' '}
        };
        mapParser.parseMap(graph);
        verify(boardFactory, times(9)).createWall();
        verify(boardFactory, times(21)).createGround();
        verify(levelFactory, times(0)).createPellet();
        verify(levelFactory, times(1)).createGhost();
    }

    //    parseMap(char [][]) 非法字符
    @Test
    @DisplayName("parseMap(char [][])IllegalChar")
    void testParseMapCharIllegalChar() {
        char[][] graph = new char[][]{
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '$'},
            {'#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', ' '}
        };
        assertThatThrownBy(() -> mapParser.parseMap(graph))
            .isInstanceOf(PacmanConfigurationException.class).hasMessage("Invalid character at "
                + 1 + "," + 9 + ": " + "$");
    }

    //    parseMap(List<String>)正确执行
    @Test
    @DisplayName("parseMap(List<String>)正确执行")
    void testParseMapListString() {
        List<String> graph = Lists.newArrayList("##########", "###  ...##", "##########");
        mapParser.parseMap(graph);
        verify(boardFactory, times(25)).createWall();
        verify(boardFactory, times(5)).createGround();
        verify(levelFactory, times(3)).createPellet();
    }

    //    parseMap(List<String>)text为null
    @Test
    @DisplayName("parseMap(List<String>)text为null")
    void testParseMapListStringTextNull() {
        assertThatThrownBy(() -> mapParser.parseMap((List<String>) null))
            .isInstanceOf(PacmanConfigurationException.class).hasMessage("Input text cannot be null.");
    }

    //    parseMap(List<String>)text为Empty
    @Test
    @DisplayName("parseMap(List<String>)text为Empty")
    void testParseMapListStringTextEmpty() {
        List<String> graph = Lists.newArrayList();
        assertThatThrownBy(() -> mapParser.parseMap(graph))
            .isInstanceOf(PacmanConfigurationException.class).hasMessage("Input text must consist of at least 1 row.");
    }

    //    parseMap(List<String>)宽度为零
    @Test
    @DisplayName("parseMap(List<String>)宽度为零")
    void testParseMapListStringWidthZero() {
        List<String> graph = Lists.newArrayList("", "##########", "##########");
        assertThatThrownBy(() -> mapParser.parseMap(graph))
            .isInstanceOf(PacmanConfigurationException.class).hasMessage("Input text lines cannot be empty.");
    }

    //    parseMap(List<String>)宽度不一致
    @Test
    @DisplayName("parseMap(List<String>)宽度不一致")
    void testParseMapListStringWidthFail() {
        List<String> graph = Lists.newArrayList("##########", "##########.....", "##########");
        assertThatThrownBy(() -> mapParser.parseMap(graph))
            .isInstanceOf(PacmanConfigurationException.class).hasMessage("Input text lines are not of equal width.");
    }

    //    parseMap(String)正确执行
    @Test
    @DisplayName("parseMap(String)")
    void testParseMapString() {
        String mapName = "/graph.txt";
        try {
            mapParser.parseMap(mapName);
            verify(boardFactory, times(28)).createWall();
            verify(boardFactory, times(5)).createGround();
            verify(levelFactory, times(3)).createPellet();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //    parseMap(String)为空
    @Test
    @DisplayName("parseMap(String)Null")
    void testParseMapStringNull() {
        String mapName = null;
        assertThatThrownBy(() -> mapParser.parseMap(mapName))
            .isInstanceOf(NullPointerException.class);
    }

    //    parseMap(String)文件名不存在
    @Test
    @DisplayName("parseMap(String)NotExist")
    void testParseMapStringNotExist() {
        String mapName = "/aNotExistFile.txt";
        assertThatThrownBy(() -> mapParser.parseMap(mapName))
            .isInstanceOf(PacmanConfigurationException.class)
            .hasMessage("Could not get resource for: /aNotExistFile.txt");
    }
}
