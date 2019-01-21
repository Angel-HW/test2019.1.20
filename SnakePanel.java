package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics; //设置画笔类
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; //设置接口，监听键盘
import java.util.Random;

import javax.swing.ImageIcon;  //调用图片类
import javax.swing.JPanel;  //调用画布类
import javax.swing.Timer;

//implements设置接口，监听键盘.  ActionListener设置一个闹钟，每个一段时间就调用Action的方法
public class SnakePanel extends JPanel implements KeyListener,ActionListener{  
	ImageIcon up = new ImageIcon("Up.png");
	ImageIcon down = new ImageIcon("Down.png");
	ImageIcon right = new ImageIcon("Right.png");
	ImageIcon left = new ImageIcon("Left.png");
	ImageIcon title = new ImageIcon("title.jpg");
	ImageIcon food = new ImageIcon("Food.png");
	ImageIcon body = new ImageIcon("Body.png");
	
	int[] snakex = new int[750];
	int[] snakey = new int[750];//记录蛇的坐标
	
	Random rand = new Random();
	int foodx = rand.nextInt(34)*25+25;
	int foody = rand.nextInt(24)*25+75;
	
	int len = 3;				//记录蛇的长度
	String fangxiang = "R" ;     //方向 R ,L,U,D;

	boolean isStarted = false; //设置一个布尔型变量，游戏是否开始，默认false
	boolean isfailed = true;  //判断是否失败
	
	Timer timer = new Timer(100,this);
	
		public SnakePanel(){		//构造函数
			this.setFocusable(true); //获得焦点
			this.addKeyListener(this);//监听自己的键盘事件，即当你敲键盘时会去找下面的几个方法
			setup();
			timer.start();
		}
		
		public void paint(Graphics g){ 	//g为画笔
			super.paint(g);
			//this.setBackground(Color.WHITE);
			title.paintIcon(this, g, 280, 1); 	//设置title文件及其显示位置
			g.setColor(Color.BLACK);
			g.fillRect(15, 30, 860,650); 		//画一个方框(方框的开始横纵坐标，方框的规格 横 纵)
			
			//画蛇头
			if(fangxiang.equals("R")){
				right.paintIcon(this, g, snakex[0], snakey[0]);
			}else if(fangxiang.equals("L")){
				left.paintIcon(this, g, snakex[0], snakey[0]);
			}else if(fangxiang.equals("U")){
				up.paintIcon(this, g, snakex[0], snakey[0]);
			}else if(fangxiang.equals("D")){
				down.paintIcon(this, g, snakex[0], snakey[0]);
			}
			
			//画蛇的身体
			for(int i=1; i<len; i++){
				body.paintIcon(this, g, snakex[i], snakey[i]);
			}
			
			if(!isStarted){
				g.setColor(Color.WHITE);//设置字体颜色
				g.setFont(new Font("宋体",Font.BOLD,30)); //设置字体，字体类型，是否粗体，大小
				g.drawString("按空格键开始或暂停", 300, 300);
			}
			
			if(!isfailed){
				g.setColor(Color.WHITE);//设置字体颜色
				g.setFont(new Font("宋体",Font.BOLD,30)); //设置字体，字体类型，是否粗体，大小
				g.drawString("游戏结束！", 300, 300);
				g.drawString("最终分数为：" + (len-3), 300, 350);
			}
			
			food.paintIcon(this, g, foodx, foody);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("宋体",Font.PLAIN,20));
			g.drawString("分数： " + (len-3), 780, 25);
		}
		
		public void setup(){ //游戏开始时蛇的长度及坐标
			isfailed =true;
			isStarted = false;
			len = 3;
			fangxiang = "R";
			snakex[0] = 100;
			snakey[0] = 100;//蛇头坐标
			snakex[1] = 90;
			snakey[1] = 100; // 蛇第一个身体的坐标
			snakex[2] = 80;
			snakey[2] = 100; //蛇的第二个身体的坐标
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();  //每个键盘的键都有一个KeyCode
			if(keyCode == KeyEvent.VK_SPACE){
				if(!isfailed){
					setup();
				}else{
					isStarted = !isStarted; 
				}
				
			}
			if(isStarted){
				if(keyCode == KeyEvent.VK_UP && fangxiang != "D"){
					fangxiang = "U";
				}else if(keyCode == KeyEvent.VK_DOWN && fangxiang != "U"){
					fangxiang = "D";
				}else if(keyCode == KeyEvent.VK_RIGHT && fangxiang != "L"){
					fangxiang = "R";
				}else if(keyCode == KeyEvent.VK_LEFT && fangxiang != "R"){
					fangxiang = "L";
				}
			}
			
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//1.再定义一个闹钟
			timer.start();
			
			//2.移动数据
			if(isStarted && isfailed){
				//移动身体
				for(int i=len; i>0; i--){
					snakex[i] = snakex[i-1];
					snakey[i] = snakey[i-1];
				}
				//移动头
				if(fangxiang.equals("R")){
					snakex[0] = snakex[0] + 25;
					if(snakex[0] > 850)  snakex[0] = 25;
				}else if(fangxiang.equals("L")){
					snakex[0] = snakex[0] - 25;
					if(snakex[0] < 25)  snakex[0] = 850;
				}else if(fangxiang.equals("U")){
					snakey[0] = snakey[0] - 25;
					if(snakey[0] < 75)  snakey[0] = 650;
				}else if(fangxiang.equals("D")){
					snakey[0] = snakey[0] + 25;
					if(snakey[0] > 650)  snakey[0] = 75;
				}
				
				if(snakex[0] == foodx && snakey[0] == foody){
					len++;
					foodx = rand.nextInt(34)*25+25;
					foody = rand.nextInt(24)*25+75;
				}
				
				for(int i=1; i<len; i++){
					if(snakex[0] == snakex[i] && snakey[0] == snakey[i])
						
						isfailed = false;
				}
			}
			
			//3.repaint()调用paint
			repaint();
			
		}
	
		
		@Override
		public void keyTyped(KeyEvent e) {	
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}


}
