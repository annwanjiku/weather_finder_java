import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main{
    public static void main(String[] args){

        ImageIcon backgroundImage = new ImageIcon("src/weatherimage.jpg");
        Image bg = backgroundImage.getImage();

        JPanel backgroundPanel = new JPanel(){
            @Override
            protected void paintComponent( Graphics g){
                super.paintComponent(g);
                g.drawImage(bg,0,0,getWidth(),getHeight(),this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        JFrame frame = new JFrame("Weather Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,350);
        frame.setContentPane(backgroundPanel);

        JPanel inputPanel = new JPanel(new GridLayout(5,2,10,10));
        inputPanel.setOpaque(false);

        JLabel dayLabel = new JLabel("Day:");
        JTextField dayField = new JTextField();

        JLabel monthLabel = new JLabel("Month:");
        JTextField monthField = new JTextField();

        JLabel yearLabel = new JLabel("Year:");
        JTextField yearField = new JTextField();

        JLabel locationLabel = new JLabel("Location:");
        JTextField locationField = new JTextField();

        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        messagePanel.setOpaque(false);
        JLabel messageLabel = new JLabel("");

        JLabel loadingIcon = new JLabel();
        Icon loadingGif = new ImageIcon(new ImageIcon("src/loadingGif.gif").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
        loadingIcon.setIcon(loadingGif);
        loadingIcon.setVisible(false);

        messagePanel.add(messageLabel);
        messagePanel.add(loadingIcon);

        inputPanel.add(dayLabel);
        inputPanel.add(dayField);
        inputPanel.add(monthLabel);
        inputPanel.add(monthField);
        inputPanel.add(yearLabel);
        inputPanel.add(yearField);
        inputPanel.add(locationLabel);
        inputPanel.add(locationField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton submitButton = new JButton("Submit");
        JButton tryAgainButton = new JButton("TryAgain");
        tryAgainButton.setVisible(false); //temporary
        buttonPanel.add(submitButton);
        buttonPanel.add(tryAgainButton);

        frame.add(inputPanel,BorderLayout.NORTH);
        frame.add(messagePanel, BorderLayout.CENTER);
        frame.add(buttonPanel,BorderLayout.SOUTH);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String day = dayField.getText();
                String month = monthField.getText();
                String year = yearField.getText();
                String location = locationField.getText();

                submitButton.setVisible(false);
                messageLabel.setText("Loading...");
                loadingIcon.setVisible(true);

                //wait 6 seconds then show error message
                Timer timer = new Timer(6000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        loadingIcon.setVisible(false);
                        messageLabel.setText("Oops! No weather result found");
                        tryAgainButton.setVisible(true);
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
        tryAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dayField.setText("");
                monthField.setText("");
                yearField.setText("");
                locationField.setText("");

                messageLabel.setText("");
                loadingIcon.setVisible(false);
                tryAgainButton.setVisible(false);
                submitButton.setVisible(true);
            }
        });


        frame.setVisible(true);
    }
}

