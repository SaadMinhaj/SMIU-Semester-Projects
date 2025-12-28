/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.util.Random;
import model.SessionManager;
import model.User;
import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author Rao Hamza Tariq
 */
public class MonthlySummary extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    public MonthlySummary() {
        initComponents();
        
        User user = SessionManager.getCurrentUser();
        SQL_MODEL db = new SQL_MODEL();
        UserData user_data = db.getUserData(user.getEmail());
        WelcomeName.setText("Welcome "+user_data.fullName);
        
        top_income(user_data.user_id);
        top_expense(user_data.user_id);
        income_card(user_data.user_id);
        expense_card(user_data.user_id);
        transaction_card(user_data.user_id);
        savings_card();
        
        createTopExpensesChart(user_data.user_id);
        
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
 
 
private void top_income(int user_id){
            ResultSet rs;
            SQL_MODEL db = new SQL_MODEL();
            rs = db.SQL_Query("select description as top_income,amount from transactions where type = 'income' and user_id = "+user_id+" order by amount asc limit 1");
            try{
                while (rs.next()){
                    String topIncome = rs.getString("top_income");
                    String amount = rs.getString("amount");
                    System.out.println(topIncome);
                    TopIncome.setText(topIncome);
                    TopIncomeValue.setText("Top Income:  "+amount);
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
 
private void top_expense(int user_id){
            ResultSet rs;
            SQL_MODEL db = new SQL_MODEL();
            rs = db.SQL_Query("select description as top_expense, amount from transactions where type = 'expense' and user_id = "+user_id+" order by amount asc limit 1");
            try{
                while (rs.next()){
                    String topExpense = rs.getString("top_expense");
                    String amount = rs.getString("amount");
                    System.out.println(topExpense);
                    TopExpense.setText(topExpense);
                    TopExpenseValue.setText("Top Expense:  "+amount);
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }

private void createTopExpensesChart(int user_id) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    SQL_MODEL db = new SQL_MODEL();
    ResultSet rs;

    String query = "SELECT amount, description, DATE(date) AS trans_date " +
                   "FROM transactions " +
                   "WHERE user_id = " +user_id+ " AND type = 'expense' " +
                   "ORDER BY amount DESC " +
                   "LIMIT 5";

    try {
        rs = db.SQL_Query(query);
        while (rs.next()) {
            double amount = rs.getDouble("amount");
            String description = rs.getString("description");
            String date = rs.getString("trans_date");

            // To keep labels short and readable
            String label = description.length() > 20 ? description.substring(0, 20) + "..." : description;
            label += " (" + date + ")";

            dataset.addValue(amount, "Expense", label);
        }
    } catch (Exception e) {
        System.out.println("Error: " + e);
    }

    JFreeChart chart = ChartFactory.createBarChart(
            "Top 5 Most Expensive Transactions",
            "Transaction",
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
        Title = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        WelcomeName = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        IncomeCard = new javax.swing.JLabel();
        ExpenseCard = new javax.swing.JLabel();
        SavingsCard = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        TotalTransactionsCard = new javax.swing.JLabel();
        chartPanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        TopIncomeValue = new javax.swing.JLabel();
        TopIncome = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        TopExpenseValue = new javax.swing.JLabel();
        TopExpense = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        addExpenseNav1 = new javax.swing.JLabel();
        addIncomeNav1 = new javax.swing.JLabel();
        transactionHistoryNav1 = new javax.swing.JLabel();
        monthlySummaryNav1 = new javax.swing.JLabel();
        homeNav1 = new javax.swing.JLabel();
        addCategoryNav1 = new javax.swing.JLabel();

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

        Title.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Personal Finance Tracker");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        WelcomeName.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        WelcomeName.setForeground(new java.awt.Color(255, 255, 255));
        WelcomeName.setText("Welcome Hamza");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(WelcomeName)
                .addGap(109, 109, 109))
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
                    .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WelcomeName))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(21, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jPanel3.setForeground(new java.awt.Color(102, 0, 204));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Monthly Summary");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Total Income");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Total Expense");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Savings");

        IncomeCard.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        IncomeCard.setText("$0,00");

        ExpenseCard.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ExpenseCard.setText("$0,00");

        SavingsCard.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        SavingsCard.setText("$0,00");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Total Transactions");

        TotalTransactionsCard.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TotalTransactionsCard.setText("$0,00");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SavingsCard)
                    .addComponent(ExpenseCard)
                    .addComponent(IncomeCard)
                    .addComponent(TotalTransactionsCard))
                .addGap(48, 48, 48))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(IncomeCard))
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(ExpenseCard))
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(SavingsCard))
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(TotalTransactionsCard))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        chartPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(chartPanel);
        chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );
        chartPanelLayout.setVerticalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        TopIncomeValue.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TopIncomeValue.setText("Top Income");

        TopIncome.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        TopIncome.setText("-----");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(TopIncome))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(TopIncomeValue)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(TopIncome)
                .addGap(18, 18, 18)
                .addComponent(TopIncomeValue)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        TopExpenseValue.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TopExpenseValue.setText("Top Expense");

        TopExpense.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        TopExpense.setText("----");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(TopExpenseValue))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(TopExpense)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(TopExpense)
                .addGap(18, 18, 18)
                .addComponent(TopExpenseValue)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(51, 51, 255));

        addExpenseNav1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        addExpenseNav1.setText("Add Expense");
        addExpenseNav1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addExpenseNav1MouseClicked(evt);
            }
        });

        addIncomeNav1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        addIncomeNav1.setText("Add income");
        addIncomeNav1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addIncomeNav1MouseClicked(evt);
            }
        });

        transactionHistoryNav1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        transactionHistoryNav1.setText("Transaction History");
        transactionHistoryNav1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transactionHistoryNav1MouseClicked(evt);
            }
        });

        monthlySummaryNav1.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        monthlySummaryNav1.setText("Monthly Summary");
        monthlySummaryNav1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monthlySummaryNav1MouseClicked(evt);
            }
        });

        homeNav1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        homeNav1.setText("Home");
        homeNav1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeNav1MouseClicked(evt);
            }
        });

        addCategoryNav1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        addCategoryNav1.setText("Add Category");
        addCategoryNav1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addCategoryNav1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(homeNav1)
                .addGap(92, 92, 92)
                .addComponent(addIncomeNav1)
                .addGap(71, 71, 71)
                .addComponent(addExpenseNav1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(transactionHistoryNav1)
                .addGap(87, 87, 87)
                .addComponent(addCategoryNav1)
                .addGap(101, 101, 101)
                .addComponent(monthlySummaryNav1)
                .addGap(79, 79, 79))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addExpenseNav1)
                    .addComponent(addIncomeNav1)
                    .addComponent(transactionHistoryNav1)
                    .addComponent(monthlySummaryNav1)
                    .addComponent(homeNav1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addCategoryNav1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addExpenseNav1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addExpenseNav1MouseClicked
        // TODO add your handling code here:
        AddExpense expense  = new AddExpense();
        expense.setVisible(true);
        dispose();
    }//GEN-LAST:event_addExpenseNav1MouseClicked

    private void addIncomeNav1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addIncomeNav1MouseClicked
        // TODO add your handling code here:
        AddIncome income  = new AddIncome();
        income.setVisible(true);
        dispose();
    }//GEN-LAST:event_addIncomeNav1MouseClicked

    private void transactionHistoryNav1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionHistoryNav1MouseClicked
        // TODO add your handling code here:
        TransactionHistory transaction  = new TransactionHistory();
        transaction.setVisible(true);
        dispose();
    }//GEN-LAST:event_transactionHistoryNav1MouseClicked

    private void monthlySummaryNav1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monthlySummaryNav1MouseClicked
        // TODO add your handling code here:

        MonthlySummary summary  = new MonthlySummary();
        summary.setVisible(true);
        dispose();
    }//GEN-LAST:event_monthlySummaryNav1MouseClicked

    private void addCategoryNav1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCategoryNav1MouseClicked
        // TODO add your handling code here:

        AddCategory category = new AddCategory();
        category.setVisible(true);
        dispose();
    }//GEN-LAST:event_addCategoryNav1MouseClicked

    private void homeNav1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeNav1MouseClicked
        // TODO add your handling code here:
        Home home = new Home();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_homeNav1MouseClicked

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
            java.util.logging.Logger.getLogger(MonthlySummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MonthlySummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MonthlySummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MonthlySummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new MonthlySummary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ExpenseCard;
    private javax.swing.JLabel IncomeCard;
    private javax.swing.JLabel SavingsCard;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel TopExpense;
    private javax.swing.JLabel TopExpenseValue;
    private javax.swing.JLabel TopIncome;
    private javax.swing.JLabel TopIncomeValue;
    private javax.swing.JLabel TotalTransactionsCard;
    private javax.swing.JLabel WelcomeName;
    private javax.swing.JLabel addCategoryNav1;
    private javax.swing.JLabel addExpenseNav1;
    private javax.swing.JLabel addIncomeNav1;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JLabel homeNav1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel monthlySummaryNav1;
    private javax.swing.JLabel transactionHistoryNav1;
    // End of variables declaration//GEN-END:variables
}
