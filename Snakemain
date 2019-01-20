package snake;

import java.awt.Color;

import javax.swing.JFrame;

public class SnakeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setBounds(10, 10, 900, 720);//设置背景板的大小
		frame.setResizable(false);//是否可以随意改变大小
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//当点击右上角的关闭按钮时要做什么事
		
		SnakePanel panel = new SnakePanel();//new出一个画布
		panel.setBackground(Color.WHITE );//加上这句
		frame.add(panel);  //将这个画布加到背景板上
		 
		frame.setVisible(true);//让建立的面板展现出来（默认是不展现的）
	}

}
