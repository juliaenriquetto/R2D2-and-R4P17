package R2D2;
import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * R4P17 - a robot by Julia Enriquetto
 */
public class R4P17 extends Robot
{

  boolean mirando; // bool para definir se o robo esta mirando
  double movimento; // double para definir o movimento a ser feito do robo

  public void run() {
    // seta as cores do robo
    setColors(Color.pink, Color.white, Color.pink, Color.red, Color.white);

    // obtém o valor máximo entre a largura e a altura do campo de batalha
    movimento = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
    // inicializa o bool de mira como falso
    mirando = false;

    // vira para a esquerda para encarar uma parede
    turnLeft(getHeading() % 90); // vira para a esquerda o resto da divisão do angulo atual por 90
    ahead(movimento); // anda para frente o valor do movimento
    // vira o canhão para a direita 90 graus para encarar o centro do campo de
    // batalha
    mirando = true;
    turnGunRight(90); // vira o canhão para a direita 90 graus
    turnRight(90); // vira o robo para a direita 90 graus

    while (true) {
      // movimentação do robo em quadrado
      mirando = true;

      // anda para frente o valor do movimento
      ahead(movimento);

      // vira para a direita 90 graus
      mirando = false;

      // vira para a direita 90 graus
      turnRight(90);
    }
  }

  public void onHitRobot(HitRobotEvent e) {
    // se o robo que nos atingiu estiver na nossa frente, recuamos
    if (e.getBearing() > -90 && e.getBearing() < 90) {
      back(100);
    } // se ele estiver atras, avançamos
    else {
      ahead(100);
    }
  }

  public void onScannedRobot(ScannedRobotEvent e) {
    String robotank = e.getName(); // obtem nome do robo inimigo
    double distancia = e.getDistance(); // obtem a distancia do inimigo
    System.out.println(robotank + " distancia " + distancia);

    // grande chance de acertar (cerca de 3 a 4 tanques de distancia)
    if (distancia < 200)
      fire(3);

    // conforme a distancia aumenta, diminiuimos a intensidade do disparo
    else if (distancia < 500)
      fire(2.5);

    else if (distancia < 800)
      fire(1.5);

    else
      fire(0.5);

    // se o robo estiver mirando, scanneamos
    if (mirando) {
      scan();
    }
  }
}