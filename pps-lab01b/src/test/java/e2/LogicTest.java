package e2;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class LogicTest {

  private LogicsImpl logic;
  private final int size = 5;

  @BeforeEach
  public void init() {
     this.logic = new LogicsImpl(5);
  }

  private Pair<Integer,Integer> getPawn(){
    for (int i=0; i<size; i++){
      for (int j=0; j<size; j++){
        if (logic.hasPawn(i, j)){
          return new Pair<>(i, j);
        }
      }
    }
    throw new IllegalStateException("No pawn found");
  }

  private Pair<Integer, Integer> getKnight(){
    for (int i=0; i<size; i++){
      for (int j=0; j<size; j++){
        if (logic.hasKnight(i, j)){
          return new Pair<>(i, j);
        }
      }
    }
    throw new IllegalStateException("No knight found");
  }

  @Test
  public void testMoveInSamePlace() {
    Pair<Integer,Integer> pawn = getPawn();
    assertFalse(logic.hit(pawn.getX(), pawn.getY()));
  }

//  @Test
//  public void testHit() {
//    Pair<Integer,Integer> pawn = getPawn();
//    Pair<Integer,Integer> knight = getKnight();
//    assertFalse(logic.hit(pawn.getX(), pawn.getY()+1));
//  }
}