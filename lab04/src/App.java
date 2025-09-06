import java.math.BigDecimal;
import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {

        read();
        create();
        update(); 
    }
    
    public static void read() throws SQLException {        
        System.out.println("Consulta de contas!");
        String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.hscwujcsucpepnhczchw&password=PasswordLab04@";
        Connection c = DriverManager.getConnection(url);
        System.out.println("Conexão ok!");
        String sql = "SELECT * FROM CONTAS";
        PreparedStatement stm = c.prepareStatement(sql);
        ResultSet resultado = stm.executeQuery();
        while (resultado.next()) {
            long nro = resultado.getLong("nro_conta");
            double saldo = resultado.getDouble("saldo");
            System.out.println("Número: " + nro + " - R$ " + saldo);
        }
        c.close();
    }

    public static void create() throws SQLException {
        System.out.print("Numero para a nova conta: ");
        long nro = Long.parseLong(System.console().readLine());
        System.out.print("Saldo da nova conta: ");
        BigDecimal saldo = new BigDecimal(System.console().readLine());

        String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.hscwujcsucpepnhczchw&password=PasswordLab04@";
        
        Connection c = DriverManager.getConnection(url);

        String sql = "INSERT into contas VALUES (?, ?)";
        PreparedStatement prepstm = c.prepareStatement(sql);
        prepstm.setLong(1, nro);
        prepstm.setBigDecimal(2, saldo);

        int ret = prepstm.executeUpdate();
        System.out.println("Numero de registros inseridos: " + ret);

        prepstm.close();
        c.close();
    }

    public static void update() throws SQLException {
    System.out.print("Numero de uma conta ja existente: ");
    long nro = Long.parseLong(System.console().readLine());
    System.out.print("Novo saldo para esta conta: ");
    BigDecimal saldo = new BigDecimal(System.console().readLine());

    String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.hscwujcsucpepnhczchw&password=PasswordLab04@";
    Connection c = DriverManager.getConnection(url);

    String sql = "UPDATE contas SET saldo=? WHERE nro_conta=?";
    PreparedStatement prepstm = c.prepareStatement(sql);
    prepstm.setBigDecimal(1, saldo);
    prepstm.setLong(2, nro);
    
    int ret = prepstm.executeUpdate();
    System.out.println("Numero de registros alterados: " + ret);
    
    c.close();
    }

    public static void delete() throws SQLException {
    System.out.print("Numero de uma conta ja existente: ");
    long nro = Long.parseLong(System.console().readLine());
    String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.hscwujcsucpepnhczchw&password=PasswordLab04@";
    Connection c = DriverManager.getConnection(url);

    String sql = "DELETE FROM contas WHERE nro_conta=?";
    PreparedStatement prepstm = c.prepareStatement(sql);
    prepstm.setLong(1, nro);

    int ret = prepstm.executeUpdate();
    System.out.println("Numero de registros apagados: " + ret);

    c.close();
    }
}