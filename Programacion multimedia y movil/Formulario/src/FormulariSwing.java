import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FormulariSwing extends JFrame {

    // Components del formulari
    private JTextField txtNom;
    private JTextField txtCorreu;
    private JTextField txtEdat;
    private JButton btnDesar;

    public FormulariSwing() {
        // Configuració de la finestra
        setTitle("Formulari d'Usuari");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la finestra a la pantalla

        // Utilitzam un GridBagLayout per ordenar els elements netament
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Marges entre components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Nom ---
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nom:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 1.0;
        txtNom = new JTextField(20);
        panel.add(txtNom, gbc);

        // --- Correu ---
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("Correu:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 1.0;
        txtCorreu = new JTextField(20);
        panel.add(txtCorreu, gbc);

        // --- Edat ---
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.weightx = 0;
        panel.add(new JLabel("Edat:"), gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.weightx = 1.0;
        txtEdat = new JTextField(20);
        panel.add(txtEdat, gbc);

        // --- Botó Desar ---
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2; // Ocupa les dues columnes
        btnDesar = new JButton("Desar a fitxer");
        panel.add(btnDesar, gbc);

        // Acció del botó
        btnDesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desarDades();
            }
        });

        // Afegir el panell a la finestra
        add(panel);
    }

    private void desarDades() {
        String nom = txtNom.getText().trim();
        String correu = txtCorreu.getText().trim();
        String edat = txtEdat.getText().trim();

        // Validació bàsica de camps buits
        if (nom.isEmpty() || correu.isEmpty() || edat.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Això no va així: omple tots els camps abans de desar.",
                    "Atenció",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Escriure les dades al fitxer
        // El paràmetre 'true' a FileWriter permet afegir línies noves sense esborrar el contingut previ
        try (FileWriter fw = new FileWriter("dades_usuari.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println("Nom: " + nom + " | Correu: " + correu + " | Edat: " + edat);

            // Missatge de confirmació
            JOptionPane.showMessageDialog(this,
                    "Les dades s'han desat correctament!",
                    "Èxit",
                    JOptionPane.INFORMATION_MESSAGE);

            // Netejar els camps de text
            txtNom.setText("");
            txtCorreu.setText("");
            txtEdat.setText("");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error en desar el fitxer: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Asseguram que la interfície s'executi en el fil d'esdeveniments de Swing (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormulariSwing().setVisible(true);
            }
        });
    }
}

