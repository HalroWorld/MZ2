package mz;

import java.awt.Color;

import javax.swing.JOptionPane;

public class MZ_ErrorWindow {
   JOptionPane aa = new JOptionPane();

   public static void main(String[] args) {
      new MZ_ErrorWindow();
   }
   
   public MZ_ErrorWindow(){
      JOptionPane.showMessageDialog(null, "업데이트 중입니다", "경고창", JOptionPane.ERROR_MESSAGE); 
   }
}