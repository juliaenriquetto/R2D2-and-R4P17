package R2D2;
import robocode.*; //importa a API do robocode 
import java.awt.Color; //importa a classe java de Color  
/**
 * R2D2 - a class by Julia Enriquetto
 */
public class R2D2 extends Robot //herança
{
   public void run(){ //equivale ao main do java 
	   setColors(Color.darkGray, Color.black, Color.lightGray, Color.white, Color.green);
	   //setColors(Color.chassi, Color.canhão, Color.bala, Color.arco)
	   while(true) { //enquanto o robo estiver vivo
       ahead(100);
	   turnRight(90);
     }  
   }
   
   public void onScannedRobot(ScannedRobotEvent e){ //quando o radar identifica o Alvo inimigo
	   String robotank = e.getName(); //obtem nome do robo inimigo 
	   double distancia = e.getDistance(); //obtem a distancia do inimigo 
	   System.out.println(robotank + " distancia " + distancia);
	   
       if(distancia < 135){ //grande chance de acertar (cerca de 3 tanques de distancia)
		   //o fire vai de 0.1 a 3 
		    fire(3);  
	   }
	   else 
	      fire(1);
   }
   
   //evento que identifica colisões com a parede 
   public void onHitWall(HitWallEvent e){
	   back(50);
	   turnRight(90);
   }
   
   //quando o robo é atingido por uma bala 
   public void onHitByBullet(HitByBulletEvent e){
     back(10);
   }
}
