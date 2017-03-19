package Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by traitorwtf on 11.03.2017.
 */
public class QuizCard_Constructor {



    private JFrame frame = new JFrame();
    private static JTextArea answerArea;
    private static JTextArea questionArea;
    private JButton saveButton;
    private JButton startButton;
    private JButton answerButton;
    private JButton nextButton;


    static ArrayList<String> baseQuizList  = new ArrayList<>();
    static ArrayList<String> quizList;
    static int currentQuiz;
    static int listSize;
    static int indexOfPalka;

    public static void main(String[] args) {
        QuizCard_Constructor constr = new QuizCard_Constructor();
        constr.go();
    }

    public void go(){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,510);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuNew = new JMenuItem("New");
        JMenuItem menuLoad = new JMenuItem("Load");
        JMenuItem menuSave = new JMenuItem("Save");
        JMenuItem menuExit = new JMenuItem("Exit");

        menu.add(menuNew);
        menu.add(menuLoad);
        menu.add(menuSave);
        menu.add(menuExit);

        menuExit.addActionListener(new exitActionListener());
        menuSave.addActionListener(new saveActionListener());
        menuLoad.addActionListener(new loadActionListener());
        menuNew.addActionListener(new newActionListener());

        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        Font font0 = new Font("Comic Sans MS", Font.BOLD, 16);

        questionArea = new JTextArea(7,30);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setFont(font0);

        answerArea = new JTextArea(7,30);
        answerArea.setLineWrap(true);
        answerArea.setWrapStyleWord(true);
        answerArea.setFont(font0);

        Font font1 = new Font("Arial", Font.BOLD, 32);
        JLabel questionLabel = new JLabel("QUESTION");
        questionLabel.setFont(font1);
        JLabel answerLabel = new JLabel("ANSWER");

        answerLabel.setFont(font1);
        panel.add(questionLabel);
        panel.add(questionArea);
        panel.add(answerLabel);
        panel.add(answerArea);

        JPanel panel2 = new JPanel();
        saveButton = new JButton("Save Question & Answer");
        startButton = new JButton("Start");
        answerButton = new JButton("Show Answer");
        nextButton = new JButton(">>");

        panel2.add(saveButton);
        panel2.add(answerButton);
        panel2.add(startButton);
        panel2.add(nextButton);

        saveButton.addActionListener(new saveButtonListener());
        startButton.addActionListener(new startButtonListener());
        answerButton.addActionListener(new answerButtonListener());
        nextButton.addActionListener(new nextButtonListener());

        frame.getContentPane().add(BorderLayout.SOUTH, panel2);
        frame.getContentPane().add(BorderLayout.CENTER, panel);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    private class newActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            baseQuizList.clear();
            questionArea.setText("");
            answerArea.setText("");
            questionArea.requestFocus();
        }
    }

    private class exitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            System.exit(0);
            //frame.dispose();
        }
    }

    private class saveActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(frame);
            //TODO saveFile(fileChooser.getSelectedFile()); доделать сохранялку
            saveFile(fileChooser.getSelectedFile());
        }
    }

    private class loadActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(frame);
            loadFile(fileChooser.getSelectedFile());
        }
    }

    private class saveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String Question = questionArea.getText();
            String Answer = answerArea.getText();
            String QA = Question+" / "+Answer;
            //System.out.println(QA);
            baseQuizList.add(QA);
            //System.out.println(quizList.size());
            questionArea.setText("");
            answerArea.setText("");
            questionArea.requestFocus();
        }
    }


    private class startButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            QuizCard_Constructor cons = new QuizCard_Constructor();
            cons.startQuiz();
        }
    }

    public void startQuiz(){
            // копирование листа
            quizList = new ArrayList<>();
            for (String string : baseQuizList){
                quizList.add(string);
            }

            JOptionPane.showMessageDialog( frame, "Get ready for the Quiz!", "Attention!",
                JOptionPane.DEFAULT_OPTION );

            questionArea.setText("");
            answerArea.setText("");

    }

    private class answerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            QuizCard_Constructor cons = new QuizCard_Constructor();
            cons.answerQuiz();
        }
    }

    public void answerQuiz() {
            String answer = quizList.get(currentQuiz).substring(indexOfPalka + 2);
            answerArea.setText(answer);
            quizList.remove(currentQuiz);
    }

    private class nextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            QuizCard_Constructor cons = new QuizCard_Constructor();
            cons.nextQuiz();
        }
    }

    public void nextQuiz() {
        listSize = quizList.size();
        if (listSize == 0){
            JOptionPane.showMessageDialog( frame, "Quiz list is empty", "Warning!",
                    JOptionPane.DEFAULT_OPTION );
        } else {
            currentQuiz = (int) (Math.random() * listSize);
            String QiA = quizList.get(currentQuiz);
            indexOfPalka = QiA.indexOf("/");
            if (indexOfPalka == -1) System.out.println("Huston, we have a problem!");
            String question = QiA.substring(0, indexOfPalka);
            questionArea.setText(question);
            answerArea.setText("");
        }
    }

    private void saveFile(File file) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            Iterator cardIterator = baseQuizList.iterator();
            while (cardIterator.hasNext()) {
                String qia = (String) cardIterator.next();
                writer.write(qia + "\n");
            }
            writer.close();


        } catch(IOException ex) {
            System.out.println("couldn't write the quizList out");
            ex.printStackTrace();
        }

    }

    private void loadFile(File file) {
        baseQuizList = new ArrayList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                baseQuizList.add(line);
            }
            reader.close();

        } catch(Exception ex) {
            System.out.println("couldn't read the card file");
            ex.printStackTrace();
        }

    }
}
