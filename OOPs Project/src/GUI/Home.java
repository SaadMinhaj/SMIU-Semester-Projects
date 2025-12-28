/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.Random;
import model.SessionManager;
import model.User;
/**
 *
 * @author Rao Hamza Tariq
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    
    
    public Home() {
        initComponents();
        
        
        User user = SessionManager.getCurrentUser();
        SQL_MODEL db = new SQL_MODEL();
        UserData user_data = db.getUserData(user.getEmail());
        WelcomeName.setText("Welcome "+user_data.fullName);
        
        income_card(user_data.user_id);
        expense_card(user_data.user_id);
        transaction_card(user_data.user_id);
        savings_card();
        createTransactionChart(user_data.user_id);
        createMonthlyCategoryChart(user_data.user_id);
        
        
        

    }
     
 
    
 private void income_card(int user_id){
            ResultSet rs;
            SQL_MODEL db = new SQL_MODEL();
            rs = db.SQL_Query("select IFNULL(SUM(amount), 0) as total_income from transactions where type = 'income' and user_id = "+user_id);
            try{
                while (rs.next()){
                    String totalIncome = rs.getString("total_income");
                    System.out.println(totalIncome);
                    IncomeCard.setText("$"+totalIncome);
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
    
 private void expense_card(int user_id){
            ResultSet rs;
            SQL_MODEL db = new SQL_MODEL();
            rs = db.SQL_Query("select IFNULL(SUM(amount), 0) as total_expense from transactions where type = 'expense' and user_id = "+user_id);
            try{
                while (rs.next()){
                    String totalExpense = rs.getString("total_expense");
                    System.out.println(totalExpense);
                    ExpenseCard.setText("$"+totalExpense);
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
 
 private void savings_card(){
     System.out.println(ExpenseCard.getText());
    double expense = Double.parseDouble((ExpenseCard.getText()).replace("$", ""));
    double income = Double.parseDouble((IncomeCard.getText()).replace("$", ""));
    String savings = Double.toString(income - expense);
    System.out.println(savings);
    SavingsCard.setText("$"+savings);
 }
 
private void transaction_card(int user_id){
            ResultSet rs;
            SQL_MODEL db = new SQL_MODEL();
            rs = db.SQL_Query("select IFNULL(count(amount), 0) as total_transaction from transactions where user_id = "+user_id);
            try{
                while (rs.next()){
                    String totalTransaction = rs.getString("total_transaction");
                    System.out.println(totalTransaction);
                    TotalTransactionsCard.setText(totalTransaction);
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
 


private void createTransactionChart(int user_id) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        SQL_MODEL db = new SQL_MODEL();
        ResultSet rs;
        
        String query = "SELECT transactions.type, transactions.amount, " +
                    "MONTHNAME(transactions.date) AS transaction_month " +
                    "FROM users, transactions " +
                    "WHERE users.id=transactions.user_id AND users.id= "+user_id;
        
        try{
            rs = db.SQL_Query(query);
            while (rs.next()) {
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                String month = rs.getString("transaction_month");

                dataset.addValue(amount, type, month);
            }
        }catch(Exception e){
            System.out.println("Error :"+ e);
        }
        
        JFreeChart chart = ChartFactory.createBarChart(
                "Monthly Income and Expense",
                "Month",
                "Amount ($)",
                dataset
        );

        ChartPanel chartSwingPanel = new ChartPanel(chart);
        chartSwingPanel.setPreferredSize(new Dimension(500, 300));  
        chartSwingPanel.setMaximumSize(new Dimension(500, 300));
        chartSwingPanel.setMinimumSize(new Dimension(500, 300));

        chartPanel.removeAll();
        chartPanel.setLayout(new BorderLayout());
        chartPanel.add(chartSwingPanel, BorderLayout.CENTER);
        chartPanel.revalidate();
        chartPanel.repaint();
    }

private void createMonthlyCategoryChart(int user_id) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    SQL_MODEL db = new SQL_MODEL();
    ResultSet rs;

    String query = "SELECT c.name AS category_name, t.amount, " +
                   "MONTHNAME(t.date) AS transaction_month " +
                   "FROM transactions t " +
                   "JOIN categories c ON t.category_id = c.id " +
                   "WHERE t.user_id = "+user_id;

    try {
        rs = db.SQL_Query(query);
        while (rs.next()) {
            String category = rs.getString("category_name");
            double amount = rs.getDouble("amount");
            String month = rs.getString("transaction_month");

            dataset.addValue(amount, category, month);
        }
    } catch (Exception e) {
        System.out.println("Error: " + e);
    }

    JFreeChart chart = ChartFactory.createStackedBarChart(
            "Monthly Totals by Category",
            "Month",
            "Amount ($)",
            dataset
    );

    ChartPanel chartSwingPanel = new ChartPanel(chart);
    chartSwingPanel.setPreferredSize(new Dimension(500, 300));
    chartSwingPanel.setMaximumSize(new Dimension(500, 300));
    chartSwingPanel.setMinimumSize(new Dimension(500, 300));

    monthlyChartCategory.removeAll();
    monthlyChartCategory.setLayout(new BorderLayout());
    monthlyChartCategory.add(chartSwingPanel, BorderLayout.CENTER);
    monthlyChartCategory.revalidate();
    monthlyChartCategory.repaint();
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        WelcomeName = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        IncomeCard = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        ExpenseCard = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        SavingsCard = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        TotalTransactionsCard = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        addExpenseNav = new javax.swing.JLabel();
        addIncomeNav = new javax.swing.JLabel();
        transactionHistoryNav = new javax.swing.JLabel();
        monthlySummaryNav = new javax.swing.JLabel();
        homeNav = new javax.swing.JLabel();
        addCategoryNav = new javax.swing.JLabel();
        chartPanel = new javax.swing.JPanel();
        monthlyChartCategory = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        jLabel3.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Personal Finance Tracker");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        WelcomeName.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        WelcomeName.setForeground(new java.awt.Color(255, 255, 255));
        WelcomeName.setText("Welcome Hamza");

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Log out");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(WelcomeName)
                .addGap(67, 67, 67)
                .addComponent(jButton2)
                .addGap(39, 39, 39))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(341, 341, 341)
                    .addComponent(jLabel4)
                    .addContainerGap(539, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WelcomeName)
                    .addComponent(jButton2))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(21, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 51, 0)));
        jPanel3.setForeground(new java.awt.Color(102, 0, 204));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 0), 1, true));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 255));
        jLabel5.setText("Income");

        IncomeCard.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        IncomeCard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        IncomeCard.setText("$345.00");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IncomeCard, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(IncomeCard)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(36, 36, 36))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 1, true));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 255));
        jLabel10.setText("Expense");

        ExpenseCard.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        ExpenseCard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ExpenseCard.setText("$200.02");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ExpenseCard, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(ExpenseCard)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(36, 36, 36))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 255));
        jLabel12.setText("Savings");

        SavingsCard.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        SavingsCard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SavingsCard.setText("$234");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SavingsCard, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SavingsCard)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(36, 36, 36))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));
        jPanel10.setForeground(new java.awt.Color(0, 51, 255));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 255));
        jLabel14.setText("Total Transactions");

        TotalTransactionsCard.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        TotalTransactionsCard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalTransactionsCard.setText("12");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TotalTransactionsCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(31, 31, 31))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TotalTransactionsCard)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(36, 36, 36))
        );

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("More Charts");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 255));
        jPanel4.setForeground(new java.awt.Color(0, 0, 255));

        addExpenseNav.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        addExpenseNav.setForeground(new java.awt.Color(255, 255, 255));
        addExpenseNav.setText("Add Expense");
        addExpenseNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addExpenseNavMouseClicked(evt);
            }
        });

        addIncomeNav.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        addIncomeNav.setForeground(new java.awt.Color(255, 255, 255));
        addIncomeNav.setText("Add income");
        addIncomeNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addIncomeNavMouseClicked(evt);
            }
        });

        transactionHistoryNav.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        transactionHistoryNav.setForeground(new java.awt.Color(255, 255, 255));
        transactionHistoryNav.setText("Transaction History");
        transactionHistoryNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transactionHistoryNavMouseClicked(evt);
            }
        });

        monthlySummaryNav.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        monthlySummaryNav.setForeground(new java.awt.Color(255, 255, 255));
        monthlySummaryNav.setText("Monthly Summary");
        monthlySummaryNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monthlySummaryNavMouseClicked(evt);
            }
        });

        homeNav.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        homeNav.setForeground(new java.awt.Color(255, 255, 255));
        homeNav.setText("Home");
        homeNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeNavMouseClicked(evt);
            }
        });

        addCategoryNav.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        addCategoryNav.setForeground(new java.awt.Color(255, 255, 255));
        addCategoryNav.setText("Add Category");
        addCategoryNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addCategoryNavMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(homeNav)
                .addGap(92, 92, 92)
                .addComponent(addIncomeNav)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(addExpenseNav)
                .addGap(42, 42, 42)
                .addComponent(transactionHistoryNav)
                .addGap(64, 64, 64)
                .addComponent(addCategoryNav)
                .addGap(78, 78, 78)
                .addComponent(monthlySummaryNav)
                .addGap(132, 132, 132))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addExpenseNav)
                    .addComponent(addIncomeNav)
                    .addComponent(transactionHistoryNav)
                    .addComponent(monthlySummaryNav)
                    .addComponent(homeNav, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addCategoryNav))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        chartPanel.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(chartPanel);
        chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 475, Short.MAX_VALUE)
        );
        chartPanelLayout.setVerticalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        monthlyChartCategory.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout monthlyChartCategoryLayout = new javax.swing.GroupLayout(monthlyChartCategory);
        monthlyChartCategory.setLayout(monthlyChartCategoryLayout);
        monthlyChartCategoryLayout.setHorizontalGroup(
            monthlyChartCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 484, Short.MAX_VALUE)
        );
        monthlyChartCategoryLayout.setVerticalGroup(
            monthlyChartCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(monthlyChartCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(monthlyChartCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addCategoryNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCategoryNavMouseClicked
        // TODO add your handling code here:
        
        AddCategory category = new AddCategory();
        category.setVisible(true);
        dispose();
    }//GEN-LAST:event_addCategoryNavMouseClicked

    private void addIncomeNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addIncomeNavMouseClicked
        // TODO add your handling code here:
        AddIncome income  = new AddIncome();
        income.setVisible(true);
        dispose();
    }//GEN-LAST:event_addIncomeNavMouseClicked

    private void addExpenseNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addExpenseNavMouseClicked
        // TODO add your handling code here:
        AddExpense expense  = new AddExpense();
        expense.setVisible(true);
        dispose();
    }//GEN-LAST:event_addExpenseNavMouseClicked

    private void transactionHistoryNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionHistoryNavMouseClicked
        // TODO add your handling code here:
        TransactionHistory transaction  = new TransactionHistory();
        transaction.setVisible(true);
        dispose();

    }//GEN-LAST:event_transactionHistoryNavMouseClicked

    private void monthlySummaryNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monthlySummaryNavMouseClicked
        // TODO add your handling code here:
        
        MonthlySummary summary  = new MonthlySummary();
        summary.setVisible(true);
        dispose();
    }//GEN-LAST:event_monthlySummaryNavMouseClicked

    private void homeNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeNavMouseClicked
        // TODO add your handling code here:
        Home home = new Home();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_homeNavMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        AnalysisCharts charts = new AnalysisCharts();
        charts.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ExpenseCard;
    private javax.swing.JLabel IncomeCard;
    private javax.swing.JLabel SavingsCard;
    private javax.swing.JLabel TotalTransactionsCard;
    private javax.swing.JLabel WelcomeName;
    private javax.swing.JLabel addCategoryNav;
    private javax.swing.JLabel addExpenseNav;
    private javax.swing.JLabel addIncomeNav;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JLabel homeNav;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel monthlyChartCategory;
    private javax.swing.JLabel monthlySummaryNav;
    private javax.swing.JLabel transactionHistoryNav;
    // End of variables declaration//GEN-END:variables
}
