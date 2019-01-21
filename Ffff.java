package love;

import javax.swing.JFrame;

public class Fffff {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(10, 10, 900, 720);//设置背景板的大小
		frame.setResizable(false);//是否可以随意改变大小
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//当点击右上角的关闭按钮时要做什么事
		
		FffffPanelTwo panel = new FffffPanelTwo();//new出一个画布
		frame.add(panel);  //将这个画布加到背景板上
		 
		frame.setVisible(true);//让建立的面板展现出来（默认是不展现的）
	}

}
