package college;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.HashMap;
import javax.swing.*;

public class CollegeFinder {

    public static JFrame collegePage;
    public static HashMap<String, String[]> courseCollegesMap;
    public static HashMap<String, String> collegeLinks;

    public static void main(String[] args) {
        initializeData();
        showCollege();
    }

    public static void showCollege() {
        collegePage = new JFrame("College Finder");
        collegePage.setSize(475, 700);
        collegePage.setLocationRelativeTo(null);
        collegePage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        collegePage.setLayout(null);

        JLabel label = new JLabel("Select a Course:");
        label.setBounds(50, 20, 150, 30);
        collegePage.add(label);

        String[] courses = {
                "Business Management", "Medicine", "Law", "Psychology",
                "Architecture", "Data Science", "Journalism and Mass Communication",
                "Environmental Science", "International Relations"
        };
        JComboBox<String> courseComboBox = new JComboBox<>(courses);
        courseComboBox.setBounds(180, 20, 160, 30);
        collegePage.add(courseComboBox);

        JButton findButton = new JButton("Find Colleges");
        findButton.setBounds(120, 70, 150, 30);
        collegePage.add(findButton);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(resultPanel);
        scrollPane.setBounds(50, 120, 380, 500);
        collegePage.add(scrollPane);

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCourse = (String) courseComboBox.getSelectedItem();
                String[] colleges = courseCollegesMap.get(selectedCourse);
                resultPanel.removeAll();

                if (colleges != null) {
                    for (String college : colleges) {
                        JPanel collegePanel = createCollegePanel(college);
                        resultPanel.add(collegePanel);
                    }
                } else {
                    resultPanel.add(new JLabel("No colleges found for the selected course."));
                }

                resultPanel.revalidate();
                resultPanel.repaint();
            }
        });

        collegePage.setVisible(true);
    }

    public static JPanel createCollegePanel(String collegeName) {
        JPanel collegePanel = new JPanel();
        collegePanel.setLayout(new BorderLayout());
        collegePanel.setBackground(new Color(204, 204, 255)); // Set the background color to Periwinkle
        collegePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true)); // Add border
        collegePanel.setPreferredSize(new Dimension(360, 50)); // Set preferred size for uniformity

        JLabel collegeLabel = new JLabel(collegeName);
        collegeLabel.setForeground(Color.BLACK); // Set text color to black for better contrast
        collegeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        collegeLabel.setOpaque(true);
        collegeLabel.setBackground(new Color(204, 204, 255)); // Ensure label has the same background

        collegeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openCollegeWebsite(collegeName);
            }
        });

        collegePanel.add(collegeLabel, BorderLayout.CENTER);
        return collegePanel;
    }

    public static void openCollegeWebsite(String collegeName) {
        try {
            String url = collegeLinks.get(collegeName);
            if (url != null) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                JOptionPane.showMessageDialog(collegePage, "No website available for " + collegeName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void initializeData() {
        courseCollegesMap = new HashMap<>();
        collegeLinks = new HashMap<>();
        // Adding colleges and links for each course
        // Business Management
        courseCollegesMap.put("Business Management", new String[]{
                "Indian Institute of Management (IIM) Ahmedabad", "Indian Institute of Management (IIM) Bangalore",
                "Indian Institute of Management (IIM) Calcutta", "Indian School of Business (ISB) Hyderabad",
                "Xavier Labour Relations Institute (XLRI) Jamshedpur", "Faculty of Management Studies (FMS) Delhi",
                "SP Jain Institute of Management and Research (SPJIMR) Mumbai", "Narsee Monjee Institute of Management Studies (NMIMS) Mumbai",
                "Management Development Institute (MDI) Gurgaon", "Institute of Management Technology (IMT) Ghaziabad"
        });
        collegeLinks.put("Indian Institute of Management (IIM) Ahmedabad", "https://www.iima.ac.in/");
        collegeLinks.put("Indian Institute of Management (IIM) Bangalore", "https://www.iimb.ac.in/");
        collegeLinks.put("Indian Institute of Management (IIM) Calcutta", "https://www.iimcal.ac.in/");
        collegeLinks.put("Indian School of Business (ISB) Hyderabad", "https://www.isb.edu/");
        collegeLinks.put("Xavier Labour Relations Institute (XLRI) Jamshedpur", "https://www.xlri.ac.in/");
        collegeLinks.put("Faculty of Management Studies (FMS) Delhi", "http://fms.edu/");
        collegeLinks.put("SP Jain Institute of Management and Research (SPJIMR) Mumbai", "https://www.spjimr.org/");
        collegeLinks.put("Narsee Monjee Institute of Management Studies (NMIMS) Mumbai", "https://www.nmims.edu/");
        collegeLinks.put("Management Development Institute (MDI) Gurgaon", "https://www.mdi.ac.in/");
        collegeLinks.put("Institute of Management Technology (IMT) Ghaziabad", "https://www.imt.edu/");

        // Medicine
        courseCollegesMap.put("Medicine", new String[]{
                "All India Institute of Medical Sciences (AIIMS) New Delhi", "Christian Medical College (CMC) Vellore",
                "King George's Medical University (KGMU) Lucknow", "Maulana Azad Medical College (MAMC) Delhi",
                "Jawaharlal Institute of Postgraduate Medical Education & Research (JIPMER) Puducherry",
                "Grant Medical College Mumbai", "St. John's Medical College Bangalore",
                "Madras Medical College (MMC) Chennai", "Armed Forces Medical College (AFMC) Pune",
                "Kasturba Medical College (KMC) Manipal"
        });
        collegeLinks.put("All India Institute of Medical Sciences (AIIMS) New Delhi", "https://www.aiims.edu/");
        collegeLinks.put("Christian Medical College (CMC) Vellore", "https://www.cmch-vellore.edu/");
        collegeLinks.put("King George's Medical University (KGMU) Lucknow", "https://www.kgmu.org/");
        collegeLinks.put("Maulana Azad Medical College (MAMC) Delhi", "http://www.mamc.ac.in/");
        collegeLinks.put("Jawaharlal Institute of Postgraduate Medical Education & Research (JIPMER) Puducherry", "https://www.jipmer.edu.in/");
        collegeLinks.put("Grant Medical College Mumbai", "https://ggmcjjh.com/");
        collegeLinks.put("St. John's Medical College Bangalore", "https://www.stjohns.in/");
        collegeLinks.put("Madras Medical College (MMC) Chennai", "http://www.mmc.tn.gov.in/");
        collegeLinks.put("Armed Forces Medical College (AFMC) Pune", "https://afmc.nic.in/");
        collegeLinks.put("Kasturba Medical College (KMC) Manipal", "https://manipal.edu/kmc-manipal.html");

        // Law
        courseCollegesMap.put("Law", new String[]{
                "National Law School of India University (NLSIU) Bangalore", "National Academy of Legal Studies and Research (NALSAR) Hyderabad",
                "National Law University (NLU) Delhi", "The West Bengal National University of Juridical Sciences (NUJS) Kolkata",
                "National Law Institute University (NLIU) Bhopal", "Symbiosis Law School Pune",
                "Gujarat National Law University (GNLU) Gandhinagar", "National University of Advanced Legal Studies (NUALS) Kochi",
                "Faculty of Law, University of Delhi", "Dr. Ram Manohar Lohiya National Law University (RMLNLU) Lucknow"
        });
        collegeLinks.put("National Law School of India University (NLSIU) Bangalore", "https://www.nls.ac.in/");
        collegeLinks.put("National Academy of Legal Studies and Research (NALSAR) Hyderabad", "https://www.nalsar.ac.in/");
        collegeLinks.put("National Law University (NLU) Delhi", "https://nludelhi.ac.in/");
        collegeLinks.put("The West Bengal National University of Juridical Sciences (NUJS) Kolkata", "https://www.nujs.edu/");
        collegeLinks.put("National Law Institute University (NLIU) Bhopal", "https://www.nliu.ac.in/");
        collegeLinks.put("Symbiosis Law School Pune", "https://www.symlaw.ac.in/");
        collegeLinks.put("Gujarat National Law University (GNLU) Gandhinagar", "https://gnlu.ac.in/");
        collegeLinks.put("National University of Advanced Legal Studies (NUALS) Kochi", "https://www.nuals.ac.in/");
        collegeLinks.put("Faculty of Law, University of Delhi", "http://www.lawfaculty.du.ac.in/");
        collegeLinks.put("Dr. Ram Manohar Lohiya National Law University (RMLNLU) Lucknow", "http://rmlnlu.ac.in/");

        // Psychology
        courseCollegesMap.put("Psychology", new String[]{
                "University of Delhi", "Tata Institute of Social Sciences (TISS) Mumbai",
                "Banaras Hindu University (BHU) Varanasi", "Amity University Noida",
                "Christ University Bangalore", "University of Calcutta",
                "Indian Institute of Psychology and Research (IIPR) Bangalore", "Jamia Millia Islamia (JMI) Delhi",
                "Panjab University Chandigarh", "University of Mumbai"
        });
        collegeLinks.put("University of Delhi", "http://www.du.ac.in/");
        collegeLinks.put("Tata Institute of Social Sciences (TISS) Mumbai", "https://www.tiss.edu/");
        collegeLinks.put("Banaras Hindu University (BHU) Varanasi", "http://www.bhu.ac.in/");
        collegeLinks.put("Amity University Noida", "https://www.amity.edu/");
        collegeLinks.put("Christ University Bangalore", "https://christuniversity.in/");
        collegeLinks.put("University of Calcutta", "http://www.caluniv.ac.in/");
        collegeLinks.put("Indian Institute of Psychology and Research (IIPR) Bangalore", "https://www.iipr.ac.in/");
        collegeLinks.put("Jamia Millia Islamia (JMI) Delhi", "http://www.jmi.ac.in/");
        collegeLinks.put("Panjab University Chandigarh", "http://www.puchd.ac.in/");
        collegeLinks.put("University of Mumbai", "http://www.mu.ac.in/");

        // Architecture
        courseCollegesMap.put("Architecture", new String[]{
                "Indian Institute of Technology (IIT) Kharagpur", "National Institute of Technology (NIT) Delhi",
                "Jawaharlal Nehru Architecture and Fine Arts University (JNAFAU) Hyderabad",
                "Birla Institute of Technology (BIT) Mesra", "Sushant School of Art and Architecture Gurugram",
                "CEPT University Ahmedabad", "L.S. Raheja School of Architecture Mumbai",
                "School of Planning and Architecture (SPA) Delhi", "K.L.E. Technological University Hubli",
                "Manipal University Jaipur"
        });
        collegeLinks.put("Indian Institute of Technology (IIT) Kharagpur", "https://www.iitkgp.ac.in/");
        collegeLinks.put("National Institute of Technology (NIT) Delhi", "https://www.nitdelhi.ac.in/");
        collegeLinks.put("Jawaharlal Nehru Architecture and Fine Arts University (JNAFAU) Hyderabad", "https://jnafaau.edu.in/");
        collegeLinks.put("Birla Institute of Technology (BIT) Mesra", "https://www.bitmesra.ac.in/");
        collegeLinks.put("Sushant School of Art and Architecture Gurugram", "https://www.sushantschool.edu.in/");
        collegeLinks.put("CEPT University Ahmedabad", "https://cept.ac.in/");
        collegeLinks.put("L.S. Raheja School of Architecture Mumbai", "https://www.lsraheja.com/");
        collegeLinks.put("School of Planning and Architecture (SPA) Delhi", "http://spa.ac.in/");
        collegeLinks.put("K.L.E. Technological University Hubli", "https://www.kletech.ac.in/");
        collegeLinks.put("Manipal University Jaipur", "https://jaipur.manipal.edu/");

        // Data Science
        courseCollegesMap.put("Data Science", new String[]{
                "Indian Institute of Science (IISc) Bangalore", "Indian Statistical Institute (ISI) Kolkata",
                "Indian Institute of Technology (IIT) Bombay", "BITS Pilani",
                "Great Lakes Institute of Management Chennai", "Jigsaw Academy",
                "Manipal Academy of Higher Education (MAHE)", "University of California, Berkeley (Online)",
                "Harvard University (Online)", "Coursera Data Science Specialization"
        });
        collegeLinks.put("Indian Institute of Science (IISc) Bangalore", "https://www.iisc.ac.in/");
        collegeLinks.put("Indian Statistical Institute (ISI) Kolkata", "https://www.isical.ac.in/");
        collegeLinks.put("Indian Institute of Technology (IIT) Bombay", "https://www.iitb.ac.in/");
        collegeLinks.put("BITS Pilani", "https://www.bits-pilani.ac.in/");
        collegeLinks.put("Great Lakes Institute of Management Chennai", "https://www.greatlakes.edu.in/");
        collegeLinks.put("Jigsaw Academy", "https://www.jigsawacademy.com/");
        collegeLinks.put("Manipal Academy of Higher Education (MAHE)", "https://manipal.edu/");
        collegeLinks.put("University of California, Berkeley (Online)", "https://extension.berkeley.edu/");
        collegeLinks.put("Harvard University (Online)", "https://online-learning.harvard.edu/");
        collegeLinks.put("Coursera Data Science Specialization", "https://www.coursera.org/specializations/jhu-data-science");

        // Journalism and Mass Communication
        courseCollegesMap.put("Journalism and Mass Communication", new String[]{
                "Indian Institute of Mass Communication (IIMC) Delhi", "Jamia Millia Islamia (JMI) Delhi",
                "Asian College of Journalism (ACJ) Chennai", "Symbiosis Institute of Media and Communication (SIMC) Pune",
                "National School of Drama (NSD) Delhi", "Mudra Institute of Communications Ahmedabad (MICA)",
                "University of Mumbai", "Manipal University", "Xavier Institute of Communications (XIC) Mumbai",
                "Shri Ram College of Commerce (SRCC) Delhi"
        });
        collegeLinks.put("Indian Institute of Mass Communication (IIMC) Delhi", "http://iimc.nic.in/");
        collegeLinks.put("Jamia Millia Islamia (JMI) Delhi", "http://www.jmi.ac.in/");
        collegeLinks.put("Asian College of Journalism (ACJ) Chennai", "https://www.asianmedia.org/");
        collegeLinks.put("Symbiosis Institute of Media and Communication (SIMC) Pune", "https://www.simc.edu/");
        collegeLinks.put("National School of Drama (NSD) Delhi", "http://nsd.gov.in/");
        collegeLinks.put("Mudra Institute of Communications Ahmedabad (MICA)", "https://www.mica.ac.in/");
        collegeLinks.put("University of Mumbai", "http://www.mu.ac.in/");
        collegeLinks.put("Manipal University", "https://www.manipal.edu/");
        collegeLinks.put("Xavier Institute of Communications (XIC) Mumbai", "https://www.xaviercomm.org/");
        collegeLinks.put("Shri Ram College of Commerce (SRCC) Delhi", "http://www.srcc.edu/");

        // Environmental Science
        courseCollegesMap.put("Environmental Science", new String[]{
                "Indian Institute of Technology (IIT) Kharagpur", "Indian Institute of Science (IISc) Bangalore",
                "Jawaharlal Nehru University (JNU) Delhi", "University of Delhi",
                "Gujarat University Ahmedabad", "National Institute of Environmental Engineering",
                "University of Pune", "Indian Institute of Technology (IIT) Bombay",
                "Banaras Hindu University (BHU) Varanasi", "Jawaharlal Nehru Technological University (JNTU) Hyderabad"
        });
        collegeLinks.put("Indian Institute of Technology (IIT) Kharagpur", "https://www.iitkgp.ac.in/");
        collegeLinks.put("Indian Institute of Science (IISc) Bangalore", "https://www.iisc.ac.in/");
        collegeLinks.put("Jawaharlal Nehru University (JNU) Delhi", "https://www.jnu.ac.in/");
        collegeLinks.put("University of Delhi", "http://www.du.ac.in/");
        collegeLinks.put("Gujarat University Ahmedabad", "http://www.gujaratuniversity.ac.in/");
        collegeLinks.put("National Institute of Environmental Engineering", "https://www.niwi.in/");
        collegeLinks.put("University of Pune", "http://www.unipune.ac.in/");
        collegeLinks.put("Indian Institute of Technology (IIT) Bombay", "https://www.iitb.ac.in/");
        collegeLinks.put("Banaras Hindu University (BHU) Varanasi", "http://www.bhu.ac.in/");
        collegeLinks.put("Jawaharlal Nehru Technological University (JNTU) Hyderabad", "http://www.jntuh.ac.in/");

        // International Relations
        courseCollegesMap.put("International Relations", new String[]{
                "Jawaharlal Nehru University (JNU) Delhi", "Delhi University",
                "Tata Institute of Social Sciences (TISS) Mumbai", "Jamia Millia Islamia (JMI) Delhi",
                "Ashoka University Haryana", "Manipal Academy of Higher Education",
                "University of Hyderabad", "Banaras Hindu University (BHU) Varanasi",
                "Gujarat University Ahmedabad", "University of Mumbai"
        });
        collegeLinks.put("Jawaharlal Nehru University (JNU) Delhi", "https://www.jnu.ac.in/");
        collegeLinks.put("Delhi University", "http://www.du.ac.in/");
        collegeLinks.put("Tata Institute of Social Sciences (TISS) Mumbai", "https://www.tiss.edu/");
        collegeLinks.put("Jamia Millia Islamia (JMI) Delhi", "http://www.jmi.ac.in/");
        collegeLinks.put("Ashoka University Haryana", "https://www.ashoka.edu.in/");
        collegeLinks.put("Manipal Academy of Higher Education", "https://www.manipal.edu/");
        collegeLinks.put("University of Hyderabad", "https://www.uohyd.ac.in/");
        collegeLinks.put("Banaras Hindu University (BHU) Varanasi", "http://www.bhu.ac.in/");
        collegeLinks.put("Gujarat University Ahmedabad", "http://www.gujaratuniversity.ac.in/");
        collegeLinks.put("University of Mumbai", "http://www.mu.ac.in/");
    }
}