/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mom;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.management.remote.JMXServerErrorException;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Cliente extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    String[] topicos = new String[50];
    String[] sensores = new String[3];
    int cont=0;
    DefaultListModel listMonitorados = new DefaultListModel();
    public Cliente() throws JMXServerErrorException, JMSException {
        initComponents();
        initList();
        setSize(650, 600);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        notificacao = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        iniciar = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();
        listSensores = new javax.swing.JScrollPane();
        sensoresDisponiveis = new javax.swing.JList<>();
        sensoresMonitorados2 = new javax.swing.JScrollPane();
        monitoramento = new javax.swing.JList<>();
        attSensor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 560, 30));

        jLabel1.setText("Nome:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel2.setText("Sensores diponíveis:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel3.setText("Sensores monitorados:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, -1));

        addBtn.setText("Adicionar");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        getContentPane().add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 90, 40));

        notificacao.setColumns(20);
        notificacao.setRows(5);
        jScrollPane3.setViewportView(notificacao);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 560, 120));

        jLabel4.setText("Notificações:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        iniciar.setBackground(new java.awt.Color(0, 156, 59));
        iniciar.setText("Iniciar monitoramento");
        iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarActionPerformed(evt);
            }
        });
        getContentPane().add(iniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 560, 40));

        titulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        listSensores.setViewportView(sensoresDisponiveis);

        getContentPane().add(listSensores, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 200, 160));

        sensoresMonitorados2.setViewportView(monitoramento);

        getContentPane().add(sensoresMonitorados2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 190, 160));

        attSensor.setText("Atualizar");
        attSensor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarSensoresActionPerformed(evt);
            }
        });
        getContentPane().add(attSensor, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 90, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String sensor = sensoresDisponiveis.getSelectedValue().toString();
        sensores[cont]=sensor;
        listMonitorados.addElement(sensor);
        monitoramento.setModel(listMonitorados);
        cont++;
    }//GEN-LAST:event_addBtnActionPerformed

    private void iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarActionPerformed
        titulo.setText(nome.getText());
        nome.setEnabled(false);
        iniciar.setEnabled(false);
        addBtn.setEnabled(false);
        Subscriber sub;
        for (String sensore : sensores) {
            if (sensore != null) {
                sub = new Subscriber(sensore);
            }
        }
        //sub2.run();
        
        
    }//GEN-LAST:event_iniciarActionPerformed

    private void atualizarSensoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarSensoresActionPerformed
        // TODO add your handling code here:
        DefaultListModel list = new DefaultListModel();
        try {
            Consumidor();
        } catch (JMSException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<topicos.length;i++){
            if(topicos[i]!=null){
                if(list.contains(topicos[i])){
                    System.out.println("Elemento já na lista.");
                } else{
                    list.addElement(topicos[i]);
                }
               
            }
        }
        
        sensoresDisponiveis.setModel(list);
        notificacao.setEditable(false);
    }//GEN-LAST:event_atualizarSensoresActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {               
                    new Cliente().setVisible(true);
                } catch (JMXServerErrorException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JMSException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton attSensor;
    private javax.swing.JButton iniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane listSensores;
    private javax.swing.JList<String> monitoramento;
    private javax.swing.JTextField nome;
    private javax.swing.JTextArea notificacao;
    private javax.swing.JList<String> sensoresDisponiveis;
    private javax.swing.JScrollPane sensoresMonitorados2;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables

    private void initList() throws JMSException {
        DefaultListModel list = new DefaultListModel();
        Consumidor();
        for(int i=0;i<topicos.length;i++){
            if(topicos[i]!=null){
                list.addElement(topicos[i]);
            }
        }
        
        sensoresDisponiveis.setModel(list);
        notificacao.setEditable(false);
        
    }
    
    public void Consumidor() throws JMSException{
            
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	String queueName = "default";
        
        
            /*
             * Estabelecendo conexão com o Servidor JMS
             */
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            /*
             * Criando Session 
             */
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            /*
             * Criando Queue
             */
            Destination destination = session.createQueue(queueName);

            /*
             * Criando Consumidor		 
             */
            MessageConsumer consumer = session.createConsumer(destination);



            //Message message = consumer.receive();

            int i=1;
            while(i<25){

                Message message = consumer.receive(i);
                i++;

                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();

                    if(text!=null){
                        System.out.println("Recebido: " + text);
                        topicos[i]=text;
                    }

                    //topicos[i] = text;
                    //System.out.println(topicos.toString());
                }else {
                System.out.println("Recebidaao: " + message);
            }
            }
            for(int j=0;j<25;j++){
                if(topicos[j]!=null){
                    MessageProducer producer = session.createProducer(destination);
                    TextMessage topic = session.createTextMessage(topicos[j]);
                    producer.send(topic);
                }
            }

            consumer.close();
            session.close();
            connection.close();

    }
    
    class Subscriber implements MessageListener, Runnable{
	/*
	 * URL do servidor JMS. DEFAULT_BROKER_URL indica que o servidor está em localhost
	*/
        
        String topicName = "";
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        
        public Subscriber(String name) {
            this.topicName = name;
            new Thread(this, name).start();
            
        }

        public void run(){

             try{

                /*
                 * Estabelecendo conexão com o Servidor JMS
                 */		
                ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
                Connection connection = connectionFactory.createConnection();
                connection.start();

                /*
                 * Criando Session 
                 */
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                /*
                 * Criando Topic
                 */ 
                Destination dest = session.createTopic(topicName);

                /*
                 * Criando Consumidor
                 */
                MessageConsumer subscriber = session.createConsumer(dest);

                /*
                 * Setando Listener
                 */
                subscriber.setMessageListener(this);



             }catch(Exception e){
                 e.printStackTrace();
             }       
         }   

         public void onMessage(Message message){
             if(message instanceof TextMessage){
                 try{
                     System.out.println( ((TextMessage)message).getText());
                     notificacao.append(((TextMessage)message).getText()+"\n");
                     notificacao.setCaretPosition(notificacao.getText().length());
                 }catch(Exception e){
                 }
             }
         }  

       
    }
}
