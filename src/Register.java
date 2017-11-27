import javax.swing.*;
import java.awt.*;

class Register extends Event{
    private JFrame frame;
    private JPanel titleP, userP, passP, nameP, buttonP, warningP;
    private JLabel warningL;
    private JTextField username, name;
    private JPasswordField password;

    public Register(){
        createPanels();

        // Title Panel
        manage.createTitle("Create account", titleP);

        username = manage.createLabelTextField(userP, "Username:");
        password = manage.createPass(passP);

        // Name Panel
        name = manage.createLabelTextField(nameP, "Name:");

        // Button Panel
        JButton back = new JButton("Back");
        JButton finish = new JButton("Finish");
        manage.addPanel(buttonP, back, finish);

        back.addActionListener(e -> {
            frame.dispose();
            new Login();
        });

        finish.addActionListener(e -> finishButtonAction());

        // Warning Panel
        warningL = new JLabel("Fill the blank spaces");
        warningP.add(warningL);

        manage.addFrame(frame, titleP, userP, passP, nameP, buttonP, warningP);
        manage.defaultWindow(frame,400, 300, super.d);
    }

    private void finishButtonAction(){
        String user = username.getText();
        String pass = String.valueOf(password.getPassword());
        String name = this.name.getText();
        // Confirm if every parm is filled in UI
        boolean userBol = user.equals("");
        boolean passBol = pass.equals("");
        boolean nameBol = name.equals("");
        boolean paramEmpty = userBol || passBol || nameBol;

        if(paramEmpty){
            warningP.remove(warningL);
            warningL = manage.setWarning(frame, warningP, "Some data is empty");
        } else {
            int indexNewUser = checkUsernameAndName(user, pass, name);
            if(indexNewUser != -1){
                frame.dispose();
                new Menu(indexNewUser);
            } else {
                warningP.remove(warningL);
                warningL = manage.setWarning(frame, warningP, "Member not in DEI community or username already exists");
            }
        }
    }

    private void createPanels(){
        frame = new JFrame();
        frame.setLayout(new GridLayout(6, 1));
        titleP = new JPanel(new BorderLayout());
        userP = new JPanel(new FlowLayout());
        passP = new JPanel(new FlowLayout());
        nameP = new JPanel(new FlowLayout());
        warningP = new JPanel(new FlowLayout());
        buttonP = new JPanel(new FlowLayout());
    }
}
