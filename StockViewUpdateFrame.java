//Şevval Bilğin 2221221013
package com.mycompany.sevval_bilgin_stock_personel_tracking;

import static com.mycompany.sevval_bilgin_stock_personel_tracking.Company_db.connectionString;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class StockViewUpdateFrame extends javax.swing.JFrame {

    /**
     * Creates new form StockViewUpdate
     */
    public static Personel pnew = new Personel();
    private DefaultTableModel list = new DefaultTableModel();

    public StockViewUpdateFrame(Personel p) {
        initComponents();
        //buraya gelen kullanıcyı bilmeliyim ki stokları update yetkisine sahip olup olmadığını bileyim
        pnew = p;
    }
//halihazırda olan bilgileri tabloya yazar

    public void GetAllStockDataFromDatabase() {
        try {

            Company_db.conn = DriverManager.getConnection(connectionString);
            Statement stmt = Company_db.conn.createStatement();
            String query = "SELECT * FROM stock_company_personels.material;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                String name = rs.getString("material_name");
                String number = String.valueOf(rs.getInt("material_number"));

                String DBdata[] = {name, number};
                if (tpane_stock_view_update.getSelectedIndex() == 0) {
                    list = (DefaultTableModel) table_stocks_view.getModel();
                } else {
                    list = (DefaultTableModel) table_stocks_update.getModel();
                }
                list.addRow(DBdata);

            }

            Company_db.conn.close();
        } catch (SQLException ex) {

            Logger.getLogger(Company_db.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void SearchFromDataBaseWriteTable(String s) {
        try {

            String query;
            Company_db.conn = DriverManager.getConnection(connectionString);
            Statement stmt = Company_db.conn.createStatement();
            if (s.compareTo("v") == 0) {
                query = "SELECT * FROM stock_company_personels.material WHERE material_name LIKE" + " '%" + txt_stock_view_search.getText() + "%' ;";

            } else {
                query = "SELECT * FROM stock_company_personels.material WHERE material_name LIKE" + " '%" + txt_stock_update_search.getText() + "%' ;";
            }
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                String name = rs.getString("material_name");
                String number = String.valueOf(rs.getInt("material_number"));

                String DBdata[] = {name, number};
                if (tpane_stock_view_update.getSelectedIndex() == 0) {
                    list = (DefaultTableModel) table_stocks_view.getModel();
                } else {
                    list = (DefaultTableModel) table_stocks_update.getModel();
                }
                list.addRow(DBdata);

            }

            Company_db.conn.close();
        } catch (SQLException ex) {

            Logger.getLogger(Company_db.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_stock_view_update = new javax.swing.JPanel();
        pnl_stock_view_update_back = new javax.swing.JPanel();
        tpane_stock_view_update = new javax.swing.JTabbedPane();
        pnl_stock_view_update_view = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_stocks_view = new javax.swing.JTable();
        btn_stock_view_update_go_LogIn = new javax.swing.JButton();
        btn_stock_view_update_go_personel = new javax.swing.JButton();
        btn_stock_view_update_go_update = new javax.swing.JButton();
        btn_stock_view_search = new javax.swing.JButton();
        txt_stock_view_search = new javax.swing.JTextField();
        pnl_stock_view_update_update = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_stocks_update = new javax.swing.JTable();
        txt_stock_update_search = new javax.swing.JTextField();
        btn_stock_update_search = new javax.swing.JButton();
        btn_stock_delete = new javax.swing.JButton();
        btn_stock_add = new javax.swing.JButton();
        btn_stock_update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnl_stock_view_update_back.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tpane_stock_view_update.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tpane_stock_view_updateStateChanged(evt);
            }
        });

        pnl_stock_view_update_view.setBackground(new java.awt.Color(255, 255, 204));

        table_stocks_view.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Materials", "Stock numbers"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_stocks_view.setEnabled(false);
        jScrollPane1.setViewportView(table_stocks_view);
        if (table_stocks_view.getColumnModel().getColumnCount() > 0) {
            table_stocks_view.getColumnModel().getColumn(0).setResizable(false);
            table_stocks_view.getColumnModel().getColumn(1).setResizable(false);
        }

        btn_stock_view_update_go_LogIn.setText("Go back to LogIn Page");
        btn_stock_view_update_go_LogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stock_view_update_go_LogInActionPerformed(evt);
            }
        });

        btn_stock_view_update_go_personel.setText("Go to Personel Page");
        btn_stock_view_update_go_personel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stock_view_update_go_personelActionPerformed(evt);
            }
        });

        btn_stock_view_update_go_update.setText("Update your Profile");
        btn_stock_view_update_go_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stock_view_update_go_updateActionPerformed(evt);
            }
        });

        btn_stock_view_search.setText("SEARCH");
        btn_stock_view_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stock_view_searchActionPerformed(evt);
            }
        });

        txt_stock_view_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stock_view_searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_stock_view_update_viewLayout = new javax.swing.GroupLayout(pnl_stock_view_update_view);
        pnl_stock_view_update_view.setLayout(pnl_stock_view_update_viewLayout);
        pnl_stock_view_update_viewLayout.setHorizontalGroup(
            pnl_stock_view_update_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_stock_view_update_viewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_stock_view_update_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_stock_view_update_viewLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(pnl_stock_view_update_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_stock_view_update_go_personel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_stock_view_update_go_LogIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_stock_view_update_go_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21))
                    .addGroup(pnl_stock_view_update_viewLayout.createSequentialGroup()
                        .addComponent(txt_stock_view_search, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_stock_view_search, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnl_stock_view_update_viewLayout.setVerticalGroup(
            pnl_stock_view_update_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_stock_view_update_viewLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnl_stock_view_update_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_stock_view_search, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_stock_view_search, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnl_stock_view_update_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl_stock_view_update_viewLayout.createSequentialGroup()
                        .addComponent(btn_stock_view_update_go_update, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_stock_view_update_go_LogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_stock_view_update_go_personel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tpane_stock_view_update.addTab("View", pnl_stock_view_update_view);

        pnl_stock_view_update_update.setBackground(new java.awt.Color(204, 255, 204));
        pnl_stock_view_update_update.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_stocks_update.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Materials", "Stock Numbers"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_stocks_update.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                table_stocks_updatePropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(table_stocks_update);
        if (table_stocks_update.getColumnModel().getColumnCount() > 0) {
            table_stocks_update.getColumnModel().getColumn(0).setResizable(false);
            table_stocks_update.getColumnModel().getColumn(1).setResizable(false);
        }

        pnl_stock_view_update_update.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 55, 369, 254));
        pnl_stock_view_update_update.add(txt_stock_update_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 15, 261, 34));

        btn_stock_update_search.setText("SEARCH");
        btn_stock_update_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stock_update_searchActionPerformed(evt);
            }
        });
        pnl_stock_view_update_update.add(btn_stock_update_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 15, 102, 34));

        btn_stock_delete.setText("DELETE");
        btn_stock_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stock_deleteActionPerformed(evt);
            }
        });
        pnl_stock_view_update_update.add(btn_stock_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 107, -1, -1));

        btn_stock_add.setText("ADD");
        btn_stock_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stock_addActionPerformed(evt);
            }
        });
        pnl_stock_view_update_update.add(btn_stock_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 176, -1, -1));

        btn_stock_update.setText("UPDATE");
        btn_stock_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stock_updateActionPerformed(evt);
            }
        });
        pnl_stock_view_update_update.add(btn_stock_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 237, -1, -1));

        tpane_stock_view_update.addTab("Update", pnl_stock_view_update_update);

        pnl_stock_view_update_back.add(tpane_stock_view_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 580, 360));

        javax.swing.GroupLayout pnl_stock_view_updateLayout = new javax.swing.GroupLayout(pnl_stock_view_update);
        pnl_stock_view_update.setLayout(pnl_stock_view_updateLayout);
        pnl_stock_view_updateLayout.setHorizontalGroup(
            pnl_stock_view_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_stock_view_updateLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnl_stock_view_update_back, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnl_stock_view_updateLayout.setVerticalGroup(
            pnl_stock_view_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_stock_view_updateLayout.createSequentialGroup()
                .addComponent(pnl_stock_view_update_back, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_stock_view_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_stock_view_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_stock_view_update_go_personelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stock_view_update_go_personelActionPerformed
        // TODO add your handling code here:
        //kullanıcı leaderesa personel listesine erişebilir
        if (pnew.position == Personel.Position.Leader) {
            this.setVisible(false);
            PersonelViewUpdateFrame p1 = new PersonelViewUpdateFrame(pnew);
            p1.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Only leaders can see this page!!");
        }
    }//GEN-LAST:event_btn_stock_view_update_go_personelActionPerformed

    private void btn_stock_view_update_go_LogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stock_view_update_go_LogInActionPerformed
        // TODO add your handling code here:
        //Login sayfasına geri döner
        this.dispose();
        LogInFrame login = new LogInFrame();
        login.setVisible(true);

    }//GEN-LAST:event_btn_stock_view_update_go_LogInActionPerformed

    private void btn_stock_view_update_go_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stock_view_update_go_updateActionPerformed
        // TODO add your handling code here:
        //kullanıcı kendi profil bilgilerini düzenleyebilir
        this.setVisible(false);
        UpdateInfoFrame f1 = new UpdateInfoFrame(pnew, "S");
        f1.setVisible(true);
    }//GEN-LAST:event_btn_stock_view_update_go_updateActionPerformed

    private void txt_stock_view_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stock_view_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stock_view_searchActionPerformed

    private void table_stocks_updatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_table_stocks_updatePropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_table_stocks_updatePropertyChange
    int a = 0;

    private void tpane_stock_view_updateStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tpane_stock_view_updateStateChanged
        // TODO add your handling code here:
        //kullanıcı leadersa update sayfasına girişe izin verir.değilse uyarır ve view sayfasına geri döner
        if (a < 2) {
            GetAllStockDataFromDatabase();
            a++;
        }
        if (pnew.position != null) {
            if ((tpane_stock_view_update.getSelectedIndex() == 1 && pnew.position == Personel.Position.Member)) {
                JOptionPane.showMessageDialog(rootPane, "ONLY TEAM  LEADERS CAN UPDATE STOCKS");
                tpane_stock_view_update.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tpane_stock_view_updateStateChanged

    private void btn_stock_view_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stock_view_searchActionPerformed
        // TODO add your handling code here:
        //searche her tıklandığında eski sonuçları siler
        int a = table_stocks_view.getRowCount();

        DefaultTableModel d = (DefaultTableModel) table_stocks_view.getModel();
        for (int i = 0; i < a; i++) {
            d.removeRow(0);
        }
        SearchFromDataBaseWriteTable("v");


    }//GEN-LAST:event_btn_stock_view_searchActionPerformed

    private void btn_stock_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stock_deleteActionPerformed
        // TODO add your handling code here:

        int[] rows = table_stocks_update.getSelectedRows();
        for (int row : rows) {
            //stoğu databaseden siler
            boolean isDeleted = Company_db.DeleteStock((String) list.getValueAt(row, 0));
            if (isDeleted) {
                //silebildiyse tablodan da siler
                list.removeRow(row);
                JOptionPane.showMessageDialog(rootPane, "Stock Deleted!!");
            }
        }


    }//GEN-LAST:event_btn_stock_deleteActionPerformed

    private void btn_stock_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stock_addActionPerformed
        try {
            // TODO add your handling code here:
            //kullanıcıdan stok adı ve sayısı alınarak ekleme yapılır
            String name = JOptionPane.showInputDialog(this, "Plz enter the name of the material you want to add");
            String number = JOptionPane.showInputDialog(this, "Plz enter the material's number ");
            boolean isAdded = Company_db.AddStock(name, number);
            if (isAdded) {
                StockViewUpdateFrame news = new StockViewUpdateFrame(pnew);
                news.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "This material already exists");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockViewUpdateFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(StockViewUpdateFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(StockViewUpdateFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_stock_addActionPerformed
    public static DefaultTableModel list2;
    private void btn_stock_update_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stock_update_searchActionPerformed
        // TODO add your handling code here:
        //searche her tıklandığında eski sonuçları siler
        int a = table_stocks_view.getRowCount();

        DefaultTableModel d = (DefaultTableModel) table_stocks_view.getModel();
        for (int i = 0; i < a; i++) {
            d.removeRow(0);
        }
        SearchFromDataBaseWriteTable("u");


    }//GEN-LAST:event_btn_stock_update_searchActionPerformed

    private void btn_stock_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stock_updateActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        String name = JOptionPane.showInputDialog(this, "Plz enter the name of the material you want to update");
        String number = JOptionPane.showInputDialog(this, "Plz enter the material's new number ");
//alınan bilgiler metodda update edilir
        boolean isUpdated = Company_db.SearchbyMaterialNameAndUpdate(name, number);
        if (isUpdated) {
            StockViewUpdateFrame news = new StockViewUpdateFrame(pnew);
            news.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Material couldn't find!!");
        }
    }//GEN-LAST:event_btn_stock_updateActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_stock_add;
    private javax.swing.JButton btn_stock_delete;
    private javax.swing.JButton btn_stock_update;
    private javax.swing.JButton btn_stock_update_search;
    private javax.swing.JButton btn_stock_view_search;
    private javax.swing.JButton btn_stock_view_update_go_LogIn;
    private javax.swing.JButton btn_stock_view_update_go_personel;
    private javax.swing.JButton btn_stock_view_update_go_update;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnl_stock_view_update;
    private javax.swing.JPanel pnl_stock_view_update_back;
    private javax.swing.JPanel pnl_stock_view_update_update;
    private javax.swing.JPanel pnl_stock_view_update_view;
    private javax.swing.JTable table_stocks_update;
    private javax.swing.JTable table_stocks_view;
    private javax.swing.JTabbedPane tpane_stock_view_update;
    private javax.swing.JTextField txt_stock_update_search;
    private javax.swing.JTextField txt_stock_view_search;
    // End of variables declaration//GEN-END:variables
}
