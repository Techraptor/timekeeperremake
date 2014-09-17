package com.falconraptor.timekeeper.init;
import javax.swing.*;
public class Gui extends JFrame{
  public Gui(){
    super("Timekeeper");
    setContentPane(setGui());
    setVisible(true);
    setLocationRelativeTo(null);
    pack();
  }
  public JPanel setGui(){
    JPanel p=new JPanel(new GridLayout(1,1,0,0));
    JButton extras=new JButton("Extras");
    extras.addActionListener(clicked("extras"));
    p.add(extras);
    return p;
  }
  public ActionListener clicked(final String button){
    return new ActionListener(){
      @Override
      public run(actionevent ea){
        if (button.equals("extras")){
          
        }
      }
    }
  }
}
