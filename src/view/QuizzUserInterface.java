package view;

import Quizz.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.border.Border;
import model.GameEngine;
import model.Player;

public class QuizzUserInterface extends JFrame implements ActionListener {
    
    private JButton one, two, three, for4;
    private HashMap<Integer, Integer> answers;
    private JTextArea score;
    private JTextArea desire;
    private String currentScore;
    private String printQ;
    private int countScore;
    private int cpt;
    private GameEngine ge;
    private Player player;
    
    public QuizzUserInterface(GameEngine ge,Player p) {
        this.cpt = 1;
        this.ge = ge;
        this.player = p;
        this.answers = new HashMap<Integer, Integer>();
        this.currentScore = "Question"+ (cpt) +"/3" +"\n"+"Score :" + countScore +"\n Your Total Score : "+(player.getPoint()+countScore);
        this.countScore = 0;
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        Container pane = getContentPane();
        
        
        BoxLayout b = new BoxLayout(pane, WIDTH);
        pane.setLayout(b);
        
        desire = new JTextArea(printQ, 10, 25);
        score = new JTextArea(currentScore, 2, 2);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        score.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder()));
        
        this.one = new JButton("1");
        this.two = new JButton("2");
        this.three = new JButton("3");
        this.for4 = new JButton("4");
        
        this.newQuestion();
        
        one.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        for4.addActionListener(this);
        pane.add(desire);
        pane.add(score);
        pane.add(one);
        pane.add(two);
        pane.add(three);
        pane.add(for4);
        setContentPane(pane);
         
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == one && answers.get(1) == 1) {
            this.win();
        } else if (source == two && answers.get(2) == 1) {
            this.win();
        } else if (source == three && answers.get(3) == 1) {
            this.win();
        } else if (source == for4 && answers.get(4) == 1) {
            this.win();
        } else {
            this.loose();
        }
      
       this.newQuestion();
    }
    
    private void win() {
        countScore++;
        this.setScore();
        Border border = BorderFactory.createLineBorder(Color.GREEN);
        score.setBorder(border);
    }
    
    private void loose() {
       
        this.setScore();
        this.newQuestion();
        Border border = BorderFactory.createLineBorder(Color.RED);
        score.setBorder(border);
        
    }
    
    private void setScore() {
       // score.setText("Question : "+ (cpt+1) +"/3" +"\n"+"Score : " + countScore);
         cpt++;
         score.setText("Question : "+ (cpt) +"/3" +"\n"+"Score : " + countScore +"\n Your Total Score : "+(player.getPoint()+countScore) );
    }
    private void newQuestion() {
        if (cpt > 3) {
        this.player.addPoint(countScore);
        this.ge.setGV(true);
        this.dispose();
            System.out.println("point player = " + player.getPoint());
        }
       
        Question q = QuizzController.getQuestion();
        
        String printQ = q.getTitle() + "\n";
        
        int cptAnswer = 0;
        for (Map.Entry<String, Integer> entry : q.getAnswers().entrySet()) {
            cptAnswer++;
            printQ += cptAnswer + ") " + entry.getKey() + "\n";
            answers.put(cptAnswer, entry.getValue());
            if (entry.getValue() == 1) {
                // result.setAnswer(entry.getKey());
            }
        }
        desire.setText(printQ);
    }
    
}
